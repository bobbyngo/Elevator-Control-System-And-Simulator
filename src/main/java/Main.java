/**
 * 
 */
package main.java;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import main.java.elevator.Elevator;
import main.java.floor.Floor;
import main.java.parser.Parser;
import main.java.scheduler.Scheduler;

/**
 * Hosts the starting point of execution for the
 * application.
 * @author Zakaria Ismail, 101143497
 *
 */
public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	/**
	 * Starting point of execution for the application.
	 * @param args	String[], command line arguments 
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
		schedulerThread = new Thread(scheduler, "Scheduler");
		floorThread = new Thread(floor, "Floor");
		elevatorThread = new Thread(elevator, "Elevator");
		
		// Start threads
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();
		
		return;
	}

}
