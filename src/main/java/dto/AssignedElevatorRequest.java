/**
 * 
 */
package main.java.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zakaria Ismail
 *
 */
public class AssignedElevatorRequest extends ElevatorRequest implements Serializable {
	private int elevatorId;
	
	public AssignedElevatorRequest(int assignedElevatorId, ElevatorRequest request) {
		super(request.getTimestamp(), request.getSourceFloor(), request.getDirection(), request.getDestinationFloor());
		elevatorId = assignedElevatorId;
	}
	
	public int getElevatorId() {
		return elevatorId;
	}
	
}
