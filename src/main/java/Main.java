package main.java;

import javax.swing.JOptionPane;

import main.java.elevator.ElevatorSubsystem;
import main.java.floor.Floor;
import main.java.floor.FloorSubsystem;
import main.java.gui.GUI;
import main.java.scheduler.SchedulerSubsystem;

/**
 * @author Trong Nguyen
 * @since 03/04/23
 */
public class Main {
	
	public static void main(String[] args) {
		
		SimulatorConfiguration configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		
		SchedulerSubsystem schedulerSubsystem = new SchedulerSubsystem(configuration);
		ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(configuration);
		FloorSubsystem floorSubsystem = new FloorSubsystem(configuration);
		GUI gui = new GUI(configuration);
		
		Thread schedulerThread = new Thread(schedulerSubsystem);
		Thread floorThread = new Thread(floorSubsystem);
		Thread elevatorThread = new Thread(elevatorSubsystem);
		
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();
		gui.displayGUI();
	}
}
