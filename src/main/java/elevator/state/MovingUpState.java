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
		ctx.incrementCurrentFloor();
		// notify arrival sensor/scheduler???
		// Q: what's an arrival sensor...
		// TODO: add conditional code
		return new StoppedState(ctx);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MovingUp";
	}

}
