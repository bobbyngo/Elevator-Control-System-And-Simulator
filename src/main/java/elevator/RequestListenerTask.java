package main.java.elevator;

import java.io.IOException;

/**
 * This class represent a listener request task.
 * 
 * @author Zakaria Ismail
 */
public class RequestListenerTask implements Runnable {
	private ElevatorSubsystem elevatorSubsystem;

	/**
	 * Request listener task constructor.
	 * 
	 * @param elevatorSubsystem ElevatorSubsystem, the elevator subsystem
	 */
	public RequestListenerTask(ElevatorSubsystem elevatorSubsystem) {
		this.elevatorSubsystem = elevatorSubsystem;
	}

	/**
	 * Run method.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			// keep getting requests from scheduler and
			// feeding to subsystem get elevator request
			try {
				System.out.println("Listening...");
				elevatorSubsystem.receiveElevatorRequest();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}
