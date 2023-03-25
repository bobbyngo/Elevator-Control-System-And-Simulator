package main.java.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.Config;
import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.floor.parser.Parser;

/**
 * The class that holds information about a floor and initiates requests 
 * to the scheduler for users wanting to travel up or down
 * @author Hussein El Mokdad
 * @since 1.0, 02/04/23
 * @version 3.0, 03/11/23
 */
public class FloorFunctionality implements Runnable {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private int floorNumber;
	private FloorSubsystem floorSubsystem;
	private UDP udp;
	private int eventCounter;
	
	/**
	 * Constructor for the Floor class.
	 * @param floorNumber the int of the floor number
	 * @param port the int of the port
	 */
	public FloorFunctionality(FloorSubsystem floorSubsystem,int floorNumber, int port) {
		this.floorNumber = floorNumber;
		this.floorSubsystem = floorSubsystem;
		udp = new UDP();
		logger.setLevel(Level.INFO);
		eventCounter = 0;
	}
	
	/**
	 * Floor override run() method.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			udp.openSocket();
			addRequestToQueue(floorSubsystem.getElevatorRequestsArr());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			udp.closeSocket();
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
	 * Sends the series of elevator requests to the Scheduler.
	 * @param elevatorRequests the ElevatorRequest array list
	 */
	private void addRequestToQueue(ArrayList<ElevatorRequest> elevatorRequests) {
		long offset;
		if (!elevatorRequests.isEmpty()) {
			// Send elevator request as per timestamp
			for (int i = 0; i < elevatorRequests.size(); i++) {
				ElevatorRequest req = elevatorRequests.get(i);
				byte[] data = EncodeDecode.encodeData(req);
				boolean floorsMatch = req.getSourceFloor() == floorNumber; // Checks if the request's source floor matches the floor number for this object
				if (i < elevatorRequests.size() - 1) {
					Timestamp currentTime = elevatorRequests.get(i).getTimestamp();
					Timestamp nextTime = elevatorRequests.get(i+1).getTimestamp();
					offset = nextTime.getTime() - currentTime.getTime();
					if (floorsMatch) {
						udp.sendPacket(data, Config.scheduler_floor_port, Config.schedulerSubsystemIP);
						System.out.println("Sent request from floor " + floorNumber);
					}
					try {
						Thread.sleep(offset);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					// Sends the last elevator request
					if (floorsMatch) {
						System.out.println("Sent request from floor " + floorNumber);
						udp.sendPacket(data, Config.scheduler_floor_port, Config.schedulerSubsystemIP);
					}
				}
			}
		}
	}
	
	/**
	 * Receives the completed requests from the scheduler
	 * @param elevatorRequests the ElevatorRequest array list
	 */
	private void receiveCompletedRequests(ArrayList<ElevatorRequest> elevatorRequests) {
		for (int i = 0; i < elevatorRequests.size(); i++) {
			System.out.println("Receiving completed request:");
			udp.receivePacket();
			System.out.println("--------------------------------------");
		}
	}

	
	
}
