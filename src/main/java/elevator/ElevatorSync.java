package main.java.elevator;

import java.util.ArrayList;
import java.util.logging.Logger;

import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;

/**
 * Synchronizes the ElevatorFunctionality and ElevatorListener objects by providing methods 
 * for accessing shared memory
 * @author Hussein Elmokdad
 * @since 1.0, 03/18/23
 * @version 1.0, 03/18/23
 */
public class ElevatorSync {
	public ArrayList<ElevatorRequest> elevatorRequestsArr; // The shared memory that ElevatorFunctionality and ElevatorListener will sync on
	private int elevatorId;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * Constructor for ElevatorSync class
	 * @param elevatorId the int of the elevator ID
	 */
	public ElevatorSync(int elevatorId) {
		this.elevatorId = elevatorId;
		elevatorRequestsArr = new ArrayList<ElevatorRequest>();
	}
	
	/**
	 * Adds an elevator request to the requests queue
	 * @param elevatorRequest the ElevatorRequest object
	 */
	public synchronized void addElevatorRequest(ElevatorRequest elevatorRequest) {
		// Checks if it's null or a duplicate request 
		if (elevatorRequest == null || elevatorRequestsArr.contains(elevatorRequest)) return;
		elevatorRequestsArr.add(elevatorRequest);
		String loggerStr = String.format("Adding request %s to Elevator %d's requests queue. Queue size: %d", elevatorRequest.toString(), elevatorId, elevatorRequestsArr.size());
		System.out.println("Adding request " + elevatorRequest.toString() + " to Elevator " + elevatorId + "'s requests queue. Queue size: " + elevatorRequestsArr.size());
		//logger.info(loggerStr);
		notifyAll();
	}
	
	/**
	 * Removes all the completed elevator requests that contain the specified destination floor from the 
	 * requests queue and returns the list of completed requests 
	 * @param destinationFloor the int of the destination floor
	 * @return the ArrayList<ElevatorRequest> of the completed elevator requests
	 */
	public synchronized ArrayList<ElevatorRequest> removeElevatorRequests(int destinationFloor) {
		ArrayList<ElevatorRequest> completedRequests = new ArrayList<>();
		for (ElevatorRequest elevatorRequest : elevatorRequestsArr) {
			if (elevatorRequest.getDestinationFloor() == destinationFloor) completedRequests.add(elevatorRequest);
		}
		elevatorRequestsArr.removeAll(completedRequests);
		return completedRequests;
	}

	/**
	 * Gets an elevator request from the requests queue
	 * @return the ElevatorRequest object
	 */
	public synchronized ElevatorRequest getElevatorRequest() {
		while (elevatorRequestsArr.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ElevatorRequest elevatorRequest = elevatorRequestsArr.get(0);
		String loggerStr = String.format("Servicing request %s from Elevator %d's requests queue. Queue size: %d", elevatorRequest.toString(), elevatorId, elevatorRequestsArr.size());
		// logger.info(loggerStr);
		return elevatorRequest;
	}
	
	/**
	 * Checks if the elevator served all the requests heading in the same direction it's heading. If
	 * it did, it can start servicing requests that are heading in the opposite direction
	 * @param direction the Direction enum of the elevator
	 * @param currentFloor the int of the floor the elevator is currently on
	 * @return the boolean of whether all the requests heading in the same direction were serviced (true if there's some left, false otherwise)
	 */
	public synchronized boolean checkForSameDirectionReqs(Direction direction, int currentFloor) {
		for (ElevatorRequest elevatorRequest : elevatorRequestsArr) {
			if ((direction == Direction.UP && elevatorRequest.getDestinationFloor() > currentFloor) 
				 || (direction == Direction.DOWN && elevatorRequest.getDestinationFloor() < currentFloor) 
				 && elevatorRequest.getDirection() == direction) {
				return true; // There's still some requests heading in the same direction left
			}
		}
		return false;
	}
	
	/**
	 * Gets the elevator requests queue
	 * @return ArrayList<Integer> of all the elevator requests
	 */
	public synchronized ArrayList<ElevatorRequest> getRequestsQueue() {
		return elevatorRequestsArr;
	}
}
