package main.java.elevator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.scheduler.Scheduler;

/**
 * Elevator class serves elevator requests from the Scheduler and stores in internal queue.
 * @author Trong Nguyen
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class Elevator implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static final int ELEVATOR_PORT = 69;
	
	private int id;
	private ElevatorState elevatorState;
	private Scheduler scheduler;
	private UDP udp;
	private ElevatorComponents elevatorComponents;
	
	/**
	 * Main method for the Elevator class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		new Thread(new Elevator(1, scheduler)).start();
		// new Thread(new Elevator(2, scheduler)).start();
	}
	
	/**
	 * Constructor for Elevator class.
	 * @param id int, elevator id
	 * @param scheduler	Scheduler, scheduler object referenced by Elevator
	 */
	public Elevator(int id, Scheduler scheduler) {
		this.id = id;
		this.scheduler = scheduler;
		udp = new UDP();
		elevatorState = ElevatorState.Idle;
		logger.setLevel(Level.INFO);
		// Start of the program, the elevator should be in floor 1
		scheduler.registerElevatorLocation(id, 1);
		// init elevator component, motor is false, doorOpen is false 
		elevatorComponents = new ElevatorComponents(false, false);
	}

	/**
	 * Elevator class run() implementation.
	 * Serves requests from the Scheduler until all requests have been served.
	 * java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			DatagramPacket reply = null;
			ElevatorRequest request = null;
			String elevatorStateStr;
			udp.openSocket();
			Thread.sleep(1000);
			while (true) {
				elevatorStateStr = elevatorState.displayCurrentState(getElevatorId(), request);
				switch (elevatorState) {
					case Idle: {
						System.out.println(elevatorStateStr);
						elevatorState = elevatorState.nextState();
						break;
					}
					case AwaitRequest: {
						System.out.println(elevatorStateStr + " ------------------------------------------ \n");
						reply = udp.sendReceivePacket("Waiting...".getBytes(), ELEVATOR_PORT);
						request = EncodeDecode.decodeData(reply);
						// TODO: Add request to elevator working queue
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case Moving: {
						System.out.println(elevatorStateStr);
						// Note: Elevator needs to move to the floor that the users request the elevator
						// to pick up the users then move the the floor they want
						
						// Move from the current floor to the floor that request the elevator
						if (scheduler.displayElevatorLocation(id) != request.getSourceFloor()) {
							logger.info(String.format("Elevator %d is moving to floor %d to pick up the users", id , request.getSourceFloor()));
							scheduler.movingTo(id, scheduler.displayElevatorLocation(id), request.getSourceFloor());
							Thread.sleep(100);						
						}
						// Move from the picked up floor to the floor users want 
						scheduler.movingTo(id, scheduler.displayElevatorLocation(id), request.getDestinationFloor());
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
						System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
						Thread.sleep(100);
						elevatorState = elevatorState.nextState();
						break;
					}
					case DoorsClose: {
						System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
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
			logger.info("Program terminated.");
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
