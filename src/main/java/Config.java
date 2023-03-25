package main.java;

import java.net.InetAddress;

/**
 * Contains the configuration information for the subsystems
 * @version 3.0, 03/11/23
 */
public class Config {
	// Elevator ---------------------------------------------------------------------------------------------------------
	public final static int[] elevatorFuncPorts = {6069, 6070}; // Ports for all the elevator functionality threads
	public final static int[] elevatorListenerPorts = {5069, 5070}; // Ports for all the elevator listener threads
	public final static int numOfElevators = elevatorListenerPorts.length;
	public final static String elevatorSubsystemIP = "127.0.0.1";
	// Scheduler --------------------------------------------------------------------------------------------------------
	public static final int scheduler_elevator_port = 69; // // Designated port for receiving elevator requests
	public static final int scheduler_elevator_data_port = 70; // Designated port for receiving elevator data (floor number, state, direction)
	public static final int scheduler_floor_port = 23; // Designated port for receiving floor requests
	public final static String schedulerSubsystemIP = "127.0.0.1";
	// Floor ------------------------------------------------------------------------------------------------------------
	public final static int[] floorFuncPorts = {8069, 8070}; // Ports for all the floor functionality threads
	public final static int[] floorListenerPorts = {7069, 7070}; // Ports for all the floor listener threads
	public final static int numOfFloors = floorListenerPorts.length;
	public final static String floorSubsystemIP = "127.0.0.1";
	
	
	

}