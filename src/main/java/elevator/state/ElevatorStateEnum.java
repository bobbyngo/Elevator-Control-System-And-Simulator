/**
 * 
 */
package main.java.elevator.state;

import java.io.Serializable;

/**
 * @author Zakaria Ismail
 *
 */
public enum ElevatorStateEnum implements Serializable {
	DOORS_OPEN,
	DOORS_CLOSED,
	IDLE,
	MOVING_DOWN,
	MOVING_UP,
	STOPPED,
	DOORS_STUCK,
	ELEVATOR_STUCK
}
