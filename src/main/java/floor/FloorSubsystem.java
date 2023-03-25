/**
 * 
 */
package main.java.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import main.java.Config;
import main.java.dto.ElevatorRequest;
import main.java.floor.parser.Parser;

/**
 * Initializes the floor threads
 * 
 * @author Hussein Elmokdad
 * @since 1.0, 03/25/23
 * @version 1.0, 03/25/23
 */
public class FloorSubsystem {
	Parser parser;
	private final File file = new File("/src/main/resources/input.txt");
	public ArrayList<ElevatorRequest> elevatorRequests;
	
	public FloorSubsystem() {
		try {
			// Filename before compilation
			String FILENAME = System.getProperty("user.dir") + file.getPath();
			this.parser = new Parser(FILENAME);
		} catch (FileNotFoundException e) {}
		try {
			// Filename after compilation
			String FILENAME = System.getProperty("user.dir") + file.getPath().substring(4);
			this.parser = new Parser(FILENAME);
		} catch (FileNotFoundException e) {}
		elevatorRequests = parseElevatorRequests();
	}
	
	public ArrayList<ElevatorRequest> getElevatorRequestsArr() {
		return elevatorRequests;
	}
	
	/**
	 * Parse user requests.
	 * @return elevatorRequests ArrayList<>, a list of elevator requests
	 */
	public ArrayList<ElevatorRequest> parseElevatorRequests() {
		ArrayList<ElevatorRequest> elevatorRequests = null;
		try {
			elevatorRequests = parser.requestParser();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return elevatorRequests;
	}
	
	/**
	 * Main method for the FloorSubsystem class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		FloorSubsystem floorSubsystem = new FloorSubsystem();
		for (int i = 0; i < Config.numOfFloors; i++) {
			FloorFunctionality floorFunctionality = new FloorFunctionality(floorSubsystem, i + 1, Config.floorFuncPorts[i]);
			FloorListener floorListener = new FloorListener(i + 1, Config.floorListenerPorts[i]);
			Thread floorFuncThread = new Thread(floorFunctionality);
			Thread floorListThread = new Thread(floorListener);
			floorFuncThread.start();
			floorListThread.start();
		}
	}
}
