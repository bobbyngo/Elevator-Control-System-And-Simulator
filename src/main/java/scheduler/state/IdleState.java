/**
 * 
 */
package main.java.scheduler.state;

import main.java.scheduler.SchedulerContext;

/**
 * @author Zakaria Ismail
 *
 */
public class IdleState extends SchedulerState {

	public IdleState(SchedulerContext ctx) {
		super(ctx);
		// entry/constructor():
		// do nothing. wait for some "signal" to wake it up
		// being in this state means that there are no pendingRequests
		// and completedRequests in the queues
	}

	@Override
	public SchedulerState handleRequestReceived() {
		// handleRequestReceived():
		// transition to IN_SERVICE
		return new InServiceState(this.getContext());

	}

	@Override
	public String toString() {
		return "IdleState";
	}

}
