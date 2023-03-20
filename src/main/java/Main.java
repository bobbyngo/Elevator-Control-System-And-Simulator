package main.java;

import java.util.ArrayList;
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
	// Elevator components need the access
	public static final int NUM_OF_FLOORS = 10;
	/*
	public static void main(String[] args) {
		
		Scheduler scheduler;
		Thread schedulerThread, floorThread, elevatorThread;	
		ArrayList<Elevator> elevatorsArr;
		ArrayList<Floor> floorsArr;
		final int NUM_OF_ELEVATORS = 3;

		scheduler = new Scheduler();
		elevatorsArr = new ArrayList<>();
		floorsArr = new ArrayList<>();
		
		for (int i = 0; i < NUM_OF_FLOORS; i++) {
			floorsArr.add(new Floor(i + 1));	
		}
		
		for (int i = 0; i < NUM_OF_ELEVATORS; i++) {
			elevatorsArr.add(new Elevator(i + 1, scheduler));
		}
		
		schedulerThread = new Thread(scheduler, "Thread-Scheduler");
		schedulerThread.start();
		
		// Defines and starts the elevator and floor threads
		for (Floor floor : floorsArr) {
			floorThread = new Thread(floor, "Thread-Floor" + floor.getFloorNumber());
			floorThread.start();
		}
		
		for (Elevator elevator : elevatorsArr) {
			elevatorThread = new Thread(elevator, "Thread-Elevator " + elevator.getElevatorId());
			elevatorThread.start();
		}
	}
*/
}