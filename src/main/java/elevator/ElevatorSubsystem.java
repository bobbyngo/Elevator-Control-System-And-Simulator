package main.java.elevator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import main.java.Config;

/**
 * Sets up the elevator threads
 * @author Hussein Elmokdad
 * @since 1.0, 03/18/23
 * @version 1.0, 03/18/23
 */
public class ElevatorSubsystem {
	
	/**
	 * Main method for the ElevatorSubsystem class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		for (int i = 0; i < Config.numOfElevators; i++) {
			ElevatorSync elevatorSync = new ElevatorSync(i + 1);
			ElevatorFunctionality elevatorFunctionality = new ElevatorFunctionality(i + 1, elevatorSync, Config.elevatorFuncPorts[i]);
			ElevatorListener elevatorListener = new ElevatorListener(elevatorSync, Config.elevatorListenerPorts[i]);
			Thread elevatorFuncThread = new Thread(elevatorFunctionality);
			Thread elevatorListThread = new Thread(elevatorListener);
			elevatorFuncThread.start();
			elevatorListThread.start();
		}
	}
}
