/**
 * 
 */
package main.java.scheduler.state;

import main.java.scheduler.SchedulerContext;

/**
 * @author Zakaria Ismail
 *
 */
public abstract class SchedulerState {
	private SchedulerContext context;
	
	/**
	 * Constructor
	 * @param ctx
	 */
	public SchedulerState(SchedulerContext ctx) {
		context = ctx;
	}
	
	/**
	 * getContext
	 * @return
	 */
	public SchedulerContext getContext() {
		return context;
	}
	
	/**
	 * start method with idle state
	 * @param ctx
	 * @return
	 */
	public static SchedulerState start(SchedulerContext ctx) {
		return new IdleState(ctx);
	}
	
	public abstract SchedulerState handleRequestReceived();
	public abstract SchedulerState handleRequestSent();
	public abstract String toString();
}
