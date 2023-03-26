/**
 * 
 */
package main.java.elevator;

import java.io.IOException;

/**
 * @author Zakaria Ismail
 *
 */
public class RequestListenerTask implements Runnable  {
	private ElevatorSubsystem elevatorSubsystem;
	
	public RequestListenerTask(ElevatorSubsystem es) {
		elevatorSubsystem = es;
	}

	@Override
	public void run() {
		while (true) {
			// keep getting requests from scheduler and feeding to subsystem
			// get elevator request
			try {
				System.out.println("listening...");
				elevatorSubsystem.receiveElevatorRequest();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	
}
