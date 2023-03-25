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
		ctx.notifyArrivalSensor();
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		if (ctx.isAtDoorsStuckFloor()) {
			return new DoorsStuckState(ctx);
		}
		if (ctx.isAtElevatorStuckFloor()) {
			return new ElevatorStuckState(ctx);
		}
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
