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
	
	public final static int[] elevatorFuncPorts = {6069}; // Ports for all the elevator functionality threads
	public final static int[] elevatorListenerPorts = {5069}; // Ports for all the elevator listener threads
	public final static int numOfElevators = elevatorListenerPorts.length;
	
	/**
	 * Main method for the ElevatorSubsystem class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		for (int i = 0; i < numOfElevators; i++) {
			ElevatorSync elevatorSync = new ElevatorSync(i + 1);
			ElevatorFunctionality elevatorFunctionality = new ElevatorFunctionality(i + 1, elevatorSync, elevatorFuncPorts[i]);
			ElevatorListener elevatorListener = new ElevatorListener(elevatorSync, elevatorListenerPorts[i]);
			Thread elevatorFuncThread = new Thread(elevatorFunctionality);
			Thread elevatorListThread = new Thread(elevatorListener);
			elevatorFuncThread.start();
			elevatorListThread.start();
		}
	}
}
