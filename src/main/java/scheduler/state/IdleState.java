package main.java.scheduler.state;

import main.java.scheduler.SchedulerContext;

/**
 * Scheduler idle state.
 * 
 * @author Zakaria Ismail
 */
public class IdleState extends SchedulerState {

	/**
	 * Constructor for SchedulerState.
	 * 
	 * @param ctx SchedulerContext, the context of the scheduler
	 */
	public IdleState(SchedulerContext ctx) {
		super(ctx);
		// entry/constructor():
		// do nothing. wait for some "signal" to wake it up
		// being in this state means that there are no pendingRequests
		// and completedRequests in the queues
	}

	/**
	 * Handle the requests received.
	 * 
	 * @return SchedulerState, the state of the scheduler
	 */
	@Override
	public SchedulerState handleRequestReceived() {
		SchedulerContext ctx = this.getContext();
		if (ctx.isSchedulerIdle()) {
			return this;
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
		return this;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "IdleState";
	}
	
}
