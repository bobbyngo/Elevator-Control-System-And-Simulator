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
public class MovingDownState extends MovingState {

	public MovingDownState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.THROTTLE_DOWN);
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		ctx.decrementCurrentFloor();
		// TODO: add conditionals + notify
		return new StoppedState(ctx);
	}

	@Override
	public String toString() {
		return "MovingDown";
	}

}
