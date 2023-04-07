/**
 * 
 */
package main.java.elevator.state;

import java.util.TimerTask;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class DoorsClosedState extends IdleMotorState {

	/**
	 * Constructor
	 * @param ctx
	 */
	public DoorsClosedState(ElevatorContext ctx) {
		super(ctx);
		
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_THROTTLE);
		ctx.setTimer(stt, ctx.getConfig().DOORS_CLOSE_TIME);
		ctx.setDoors(Door.CLOSED);
	}

	/**
	 * handleRequestReceived
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		// if request for current floor at current direction detected, go back to
		// DoorsOpen. I could have made this goto Stopped then DoorsOpen, but for
		// this scenario, I will allow the DoorsClose "animation" to instantaneously
		// jump to DoorsOpen instead of either a. waiting for doors to close then reopen
		// or b. instantaneously close doors then wait for doors to open
		ElevatorContext ctx = this.getContext();
		if (ctx.shouldElevatorStop(request)) {
			ctx.killTimer();
			return new DoorsOpenState(ctx);
		}
		return this;
	}

	/**
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		Direction nextDirection;
		ctx.killTimer();
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
		default:
			break;
		}
		return new IdleState(ctx); // this shouldn't happen
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "DoorsClosed";
	}

	/**
	 * getElevatorStateEnum
	 */ 
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_CLOSED;
	}

}
