package main.java.elevator;

import java.util.ArrayList;
import java.util.List;
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
	
	/**
	 * Constructor for Elevator class.
	 * @param id int, elevator id
	 * @param scheduler	Scheduler, scheduler object referenced by Elevator
	 */
	public Elevator(int id, Scheduler scheduler) {
		this.id = id;
		this.scheduler = scheduler;
	}
	
	/**
	 * Get the elevator id.
	 * @return int, elevator id
	 */
	public int getElevatorId() {
		return this.id;
	}
	
	/**
	 * Fetch a request from the Scheduler and add to requests queue.
	 */
	public void serveRequest() {
		ElevatorRequest request;
		// dispatch request, finished the request
		request = scheduler.dispatchRequest();
		logger.info(String.format("Elevator request queued: %s", request));
		
		// add the completed request to the queue
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
		while (true) {
			// TODO: add functionality to end when there are no more requests to serve
			serveRequest();
		}
	}

}
