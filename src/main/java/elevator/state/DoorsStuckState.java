package main.java.elevator.state;

import main.java.elevator.ElevatorContext;

/**
 * This substate represents the doors stuck state.
 * @author Zakaria Ismail
 */
public class DoorsStuckState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public DoorsStuckState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_UNSTUCK);
		ctx.setTimer(stt, ctx.getConfig().DOORS_OBSTRUCTED_TIME);
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
		return new DoorsOpenState(ctx);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DoorsStuck";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		// TODO Auto-generated method stub
		return ElevatorStateEnum.DOORS_STUCK;
	}

}
