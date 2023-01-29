/**
 * 
 */
package main.java.floor;

import java.util.ArrayList;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.scheduler.Scheduler;

/**
 * The class that holds information about a floor
 * and initiates requests to the scheduler for 
 * users wanting to travel up or down
 * 
 * @author Hussein El Mokdad, 101171490
 */
public class Floor {
	
	private static final Logger LOG = Logger.getLogger(Floor.class.getName());
	
	private int floorNumber;
	private Scheduler scheduler; 
	
	/**
	 * Constructor for the Floor class
	 * 
	 * @param floorNumber the int of the floor number
	 * @param scheduler the scheduler of type Scheduler
	 */
	public Floor(int floorNumber, Scheduler scheduler) {
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
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
		LOG.info("Requesting an elevator: " + request.toString());
		scheduler.putRequest(request);
	}
}
