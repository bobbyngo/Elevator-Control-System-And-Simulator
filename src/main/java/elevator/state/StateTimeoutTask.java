/**
 * 
 */
package main.java.elevator.state;

import java.util.TimerTask;

import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class StateTimeoutTask extends TimerTask {
	private ElevatorContext context;
	private TimeoutEvent event;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param e
	 */
	public StateTimeoutTask(ElevatorContext ctx, TimeoutEvent e) {
		context = ctx;
		event = e;
	}
	
	/**
	 * Run method
	 */
	@Override
	public void run() {
		context.onTimeout(event);
	}

}
