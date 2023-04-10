package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;

/**
 * Abstract for Elevator state.
 * @author Zakaria Ismail
 */
public abstract class ElevatorState {
	private ElevatorContext context;
	
	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public ElevatorState(ElevatorContext ctx) {
		context = ctx;
	}
	
	/**
	 * Start the state with Idle
	 * @param ctx ElevatorContext, the elevator context
	 * @return ElevatorState, the state of the elevator
	 */
	public static ElevatorState start(ElevatorContext ctx) {
		return new IdleState(ctx);
	}
	
	/**
	 * Getter for context.
	 * @return ElevatorContext, the context of the elevator
	 */
	public ElevatorContext getContext() {
		return context;
	}

	/**
	 * Handle the request received
	 * @return ElevatorState, the state of the elevator
	 */
	public abstract ElevatorState handleRequestReceived(ElevatorRequest request);

	/**
	 * Handle the Timeout event.
	 * @return ElevatorState, the state of the elevator
	 */
	public abstract ElevatorState handleTimeout();
	
	/**
	 * toString method
	 * @return String
	 */
	public abstract String toString();
	
	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	public abstract ElevatorStateEnum getElevatorStateEnum();
	
}
