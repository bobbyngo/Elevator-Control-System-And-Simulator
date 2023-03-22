package main.java.scheduler;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;
import main.java.dto.EncodeDecode;
import main.java.dto.UDP;
import main.java.elevator.ElevatorSubsystem;
import main.java.scheduler.SchedulerSubsystem.SchedulerType;

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
	// Map the port of elevator to its current location (all the elevator)
	
	// Map the port of elevator to its current direction (all the elevator)
	
	private SchedulerState schedulerState;
	private SchedulerType schedulerType;
	private UDP udpE; // Contains the socket for receiving packets from the elevators 
	private UDP udpF; // Contains the socket for receiving packets from the floors
	private UDP udpED; // Contains the socket for receiving packets from the elevators
	private static final int FLOOR_PORT = 23; // Designated port for receiving floor requests
	private static final int ELEVATOR_PORT = 69; // Designated port for receiving elevator requests
	private static final int ELEVATOR_DATA_PORT = 70; // Designated port for receiving elevator data (floor number, state, direction)
	
	/**
	 * Constructor for the Scheduler.
	 * @param schedulerType the SchedulerType enum that determines what the scheduler will listen to
	 */
	public Scheduler(SchedulerType schedulerType) {
		this.schedulerType = schedulerType;
		requestsQueue = Collections.synchronizedList(new ArrayList<>());
		completedQueue = Collections.synchronizedList(new ArrayList<>());

		if (schedulerType == SchedulerType.FloorListener) udpF = new UDP();
		else if (schedulerType == SchedulerType.ElevatorListener) udpE = new UDP();
		else if (schedulerType == SchedulerType.ElevatorDataListener) udpED = new UDP();
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
			if (schedulerType == SchedulerType.FloorListener) udpF.openSocket(FLOOR_PORT);
			else if (schedulerType == SchedulerType.ElevatorListener) udpE.openSocket(ELEVATOR_PORT);
			else if (schedulerType == SchedulerType.ElevatorDataListener) udpED.openSocket(ELEVATOR_DATA_PORT);
			Thread.sleep(0);
			while (true) {
				switch (schedulerState) {
				case Idle: {
					schedulerState = schedulerState.nextState();
					break;
				}
				case Ready: {
					if (schedulerType == SchedulerType.FloorListener) {
						DatagramPacket receivedFloorRequest = udpF.receivePacket();
						SchedulerSubsystem.floorPortNumber = receivedFloorRequest.getPort();
						ElevatorRequest elevatorRequest = EncodeDecode.decodeData(receivedFloorRequest);
						byte[] data = EncodeDecode.encodeData(elevatorRequest);
						// TODO: Scanning algorithm to determine which elevator receives the request should go here
						int port = assignRequestToElevator(elevatorRequest.getSourceFloor(), elevatorRequest.getDirection());
						udpF.sendPacket(data, port - 1000); // Send the packet to Elevator Listener Port
						schedulerState = schedulerState.nextState();
					}
					else if (schedulerType == SchedulerType.ElevatorListener) {
						DatagramPacket receivedCompletedElevatorRequest = udpE.receivePacket();
						udpE.sendPacket(receivedCompletedElevatorRequest.getData(), SchedulerSubsystem.floorPortNumber); // Sends completed request back to the floor
						schedulerState = schedulerState.nextState();
					}
					else if (schedulerType == SchedulerType.ElevatorDataListener) {
						DatagramPacket receivedElevatorData = udpED.receivePacket();
						
						System.out.println("Data String: " + new String(receivedElevatorData.getData()));
						
						String[] elevatorDataArr = new String(receivedElevatorData.getData()).split(" ");
						SchedulerSubsystem.elevatorLocation.put(receivedElevatorData.getPort(), Integer.valueOf(elevatorDataArr[0]));
						
						// Actually works!
						SchedulerSubsystem.elevatorDirection.put(receivedElevatorData.getPort(), Direction.valueOf(elevatorDataArr[1]));
						
						// For the purpose of viewing the contents of the elevatorLocation/Direction maps. Could delete ------------
						for (Integer elevatorFuncPort : SchedulerSubsystem.elevatorLocation.keySet()) {
							System.out.println(elevatorFuncPort + " " + SchedulerSubsystem.elevatorLocation.get(elevatorFuncPort));
						}
						for (Integer elevatorFuncPort : SchedulerSubsystem.elevatorDirection.keySet()) {
							System.out.println(elevatorFuncPort + " " + SchedulerSubsystem.elevatorDirection.get(elevatorFuncPort));
						}
						// ---------------------------------------------------------------------------------------------------------
						schedulerState = schedulerState.nextState();
					}
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
			if (schedulerType == SchedulerType.FloorListener) udpF.closeSocket();
			else if (schedulerType == SchedulerType.ElevatorListener) udpE.closeSocket();
			else if (schedulerType == SchedulerType.ElevatorDataListener) udpED.closeSocket();
			System.out.println("--------- Program terminated ---------");
		}
	}
	
	private HashMap<Integer, Direction> getMovingElevators() {
		HashMap<Integer, Direction> movingElevatorHashMap = new HashMap<>();
		for (Integer port: SchedulerSubsystem.elevatorDirection.keySet()) {
			
			System.out.println("elevatorDirection port: " + port);
			
			// comparing enum with == or equals are the same, but == is null safe
			if (SchedulerSubsystem.elevatorDirection.get(port) !=  Direction.NONE) {
				movingElevatorHashMap.put(port, SchedulerSubsystem.elevatorDirection.get(port));
			}
		}
		return movingElevatorHashMap;
	}
	
	
	private int assignRequestToElevator(int newRequestSourceFloor, Direction newRequestDirection) {
		
		System.out.println("elevatorDirection map size: " + SchedulerSubsystem.elevatorDirection.size());
		System.out.println("elevatorLocation map size: " + SchedulerSubsystem.elevatorLocation.size());
		
		//Both elevatorLocation and elevatorDirection should have the same length?
		if (SchedulerSubsystem.elevatorDirection.size() != SchedulerSubsystem.elevatorLocation.size() || SchedulerSubsystem.elevatorDirection.size() == 0) {
			if (SchedulerSubsystem.elevatorDirection.size() == 0) {
				System.out.println("elevatorDirection is empty");
			}
			// Something super weird is happening
			logger.severe("Elevator direction and elevator location mapping are not match");
		}
		
		// Send request to an elevator that is heading in the same direction and hasn't passed the source floor
		for (Integer port: SchedulerSubsystem.elevatorDirection.keySet()) {
			System.out.println("elevatorDirection port: " + port);
			if (SchedulerSubsystem.elevatorDirection.get(port) == newRequestDirection  && SchedulerSubsystem.elevatorDirection.get(port) == Direction.UP 
					&& SchedulerSubsystem.elevatorLocation.get(port) < newRequestSourceFloor) {
				System.out.println("Elevator that is going UP will be selected");
				return port;
			} else if (SchedulerSubsystem.elevatorDirection.get(port) == newRequestDirection  && SchedulerSubsystem.elevatorDirection.get(port) == Direction.DOWN 
					&& SchedulerSubsystem.elevatorLocation.get(port) > newRequestSourceFloor) {
				System.out.println("Elevator that is going DOWN will be selected");
				return port;
			}
		}
		
		// Send request to an idle elevator
		for (Integer port : SchedulerSubsystem.elevatorDirection.keySet())	{
			if (SchedulerSubsystem.elevatorDirection.get(port) == Direction.NONE) {
				System.out.println("Elevator that is idle will be selected");
				return port;
			}		
		}
		
		// Send request to any elevator (we were not able to find an ideal elevator)
		for (Integer port : SchedulerSubsystem.elevatorDirection.keySet())	{
			return port;	
		}
		
		return -1; // There are no elevators in the system
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
	 * Display the elevator's current location based on the provided id.
	 * @param id Integer, the elevator id
	 * @return Integer, the elevator's current location based on the provided id
	 * @author Patrick Liu
	 */
	public synchronized Integer displayElevatorLocation(Integer id) {
		return SchedulerSubsystem.elevatorLocation.get(id);
	}
	
	
}

