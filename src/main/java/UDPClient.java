package main.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * UDP Client class for sending and receiving requests between Elevator,
 * Scheduler and Floor Subsystem.
 * 
 * @author Zakaria Ismail
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
	 * 
	 * @param port int, the port number
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
	 * Closing socket method.
	 */
	public void close() {
		socket.close();
	}

	/**
	 * Method for sending the data.
	 * 
	 * @param data     byte[], the data to be sent
	 * @param destAddr InetAddress, destination address
	 * @param destPort int, destination port number
	 * @return DatagramPacket, sent packet
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
	 * Method for converting the destAddr in String to InetAddress object then send
	 * the data.
	 * 
	 * @param data     byte[], the data to be sent
	 * @param destAddr InetAddress, destination address
	 * @param destPort int, destination port number
	 * @return DatagramPacket, sent packet
	 * @throws UnknownHostException
	 */
	public DatagramPacket sendMessage(byte[] data, String destAddr, int destPort) throws UnknownHostException {
		InetAddress hostAddr = InetAddress.getByName(destAddr);
		DatagramPacket sendPacket;
		sendPacket = sendMessage(data, hostAddr, destPort);
		return sendPacket;
	}

	/**
	 * Method for receiving the data.
	 * 
	 * @return DatagramPacket, received packet
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
	 * Read datagram packet method.
	 * 
	 * @param packet DatagramPacket, data packet
	 * @return byte[], the data
	 */
	public static byte[] readPacketData(DatagramPacket packet) {
		byte[] receiveData;
		receiveData = new byte[packet.getLength()];
		System.arraycopy(packet.getData(), packet.getOffset(), receiveData, 0, packet.getLength());
		return receiveData;
	}

	/**
	 * Formating the datagram packet.
	 * 
	 * @param packet DatagramPacket, the data packet
	 * @return String, the string format of the data packet.
	 */
	public static String formatPacketData(DatagramPacket packet) {
		byte[] data = UDPClient.readPacketData(packet);
		data = new byte[packet.getLength()];
		return String.format("%s (%s)", Arrays.toString(data), new String());
	}
	
}
