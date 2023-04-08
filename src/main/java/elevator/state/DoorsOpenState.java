/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * Doors Open state. Loads and unloads passengers
 * @author Zakaria Ismail
 *
 */
public class DoorsOpenState extends IdleMotorState {

	/**
	 * Constructor
	 * @param ctx
	 */
	public DoorsOpenState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_CLOSE);
		ctx.setTimer(stt, ctx.getConfig().LOADING_TIME);
		ctx.setDoors(Door.OPEN);
		// TODO: load/unload passengers
		ctx.unloadPassengers();
		ctx.loadPassengers();
	}

	/**
	 * handleRequestReceived
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		// during the loading time period, appropriate passengers should be able
		// to board the elevator
		// note to test: if passenger is trolling and boards and elevator to request the same
		// floor, the elevator will go Open->Closed->Stopped->Open
		ElevatorContext ctx = this.getContext();
		ctx.loadPassengers(request);
		return this;
	}

	/**
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		Direction nextDirection = ctx.calculateNextDirection();
		ctx.killTimer();
		
		if (nextDirection == Direction.IDLE) 
			return new IdleState(ctx);
		if (ctx.getDirection() != nextDirection) {
			// no more requests in current direction, go opposite & load passengers
			ctx.setDirection(nextDirection);
			return new DoorsOpenState(ctx);
		}
		return new DoorsClosedState(ctx); // elevator continues in current direction
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "DoorsOpen";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_OPEN;
	}

}
