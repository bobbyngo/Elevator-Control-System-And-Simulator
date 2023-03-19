/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;

/**
 * Abstract for Elevator state
 * @author Zakaria Ismail
 *
 */
public abstract class ElevatorState {
	private ElevatorContext context;
	
	public ElevatorState(ElevatorContext ctx) {
		context = ctx;
	}
	
	public static ElevatorState start(ElevatorContext ctx) {
		return new DoorsOpenState(ctx);
	}
	
	public ElevatorContext getContext() {
		return context;
	}
	
	public abstract ElevatorState handleRequestReceived();
	public abstract ElevatorState handleTimeout();
	public abstract String toString();
	
}
