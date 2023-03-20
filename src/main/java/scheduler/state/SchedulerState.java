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
	
	public SchedulerState(SchedulerContext ctx) {
		context = ctx;
	}
	
	public SchedulerContext getContext() {
		return context;
	}
	
	//public abstract SchedulerContext handle
}
