/**
 * 
 */
package main.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * @author Zakaria Ismail
 *
 */
public class UDPClient {
	private static final int BUF_SIZE = 100;
	private DatagramSocket socket;
	
	public UDPClient() {
		// no port specified
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public UDPClient(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void close() {
		socket.close();
	}
	
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
	
	public static byte[] readPacketData(DatagramPacket packet) {
		byte[] receiveData;
		receiveData = new byte[packet.getLength()];
		System.arraycopy(packet.getData(), packet.getOffset(), receiveData, 0, packet.getLength());
		return receiveData;
	}
	
	public static String formatPacketData(DatagramPacket packet) {
		byte[] data = UDPClient.readPacketData(packet);
		data = new byte[packet.getLength()];
		return String.format("%s (%s)", Arrays.toString(data), new String());
	}
}
