/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class HomingDoorsClosedState extends IdleMotorState {

	public HomingDoorsClosedState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.MOTOR_THROTTLE);
		ctx.setTimer(stt, ctx.getConfig().DOORS_CLOSE_TIME);
		ctx.setDoors(Door.CLOSED);
	}

	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		return new HomingState(ctx);
	}

	@Override
	public String toString() {
		return "DoorsClosed";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.HOMING_DOORS_CLOSED;
	}

}
