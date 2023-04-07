/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorError;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public class StoppedState extends IdleMotorState {

	/**
	 * Constructor
	 * @param ctx
	 */
	public StoppedState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_OPEN);
		ctx.setTimer(stt, ctx.getConfig().DOORS_OPEN_TIME);
	}

	/**
	 * handleRequestReceived
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		return this;
	}

	/**
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ElevatorError error;
		ctx.killTimer();
		error = ctx.isAtErrorFloor();
		if (error != null) {
			switch (error) {
				case ELEVATOR_STUCK: return new ElevatorStuckState(ctx);
				case DOORS_STUCK: return new DoorsStuckState(ctx);
			}
		}
		return new DoorsOpenState(ctx);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Stopped";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.STOPPED;
	}

}
