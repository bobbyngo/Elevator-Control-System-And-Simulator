/**
 * 
 */
package main.java.scheduler;

import java.net.Authenticator.RequestorType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.scheduler.state.IdleState;
import main.java.scheduler.state.SchedulerState;
import main.java.dto.ElevatorStatus;

/**
 * @author Bobby Ngo
 *
 */

public class SchedulerContext {
	
	private SchedulerState currentState;
	private SchedulerSubsystem schedulerSubsystem;
	
	// storing all the elevators that are available
	private List<ElevatorStatus> availableElevatorStatus;
	// pending elevators requests
	private List<ElevatorRequest> pendingElevatorRequests;
	// Elevator requests that completed
	private List<ElevatorRequest> completedElevatorRequests;
	
	public SchedulerContext(SchedulerSubsystem schedulerSubsystem) {
		this.schedulerSubsystem = schedulerSubsystem;
		
		currentState = new IdleState(this);
		
		// make sure that 4 scheduler threads use the same instance of these 3 array list
		availableElevatorStatus = Collections.synchronizedList(new ArrayList<>());
		pendingElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		completedElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		
		for (int i = 1; i <= schedulerSubsystem.getSimulatorConfiguration().NUM_ELEVATORS; i++) {
			availableElevatorStatus.add(new ElevatorStatus(i));
		}
	}
	
	private ElevatorStatus findTheAvailableElevator(Direction direction, int newRequestSourceFloor) {
		// checked the available is empty already so no need to check again
		ElevatorStatus chosenElevatorStatus = null;
		for (ElevatorStatus status : availableElevatorStatus) {
			// 1st priority: Elevator that is idle
			if (status.getDirection() == direction && direction == Direction.IDLE) {
				chosenElevatorStatus = status;
			} 
			// 2nd priority: Elevator that is moving up and current floor <= source floor
			else if (status.getDirection() == direction && direction == Direction.UP
					&& status.getFloor() >= newRequestSourceFloor) {
				chosenElevatorStatus = status;
			} 
			// 3rd priority: Elevator that is moving down and current floor >= source floor
			else if (status.getDirection() == direction && direction == Direction.DOWN
					&& status.getFloor() <= newRequestSourceFloor) {
				chosenElevatorStatus = status;
			}
		}
		return chosenElevatorStatus;
	}
	
	
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
	
	public List<ElevatorStatus> getAvailableElevatorContexts() {
		return availableElevatorStatus;
	}
	
	public void addAvailableElevatorContexts(ElevatorStatus elevatorStatus) {
		synchronized(availableElevatorStatus) {
			availableElevatorStatus.add(elevatorStatus);
		}
	}
	
	public void modifyAvailableElevatorContexts(int index, ElevatorStatus elevatorStatus) {
		synchronized(availableElevatorStatus) {
			availableElevatorStatus.set(index, elevatorStatus);
		}
	}

	public List<ElevatorRequest> getPendingElevatorRequests() {
		return pendingElevatorRequests;
	}
	
	public void addPendingElevatorRequests(ElevatorRequest elevatorRequest) {
		synchronized(pendingElevatorRequests) {
			pendingElevatorRequests.add(elevatorRequest);
		}
	}

	public List<ElevatorRequest> getCompletedElevatorRequests() {
		return completedElevatorRequests;
	}
	
	public void addCompletedElevatorRequests(ElevatorRequest elevatorRequest) {
		synchronized(completedElevatorRequests) {
			completedElevatorRequests.add(elevatorRequest);
		}
	}
}
