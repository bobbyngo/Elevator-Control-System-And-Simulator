/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public class HomingState extends ElevatorState {

	public HomingState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_STOP);
		ctx.setTimer(stt, ctx.getConfig().MOVING_TIME);
		
		switch (ctx.getDirection()) {
		case UP: ctx.setMotor(Motor.THROTTLE_DOWN); break;
		case DOWN: ctx.setMotor(Motor.THROTTLE_UP); break;
		default:
			System.out.println("PANIC! THIS SHOULDN'T HAPPEN!");
		}
		
		
	}

	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		//
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		switch(ctx.getMotor()) {
		case THROTTLE_UP: 
			if (!ctx.incrementCurrentFloor()) {
				return new StoppedState(ctx);
			}
			break;
		case THROTTLE_DOWN: 
			if (!ctx.decrementCurrentFloor()) {
				return new StoppedState(ctx);
			}
			break;
		default:
		}
		
		if (ctx.shouldElevatorHome()) {
			return new HomingState(ctx);
		}
		return new StoppedState(ctx);
	}

	@Override
	public String toString() {
		return "Homing";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.HOMING;
	}

}
