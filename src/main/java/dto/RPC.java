package main.java.dto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class contains methods used for remote procedure calls using UDP.
 * @author Trong Nguyen
 * @version 1.0, 03/11/23
 * @since 1.0, 03/11/23
 */
public class RPC {
	
	private static final int FLOOR_PORT = 23;
	private static final int ELEVATOR_PORT = 69;
	private DatagramSocket dataSocket, ackSocket, floorSocket, elevatorSocket;
	
	/**
	 * Open DatatgramSockets.
	 */
	public void openSocket() {
		try {
			dataSocket = new DatagramSocket();
			ackSocket = new DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open DatagramSockets for SchedulerOld.
	 */
	public void openSchedulerSocket() {
		try {
			floorSocket = new DatagramSocket(FLOOR_PORT);
			elevatorSocket = new DatagramSocket(ELEVATOR_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close DatagramSockets.
	 */
	public void closeSocket() {
		dataSocket.close();
		ackSocket.close();
	}
	
	/**
	 * Close DatagramSockets for SchedulerOld.
	 */
	public void closeSchedulerSocket() {
		floorSocket.close();
		elevatorSocket.close();
	}
	
	/**
	 * Send and receive remote procedure call for Floor.
	 * @param data byte[], data sent
	 * @param port int, Floor port number
	 * @return DatagramPacket, reply message
	 */
	public DatagramPacket floorSendReceive(byte[] data, int port) {
		floorSendData(data, port);
		return floorReceive();
	}
	
	/**
	 * Acknowledge Floor remote procedure call.
	 * @param replyPacket DatagramPacket, reply message
	 */
	public void floorAck(DatagramPacket replyPacket) {
		floorSendAck(replyPacket);
		floorReceiveAck();
	}
	
	/**
	 * Send and receive remote procedure call for Elevator.
	 * @param port int, Elevator port number
	 * @return DatagramPacket, reply message
	 */
	public DatagramPacket elevatorSendReceive(int port) {
		elevatorSend(port);
		return elevatorReceiveData();
	}
	
	/**
	 * Acknowledge Elevator remote procedure call.
	 * @param replyPacket DatagramPacket, reply message
	 */
	public void elevatorAck(DatagramPacket replyPacket) {
		elevatorSendAck(replyPacket);
		elevatorReceiveAck();
	}
	
	/**
	 * Send data to destination port.
	 * @param data byte[], parsed floor requests
	 */
	private void floorSendData(byte[] data, int port) {
		try {
			DatagramPacket dataPacket = new DatagramPacket(
					data, 
					data.length, 
					InetAddress.getLocalHost(), 
					port);
			dataSocket.send(dataPacket);
			printPacketContent(dataPacket, "send(:request) -> SchedulerOld");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive reply from sender.
	 * @return DatagramPacket, message received from sender
	 */
	private DatagramPacket floorReceive() {
		byte[] data = new byte[100];
		DatagramPacket replyPacket = null;
		try {
			replyPacket = new DatagramPacket(data, data.length);
			System.out.println("Waiting...\n");
			dataSocket.receive(replyPacket);
			printPacketContent(replyPacket, "reply() <- SchedulerOld");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return replyPacket;
	}
	
	/**
	 * Request acknowledge from SchedulerOld.
	 * @param replyPacket DatagramPacket, message containing request for ack
	 */
	private void floorSendAck(DatagramPacket replyPacket) {
		byte[] data = ("Waiting for ack").getBytes();
		try {
			DatagramPacket ackPacket = new DatagramPacket(
					data, 
					data.length, 
					replyPacket.getAddress(), 
					replyPacket.getPort());
			ackSocket.send(ackPacket);
			printPacketContent(ackPacket, "send() -> SchedulerOld");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive acknowledge request from SchedulerOld.
	 */
	private void floorReceiveAck() {
		byte[] data = new byte[100];
		DatagramPacket ackPacket = new DatagramPacket(data, data.length);
		try {
			System.out.println("Waiting...\n");
			ackSocket.receive(ackPacket);
			printPacketContent(ackPacket, "reply(:ack) <- SchedulerOld");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
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
			floorSocket.receive(receivePacket);
			printPacketContent(receivePacket, "Floor -> send(:request)");
			
			DatagramPacket replyClientPacket = new DatagramPacket(
					receivePacket.getData(), 
					receivePacket.getLength(), 
					receivePacket.getAddress(), 
					receivePacket.getPort());
			floorSocket.send(replyClientPacket);
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
			elevatorSocket.receive(receivePacket);
			printPacketContent(receivePacket, "send() <- Elevator");
			
			DatagramPacket replyPacket = new DatagramPacket(
					reply,
					reply.length, 
					receivePacket.getAddress(), 
					receivePacket.getPort());
			elevatorSocket.send(replyPacket);
			printPacketContent(replyPacket, "reply(:request) -> Elevator");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Acknowledge Elevator request.
	 * @return DatagramPacket, ack packet containing data from Elevator
	 */
	public DatagramPacket ackElevator() {
		byte[] data = new byte[100];
		DatagramPacket ackReceivePacket = null;
		try {
			ackReceivePacket = new DatagramPacket(data, data.length);
			System.out.println("Waiting...\n");
			elevatorSocket.receive(ackReceivePacket);
			printPacketContent(ackReceivePacket, "send(:request) <- Elevator");
			
			DatagramSocket ackSocket = new DatagramSocket();
			DatagramPacket ackReplyPacket = new DatagramPacket(
					ackReceivePacket.getData(),
					ackReceivePacket.getLength(), 
					ackReceivePacket.getAddress(), 
					ackReceivePacket.getPort());
			ackSocket.send(ackReplyPacket);
			printPacketContent(ackReplyPacket, "reply() -> Elevator");
			ackSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return ackReceivePacket;
	}
	
	/**
	 * Acknowledge Floor request.
	 * @param ack DatagramPacket, ack packet containing data from Server
	 */
	public void ackFloor(byte[] ack) {
		byte[] data = new byte[100];
		try {
			DatagramPacket receiveAckPacket = new DatagramPacket(data, data.length);
			System.out.println("Waiting...\n");
			floorSocket.receive(receiveAckPacket);
			printPacketContent(receiveAckPacket, "Floor -> send()");
			
			DatagramSocket ackSocket = new DatagramSocket();
			DatagramPacket replyAckPacket = new DatagramPacket(
					ack, 
					ack.length, 
					receiveAckPacket.getAddress(), 
					receiveAckPacket.getPort());
			ackSocket.send(replyAckPacket);
			printPacketContent(replyAckPacket, "Floor <- reply(:ack)");
			ackSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Send request for data to SchedulerOld.
	 */
	private void elevatorSend(int port) {
		byte[] data = ("Waiting for request...").getBytes();
		try {
			DatagramPacket sendPacket = new DatagramPacket(
					data, 
					data.length, 
					InetAddress.getLocalHost(), 
					port);
			dataSocket.send(sendPacket);
			printPacketContent(sendPacket, "SchedulerOld <- send()");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive data from SchedulerOld.
	 * @return DatagramPacket, elevator requested from SchedulerOld
	 */
	private DatagramPacket elevatorReceiveData() {
		byte[] data = new byte[100];
		DatagramPacket replyPacket = new DatagramPacket(data, data.length);
		try {
			System.out.println("Waiting...\n");
			dataSocket.receive(replyPacket);
			printPacketContent(replyPacket, "SchedulerOld -> reply(:request)");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return replyPacket;
	}	
	
	/**
	 * Send acknowledge to SchedulerOld.
	 * @param receiveHostPacket DatagramPacket, data packet received from request
	 */
	private void elevatorSendAck(DatagramPacket replyPacket) {
		DatagramPacket ackPacket = new DatagramPacket(
				replyPacket.getData(), 
				replyPacket.getLength(), 
				replyPacket.getAddress(), 
				replyPacket.getPort());
		try {
			ackSocket.send(ackPacket);
			printPacketContent(ackPacket, "SchedulerOld <- send(:ack)");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Receive reply to acknowledge from SchedulerOld.
	 */
	private void elevatorReceiveAck() {
		byte[] data = new byte[100];
		DatagramPacket ackPacket = new DatagramPacket(data, data.length);
		try {
			System.out.println("Waiting...\n");
			ackSocket.receive(ackPacket);
			printPacketContent(ackPacket, "SchedulerOld -> reply()");
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
