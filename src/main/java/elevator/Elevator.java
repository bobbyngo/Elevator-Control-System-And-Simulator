package main.java.elevator;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.scheduler.Scheduler;

/**
 * Elevator class serves elevator requests from the Scheduler and stores in internal queue.
 * @author Trong Nguyen
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class Elevator implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private int id;
	private Scheduler scheduler;
	private ElevatorState elevatorState;
	
	/**
	 * Constructor for Elevator class.
	 * @param id int, elevator id
	 * @param scheduler	Scheduler, scheduler object referenced by Elevator
	 */
	public Elevator(int id, Scheduler scheduler) {
		this.id = id;
		this.scheduler = scheduler;
		elevatorState = ElevatorState.Idle;
		logger.setLevel(Level.INFO);
		// Start of the program, the elevator should be in floor 1
		scheduler.registerElevatorLocation(id, 1);
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
	
	/**
	 * Fetch a request from the Scheduler and add to requests queue.
	 * @return request ElevatorRequest object
	 */
	public ElevatorRequest serveRequest() {
		ElevatorRequest request;
		request = scheduler.dispatchRequest();
		String loggerStr = String.format("Serve request %s \n", request.toString());
		logger.info(loggerStr);
		return request;
	}
	
	/**
	 * Replies a completed request back to the Scheduler
	 * @param request ElevatorRequest, completed request to be replied back
	 */
	public void sendCompletedRequest(ElevatorRequest request) {
		scheduler.putCompletedRequest(request);
		String loggerStr = String.format("Complete request %s \n", request.toString());
		logger.info(loggerStr);
		return;
	}

	/**
	 * Elevator class run() implementation.
	 * Serves requests from the Scheduler until all requests have been served.
	 * java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ElevatorRequest request = null;
		try {
			Thread.sleep(1000);
			while (true) {
				if (scheduler.getRequestsQueue().size() >= 0) {
					String elevatorStateStr = elevatorState.displayCurrentState(getElevatorId(), request);
					switch (elevatorState) {
						// Only State Moving and Stop only use request argument
						case Idle: {
							System.out.println(elevatorStateStr);
							elevatorState = elevatorState.nextState();
							break;
						}
						case AwaitRequest: {
							System.out.println(elevatorStateStr + " ------------------------------------------ \n");
							request = serveRequest();
							
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
								logger.info(String.format("Elevator %d picked up the users, start doing the request", id));
							}
							// Move from the picked up floor to the floor users want 
							scheduler.movingTo(id, scheduler.displayElevatorLocation(id), request.getDestinationFloor());
							elevatorState = elevatorState.nextState();
							break;
						}
						case Stop: {
							System.out.println(elevatorStateStr + "\n");
							sendCompletedRequest(request);
							elevatorState = elevatorState.nextState();
							break;
						}
						case DoorsOpen: {
							System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
							elevatorState = elevatorState.nextState();
							break;
						}
						case DoorsClose: {
							System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
							elevatorState = elevatorState.nextState();
							break;
						}
						default:
							break; 		
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
