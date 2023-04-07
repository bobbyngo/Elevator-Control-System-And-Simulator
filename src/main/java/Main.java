package main.java;

import javax.swing.JOptionPane;

import main.java.elevator.ElevatorSubsystem;
import main.java.floor.Floor;
import main.java.scheduler.SchedulerSubsystem;

/**
 * @author Trong Nguyen
 * @since 03/04/23
 */
public class Main {
	
	public static void main(String[] args) {
		
		SimulatorConfiguration configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		int numFloors = 0, numElevators = 0;
		String[] options = {"Default Values", "User Inputs"};
		int popUp = JOptionPane.showOptionDialog(null, "Would you like to input values?", 
				"Confirmation", JOptionPane.INFORMATION_MESSAGE, 0, null, options, options[0]);
		switch(popUp) {
		case -1:
			System.exit(0);
		case 0:
			numFloors = configuration.NUM_FLOORS; //default floors
			numElevators = configuration.NUM_ELEVATORS; //default elevators
			break;
		case 1:
			numElevators = Integer.parseInt(JOptionPane.showInputDialog("How many elevators?"));
			numFloors = Integer.parseInt(JOptionPane.showInputDialog("How many floors?"));
		}
		
		SchedulerSubsystem schedulerSubsystem = new SchedulerSubsystem(configuration);
		ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(configuration, numElevators);
		Floor floorSubsystem = new Floor(numElevators);
		
		Thread schedulerThread = new Thread(schedulerSubsystem);
		Thread floorThread = new Thread(floorSubsystem);
		Thread elevatorThread = new Thread(elevatorSubsystem);
		
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();
	}
}
