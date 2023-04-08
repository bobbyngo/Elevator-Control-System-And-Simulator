/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
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
		ctx.setDirection(Direction.IDLE);
		ctx.returnExternalRequests();
	}

	/**
	 * handleRequestReceived
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		// Scheduler should not be assigning jobs at this state
		// Immediately return all incoming requests for re-scheduling
		ElevatorContext ctx = this.getContext();
		ctx.returnExternalRequests();
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
