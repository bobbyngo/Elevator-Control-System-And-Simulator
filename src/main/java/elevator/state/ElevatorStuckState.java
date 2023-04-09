package main.java.elevator.state;

import main.java.elevator.ElevatorContext;

/**
 * This substate represents the elevator stuck state.
 * @author Zakaria Ismail
 */
public class ElevatorStuckState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public ElevatorStuckState(ElevatorContext ctx) {
		super(ctx);
		ctx.returnExternalRequests();
	}
	
	/**
	 * Handle the request received
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	/**
	 * Handle the Timeout event.
	 * @return ElevatorState, the stateo of the elevator
	 */
	@Override
	public ElevatorState handleTimeout() {
		return this;
	}
	
	/**
	 * toString method
	 * @return String
	 */
	@Override
	public String toString() {
		return "ElevatorStuck";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.ELEVATOR_STUCK;
	}

}
