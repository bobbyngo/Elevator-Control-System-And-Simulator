package main.java.elevator.state;

import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * This substate represent the moving state.
 * @author Zakaria Ismail
 */
public abstract class MovingState extends ElevatorState {
	
	/**
	 * Constructor for this state.
	 * @param ctx  ElevatorContext, the context of the elevator
	 */
	public MovingState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_STOP);
		ctx.setTimer(stt, ctx.getConfig().MOVING_TIME);
		ctx.setDoors(Door.CLOSED);
	}
}
