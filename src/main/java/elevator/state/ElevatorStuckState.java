/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorStuckState extends IdleMotorState {

	/**
	 * Constructor
	 * @param ctx
	 */
	public ElevatorStuckState(ElevatorContext ctx) {
		super(ctx);
		ctx.returnExternalRequests();
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
		return this;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "ElevatorStuck";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.ELEVATOR_STUCK;
	}

}
