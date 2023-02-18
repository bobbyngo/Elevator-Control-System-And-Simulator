package main.java.elevator;

import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.scheduler.Scheduler;

/**
 * Elevator class serves elevator requests from the Scheduler and stores in internal queue.
 * @author Trong Nguyen
 * @version 1.0, 02/04/23
 */
public class Elevator implements Runnable {
	
	private static final Logger logger = Logger.getLogger(Elevator.class.getName());
	
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
	
	public ElevatorState getElevatorState() {
		return elevatorState;
	}
	
	/**
	 * Fetch a request from the Scheduler and add to requests queue.
	 */
	public ElevatorRequest serveRequest() {
		ElevatorRequest request;
		// dispatch request
		request = scheduler.dispatchRequest();
		logger.info(String.format("Elevator request queued: %s", request));
		return request;
	}
	
	/**
	 * Replies a completed request back to the Scheduler
	 * @param request ElevatorRequest, completed request to be replied back
	 */
	public void sendCompletedRequest(ElevatorRequest request) {
		scheduler.putCompletedRequest(request);
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
			Thread.sleep(2000);
			while (true) {
				if (scheduler.getRequestsQueue().size() >= 0) {
					switch (elevatorState) {
						case Idle: {
							System.out.println(getElevatorState());
							elevatorState = elevatorState.nextState();
							break;
						}
						case AcknowledgeRequest: {
							System.out.println(getElevatorState());
							request = serveRequest();
							elevatorState = elevatorState.nextState();
							break;
						}
						case Moving: {
							System.out.println(getElevatorState());
							elevatorState = elevatorState.nextState();
							break;
						}
						case Stop: {
							System.out.println(getElevatorState());
							sendCompletedRequest(request);
							elevatorState = elevatorState.nextState();
							break;
						}
						case DoorsOpen: {
							System.out.println(getElevatorState());
							elevatorState = elevatorState.nextState();
							break;
						}
						case DoorsClose: {
							System.out.println(getElevatorState());
							elevatorState = elevatorState.nextState();
							break;
						}
						case AwaitRequest: {
							System.out.println(getElevatorState());
							elevatorState = elevatorState.nextState();
							break;
						}
						case DoorsObstruction: {
							System.out.println(getElevatorState());
							elevatorState = elevatorState.nextState();
							break;
						}
						case OutOfService: {
							System.out.println(getElevatorState());
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
