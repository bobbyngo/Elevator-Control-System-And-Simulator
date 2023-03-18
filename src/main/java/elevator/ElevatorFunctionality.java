/**
 * 
 */
package main.java.elevator;

import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.scheduler.Scheduler;

/**
 * The class that controls the elevator functionality (moving, opening doors, etc.)
 * @author Trong Nguyen
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class ElevatorFunctionality implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static final int ELEVATOR_PORT = 69;
	
	private int id;
	private ElevatorState elevatorState;
	private ElevatorSync elevatorSync;
	private Scheduler scheduler;
	private UDP udp;
	private ElevatorComponents elevatorComponents;
	
	/**
	 * Constructor for the ElevatorFunctionality class.
	 * @param id the int of the elevator id
	 * @param scheduler	Scheduler, scheduler object referenced by Elevator
	 * @param elevatorSync the ElevatorSync object to synchronize on
	 */
	public ElevatorFunctionality(int id, Scheduler scheduler, ElevatorSync elevatorSync) {
		this.id = id;
		this.scheduler = scheduler;
		this.elevatorSync = elevatorSync;
		udp = new UDP();
		elevatorState = ElevatorState.Idle;
		
		// Start of the program, the elevator should be in floor 1
		scheduler.registerElevatorLocation(id, 1);
		
		// init elevator component, motor is false, doorOpen is false 
		elevatorComponents = new ElevatorComponents(false, false);
		
		logger.setLevel(Level.INFO);
	}
	
	/**
	 * Moves the elevator to the provided floor and registers its new location
	 * @author Bobby Ngo
	 * @param currentFloor the int of the current elevator floor
	 * @param destinationFloor the int of the destination floor
	 */
	public void moveToDestination(int id, int currentFloor, int destinationFloor) {
		while (currentFloor != destinationFloor) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String loggerStr = String.format("Moving from floor %d -> floor %d \n", currentFloor, destinationFloor);
			logger.info(loggerStr);
			
			if (currentFloor < destinationFloor) {
				currentFloor += 1;
			} else {
				currentFloor -= 1;
			}
			// After the elevator goes to a different floor, update the floor location immediately
			scheduler.registerElevatorLocation(id, currentFloor);
		}
		logger.info(String.format("Arrived at destination floor %d", destinationFloor));
	}

	/**
	 * ElevatorFunctionality's run() implementation.
	 * Serves requests from the elevator requests queue in ElevatorSync
	 */
	@Override
	public void run() {
		try {
			String elevatorStateStr;
			udp.openSocket();
			ElevatorRequest elevatorRequest = null;
			Thread.sleep(1000);
			while (true) {
				elevatorStateStr = elevatorState.displayCurrentState(getElevatorId(), elevatorRequest);
				switch (elevatorState) {
					case Idle: {
						System.out.println(elevatorStateStr);
						elevatorState = elevatorState.nextState();
						break;
					}
					case AwaitRequest: {
						System.out.println(elevatorStateStr + " ------------------------------------------ \n");
						elevatorRequest = elevatorSync.getElevatorRequest();
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case Moving: {
						System.out.println(elevatorStateStr);
						// Move from the current floor to the floor that requested the elevator
						if (scheduler.displayElevatorLocation(id) != elevatorRequest.getSourceFloor()) {
							logger.info(String.format("Elevator %d is moving to floor %d to pick up the users", id , elevatorRequest.getSourceFloor()));
							moveToDestination(id, scheduler.displayElevatorLocation(id), elevatorRequest.getSourceFloor());
							Thread.sleep(100);						
						}
						
						// Move from the picked up floor to the floor users want 
						moveToDestination(id, scheduler.displayElevatorLocation(id), elevatorRequest.getDestinationFloor());
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case Stop: {
						System.out.println(elevatorStateStr + "\n");
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case DoorsOpen: {
						System.out.println(elevatorState.displayCurrentState(getElevatorId(), elevatorRequest));
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case DoorsClose: {
						System.out.println(elevatorState.displayCurrentState(getElevatorId(), elevatorRequest));
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
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

