package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * This class represent the elevator moving up state.
 * @author Zakaria Ismail
 */
public class MovingUpState extends MovingState {

	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public MovingUpState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.THROTTLE_UP);
		ctx.setDirection(Direction.UP);
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
		
		// Increment the current floor onTimeout
		// if external request in current direction exists
		// or internal request exists at current floor
		if (!ctx.incrementCurrentFloor() || ctx.shouldElevatorStop()) {
			return new StoppedState(ctx);
		}
		return new MovingUpState(ctx);
	}

	/**
	 * toString method
	 * @return String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MovingUp";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.MOVING_UP;
	}

}
