/**
 * 
 */
package main.java.elevator;

import main.java.scheduler.Scheduler;

/**
 * Sets up the elevator threads
 * @author Hussein Elmokdad
 * @since 1.0, 03/18/23
 * @version 1.0, 03/18/23
 */
public class ElevatorSubsystem {
	
	public final static int[] elevatorPorts = {5050}; // Ports for all the elevator listener threads
	
	/**
	 * Main method for the ElevatorSubsystem class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		ElevatorSync elevatorSync = new ElevatorSync(1);
		ElevatorFunctionality elevatorFunctionality = new ElevatorFunctionality(1, scheduler, elevatorSync);
		ElevatorListener elevatorListener = new ElevatorListener(elevatorSync, elevatorPorts[0]);
		Thread elevatorFuncThread = new Thread(elevatorFunctionality);
		Thread elevatorListThread = new Thread(elevatorListener);
		elevatorFuncThread.start();
		elevatorListThread.start();
	}
}
