/**
 * 
 */
package main.java.floor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.parser.Parser;
import main.java.scheduler.Scheduler;

/**
 * The class that holds information about a floor
 * and initiates requests to the scheduler for 
 * users wanting to travel up or down
 * 
 * @author Hussein El Mokdad, 101171490
 */
public class Floor implements Runnable {
	
	private static final Logger logger = Logger.getLogger(Floor.class.getName());
	
	private int floorNumber;
	private Scheduler scheduler; 
	private Parser parser;
	
	/**
	 * Constructor for the Floor class
	 * 
	 * @param floorNumber 	int, the floor number
	 * @param scheduler 	Scheduler, the scheduler obj
	 * @param parser		Parser, parser obj to read the text file input
	 */
	public Floor(int floorNumber, Scheduler scheduler, Parser parser) {
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
		this.parser = parser;
	}
	
	/**
	 * Gets the floor number
	 * @return the int of the floor number
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	
	/**
	 * Requests an elevator from the Scheduler
	 * @param request	ElevatorRequest, user request for an Elevator
	 * @author Zakaria Ismail, 101143497
	 */
	public void requestElevator(ElevatorRequest request) {
		logger.info("Requesting an elevator: " + request.toString());
		scheduler.putRequest(request);
	}

	/**
	 * Floor run() method. Parses all elevator requests from the input
	 * file and sends ElevatorRequest objects to the Scheduler.
	 * @author Zakaria Ismail, 101143497
	 */
	@Override
	public void run() {
		ArrayList<ElevatorRequest> elevatorRequests = null;
		
		try {
			elevatorRequests = parser.requestParser();
		} catch (IOException e) {
			logger.severe("An IOException occurred.");
			System.exit(1);
		}
		
		if (!elevatorRequests.isEmpty()) {
			for (ElevatorRequest req : elevatorRequests) {
				requestElevator(req);
			}
		}
		
		// End process when all requests have been served?
		logger.info("All requests have been sent.");
		return;
	}
}