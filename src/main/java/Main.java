package main.java;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import main.java.elevator.Elevator;
import main.java.floor.Floor;
import main.java.parser.Parser;
import main.java.scheduler.Scheduler;

/**
 * Main class for application execution.
 * @author Zakaria Ismail
 * @version 1.0, 02/04/23
 */
public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	/**
	 * Main method for program execution.
	 * @param args	String[], default parameters 
	 */
	public static void main(String[] args) {
		// Initialize objects
		String filename = "./src/main/resources/input.txt";
		Scheduler scheduler;
		Floor floor;
		Elevator elevator;
		Parser parser = null;
		Thread schedulerThread, floorThread, elevatorThread;	
		
		try {
			parser = new Parser(filename);
		} catch (FileNotFoundException e) {
			logger.severe(String.format("Input file %s not found. Exiting program.", filename));
			System.exit(1);
		}
		
		// Define objects
		scheduler = new Scheduler();
		floor = new Floor(1, scheduler, parser);
		elevator = new Elevator(1, scheduler);
		
		// Define threads
		schedulerThread = new Thread(scheduler, "Thread-Scheduler");
		floorThread = new Thread(floor, "Thread-Floor");
		elevatorThread = new Thread(elevator, "Thread-Elevator");
		
		// Start threads
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();
		
		return;
	}

}
