package main.java.floor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
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
		if (!elevatorRequests.isEmpty()) {
			// TODO: Send request as per time stamp logic potential added here
			// Sends all the request for Floors at serially
			for (ElevatorRequest req : elevatorRequests) {
				byte[] data = EncodeDecode.encodeData(req);
				udp.sendPacket(data, FLOOR_PORT);
				System.out.println("--------------------------------------");
			}
		}
	}
	
	/**
	 * Receives the completed requests from the scheduler
	 * @param elevatorRequests the ElevatorRequest array list
	 */
	private void receiveCompletedRequests(ArrayList<ElevatorRequest> elevatorRequests) {
		for (ElevatorRequest req : elevatorRequests) {
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
