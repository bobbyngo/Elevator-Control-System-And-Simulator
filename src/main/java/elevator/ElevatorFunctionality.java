package main.java.elevator;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.Config;
import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.scheduler.Scheduler;
import main.java.scheduler.SchedulerSubsystem;

/**
 * The class that controls the elevator functionality (moving, opening doors, etc.)
 * @author Trong Nguyen
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class ElevatorFunctionality implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private int id;
	private int port;
	private ElevatorState elevatorState;
	private ElevatorSync elevatorSync;
	private Direction elevatorDirection;
	private int currentFloor;
	private UDP udp;
	
	/**
	 * Constructor for the ElevatorFunctionality class.
	 * @param id the int of the elevator id
	 * @param elevatorSync the ElevatorSync object to synchronize on
	 */
	public ElevatorFunctionality(int id, ElevatorSync elevatorSync, int port) {
		this.id = id;
		new Scheduler(SchedulerSubsystem.SchedulerType.ElevatorListener);
		this.elevatorSync = elevatorSync;
		this.port = port;
		udp = new UDP();
		elevatorState = ElevatorState.Idle;
		elevatorDirection = Direction.NONE;
		
		// Start of the program, the elevator should be in floor 1
		currentFloor = 1;
		
		new ElevatorComponents(false, false);
		
		logger.setLevel(Level.INFO);
	}
	
	/**
	 * Moves the elevator one floor in the direction of the provided and registers its new location
	 * @author Hussein Elmokdad
	 * @param id the int of the elevator id
	 * @param direction the Direction enum
	 */
	public void moveInDirection(int id, Direction direction) {
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (direction == Direction.UP) {
			currentFloor += 1;
		} else {
			currentFloor -= 1;
		}

		registerElevatorData(currentFloor, elevatorDirection);
		
		// logger.info(String.format("Elevator #%d: Arrived at floor %d",id,  currentFloor));
		System.out.println("Elevator #" + id + ": Moving " + direction + ". Arrived at floor " + currentFloor);
	}
	
	/**
	 * Moves the elevator to the source floor that made the request
	 * @author Bobby Ngo
	 * @param currentFloor of the elevator
	 * @param destinationFloor the int of the destination floor
	 */
	public void movingToSourceFloor(int id, int sourceFloor) {
		while (currentFloor != sourceFloor) {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String loggerStr = String.format("Elevator #%d: Moving from floor %d -> floor %d \n", id, currentFloor, sourceFloor);
			System.out.println("Elevator #" + id + ": Moving to source floor (" + currentFloor + " to " + sourceFloor + ")");
			// logger.info(loggerStr);
			
			if (currentFloor < sourceFloor) {
				currentFloor += 1;
			} else {
				currentFloor -= 1;
			}

			registerElevatorData(currentFloor, elevatorDirection);
		}
		// logger.info(String.format("Elevator #%d: Arrived at source floor %d \n", id, currentFloor));
		System.out.println("Elevator #" + id + ": Arrived at source floor " + currentFloor);
	}

	/**
	 * ElevatorFunctionality's run() implementation.
	 * Serves requests from the elevator requests queue in ElevatorSync
	 */
	@Override
	public void run() {
		try {
			String elevatorStateStr;
			udp.openSocket(port);
			ElevatorRequest elevatorRequest = null;
			while (true) {
				elevatorStateStr = elevatorState.displayCurrentState(getElevatorId(), elevatorRequest);
				switch (elevatorState) {
					case Idle: {
						System.out.println("Elevator #" + id + " is idle");
						elevatorDirection = Direction.NONE;
						registerElevatorData(currentFloor, elevatorDirection);
						elevatorRequest = elevatorSync.getElevatorRequest(); // Waits for a request
						elevatorDirection = elevatorRequest.getDirection();
						registerElevatorData(currentFloor, elevatorDirection);
						elevatorState = elevatorState.nextState();
						break;
					}
					case MovingToSource: {
						movingToSourceFloor(id, elevatorRequest.getSourceFloor());
						String packetStr = "Elevator #" + id + " arrived at floor";
						udp.sendPacket(packetStr.getBytes(), Config.scheduler_elevator_port, Config.schedulerSubsystemIP);
						elevatorState = elevatorState.nextState();
						break;
					}
					case AwaitRequest: {
					}
					case Moving: {
						moveInDirection(id, elevatorDirection);
						if (checkIfDestinationFloor(elevatorSync.getRequestsQueue()) || checkIfSourceFloor(elevatorSync.getRequestsQueue())) {
							elevatorState = ElevatorState.Stop; // hardcoded for now
						}
						else {
							elevatorState = ElevatorState.Moving; // hardcoded for now
						}
						if (checkIfDestinationFloor(elevatorSync.getRequestsQueue())) {
							elevatorSync.removeElevatorRequests(currentFloor);
							System.out.println("Elevator #" + id + ": Completed request(s) at floor " + currentFloor);
						}
						if (checkIfSourceFloor(elevatorSync.getRequestsQueue())) {
							String packetStr = "Elevator #" + id + " arrived at floor";
							udp.sendPacket(packetStr.getBytes(), Config.scheduler_elevator_port, Config.schedulerSubsystemIP);
							System.out.println("Elevator #" + id + ": Letting in people at floor " + currentFloor);
						}
						break;
					}
					case Stop: {
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case DoorsOpen: {
						Thread.sleep(500);
						elevatorState = elevatorState.nextState();
						break;
					}
					case DoorsClose: {
						// If there's requests heading in the same direction as the elevator left, service them and go back to moving
						if (elevatorSync.checkForSameDirectionReqs(elevatorDirection, currentFloor)) {
							elevatorState = ElevatorState.Moving; // hardcoded for now
						}
						// If the only requests left are in the opposite direction, go back to idle and change directions
						// If the requests queue is empty, go back to idle and wait for a request
						else { 
							elevatorState = ElevatorState.Idle; // hardcoded for now
						}
						Thread.sleep(500);
						break;
					}
					default:
						break; 		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			udp.closeSocket();
			System.out.println("--------- Program terminated ---------");
		}
	}
		
	/**
	 * Sends a datagram packet to the scheduler that contains info about the elevator floor, direction, and state
	 * @param currentFloor the int of the current floor that the elevator is on
	 * @param elevatorDirection the ElevatorDirection enum that stores the direction that the elevator's traveling in
	 * @author Hussein Elmokdad
	 */
	public void registerElevatorData(int currentFloor, Direction elevatorDirection) {
		// Do not remove the " " at the end of the string; otherwise, the empty bits in the received packet will be included after the .split(" ")
		String elevatorDataStr = String.valueOf(currentFloor) + " " + elevatorDirection + " "; 
		udp.sendPacket(elevatorDataStr.getBytes(), Config.scheduler_elevator_data_port, Config.schedulerSubsystemIP);
	}
	
	/**
	 * Checks if the current floor is a source floor for any requests in the queue
	 * and that the request's direction is the same as the elevator's
	 * @param elevatorRequests the ArrayList of the elevator requests
	 * @return the boolean of whether the current floor is a source floor
	 */
	private boolean checkIfSourceFloor(ArrayList<ElevatorRequest> elevatorRequests) {
		for (ElevatorRequest elevatorRequest : elevatorRequests) {
			if (currentFloor == elevatorRequest.getSourceFloor() && elevatorRequest.getDirection() == elevatorDirection) return true;
		}
		return false;
	}
	
	/**
	 * Checks if the current floor is a destination floor for any requests in the queue
	 * and that the request's direction is the same as the elevator's|
	 * @param elevatorRequests the ArrayList of the elevator requests
	 * @return the boolean of whether the current floor is a destination floor
	 */
	private boolean checkIfDestinationFloor(ArrayList<ElevatorRequest> elevatorRequests) {
		for (ElevatorRequest elevatorRequest : elevatorRequests) {
			if (currentFloor == elevatorRequest.getDestinationFloor() && elevatorRequest.getDirection() == elevatorDirection) return true;
		}
		return false;
	}

	/**
	 * Get the elevator id.
	 * @return int, elevator id
	 */
	public int getElevatorId() {
		return this.id;
	}
	
	/**
	 * Get current elevator state
	 * @return elevatorState, current elevator state
	 */
	public ElevatorState getElevatorState() {
		return elevatorState;
	}
}

