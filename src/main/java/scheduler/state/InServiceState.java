package main.java.scheduler.state;

import java.io.IOException;

import main.java.scheduler.SchedulerContext;

/**
 * @author Zakaria Ismail
 *
 */
public class InServiceState extends SchedulerState {

	/**
	 * Constructor
	 * @param ctx
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
	 * handleRequestReceived
	 */
	@Override
	public SchedulerState handleRequestReceived() {
		
		// handleRequestReceived():
		// if there are no pendingRequests and no completedRequests, go to idle state, else continue
		SchedulerContext ctx = this.getContext();
		if (ctx.isSchedulerIdle()) {
			return new IdleState(ctx);
		}
		return new InServiceState(ctx);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "InServiceState";
	}

	/**
	 * handleRequestSent
	 */
	@Override
	public SchedulerState handleRequestSent() {
		SchedulerContext ctx = this.getContext();
		if (ctx.isSchedulerIdle()) {
			return new IdleState(ctx);
		}
		return this;
	}

}
