package main.java.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.floor.parser.Parser;

/**
 * The class that holds information about a floor and initiates requests 
 * to the scheduler for users wanting to travel up or down
 * @author Hussein El Mokdad
 * @since 1.0, 02/04/23
 * @version 3.0, 03/11/23
 */
public class Floor implements Runnable {
	
	private static final int FLOOR_PORT = 23;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final File file = new File("/src/main/resources/input.txt");
	
	private int floorNumber;
	private Parser parser;
	private UDP udp;
	private int eventCounter;

	/**
	 * Main method for the Floor class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		new Thread(new Floor(1)).start();
	}
	
	/**
	 * Constructor for the Floor class.
	 */
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		udp = new UDP();
		logger.setLevel(Level.INFO);
		eventCounter = 0;
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
			udp.openSocket();
			ArrayList<ElevatorRequest> elevatorRequests = getElevatorRequests();
			addRequestToQueue(elevatorRequests);
			receiveCompletedRequests(elevatorRequests);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			udp.closeSocket();
			logger.info("Program terminated.");
		}
	}

	/**
	 * Get the floor number.
	 * @return int, floor number
	 */
	public int getFloorNumber() {
		return this.floorNumber;
	}
	
	
	/**
	 * Sends the series of elevator requests to the Scheduler.
	 * @param elevatorRequests the ElevatorRequest array list
	 */
	private void addRequestToQueue(ArrayList<ElevatorRequest> elevatorRequests) {
		long offset;
		if (!elevatorRequests.isEmpty()) {
			// Send elevator request as per timestamp
			for (int i = 0; i < elevatorRequests.size(); i++) {
				ElevatorRequest req = elevatorRequests.get(i);
				byte[] data = EncodeDecode.encodeData(req);
				if (i < elevatorRequests.size() - 1) {
					Timestamp currentTime = elevatorRequests.get(i).getTimestamp();
					Timestamp nextTime = elevatorRequests.get(i+1).getTimestamp();
					offset = nextTime.getTime() - currentTime.getTime();
					// Send elevator request as per offset from current timestamp and next timestamp
					try {
						Thread.sleep(offset);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					udp.sendPacket(data, FLOOR_PORT);
					System.out.println(String.format("%s: Request for elevator sent %d", this.getClass().getSimpleName(), eventCounter++));
				} else {
					// Sends the last elevator request
					udp.sendPacket(data, FLOOR_PORT);
					System.out.println("All tasks has been completed!");
				}
			}
		}
	}
	
	/**
	 * Receives the completed requests from the scheduler
	 * @param elevatorRequests the ElevatorRequest array list
	 */
	private void receiveCompletedRequests(ArrayList<ElevatorRequest> elevatorRequests) {
		for (int i = 0; i < elevatorRequests.size(); i++) {
			System.out.println("Receiving completed request:");
			udp.receivePacket();
			System.out.println("--------------------------------------");
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
	
}
