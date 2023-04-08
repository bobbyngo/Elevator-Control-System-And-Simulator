/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public class MovingUpState extends MovingState {

	/**
	 * Constructor
	 * @param ctx
	 */
	public MovingUpState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.THROTTLE_UP);
		ctx.setDirection(Direction.UP);
	}

	/**
	 * handleRequestReceived
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
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		
		ctx.killTimer();
		
		// Increment the current floor onTimeout
		if (!ctx.incrementCurrentFloor()) {
			return new StoppedState(ctx);
		}
		// if external request in current direction exists
		// or internal request exists at current floor
		if (ctx.shouldElevatorStop()) {
			return new StoppedState(ctx);
		}
		return new MovingUpState(ctx);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MovingUp";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.MOVING_UP;
	}

}
