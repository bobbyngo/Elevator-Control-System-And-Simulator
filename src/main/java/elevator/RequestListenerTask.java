/**
 * 
 */
package main.java.elevator;

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
			elevatorSubsystem.receiveElevatorRequest();
		}
	}
	
	
}
