/**
 * 
 */
package main.java.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.Direction;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.scheduler.state.SchedulerState;

/**
 * Responsible for routing each elevator to requested floors and coordinating elevators in such a way to minimize
 * waiting times for people moving between floors (avoiding starvation).
 * @author Bobby Ngo, Patrick Liu
 */

public class SchedulerContext {
	
	private SchedulerSubsystem schedulerSubsystem;
	
	// storing all the elevators that are available
	private List<ElevatorStatus> availableElevatorStatus;
	// pending elevators requests
	private List<ElevatorRequest> pendingElevatorRequests;
	// Elevator requests that completed
	private List<ElevatorRequest> completedElevatorRequests;
	
	private SchedulerState currentState;
	
	/**
	 * Constructor for Scheduler Context
	 * @param schedulerSubsystem
	 */
	public SchedulerContext(SchedulerSubsystem schedulerSubsystem) {
		this.schedulerSubsystem = schedulerSubsystem;
		
		// make sure that 4 scheduler threads use the same instance of these 3 array list
		availableElevatorStatus = Collections.synchronizedList(new ArrayList<>());
		pendingElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		completedElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		
		for (int i = 1; i <= schedulerSubsystem.getSimulatorConfiguration().NUM_ELEVATORS; i++) {
			availableElevatorStatus.add(new ElevatorStatus(i));
		}
		
		currentState = SchedulerState.start(this);
		System.out.println(String.format("Current state: %s", currentState));
	}
	
	/**
	 * Method for finding all the elevators that are available and fit with the input
	 * @param direction
	 * @param newRequestSourceFloor
	 * @return Elevator Status
	 */
	private ElevatorStatus findTheAvailableElevator(Direction direction, int newRequestSourceFloor) {
		// checked the available is empty already so no need to check again
		ElevatorStatus chosenElevatorStatus = null;
		for (ElevatorStatus status : availableElevatorStatus) {
			if (status.getState() != ElevatorStateEnum.DOORS_STUCK || status.getState() != ElevatorStateEnum.ELEVATOR_STUCK) {
				// 1st priority: Elevator that is idle
				if (status.getDirection() == Direction.IDLE) {
					chosenElevatorStatus = status;
				} 
				// 2nd priority: Elevator that is moving up and current floor <= source floor
				else if (status.getDirection() == direction && direction == Direction.UP
						&& status.getFloor() <= newRequestSourceFloor) {
					chosenElevatorStatus = status;
				} 
				// 3rd priority: Elevator that is moving down and current floor >= source floor
				else if (status.getDirection() == direction && direction == Direction.DOWN
						&& status.getFloor() >= newRequestSourceFloor) {
					chosenElevatorStatus = status;
				}
			}
		}
		return chosenElevatorStatus;
	}
	
	/**
	 * Method for finding the best elevator following by the priority
	 * @return AssignedElevatorRequest
	 */
	public AssignedElevatorRequest findBestElevatorToAssignRequest() {
		// following C convention or zak will be malding
		AssignedElevatorRequest assignedElevatorRequest = null;
		
		if (availableElevatorStatus.size() == 0) {
			// should add udp saying there is not available elevator?
			System.out.println("There is no available elevator");
		} 
		// these 2 list could both be empty so if 
		if (pendingElevatorRequests.size() == 0) {
			// should add udp saying there is not available elevator?
			System.out.println("There is no elevator requests");
		} else {
			ElevatorStatus chosenElevatorStatus = null;
			
			for (ElevatorRequest request : pendingElevatorRequests)	{
				 chosenElevatorStatus = findTheAvailableElevator(request.getDirection(), request.getSourceFloor()); 
				if (chosenElevatorStatus != null) {
					assignedElevatorRequest = new AssignedElevatorRequest(chosenElevatorStatus.getElevatorId(), request);
				} else {
					// Get the first elevator
					chosenElevatorStatus = availableElevatorStatus.get(0);
					assignedElevatorRequest = new AssignedElevatorRequest(chosenElevatorStatus.getElevatorId(), request);
				}
			}
		}
		
		// can be null cause the 2 if statements
		return assignedElevatorRequest;
	}
	
	public void assignNextBestElevatorRequest() throws IOException {
		AssignedElevatorRequest request = this.findBestElevatorToAssignRequest();
		schedulerSubsystem.sendPendingRequest(request);
	}
	
	/**
	 * Getter for availableElevatorStatus
	 * @return all available elevators status
	 */
	public List<ElevatorStatus> getAvailableElevatorStatus() {
		return availableElevatorStatus;
	}
	
	/**
	 * Adding method for availableElevatorStatus list
	 * @param elevatorStatus
	 */
	public void addAvailableElevatorStatus(ElevatorStatus elevatorStatus) {
		synchronized(availableElevatorStatus) {
			availableElevatorStatus.add(elevatorStatus);
		}
	}
	
	/**
	 * Method for modifying the availableElevatorStatus list
	 * @param index
	 * @param elevatorStatus
	 */
	public void modifyAvailableElevatorStatus(int index, ElevatorStatus elevatorStatus) {
		synchronized(availableElevatorStatus) {
			availableElevatorStatus.set(index, elevatorStatus);
		}
	}

	/**
	 * Getter for pendingElevatorRequests
	 * @return pendingElevatorRequests list
	 */
	public List<ElevatorRequest> getPendingElevatorRequests() {
		return pendingElevatorRequests;
	}
	
	/**
	 * Adding method for pendingElevatorRequests list
	 * @param elevatorRequest
	 */
	public void addPendingElevatorRequests(ElevatorRequest elevatorRequest) {
		synchronized(pendingElevatorRequests) {
			pendingElevatorRequests.add(elevatorRequest);
			onRequestReceived();
		}
	}

	/**
	 * Getter for completedElevatorRequests
	 * @return elevator request
	 */
	public List<ElevatorRequest> getCompletedElevatorRequests() {
		return completedElevatorRequests;
	}
	
	/**
	 * Adding method for completedElevatorRequests list
	 * @param elevatorRequest
	 */
	public void addCompletedElevatorRequests(ElevatorRequest elevatorRequest) {
		synchronized(completedElevatorRequests) {
			completedElevatorRequests.add(elevatorRequest);
			onRequestReceived();
		}
	}
	
	public void onRequestReceived() {
		synchronized (currentState) {
			System.out.println("Event: Request Received");
			currentState = currentState.handleRequestReceived();
			System.out.println(String.format("Current state: %s", currentState));
		}
	}
	
	public boolean isSchedulerIdle() {
		return pendingElevatorRequests.size() == 0 && completedElevatorRequests.size() == 0;
	}
	
	public void processCompletedElevatorRequest() throws IOException {
		// do something with the completed elevator request here...?
		ElevatorRequest nextCompletedRequest;
		if (completedElevatorRequests.size() > 0) {
			nextCompletedRequest = completedElevatorRequests.get(0);
			schedulerSubsystem.sendCompletedElevatorRequest(nextCompletedRequest);
		}
	}

}
