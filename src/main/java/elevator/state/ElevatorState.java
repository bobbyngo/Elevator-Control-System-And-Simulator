/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;

/**
 * Abstract for Elevator state
 * @author Zakaria Ismail
 *
 */
public abstract class ElevatorState {
	private ElevatorContext context;
	
	/**
	 * Constructor
	 * @param ctx
	 */
	public ElevatorState(ElevatorContext ctx) {
		context = ctx;
	}
	
	/**
	 * Start the state with Idle
	 * @param ctx
	 * @return
	 */
	public static ElevatorState start(ElevatorContext ctx) {
		return new IdleState(ctx);
	}
	
	/**
	 * Getter for context
	 * @return
	 */
	public ElevatorContext getContext() {
		return context;
	}
	
	// FIXME: Optimization - handleRequestReceived should take ElevatorRequest object as arg (why loop?)
	public abstract ElevatorState handleRequestReceived(ElevatorRequest request);
	public abstract ElevatorState handleTimeout();
	public abstract String toString();
	public abstract ElevatorStateEnum getElevatorStateEnum();
	
}
