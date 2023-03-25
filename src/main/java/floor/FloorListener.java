/**
 * 
 */
package main.java.floor;

import java.net.DatagramPacket;

import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.elevator.ElevatorSync;

/**
 * The class responsible for listening to arriving elevators
 * @author Hussein El Mokdad
 * @since 1.0, 03/25/23
 * @version 1.0, 03/25/23
 *
 */
public class FloorListener implements Runnable {
	private int floorNumber;
	private UDP udp;
	
	/**
	 * The constructor for FloorListener
	 * @param the int of the floor number
	 * @param port the int of the port that the datagram socket will listen on
	 */
	public FloorListener(int floorNumber, int port) {
		this.floorNumber = floorNumber;
		udp = new UDP();
		udp.openSocket(port);
	} 

	@Override
	public void run() {
		try {
			while (true) {
				DatagramPacket arrivedAtFloorPacket = udp.receivePacket();
				System.out.println(new String(arrivedAtFloorPacket.getData()).trim() + " " + floorNumber);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("--------- Program terminated ---------");
		}	
	}
	
	
	
}
