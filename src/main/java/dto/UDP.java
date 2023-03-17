package main.java.dto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class contains methods for UDP communication between the subsystems.
 * @author Trong Nguyen, Hussein Elmokdad
 * @version 1.1, 03/17/23
 * @since 1.0, 03/11/23
 */
public class UDP {
	
	private static final int FLOOR_PORT = 23; // Designated port for receiving floor requests
	private static final int ELEVATOR_PORT = 69; // Designated port for receiving elevator requests
	private DatagramSocket dataSocket; // Each elevator and floor gets a data socket to communicate with the scheduler 
	private DatagramSocket schedulerSocketE, schedulerSocketF ; // schedulerSocketE: receives requests from the elevators. schedulerSocketF: receives requests from the floors 
	
	/**
	 * Open DatatgramSockets.
	 */
	public void openSocket() {
		try {
			dataSocket = new DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open DatagramSockets for Scheduler.
	 */
	public void openSchedulerSocket() {
		try {
			schedulerSocketF = new DatagramSocket(FLOOR_PORT);
			schedulerSocketE = new DatagramSocket(ELEVATOR_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close DatagramSockets.
	 */
	public void closeSocket() {
		dataSocket.close();
	}
	
	/**
	 * Close DatagramSockets for Scheduler.
	 */
	public void closeSchedulerSocket() {
		schedulerSocketF.close();
		schedulerSocketE.close();
	}
	
	/**
	 * Sends a data packet then receives a response data packet
	 * @param data byte[], the data of the packet to send
	 * @param port int, the port that the packet will be sent to
	 * @return DatagramPacket, the packet of the response received 
	 */
	public DatagramPacket sendReceivePacket(byte[] data, int port) {
		sendPacket(data, port);
		return receivePacket();
	}
	
	/**
	 * Sends a packet to a destination port. Typically used to make a request for data (floor requests)
	 * or to send data (floor requests)
	 * @param data the byte[] of the data to send
	 */
	private void sendPacket(byte[] dataByteArr, int port) {
		try {
			DatagramPacket dataPacket = new DatagramPacket(
					dataByteArr, 
					dataByteArr.length, 
					InetAddress.getLocalHost(), 
					port);
			dataSocket.send(dataPacket);
			printPacketContent(dataPacket, "Sending packet");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive and return a reply packet from a sender.
	 * @return DatagramPacket, message received from sender
	 */
	private DatagramPacket receivePacket() {
		byte[] data = new byte[100];
		DatagramPacket replyPacket = null;
		try {
			replyPacket = new DatagramPacket(data, data.length);
			System.out.println("Waiting...\n");
			dataSocket.receive(replyPacket);
			printPacketContent(replyPacket, "Received reply packet from sender");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return replyPacket;
	}
	
	/**
	 * Reply to Floor.
	 * @return DatagramPacket, containing the same message received
	 */
	public DatagramPacket replyFloor() {
		byte[] data = new byte[100];
		DatagramPacket receivePacket = null;
		try {
			receivePacket = new DatagramPacket(data, data.length);
			System.out.println("Waiting...\n");
			schedulerSocketF.receive(receivePacket);
			printPacketContent(receivePacket, "Floor -> send(:request)");
			
			DatagramPacket replyClientPacket = new DatagramPacket(
					receivePacket.getData(), 
					receivePacket.getLength(), 
					receivePacket.getAddress(), 
					receivePacket.getPort());
			schedulerSocketF.send(replyClientPacket);
			printPacketContent(replyClientPacket, "Floor <- reply()");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return receivePacket;
	}
	
	/**
	 * Reply to Elevator with request.
	 * @param DatagramPacket, containing the request from Floor
	 */
	public void replyElevator(byte[] reply) {
		byte[] data = new byte[100];
		try {
			DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			System.out.println("Waiting...\n");
			schedulerSocketE.receive(receivePacket);
			printPacketContent(receivePacket, "send() <- Elevator");
			
			DatagramPacket replyPacket = new DatagramPacket(
					reply,
					reply.length, 
					receivePacket.getAddress(), 
					receivePacket.getPort());
			schedulerSocketE.send(replyPacket);
			printPacketContent(replyPacket, "reply(:request) -> Elevator");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Prints out the information it has put in the packet 
	 * both as a String and as bytes. 
	 * @param packet, DatagramPacket
	 * @param description, String (i.e. received or sending)
	 */
	private void printPacketContent(DatagramPacket packet, String description) {
		System.err.println(description);
	    System.out.println("Address: " + packet.getAddress());
	    System.out.println("Port: " + packet.getPort());
	    int len = packet.getLength();
	    System.out.println("Length: " + packet.getLength());
	    System.out.print("Containing: ");
	    String packetStr = new String(packet.getData(), 0, len);
	    System.out.println(packetStr + "\n");
	    try {
	        Thread.sleep(500);
	    } catch (InterruptedException e ) {
	        e.printStackTrace();
	        System.exit(1);
	    }
	}
}