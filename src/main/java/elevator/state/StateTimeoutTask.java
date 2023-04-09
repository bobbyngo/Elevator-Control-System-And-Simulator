/**
 * 
 */
package main.java.elevator.state;

import java.util.TimerTask;

import main.java.elevator.ElevatorContext;

/**
 * This class represent the state timeout task.
 * @author Zakaria Ismail
 */
public class StateTimeoutTask extends TimerTask {
	private ElevatorContext context;
	private TimeoutEvent event;
	
	/**
	 * Constructor for the state timeout task.
	 * @param ctx ElevatorContext, the context of the elevator
	 * @param e TimeoutEvent, the timeout event object
	 */
	public StateTimeoutTask(ElevatorContext ctx, TimeoutEvent e) {
		context = ctx;
		event = e;
	}
	
	/**
	 * Run method.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		context.onTimeout(event);
	}

}
