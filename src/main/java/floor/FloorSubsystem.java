package main.java.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.ElevatorRequest;
import main.java.floor.parser.Parser;

/**
 * The class that holds information about a floor and initiates requests 
 * to the scheduler for users wanting to travel up or down
 * @author Hussein El Mokdad
 * @since 1.0, 02/04/23
 * @version 4.0, 04/1/23
 */
public class FloorSubsystem implements Runnable {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final File file = new File("/src/main/resources/input.txt");
	private SimulatorConfiguration simulatorConfiguration;
	private Parser parser;
	private UDPClient udpRequestReceiver;
	
	/**
	 * Constructor for the FloorSubsystem class.
	 */
	public FloorSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		udpRequestReceiver = new UDPClient(simulatorConfiguration.FLOOR_SUBSYSTEM_REQ_PORT);
		logger.setLevel(Level.INFO);
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
	}
	
	/**
	 * Floor override run() method.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			ArrayList<ElevatorRequest> elevatorRequests = getElevatorRequests();
			addRequestsToQueue(elevatorRequests);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("Program terminated.");
		}
	}
	
	/**
	 * Sends the series of elevator requests to the SchedulerOld.
	 * @param elevatorRequests
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void addRequestsToQueue(ArrayList<ElevatorRequest> elevatorRequests) throws IOException, InterruptedException {
		if (!elevatorRequests.isEmpty()) {
			for (ElevatorRequest req : elevatorRequests) {
				byte[] data = req.encode();
				udpRequestReceiver.sendMessage(data, simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
				System.out.println("--------------------------------------");
				Thread.sleep(10000);
			}
		}
	}

	/**
	 * Parse user requests.
	 * @return elevatorRequests ArrayList<>, a list of elevator requests
	 */
	private ArrayList<ElevatorRequest> getElevatorRequests() {
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
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatorConfiguration configuration;
		FloorSubsystem floorSubsystem;
		Thread floorSubsystemThread;
		
		configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		floorSubsystem = new FloorSubsystem(configuration);
		floorSubsystemThread = new Thread(floorSubsystem);
		floorSubsystemThread.start();
	}
	
}
