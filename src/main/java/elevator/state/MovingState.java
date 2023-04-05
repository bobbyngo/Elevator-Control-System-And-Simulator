/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public abstract class MovingState extends ElevatorState {
	/**
	 * Constructor
	 * @param ctx
	 */
	public MovingState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_STOP);
		ctx.setTimer(stt, ctx.getConfig().MOVING_TIME);
		ctx.setDoors(Door.CLOSED);
	}
}
