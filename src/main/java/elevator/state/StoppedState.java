/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public class StoppedState extends IdleMotorState {

	public StoppedState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_OPEN);
		ctx.setTimer(stt, ctx.getConfig().DOORS_OPEN_TIME);
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		return new DoorsOpenState(ctx);
	}

	@Override
	public String toString() {
		return "Stopped";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.STOPPED;
	}

}
