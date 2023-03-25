/**
 * 
 */
package main.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import main.java.dto.AssignedElevatorRequest;
import main.java.elevator.Direction;

/**
 * UDP Client class for sending and receiving requests between Elevator, Scheduler and Floor Subsystem
 * @author Zakaria Ismail
 *
 */
public class UDPClient {
	private static final int BUF_SIZE = 1000;
	private DatagramSocket socket;
	
	/**
	 * Constructor for UDP Client class, for sending datagram socket
	 */
	public UDPClient() {
		// no port specified
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Constructor for UDP Client class, for receiving datagram socket
	 * @param port
	 */
	public UDPClient(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Closing socket method
	 */
	public void close() {
		socket.close();
	}
	
	/**
	 * Method for sending the data
	 * @param data
	 * @param destAddr
	 * @param destPort
	 * @return
	 */
	public DatagramPacket sendMessage(byte[] data, InetAddress destAddr, int destPort) {
		DatagramPacket sendPacket;
		
		sendPacket = new DatagramPacket(data, data.length, destAddr, destPort);
		try {
			socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return sendPacket;
	}
	
	/**
	 * Method for converting the destAddr in String to InetAddress object then send the data
	 * @param data
	 * @param destAddr
	 * @param destPort
	 * @return
	 * @throws UnknownHostException
	 */
	public DatagramPacket sendMessage(byte[] data, String destAddr, int destPort) throws UnknownHostException {
		InetAddress hostAddr = InetAddress.getByName(destAddr);
		DatagramPacket sendPacket;
		sendPacket = sendMessage(data, hostAddr, destPort);
		return sendPacket;
	}
	
	/**
	 * Method for receiving the data
	 * @return DatagramPacket
	 */
	public DatagramPacket receiveMessage() {
		DatagramPacket receivePacket;
		byte[] receiveBuf = new byte[BUF_SIZE];
		
		receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
		try {
			socket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return receivePacket;
	}
	
	/**
	 * Read datagram packet method
	 * @param packet
	 * @return
	 */
	public static byte[] readPacketData(DatagramPacket packet) {
		byte[] receiveData;
		receiveData = new byte[packet.getLength()];
		System.arraycopy(packet.getData(), packet.getOffset(), receiveData, 0, packet.getLength());
		return receiveData;
	}
	
	/**
	 * Formating the datagram 
	 * @param packet
	 * @return
	 */
	public static String formatPacketData(DatagramPacket packet) {
		byte[] data = UDPClient.readPacketData(packet);
		data = new byte[packet.getLength()];
		return String.format("%s (%s)", Arrays.toString(data), new String());
	}
	
	public static void main(String[] args) throws ParseException, UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		int sendPort = 4001;
		int recvPort = 4002;
		UDPClient sender = new UDPClient(sendPort);
		UDPClient receiver = new UDPClient(recvPort);
		AssignedElevatorRequest testObj = new AssignedElevatorRequest(
				1, "07:01:15.000", 3, Direction.UP, 5
			), testObj2;
		DatagramPacket packet;
		byte[] encodedData = testObj.encode();
		System.out.println("sizeof encoded data: " + encodedData.length);
		
		Timer recv = new Timer();
		recv.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					sender.sendMessage(encodedData, InetAddress.getLocalHost(), recvPort);
					System.out.println("sent message!");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			
		},3000);
		System.out.println("waiting for message...");
		packet = receiver.receiveMessage();
		//sender.sendMessage(encodedData, InetAddress.getLocalHost(), 6000);
		System.out.println("received message!");
		System.out.println(new String(packet.getData()));
		testObj2 = AssignedElevatorRequest.decode(UDPClient.readPacketData(packet));
		System.out.println(testObj2);
		sender.close();
		receiver.close();
	}
}
