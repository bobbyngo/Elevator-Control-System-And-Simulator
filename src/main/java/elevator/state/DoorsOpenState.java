/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class DoorsOpenState extends IdleMotorState {

	public DoorsOpenState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_CLOSE);
		ctx.setTimer(stt, ctx.getConfig().LOADING_TIME);
		ctx.setDoors(Door.OPEN);
		// TODO: load/unload passengers
		ctx.unloadPassengers();
		ctx.loadPassengers();
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		if (ctx.getInternalRequests().isEmpty() && ctx.getExternalRequests().isEmpty()) {
			return new IdleState(ctx);
		}
		return new DoorsClosedState(ctx);
	}

	@Override
	public String toString() {
		return "DoorsOpen";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_OPEN;
	}

}
