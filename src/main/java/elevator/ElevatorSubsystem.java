/**
 * 
 */
package main.java.elevator;

import java.util.ArrayList;

import main.java.SimulatorConfiguration;

/**
 * Controller. Routes requests to respective elevators.
 * Handles communication aspect.
 * @author Zakaria Ismail
 *
 */
public class ElevatorSubsystem {
	private ArrayList<ElevatorContext> elevators;
	private SimulatorConfiguration simulatorConfiguration;
	//private Thread 
	//private UDPClient udpClient;
	
	public ElevatorSubsystem(SimulatorConfiguration config) {
		ElevatorContext elevator;
		
		elevators = new ArrayList<>();
		simulatorConfiguration = config;
		
		// 0-index elevator identification
		for (int i=0; i<simulatorConfiguration.NUM_ELEVATORS; i++) {
			elevator = new ElevatorContext(this, i);
			elevator.startElevator();
			elevators.add(elevator);
		}
	}
	
	public SimulatorConfiguration getConfig() {
		return simulatorConfiguration;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatorConfiguration configuration;
		ElevatorSubsystem subsystem;
		
		configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		subsystem = new ElevatorSubsystem(configuration);
	}

}
