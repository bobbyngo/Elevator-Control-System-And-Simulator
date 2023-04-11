package main.java.scheduler.state;

import java.io.IOException;

import main.java.scheduler.SchedulerContext;

/**
 * Scheduler in service state.
 * 
 * @author Zakaria Ismail
 */
public class InServiceState extends SchedulerState {

	/**
	 * Constructor for SchedulerState.
	 * 
	 * @param ctx SchedulerContext, the context of the scheduler
	 */
	public InServiceState(SchedulerContext ctx) {
		super(ctx);
		try {
			ctx.assignNextBestElevatorRequest();
			ctx.processCompletedElevatorRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handle the requests received.
	 * 
	 * @return SchedulerState, the state of the scheduler
	 */
	@Override
	public SchedulerState handleRequestReceived() {

		// handleRequestReceived():
		// if there are no pendingRequests and no completedRequests, go to idle state,
		// else continue
		SchedulerContext ctx = this.getContext();
		if (ctx.isSchedulerIdle()) {
			return new IdleState(ctx);
		}
		return new InServiceState(ctx);
	}

	/**
	 * Handle the requests sent.
	 * 
	 * @return SchedulerState, the state of the scheduler
	 */
	@Override
	public SchedulerState handleRequestSent() {
		SchedulerContext ctx = this.getContext();
		if (ctx.isSchedulerIdle()) {
			return new IdleState(ctx);
		}
		return this;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "InServiceState";
	}

}
