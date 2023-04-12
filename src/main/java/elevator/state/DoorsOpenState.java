package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * Doors Open state. Loads and unloads passengers
 * @author Zakaria Ismail
 */
public class DoorsOpenState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * 
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public DoorsOpenState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_CLOSE);
		ctx.setTimer(stt, ctx.getConfig().LOADING_TIME);
		ctx.setDoors(Door.OPEN);
		
		ctx.loadPassengers();
		ctx.unloadPassengers();
	}

	/**
	 * Handle the request received.
	 * 
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		ElevatorContext ctx = this.getContext();
		ctx.loadPassengers(request);
		return this;
	}

	/**
	 * Handle the Timeout event.
	 * 
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		Direction nextDirection = ctx.calculateNextDirection();
		Direction nextHomingDirection = ctx.calculateNextHomingDirection();
		ctx.killTimer();
		
		if (nextDirection == Direction.IDLE) {
			if (nextHomingDirection != Direction.IDLE) {
				ctx.setDirection(nextHomingDirection);
				return new HomingDoorsClosedState(ctx);
			}
			return new IdleState(ctx);
		}
		if (ctx.getDirection() != nextDirection) {
			// no more requests in current direction, go opposite & load passengers
			ctx.setDirection(nextDirection);
			return new DoorsOpenState(ctx);
		}
		return new DoorsClosedState(ctx); // elevator continues in current direction
	}

	/**
	 * toString method
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "DoorsOpen";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * 
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_OPEN;
	}

}
