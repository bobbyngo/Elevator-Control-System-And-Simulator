/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public class MovingUpState extends MovingState {

	public MovingUpState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.THROTTLE_UP);
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		
		ctx.killTimer();
		ctx.incrementCurrentFloor();	// arrival notif is fired when increment is called
		
		// if external request in current direction exists
		// or internal request exists at current floor
		if (ctx.shouldElevatorStop()) {
			return new StoppedState(ctx);
		}
		return new MovingUpState(ctx);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MovingUp";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.MOVING_UP;
	}

}
