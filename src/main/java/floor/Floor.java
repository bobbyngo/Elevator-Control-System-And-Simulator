package main.java.floor;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
import main.java.dto.RPC;
import main.java.floor.parser.Parser;

/**
 * The class that holds information about a floor and initiates requests 
 * to the scheduler for users wanting to travel up or down
 * @author Hussein El Mokdad
 * @since 1.0, 02/04/23
 * @version 3.0, 03/11/23
 */
public class Floor implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private static final String FILENAME = "./src/main/resources/input.txt";
	private static final int FLOOR_PORT = 23;
	
	private int floorNumber;
	private Parser parser;
	private RPC rpc;
	
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
		rpc = new RPC();
		logger.setLevel(Level.INFO);
		try {
			this.parser = new Parser(FILENAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Floor override run() method.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			rpc.openSocket();
			ArrayList<ElevatorRequest> elevatorRequests = getElevatorRequests();
			addRequestToQueue(elevatorRequests);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rpc.closeSocket();
			logger.info("Program terminated.");
		}
	}
	
	/**
	 * Sends the series of elevator requests to the Scheduler.
	 * @param elevatorRequests
	 */
	private void addRequestToQueue(ArrayList<ElevatorRequest> elevatorRequests) {
		if (!elevatorRequests.isEmpty()) {
			// TODO: Send request as per time stamp logic potential added here
			// Sends all the request for Floors at serially
			for (ElevatorRequest req : elevatorRequests) {
				byte[] data = encodeData(req);
				DatagramPacket reply = rpc.floorSendReceive(data, FLOOR_PORT);
				rpc.floorAck(reply);
				System.out.println("--------------------------------------");
			}
		}
	}
	
	/**
	 * Encodes an elevator request object into a byte[] data.
	 * @param elevatorRequest, ElevatorRequest obj
	 * @return message byte[], the encoded elevatorRequest
	 * @throws IOException
	 */
	private byte[] encodeData(ElevatorRequest elevatorRequest) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] message = null;
		try {
			os.write(elevatorRequest.toString().getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		message = os.toByteArray();
		return message;
	}
	
	/**
	 * Get the floor number.
	 * @return int, floor number
	 */
	public int getFloorNumber() {
		return this.floorNumber;
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
