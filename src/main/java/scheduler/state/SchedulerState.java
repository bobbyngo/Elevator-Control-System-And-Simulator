package main.java.scheduler.state;

import main.java.scheduler.SchedulerContext;

/**
 * Scheduler superstate.
 * @author Zakaria Ismail
 */
public abstract class SchedulerState {
	private SchedulerContext context;
	
	/**
	 * Constructor for SchedulerState.
	 * @param ctx SchedulerContext, the context of the scheduler
	 */
	public SchedulerState(SchedulerContext ctx) {
		context = ctx;
	}
	
	/**
	 * Get the context of the scheduler.
	 * @return context, SchedulerContext
	 */
	public SchedulerContext getContext() {
		return context;
	}
	
	/**
	 * Start method with idle state
	 * @param ctx context, SchedulerContext
	 * @return SchedulerState, the state of the scheduler
	 */
	public static SchedulerState start(SchedulerContext ctx) {
		return new IdleState(ctx);
	}
	
	/**
	 * Handle the requests received.
	 * @return SchedulerState, the state of the scheduler
	 */
	public abstract SchedulerState handleRequestReceived();
	
	/**
	 * Handle the requests sent.
	 * @return SchedulerState, the state of the scheduler
	 */
	public abstract SchedulerState handleRequestSent();
	
	/**
	 * toString method
	 */
	public abstract String toString();
}
