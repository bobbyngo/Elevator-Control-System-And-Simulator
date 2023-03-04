package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Logger;

import main.java.elevator.Elevator;
import main.java.floor.Floor;
import main.java.floor.parser.Parser;
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
		ArrayList<Elevator> elevatorsArr;
		ArrayList<Floor> floorsArr;
		final int NUM_OF_ELEVATORS = 3;
		final int NUM_OF_FLOORS = 10;
		Thread schedulerThread;
		
		// Define objects
		scheduler = new Scheduler();
		elevatorsArr = new ArrayList<>();
		floorsArr = new ArrayList<>();
		
		for (int i = 0; i < NUM_OF_FLOORS; i++) {
			// Create a parser for each floor object
			try {
				Parser parser = new Parser(filename);
				floorsArr.add(new Floor(i + 1, scheduler, parser));
			} catch (FileNotFoundException e) {
				logger.severe(String.format("Input file %s not found. Exiting program.", filename));
				System.exit(1);
			}
			
		}
		for (int i = 0; i < NUM_OF_ELEVATORS; i++) {
			elevatorsArr.add(new Elevator(i + 1, scheduler));
		}
		
		// Define threads
		schedulerThread = new Thread(scheduler, "Thread-Scheduler");
		
		// Start threads
		schedulerThread.start();
		
		System.out.println("-------------------------- Parsing user requests ------------------------- \n");
		// Defines and starts the elevator and floor threads
		for (Floor floor : floorsArr) {
			Thread floorThread = new Thread(floor, "Thread-Floor" + floor.getFloorNumber());
			floorThread.start();
		}
		//System.out.println("------------------------ Finished parsing requests ----------------------- \n");
		
		for (Elevator elevator : elevatorsArr) {
			Thread elevatorThread = new Thread(elevator, "Thread-Elevator " + elevator.getElevatorId());
			elevatorThread.start();
		}
	}

}
