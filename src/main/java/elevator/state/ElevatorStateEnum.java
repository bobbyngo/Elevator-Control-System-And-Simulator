package main.java.elevator.state;

import java.io.Serializable;

/**
 * This class represent the elevator state enum.
 * 
 * @author Zakaria Ismail
 */
public enum ElevatorStateEnum implements Serializable {
	DOORS_OPEN,
	DOORS_CLOSED,
	HOMING_DOORS_CLOSED,
	IDLE,
	MOVING_DOWN,
	MOVING_UP,
	STOPPED,
	HOMING,
	DOORS_STUCK,
	ELEVATOR_STUCK,
}
