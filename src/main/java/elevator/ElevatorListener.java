/**
 * 
 */
package main.java.elevator;

import java.net.DatagramPacket;

import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;

/**
 * Responsible for receiving incoming requests from the scheduler and adding them 
 * to the elevator requests queue for ElevatorFunctionality to use
 * @author Hussein Elmokdad
 * @since 1.0, 03/18/23
 * @version 1.0, 03/18/23
 */
public class ElevatorListener implements Runnable{
	
	private ElevatorSync elevatorSync;
	private UDP udp;
	
	/**
	 * The constructor for ElevatorListener
	 * @param port the int that the datagram socket will listen on
	 * @param elevatorSync the ELevatorSync object that provides the methods 
	 * for adding requests to the elevator requests queue
	 */
	public ElevatorListener(ElevatorSync elevatorSync, int port) {
		this.elevatorSync = elevatorSync;
		udp = new UDP();
		udp.openSocket(port);
	}

	@Override
	public void run() {
		while (true) {
			DatagramPacket elevatorRequestPacket = udp.receivePacket();
			ElevatorRequest elevatorRequest = EncodeDecode.decodeData(elevatorRequestPacket);
			elevatorSync.addElevatorRequest(elevatorRequest);
		}
	}
	
}
