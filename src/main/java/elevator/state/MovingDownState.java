/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * Moving Down State for Elevator
 * @author Zakaria Ismail
 *
 */
public class MovingDownState extends MovingState {

	/**
	 * Constructor for this state.
	 * @param ctx  ElevatorContext, the context of the elevator
	 */
	public MovingDownState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.THROTTLE_DOWN);
		ctx.setDirection(Direction.DOWN);
	}

	/**
	 * Handle the request received
	 * @return ElevatorState, the state of the elevator
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
	 * Handle the Timeout event.
	 * @return ElevatorState, the stateo of the elevator
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		// arrival notif called here
		if (!ctx.decrementCurrentFloor() && ctx.shouldElevatorStop()) {
			return new StoppedState(ctx);
		}
		return new MovingDownState(ctx);
	}

	/**
	 * toString method
	 * @return String
	 */
	@Override
	public String toString() {
		return "MovingDown";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.MOVING_DOWN;
	}

}
