/**
 * 
 */
package main.java.scheduler.state;

import java.io.IOException;

import main.java.dto.AssignedElevatorRequest;
import main.java.scheduler.SchedulerContext;

/**
 * @author Zakaria Ismail
 *
 */
public class InServiceState extends SchedulerState {

	public InServiceState(SchedulerContext ctx) {
		super(ctx);
		// entry/constructor:
			// 1. execute behaviour for pendingRequests if there exists a pendingRequest (independent thread)
			// 2. execute behaviour for completedRequests if there exists a completedRequests (independent thread)
			//	-> join these two tasks
//		Thread pendingReqTask, completedReqTask;
//		
//		pendingReqTask = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					ctx.assignNextBestElevatorRequest();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		completedReqTask = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//					ctx.processCompletedElevatorRequest();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		pendingReqTask.start();
//		completedReqTask.start();
//		try {
//			completedReqTask.join();
//			pendingReqTask.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		try {
			ctx.assignNextBestElevatorRequest();
			ctx.processCompletedElevatorRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	@Override
	public String toString() {
		return "InServiceState";
	}

}
