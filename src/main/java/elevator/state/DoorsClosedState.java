/**
 * 
 */
package main.java.elevator.state;

import java.util.TimerTask;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class DoorsClosedState extends IdleMotorState {

	public DoorsClosedState(ElevatorContext ctx) {
		super(ctx);
		
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_THROTTLE);
		ctx.setTimer(stt, ctx.getConfig().DOORS_CLOSE_TIME);
		ctx.setDoors(Door.CLOSED);
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		Direction nextDirection;
		ctx.killTimer();
		// TODO: perform some conditional checks
		// consider current direction
		nextDirection = ctx.calculateNextDirection();
		ctx.setDirection(nextDirection);
	
		switch (ctx.getDirection()) {
		case UP:
			return new MovingUpState(ctx);
		case DOWN:
			return new MovingDownState(ctx);
		default:
			return new IdleState(ctx);
		}
	}

	@Override
	public String toString() {
		return "DoorsClosed";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_CLOSED;
	}

}
