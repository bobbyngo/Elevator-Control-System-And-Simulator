/**
 * 
 */
package main.java.dto;

import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorStatus {
	private int elevatorId;
	private Direction direction;
	int numRequests;
	
	public ElevatorStatus(ElevatorContext ctx) {
		elevatorId = ctx.getId();
		direction = ctx.getDirection();
		numRequests = ctx.getNumRequests();
	}
}
