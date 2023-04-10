package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorError;

/**
 * This substate represents the motor stopped state.
 * @author Zakaria Ismail
 */
public class StoppedState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public StoppedState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_OPEN);
		ctx.setTimer(stt, ctx.getConfig().DOORS_OPEN_TIME);
	}

	/**
	 * Handle the request received
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		return this;
	}

	/**
	 * Handle the Timeout event.
	 * @return ElevatorState, the state of the elevator
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
	 * toString method
	 * @return String
	 */
	@Override
	public String toString() {
		return "Stopped";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.STOPPED;
	}
}