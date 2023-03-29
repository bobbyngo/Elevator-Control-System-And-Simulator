/**
 * 
 */
package main.java.dto;

import java.io.Serializable;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.ElevatorStateEnum;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorGuiData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ElevatorStateEnum currentState;
	private int currentFloor;
	private Motor motor;
	private Direction direction;
	private Door door;
	// XXX: might include external & internal requests if it is needed
	
	public ElevatorGuiData(ElevatorContext ctx) {
		id = ctx.getId();
		currentState = ctx.getCurrentState().getElevatorStateEnum();
		currentFloor = ctx.getCurrentFloor();
		direction = ctx.getDirection();
		door = ctx.getDoors();
		motor = ctx.getMotor();
	}

	public int getId() {
		return id;
	}

	public ElevatorStateEnum getCurrentState() {
		return currentState;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public Motor getMotor() {
		return motor;
	}

	public Direction getDirection() {
		return direction;
	}

	public Door getDoor() {
		return door;
	}
	

}
