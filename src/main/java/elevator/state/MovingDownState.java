/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * Moving Down State for Elevator
 * @author Zakaria Ismail
 *
 */
public class MovingDownState extends MovingState {

	/**
	 * Constructor for MovingDownState
	 * @param ctx
	 */
	public MovingDownState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.THROTTLE_DOWN);
	}

	/**
	 * Override method for handling the receiving request of this state
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		ElevatorContext ctx = this.getContext();
		
		if (ctx.shouldElevatorStop(request)) {
			ctx.killTimer();
			return new StoppedState(ctx);
		}
 		return this;
	}

	/**
	 * Override method for timing out of this state
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		// arrival notif called here
		if (!ctx.decrementCurrentFloor()) {
			return new StoppedState(ctx);
		}

		if (ctx.shouldElevatorStop()) {
		return new StoppedState(ctx);
		}
		return new MovingDownState(ctx);
	}

	/**
	 * toString overriding method
	 */
	@Override
	public String toString() {
		return "MovingDown";
	}

	/**
	 * Getter for elevator state enum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.MOVING_DOWN;
	}

}
