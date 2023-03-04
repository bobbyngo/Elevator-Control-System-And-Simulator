package main.java.floor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.floor.parser.Parser;
import main.java.scheduler.Scheduler;

/**
 * The class that holds information about a floor and initiates requests 
 * to the scheduler for users wanting to travel up or down
 * @author Hussein El Mokdad
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class Floor implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private int floorNumber;
	private Scheduler scheduler; 
	private Parser parser;
	
	/**
	 * Constructor for the Floor class.
	 * @param floorNumber int, the floor number
	 * @param scheduler Scheduler, the scheduler obj
	 * @param parser	 Parser, parser obj to read the text file input
	 */
	public Floor(int floorNumber, Scheduler scheduler, Parser parser) {
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
		this.parser = parser;
		logger.setLevel(Level.INFO);
	}
	
	/**
	 * Get the floor number.
	 * @return int, the floor number
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	
	/**
	 * Request an elevator from the Scheduler.
	 * @param request ElevatorRequest, user requested Elevator
	 * @author Zakaria Ismail
	 */
	public void requestElevator(ElevatorRequest request) {
		scheduler.putRequest(request);
		String loggerStr = String.format("Request elevator: %s \n", request.toString());
		logger.info(loggerStr);
	}
	
	/**
	 * Notify scheduler that request has been completed.
	 * @return completedRequest, ElevatorRequest completed
	 */
	public ElevatorRequest receiveCompletedRequest() {
		ElevatorRequest completedRequest = scheduler.getCompletedRequest();
		String loggerStr = String.format("Elevator completed request: %s", completedRequest.toString());
		logger.info(loggerStr);
		return completedRequest;
	}

	/**
	 * Floor override run() method. 
	 * Parses all elevator requests from the input file and 
	 * sends ElevatorRequest objects to the Scheduler.
	 * @see java.lang.Runnable#run()
	 * @author Zakaria Ismail
	 */
	@Override
	public void run() {
		ArrayList<ElevatorRequest> elevatorRequests = null;
		
		try {
			System.out.println("-------------------------- Parsing user requests ------------------------- \n");
			elevatorRequests = parser.requestParser();
		} catch (IOException e) {
			logger.severe("IOException occurred");
			System.exit(1);
		}
		
		if (!elevatorRequests.isEmpty()) {
			System.out.println("------------------------ Adding requests to queue ------------------------ \n");
			for (ElevatorRequest req : elevatorRequests) {
				requestElevator(req);
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {}
			}
		}
		// End process when all requests have been served?
		logger.info("All requests have been sent.");
		System.exit(0); // This is terminates the program even if the elevators are still serving the requests. Need to fix
		return;
	}
	
}
