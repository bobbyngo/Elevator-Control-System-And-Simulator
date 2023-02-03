package main.java.scheduler;

import java.util.ArrayList;
import java.util.logging.*;
import java.util.Collections;
import java.util.List;

import main.java.dto.ElevatorRequest;

/**
 * Responsible for accepting input from all of the sensors, and
 * sending indications (to devices such as lamps) and commands (to devices such as the motor and door). It is
 * responsible for routing each elevator to requested floors and coordinating elevators in such a way to minimize
 * waiting times for people moving between floors (avoiding starvation).
 * @author Bobby Ngo
 * @version 1.0, 02/04/23
 */
public class Scheduler implements Runnable {
	
	private static final Logger logger = Logger.getLogger(Scheduler.class.getName());
	private List<ElevatorRequest> requestsQueue = Collections.synchronizedList(new ArrayList<>());

	/**
	 * This method is called by the Floor class. The new request will be added to the list of floors to visit.
	 * @param elevatorRequest
	 */
	public synchronized void putRequest(ElevatorRequest elevatorRequest) {
		// No duplicate values
		if (!requestsQueue.contains(elevatorRequest)) {
			requestsQueue.add(elevatorRequest);
			logger.info("Added " + elevatorRequest.toString() + " to the queue");
			logger.info("The queue size is " + requestsQueue.size());
		}
		notifyAll();
	}
	
	/**
	 * This method will be called by Elevator class. 
	 * After the floor finished a request it should dispatch an item from the list.
	 * @return ElevatorRequest, the requested elevator object
	 */
	public synchronized ElevatorRequest dispatchRequest() {
		while(requestsQueue.size() == 0) {
			try {
				logger.info("Waiting for the request");
				wait();
			} catch (InterruptedException e) {
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
		}
		
		// Iteration 1 we will first come first serve: remove the former index
		ElevatorRequest removedElevatorRequest = requestsQueue.remove(0);
		logger.info("Dispatched request " + removedElevatorRequest.toString());
		logger.info("The queue size is " + requestsQueue.size());
		
		notifyAll();
		
		return removedElevatorRequest;
	}

	/**
	 * Scheduler override run() method. Sleeps until the process is killed.
	 * @see java.lang.Runnable#run()
	 * @author Zakaria Ismail
	 */
	@Override
	public void run() {
		try {
			// Scheduler class is only used for its resource put/get methods
			// Q: How will the Scheduler thread be used when we distribute our application?
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}
	
}

