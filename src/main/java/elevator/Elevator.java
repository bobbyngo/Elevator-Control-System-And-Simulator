package main.java.elevator;

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
	 */
	public ElevatorRequest serveRequest() {
		ElevatorRequest request;
		request = scheduler.dispatchRequest();
		logger.info(String.format("Serve request %s ", 
				request.toString()));
		return request;
	}
	
	/**
	 * Replies a completed request back to the Scheduler
	 * @param request ElevatorRequest, completed request to be replied back
	 */
	public void sendCompletedRequest(ElevatorRequest request) {
		scheduler.putCompletedRequest(request);
		logger.info(String.format("Complete request %s ", 
				request.toString()));
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
					switch (elevatorState) {
						// Only State Moving and Stop only use request argument
						case Idle: {
							System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
							elevatorState = elevatorState.nextState();
							break;
						}
						case AwaitRequest: {
							System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
							request = serveRequest();
							elevatorState = elevatorState.nextState();
							break;
						}
						case Moving: {
							// request must never be null here since it's init in AwaitRequest state
							System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
							elevatorState = elevatorState.nextState();
							break;
						}
						case Stop: {
							System.out.println(elevatorState.displayCurrentState(getElevatorId(), request));
							sendCompletedRequest(request);
							scheduler.registerElevatorLocation(Integer.valueOf(id), request.getDestinationFloor());
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
