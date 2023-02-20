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
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class Scheduler implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private List<ElevatorRequest> requestsQueue;
	private List<ElevatorRequest> completedQueue;
	private SchedulerState schedulerState;
	
	public Scheduler() {
		requestsQueue = Collections.synchronizedList(new ArrayList<>());
		completedQueue = Collections.synchronizedList(new ArrayList<>());
		schedulerState = SchedulerState.Idle;
	}
	
	/**
	 * Get the queue of the elevator request.
	 * @return List<>, list of elevator request
	 */
	public List<ElevatorRequest> getRequestsQueue() {
		return requestsQueue;
	}
	
	/**
	 * Get current scheduler state
	 * @return schedulerState, current scheduler state
	 */
	public SchedulerState getSchedulerState() {
		return schedulerState;
	}
	
	/**
	 * This method is called by the Floor class. The new request will be added to the list of floors to visit.
	 * @param elevatorRequest
	 */
	public synchronized void putRequest(ElevatorRequest elevatorRequest) {
		// No duplicate values
		if (!requestsQueue.contains(elevatorRequest)) {
			requestsQueue.add(elevatorRequest);
			String loggerStr = String.format("Add request %s > request queue: %d", elevatorRequest.toString(), requestsQueue.size());
			logger.info(loggerStr);
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
		String loggerStr = String.format("Dispatch request %s > request queue: %d", removedElevatorRequest.toString(), requestsQueue.size());
		logger.info(loggerStr);
		notifyAll();
		return removedElevatorRequest;
	}
	
	/**
	 * Puts elevator request data into the Scheduler's reply queue.
	 * @param reply ElevatorRequest, replied elevator request data
	 * @author Zakaria Ismail
	 */
	public synchronized void putCompletedRequest(ElevatorRequest reply) {
		if (!completedQueue.contains(reply)) {
			completedQueue.add(reply);
			String loggerStr = String.format("Add request %s to the completed queue > completed queue: %d", reply, completedQueue.size());
			logger.info(loggerStr);
		}
		notifyAll();
	}
	
	/**
	 * Gets reply message from the reply queue
	 * @return ElevatorRequest, message from the reply queue
	 */
	public synchronized ElevatorRequest getCompletedRequest() {
		ElevatorRequest reply;
		
		while (completedQueue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		reply = completedQueue.remove(0);
		notifyAll();
		return reply;
	}

	/**
	 * Scheduler override run() method. Sleeps until the process is killed.
	 * @see java.lang.Runnable#run()
	 * @author Zakaria Ismail
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(0);
			while (true) {
				switch (schedulerState) {
				case Idle: {
					schedulerState = schedulerState.nextState();
					break;
				}
				case Ready: {
					if (!requestsQueue.isEmpty()) {
						schedulerState = schedulerState.nextState();
					}
					break;
				}
				case InService: {
					if (!completedQueue.isEmpty()) {
						schedulerState = schedulerState.nextState();
					}
					break;
				}
				default:
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

