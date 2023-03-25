/**
 * 
 */
package main.java.scheduler;

import java.net.Authenticator.RequestorType;

import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.scheduler.state.IdleState;
import main.java.scheduler.state.SchedulerState;

/**
 * @author Bobby Ngo
 *
 */

public class SchedulerContext {
	
	private SchedulerState currentState;
	private SchedulerSubsystem schedulerSubsystem;
	
	
	public SchedulerContext(SchedulerSubsystem schedulerSubsystem) {
		this.schedulerSubsystem = schedulerSubsystem;
		
		currentState = new IdleState(this);
		
		
	}
	
	private ElevatorContext findTheAvailableElevator(Direction direction, int newRequestSourceFloor) {
		// checked the available is empty already so no need to check again
		ElevatorContext chosenElevatorContext = null;
		for (ElevatorContext context : schedulerSubsystem.getAvailableElevatorContexts()) {
			// 1st priority: Elevator that is idle
			if (context.getDirection() == direction && direction == Direction.IDLE) {
				chosenElevatorContext = context;
			} 
			// 2nd priority: Elevator that is moving up and current floor <= source floor
			else if (context.getDirection() == direction && direction == Direction.UP
					&& context.getCurrentFloor() >= newRequestSourceFloor) {
				chosenElevatorContext = context;
			} 
			// 3rd priority: Elevator that is moving down and current floor >= source floor
			else if (context.getDirection() == direction && direction == Direction.DOWN
					&& context.getCurrentFloor() <= newRequestSourceFloor) {
				chosenElevatorContext = context;
			}
		}
		return chosenElevatorContext;
	}
	
	
	public AssignedElevatorRequest findBestElevatorToAssignRequest() {
		// following C convention or zak will be malding
		AssignedElevatorRequest assignedElevatorRequest = null;
		
		if (schedulerSubsystem.getAvailableElevatorContexts().size() == 0) {
			// should add udp saying there is not available elevator?
			System.out.println("There is no available elevator");
		} 
		// these 2 list could both be empty so if 
		if (schedulerSubsystem.getPendingElevatorRequests().size() == 0) {
			// should add udp saying there is not available elevator?
			System.out.println("There is no elevator requests");
		} else {
			ElevatorContext chosenElevatorContext = null;
			
			
			for (ElevatorRequest request : schedulerSubsystem.getPendingElevatorRequests())	{
				 chosenElevatorContext = findTheAvailableElevator(request.getDirection(), request.getSourceFloor()); 
				if (chosenElevatorContext != null) {
					assignedElevatorRequest = new AssignedElevatorRequest(chosenElevatorContext.getId(), request);
				} else {
					// Get the first elevator
					chosenElevatorContext = schedulerSubsystem.getAvailableElevatorContexts().get(0);
					assignedElevatorRequest = new AssignedElevatorRequest(chosenElevatorContext.getId(), request);
				}
			}
		}
		
		// can be null cause the 2 if statements
		return assignedElevatorRequest;
	}
}
