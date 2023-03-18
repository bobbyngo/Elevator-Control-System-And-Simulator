package main.java.elevator;

import java.util.ArrayList;
import java.util.logging.Logger;

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
		logger.info(loggerStr);
		notifyAll();
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
		ElevatorRequest elevatorRequest = elevatorRequestsArr.remove(0);
		String loggerStr = String.format("Servicing request %s from Elevator %d's requests queue. Queue size: %d", elevatorRequest.toString(), elevatorId, elevatorRequestsArr.size());
		logger.info(loggerStr);
		return elevatorRequest;
	}
}
