package main.java.elevator.state;

import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * This substate represent the doors open state.
 * @author Zakaria Ismail
 */
public class DoorsOpenState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public DoorsOpenState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_CLOSE);
		ctx.setTimer(stt, ctx.getConfig().LOADING_TIME);
		ctx.setDoors(Door.OPEN);
		// TODO: load/unload passengers
		ctx.unloadPassengers();
		ctx.loadPassengers();
	}

	/**
	 * handleRequestReceived
	 */
	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	/**
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		if (ctx.getInternalRequests().isEmpty() && ctx.getExternalRequests().isEmpty()) {
			return new IdleState(ctx);
		}
		return new DoorsClosedState(ctx);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "DoorsOpen";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_OPEN;
	}

}
