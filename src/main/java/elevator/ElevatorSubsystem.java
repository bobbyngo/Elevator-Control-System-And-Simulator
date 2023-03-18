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
	
	public final static int[] elevatorPorts = {5069, 5070}; // Ports for all the elevator listener threads
	
	/**
	 * Main method for the ElevatorSubsystem class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		for (int i = 0; i < elevatorPorts.length; i++) {
			ElevatorSync elevatorSync = new ElevatorSync(i + 1);
			ElevatorFunctionality elevatorFunctionality = new ElevatorFunctionality(i + 1, scheduler, elevatorSync);
			ElevatorListener elevatorListener = new ElevatorListener(elevatorSync, elevatorPorts[i]);
			Thread elevatorFuncThread = new Thread(elevatorFunctionality);
			Thread elevatorListThread = new Thread(elevatorListener);
			elevatorFuncThread.start();
			elevatorListThread.start();
		}
	}
}
