/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class DoorsStuckState extends IdleMotorState {

	public DoorsStuckState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_UNSTUCK);
		ctx.setTimer(stt, ctx.getConfig().DOORS_OBSTRUCTED_TIME);
		ctx.notifyArrivalSensor();
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		return new DoorsOpenState(ctx);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DoorsStuck";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		// TODO Auto-generated method stub
		return ElevatorStateEnum.DOORS_STUCK;
	}

}
