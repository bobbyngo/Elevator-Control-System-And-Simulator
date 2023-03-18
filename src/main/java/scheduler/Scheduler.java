package main.java.scheduler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.elevator.ElevatorSubsystem;

/**
 * Responsible for accepting input from all of the sensors, and
 * sending indications (to devices such as lamps) and commands (to devices such as the motor and door). It is
 * responsible for routing each elevator to requested floors and coordinating elevators in such a way to minimize
 * waiting times for people moving between floors (avoiding starvation).
 * @author Bobby Ngo
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class Scheduler implements Runnable {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
		
	private List<ElevatorRequest> requestsQueue;
	private List<ElevatorRequest> completedQueue;
	private Map<Integer, Integer> elevatorLocation;
	private SchedulerState schedulerState;
	private UDP udpE; // Contains the socket for receiving packets from the elevators 
	private UDP udpF; // Contains the socket for receiving packets from the floors
	private static final int FLOOR_PORT = 23; // Designated port for receiving floor requests
	private static final int ELEVATOR_PORT = 69; // Designated port for receiving elevator requests
	
	/**
	 * Main method for the Scheduler class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		new Thread(new Scheduler()).start();
	}
	
	/**
	 * Constructor for the Scheduler.
	 */
	public Scheduler() {
		requestsQueue = Collections.synchronizedList(new ArrayList<>());
		completedQueue = Collections.synchronizedList(new ArrayList<>());
		elevatorLocation = Collections.synchronizedMap(new HashMap<>());
		udpE = new UDP();
		udpF = new UDP();
		schedulerState = SchedulerState.Idle;
		logger.setLevel(Level.INFO);
	}
	
	/**
	 * Scheduler override run() method. Sleeps until the process is killed.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			udpE.openSocket(ELEVATOR_PORT);
			udpF.openSocket(FLOOR_PORT);
			Thread.sleep(0);
			while (true) {
				switch (schedulerState) {
				case Idle: {
					schedulerState = schedulerState.nextState();
					break;
				}
				case Ready: {
					DatagramPacket receivedFloorRequest = udpF.receivePacket();
					udpF.sendPacket(receivedFloorRequest.getData(), receivedFloorRequest.getPort()); // Echos the request back to the floor
					ElevatorRequest elevatorRequest = EncodeDecode.decodeData(receivedFloorRequest);
					putRequest(elevatorRequest);
					// TODO: Scanning algorithm to the queue should happen here
					elevatorRequest = dispatchRequest();
					byte[] data = EncodeDecode.encodeData(elevatorRequest);
					udpE.sendPacket(data, ElevatorSubsystem.elevatorPorts[(int) (Math.random() * 2)]); // Hardcoded for now
					schedulerState = schedulerState.nextState();
					break;
				}
				case InService: {
					// TODO: implement a new method to send completed requests 
					// DatagramPacket ack = udp.ackElevator();
					// ElevatorRequest elevatorRequest = decodeData(ack);
					// putCompletedRequest(elevatorRequest);
					// elevatorRequest = getCompletedRequest();
					// byte[] data = encodeData(elevatorRequest);
					// udp.ackFloor(data);
					schedulerState = schedulerState.nextState();
					System.out.println("--------------------------------------");
					break;
				}
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			udpE.closeSocket();
			udpF.closeSocket();
			System.out.println("--------- Program terminated ---------");
		}
	}
	
	/**
	 * Get the queue of the elevator request.
	 * @return List<>, list of elevator request
	 */
	public List<ElevatorRequest> getRequestsQueue() {
		return requestsQueue;
	}
	
	/**
	 * Get current scheduler state
	 * @return schedulerState SchedulerState, current scheduler state
	 */
	public SchedulerState getSchedulerState() {
		return schedulerState;
	}
	
	/**
	 * This method is called by the Floor class. The new request will be added to the list of floors to visit.
	 * @param elevatorRequest
	 */
	public synchronized void putRequest(ElevatorRequest elevatorRequest) {
		if (elevatorRequest == null) {
			return;
		}
		// No duplicate values
		else if (!requestsQueue.contains(elevatorRequest)) {
			requestsQueue.add(elevatorRequest);
			String loggerStr = String.format("Add request %s > request queue: %d", elevatorRequest.toString(), requestsQueue.size());
			logger.info(loggerStr);
		}
		notifyAll();
	}
	
	/**
	 * This method will be called by Elevator class. 
	 * After the floor finished a request it should dispatch an item from the list.
	 * @return ElevatorRequest, the requested elevator object
	 */
	public synchronized ElevatorRequest dispatchRequest() {
		while(requestsQueue.size() == 0) {
			try {
				logger.info("Waiting for the request");
				wait();
			} catch (InterruptedException e) {
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
		}
		
		// TODO: Elevator assignment algorithm goes here
		ElevatorRequest removedElevatorRequest = requestsQueue.remove(0);
		String loggerStr = String.format("Dispatch request %s > request queue: %d", removedElevatorRequest.toString(), requestsQueue.size());
		logger.info(loggerStr);
		notifyAll();
		return removedElevatorRequest;
	}
	
	/**
	 * Puts elevator request data into the Scheduler's reply queue.
	 * @param reply ElevatorRequest, replied elevator request data
	 * @author Zakaria Ismail
	 */
	public synchronized void putCompletedRequest(ElevatorRequest reply) {
		if (!completedQueue.contains(reply)) {
			completedQueue.add(reply);
			String loggerStr = String.format("Add request %s to the completed queue > completed queue: %d", reply, completedQueue.size());
			logger.info(loggerStr);
		}
		notifyAll();
	}
	
	/**
	 * Gets reply message from the reply queue
	 * @return ElevatorRequest, message from the reply queue
	 */
	public synchronized ElevatorRequest getCompletedRequest() {
		ElevatorRequest reply;
		
		while (completedQueue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		reply = completedQueue.remove(0);
		notifyAll();
		return reply;
	}
	
	/**
	 * This method stores the elevator's current floor number together with its id.
	 * @param id Integer, the id of the elevator
	 * @param floorNumber, Integer, the floor number
	 * @author Patrick Liu
	 */
	public synchronized void registerElevatorLocation(Integer id, Integer floorNumber) {
		elevatorLocation.put(id, floorNumber);
		System.out.println(String.format("Scheduler: Elevator# %s current location: Floor %s", id, displayElevatorLocation(id)));
	}
	
	/**
	 * Display the elevator's current location based on the provided id.
	 * @param id Integer, the elevator id
	 * @return Integer, the elevator's current location based on the provided id
	 * @author Patrick Liu
	 */
	public synchronized Integer displayElevatorLocation(Integer id) {
		return elevatorLocation.get(id);
	}
	
	
}

