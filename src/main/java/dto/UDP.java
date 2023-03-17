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
	private DatagramSocket dataSocket; // Each elevator and floor gets a data socket to communicate with the scheduler  
	
	/**
	 * Opens a DatatgramSocket.
	 */
	public void openSocket() {
		try {
			dataSocket = new DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a DatatgramSocket on a specified port.
	 * @param port the int of the port to open the socket on
	 */
	public void openSocket(int port) {
		try {
			dataSocket = new DatagramSocket(port);
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
	 * Sends a packet to a destination port.
	 * @param data the byte[] of the data to send
	 */
	public void sendPacket(byte[] dataByteArr, int port) {
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
	public DatagramPacket receivePacket() {
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