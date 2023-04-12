package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;

/**
 * This substate represents the doors stuck state.
 * 
 * @author Zakaria Ismail
 */
public class DoorsStuckState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * 
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public DoorsStuckState(ElevatorContext ctx) {
		super(ctx);
		StateTimeoutTask stt = new StateTimeoutTask(ctx, TimeoutEvent.DOORS_UNSTUCK);
		ctx.setTimer(stt, ctx.getConfig().DOORS_OBSTRUCTED_TIME);
		//ctx.returnExternalRequests();
	}

	/**
	 * Handle the request received.
	 * 
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		// Scheduler should not be assigning requests at this state
		// all requests are immediately returned for re-scheduling
		ElevatorContext ctx = this.getContext();
		//ctx.returnExternalRequests();
		return this;
	}

	/**
	 * Handle the Timeout event.
	 * 
	 * @return ElevatorState, the state of the elevator
	 */
	@Override
	public ElevatorState handleTimeout() {
		ElevatorContext ctx = this.getContext();
		ctx.killTimer();
		return new DoorsOpenState(ctx);
	}
	
	/**
	 * toString method
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "DoorsStuck";
	}

	/**
	 * Get the ElevatorStateEnum.
	 * 
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.DOORS_STUCK;
	}

}
