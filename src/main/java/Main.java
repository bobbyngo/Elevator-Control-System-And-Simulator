package main.java;

import java.io.IOException;

import main.java.elevator.ElevatorSubsystem;
import main.java.floor.FloorSubsystem;
import main.java.gui.GUI;
import main.java.scheduler.SchedulerSubsystem;
import main.resources.GenerateEvents;

/**
 * Main execution of the elevator control system and simulator application.
 * @author Trong Nguyen
 */
public class Main {
	
	/**
	 * Main method.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {

		SimulatorConfiguration configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		
		try {
			new GenerateEvents();
			GenerateEvents.generateEvents(configuration);
			Thread.sleep(500);
		} catch (InterruptedException | IOException e) {

		}
		
		SchedulerSubsystem schedulerSubsystem = new SchedulerSubsystem(configuration);
		ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(configuration);
		FloorSubsystem floorSubsystem = new FloorSubsystem(configuration);
		GUI gui = new GUI(configuration);
		
		Thread schedulerThread = new Thread(schedulerSubsystem);
		Thread floorThread = new Thread(floorSubsystem);
		Thread elevatorThread = new Thread(elevatorSubsystem);
		Thread guiThread = new Thread(gui);
		
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();
		guiThread.start();
	}
}
