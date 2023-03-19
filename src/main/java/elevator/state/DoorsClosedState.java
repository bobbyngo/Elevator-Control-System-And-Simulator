/**
 * 
 */
package main.java.elevator.state;

import java.util.TimerTask;

import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class DoorsClosedState extends IdleMotorState {

	public DoorsClosedState(ElevatorContext ctx) {
		super(ctx);
		
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_THROTTLE);
		ctx.setTimer(stt, ctx.getConfig().DOORS_CLOSE_TIME);
		ctx.setDoors(Door.CLOSED);
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		// TODO: perform some conditional checks
		return new MovingUpState(ctx);
	}

	@Override
	public String toString() {
		return "DoorsClosed";
	}

}
