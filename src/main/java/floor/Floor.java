package main.java.floor;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.ElevatorRequest;
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
	private DatagramSocket dataSocket, ackSocket;
	
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
			dataSocket = new DatagramSocket();
			ackSocket = new DatagramSocket();
			ArrayList<ElevatorRequest> elevatorRequests = getElevatorRequests();
			addRequestToQueue(elevatorRequests);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dataSocket.close();
			ackSocket.close();
			logger.info("Program terminated.");
		}
	}
	
	/**
	 * 
	 * Sends the series of elevator requests to the Scheduler.
	 * @param elevatorRequests
	 */
	private void addRequestToQueue(ArrayList<ElevatorRequest> elevatorRequests) {
		if (!elevatorRequests.isEmpty()) {
			// TODO: Send request as per time stamp logic potential added here
			// Sends all the request for all Floors at once - OK for now
			for (ElevatorRequest req : elevatorRequests) {
				byte[] data = encodeData(req);
				sendData(data);
				DatagramPacket reply = receive();
				send(reply);
				receiveAck();
				System.out.println("--------------------------------------");
			}
		}
	}
	
	/**
	 * Send data to destination port.
	 * @param data byte[], parsed floor requests
	 */
	private void sendData(byte[] data) {
		try {
			DatagramPacket dataPacket = new DatagramPacket(
					data, 
					data.length, 
					InetAddress.getLocalHost(), 
					FLOOR_PORT);
			dataSocket.send(dataPacket);
			printPacketContent(dataPacket, "send(:request) -> Scheduler");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive reply from sender.
	 * @return DatagramPacket, message received from sender
	 */
	private DatagramPacket receive() {
		byte[] data = new byte[100];
		DatagramPacket replyPacket = null;
		try {
			replyPacket = new DatagramPacket(data, data.length);
			System.out.println(this.getClass().getName() + ": Waiting...\n");
			dataSocket.receive(replyPacket);
			printPacketContent(replyPacket, "reply() <- Scheduler");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return replyPacket;
	}
	
	/**
	 * Request acknowledge from Scheduler.
	 * @param replyPacket DatagramPacket, message containing request for ack
	 */
	private void send(DatagramPacket replyPacket) {
		byte[] data = (this.getClass().getName() + ": Waiting for ack").getBytes();
		try {
			DatagramPacket ackPacket = new DatagramPacket(
					data, 
					data.length, 
					replyPacket.getAddress(), 
					replyPacket.getPort());
			ackSocket.send(ackPacket);
			printPacketContent(ackPacket, "send() -> Scheduler");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive acknowledge request from Scheduler.
	 */
	private void receiveAck() {
		byte[] data = new byte[100];
		DatagramPacket ackPacket = new DatagramPacket(data, data.length);
		try {
			System.out.println(this.getClass().getName() + ": Waiting...\n");
			ackSocket.receive(ackPacket);
			printPacketContent(ackPacket, "reply(:ack) <- Scheduler");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
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
	 * Prints out the information it has put in the packet 
	 * both as a String and as bytes. 
	 * @param packet, DatagramPacket
	 * @param direction, String (i.e. received or sending)
	 */
	private void printPacketContent(DatagramPacket packet, String direction) {
		System.out.println(this.getClass().getName() + ": " + direction);
	    System.out.println("Address: " + packet.getAddress());
	    System.out.println("Port: " + packet.getPort());
	    int len = packet.getLength();
	    System.out.println("Length: " + packet.getLength());
	    System.out.print("Containing: ");
	    String packetStr = new String(packet.getData(), 0, len);
	    System.out.println(packetStr + "\n");
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e ) {
	        e.printStackTrace();
	        System.exit(1);
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
