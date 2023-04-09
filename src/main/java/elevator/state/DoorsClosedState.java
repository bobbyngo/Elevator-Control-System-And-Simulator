package main.java.elevator.state;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * This substate represents the doors closed state.
 * @author Zakaria Ismail
 */
public class DoorsClosedState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public DoorsClosedState(ElevatorContext ctx) {
		super(ctx);
		
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_THROTTLE);
		ctx.setTimer(stt, ctx.getConfig().DOORS_CLOSE_TIME);
		ctx.setDoors(Door.CLOSED);
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
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		Direction nextDirection;
		ctx.killTimer();
		// TODO: perform some conditional checks
		// consider current direction
		nextDirection = ctx.calculateNextDirection();
		ctx.setDirection(nextDirection);
		
		if (ctx.shouldElevatorStop()) {
			return new StoppedState(ctx);
		}
	
		switch (ctx.getDirection()) {
		case UP:
			return new MovingUpState(ctx);
		case DOWN:
			return new MovingDownState(ctx);
		case IDLE:
			return new IdleState(ctx);
		}
		return new IdleState(ctx);
	}

	/**
	 * toString method
	 * @return String
	 */
	@Override
	public String toString() {
		return "DoorsClosed";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_CLOSED;
	}

}
