package main.java;

import main.java.elevator.Elevator;
import main.java.floor.Floor;
import main.java.scheduler.Scheduler;

/**
 * Main class for application execution.
 * @version 3.0, 03/11/23
 */
public class Main {

	/**
	 * Main method for program execution.
	 * @param args	String[], default parameters 
	 */
	public static void main(String[] args) {
		// Initialize objects
		Scheduler scheduler;
		Floor floor;
		Elevator elevator;
		Thread schedulerThread, floorThread, elevatorThread;	
		
		// Define objects
		scheduler = new Scheduler();
		floor = new Floor(1);
		elevator = new Elevator(1, scheduler);
		
		// Define threads
		schedulerThread = new Thread(scheduler, "Thread-Scheduler");
		floorThread = new Thread(floor, "Thread-Floor");
		elevatorThread = new Thread(elevator, "Thread-Elevator");
		
		// Start threads
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();
	}

}
