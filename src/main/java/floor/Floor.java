/**
 * 
 */
package main.java.floor;

import java.util.ArrayList;
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
	private int floorNumber;
	private FloorButton button1;
	private FloorButton button2;
	private Scheduler scheduler; 
	
	/**
	 * Constructor for the Floor class
	 * 
	 * @param floorNumber the int of the floor number
	 * @param scheduler the scheduler of type Scheduler
	 * @param button1 one of the buttons on the floor of type FloorButton
	 * @param button2 the other button on the floor (for floors between the ground floor and the last floor)
	 *                of type FloorButton
	 */
	public Floor(int floorNumber, Scheduler scheduler, FloorButton button1, FloorButton button2) {
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
		this.button1 = button1;
		this.button2 = button2;
	}
	
	/**
	 * Constructor for the Floor class
	 * 
	 * @param floorNumber the int of the floor number
	 * @param scheduler the scheduler of type Scheduler
	 * @param button1 one of the buttons on the floor of type FloorButton
	 */
	public Floor(int floorNumber, Scheduler scheduler, FloorButton button1) {
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
		this.button1 = button1;
	}
	
	/**
	 * Gets the floor number
	 * @return the int of the floor number
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	
	/**
	 * Gets one of the floor buttons
	 * @return the button of type FloorButton
	 */
	public FloorButton getButton1() {
		return button1;
	}
	
	/**
	 * Gets one of the floor buttons
	 * @return the button of type FloorButton
	 */
	public FloorButton getButton2() {
		return button2;
	}
	
	/**
	 * Requests an elevator
	 * @param request the request made of type ElevatorRequest
	 */
	public void requestElevator(ElevatorRequest request) {
		// To be implemented when the scheduler works
	}
}
