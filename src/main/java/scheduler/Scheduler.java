/**
 * 
 */
package main.java.scheduler;

import java.util.ArrayList;
import java.util.logging.*;

import main.java.dto.ElevatorRequest;

/**
 * Responsible for accepting input from all of the sensors, and
 * sending indications (to devices such as lamps) and commands (to devices such as the motor and door). It is
 * responsible for routing each elevator to requested floors and coordinating elevators in such a way to minimize
 * waiting times for people moving between floors (avoiding starvation)
 * 
 * @author Bobby Ngo
 *
 */
public class Scheduler implements Runnable {
	
	private ArrayList<ElevatorRequest> requestsQueue = new ArrayList<>();
	private ArrayList<ElevatorRequest> completedQueue = new ArrayList<>();
	
	private Logger logger = Logger.getLogger(Scheduler.class.getName());

	/**
	 * This method is called by the Floor class. The new request will be added to the list of floors to visit
	 * @param elevatorRequest
	 */
	public void putRequest(ElevatorRequest elevatorRequest) {
		
		synchronized (requestsQueue) {
			// No duplicate values
			if (!requestsQueue.contains(elevatorRequest)) {
				requestsQueue.add(elevatorRequest);
				logger.info("Added " + elevatorRequest.toString() + " to the request queue. Queue size is: " + requestsQueue.size());
			}
			requestsQueue.notifyAll();
		}
		
	}
	
	/**
	 * This method will be called by Elevator class. After the floor finished a request it should dispatch an item from the list
	 * @return ElevatorRequest
	 */
	public ElevatorRequest dispatchRequest() {
		
		synchronized (requestsQueue) {
			while(requestsQueue.size() == 0) {
				try {
					logger.info("Waiting for the request");
					requestsQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.severe(e.getMessage());
					e.printStackTrace();
				}
			}
			
			// Iteration 1 we will first come first serve: remove the former index
			ElevatorRequest removedElevatorRequest = requestsQueue.remove(0);
			logger.info("Dispatched request " + removedElevatorRequest.toString());
			logger.info("The queue size is " + requestsQueue.size());
			
			requestsQueue.notifyAll();
			
			return removedElevatorRequest;
		}
		
	}
	
	/**
	 * Puts elevator request data into the Scheduler's reply queue.
	 * @param reply		ElevatorRequest, replied elevator request data
	 * @author Zakaria Ismail, 101143497
	 */
	public void putCompletedRequest(ElevatorRequest reply) {
		
		synchronized (completedQueue) {
			if (!completedQueue.contains(reply)) {
				completedQueue.add(reply);
				logger.info(String.format("Added %s to the completed queue. Queue size is %d", reply, completedQueue.size()));
			}
			completedQueue.notifyAll();
		}
		
	}
	
	/**
	 * Gets reply message from the reply queue
	 * @return		ElevatorRequest, message from the reply queue
	 */
	public ElevatorRequest getCompletedRequest() {
		
		synchronized (completedQueue) {
			ElevatorRequest reply;
			
			while (completedQueue.size() == 0) {
				try {
					completedQueue.wait();
				} catch (InterruptedException e) {}
			}
			
			reply = completedQueue.remove(0);
			completedQueue.notifyAll();
			return reply;
		}
		
	}

	/**
	 * Scheduler run() method. Sleeps until the process is
	 * killed.
	 * @author Zakaria Ismail, 101143497
	 */
	@Override
	public void run() {
		try {
			// Scheduler class is only used for its resource put/get methods
			// Q: How will the Scheduler thread be used when we distribute our application?
			Thread.sleep(0);
		} catch (InterruptedException e) {}
		return;
	}
}
