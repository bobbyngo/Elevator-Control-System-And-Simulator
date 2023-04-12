package main.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Takes the values of the config.properties files and creates variable
 * instances.
 * 
 * @author Zakaria Ismail
 */
public class SimulatorConfiguration {
	// node topology configuration
	public final int NUM_ELEVATORS;
	public final int NUM_FLOORS;

	public final String INPUT_PATH;

	// times in ms
	public final int DOORS_OPEN_TIME;
	public final int DOORS_CLOSE_TIME;
	public final int LOADING_TIME;
	public final int MOVING_TIME;
	public final int DOORS_OBSTRUCTED_TIME;

	// SchedulerOld and SchedulerSubsystem config
	public final String SCHEDULER_HOST;
	public final int SCHEDULER_PENDING_REQ_PORT;
	public final int SCHEDULER_ARRIVAL_REQ_PORT;
	public final int SCHEDULER_COMPLETED_REQ_PORT;

	// Elevator config
	public final String ELEVATOR_SUBSYSTEM_HOST;
	public final int ELEVATOR_SUBSYSTEM_REQ_PORT;

	// Floor config
	public final String FLOOR_SUBSYSTEM_HOST;
	public final int FLOOR_SUBSYSTEM_COMPLETED_REQ_PORT;
	public final int FLOOR_SUBSYSTEM_ARRIVAL_REQ_PORT;

	// GUI config
	public final String GUI_HOST;
	public final int GUI_FLOOR_DTO_PORT;
	public final int GUI_ELEVATOR_DTO_PORT;

	// Test config, disables automataic state transitions via clock timeout
	public final boolean TEST_MODE;

	/**
	 * Constructor for the simulator configuration.
	 * 
	 * @param configFilePath String, file path name
	 */
	public SimulatorConfiguration(String configFilePath) {
		FileInputStream propsInput;
		Properties prop = null;

		try {
			propsInput = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(propsInput);
		} catch (IOException e) {
			System.out.println("Failed to load config.");
			System.exit(0);
		}

		NUM_ELEVATORS = Integer.parseInt(prop.getProperty("NUM_ELEVATORS"));
		NUM_FLOORS = Integer.parseInt(prop.getProperty("NUM_FLOORS"));

		INPUT_PATH = prop.getProperty("INPUT_PATH");

		DOORS_OPEN_TIME = Integer.parseInt(prop.getProperty("DOORS_OPEN_TIME"));
		DOORS_CLOSE_TIME = Integer.parseInt(prop.getProperty("DOORS_CLOSE_TIME"));
		LOADING_TIME = Integer.parseInt(prop.getProperty("LOADING_TIME"));
		MOVING_TIME = Integer.parseInt(prop.getProperty("MOVING_TIME"));
		DOORS_OBSTRUCTED_TIME = Integer.parseInt(prop.getProperty("DOORS_OBSTRUCTED_TIME"));

		SCHEDULER_HOST = prop.getProperty("SCHEDULER_HOST");
		SCHEDULER_PENDING_REQ_PORT = Integer.parseInt(prop.getProperty("SCHEDULER_PENDING_REQ_PORT"));
		SCHEDULER_ARRIVAL_REQ_PORT = Integer.parseInt(prop.getProperty("SCHEDULER_ARRIVAL_REQ_PORT"));
		SCHEDULER_COMPLETED_REQ_PORT = Integer.parseInt(prop.getProperty("SCHEDULER_COMPLETED_REQ_PORT"));

		ELEVATOR_SUBSYSTEM_HOST = prop.getProperty("ELEVATOR_SUBSYSTEM_HOST");
		ELEVATOR_SUBSYSTEM_REQ_PORT = Integer.parseInt(prop.getProperty("ELEVATOR_SUBSYSTEM_REQ_PORT"));

		FLOOR_SUBSYSTEM_HOST = prop.getProperty("FLOOR_SUBSYSTEM_HOST");
		FLOOR_SUBSYSTEM_COMPLETED_REQ_PORT = Integer.parseInt(prop.getProperty("FLOOR_SUBSYSTEM_COMPLETED_REQ_PORT"));
		FLOOR_SUBSYSTEM_ARRIVAL_REQ_PORT = Integer.parseInt(prop.getProperty("FLOOR_SUBSYSTEM_ARRIVAL_REQ_PORT"));

		GUI_HOST = prop.getProperty("GUI_HOST");
		GUI_FLOOR_DTO_PORT = Integer.parseInt(prop.getProperty("GUI_FLOOR_DTO_PORT"));
		GUI_ELEVATOR_DTO_PORT = Integer.parseInt(prop.getProperty("GUI_ELEVATOR_DTO_PORT"));

		TEST_MODE = Boolean.parseBoolean(prop.getProperty("TEST_MODE", "false"));
	}

}
