package main.java.scheduler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Timestamp;
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
	
	private static final int FLOOR_PORT = 23;
	private static final int ELEVATOR_PORT = 69;
	
	private DatagramSocket floorSocket, elevatorSocket;
	private List<ElevatorRequest> requestsQueue;
	private List<ElevatorRequest> completedQueue;
	private Map<Integer, Integer> elevatorLocation;
	private SchedulerState schedulerState;
	
	
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
			floorSocket = new DatagramSocket(FLOOR_PORT);
			elevatorSocket = new DatagramSocket(ELEVATOR_PORT);
			Thread.sleep(0);
			while (true) {
				switch (schedulerState) {
				case Idle: {
					schedulerState = schedulerState.nextState();
					break;
				}
				case Ready: {;
					DatagramPacket reply = replyFloor();
					ElevatorRequest elevatorRequest = decodeData(reply);
					putRequest(elevatorRequest);
					// TODO: Scanning algorithm to the queue should happen here
					elevatorRequest = dispatchRequest();
					byte[] data = encodeData(elevatorRequest);
					replyElevator(data);
					schedulerState = schedulerState.nextState();
					break;
				}
				case InService: {
					DatagramPacket ack = ackElevator();
					ElevatorRequest elevatorRequest = decodeData(ack);
					putCompletedRequest(elevatorRequest);
					elevatorRequest = getCompletedRequest();
					byte[] data = encodeData(elevatorRequest);
					ackFloor(data);
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
		}
	}
	
	/**
	 * Reply to Floor.
	 * @return DatagramPacket, containing the same message received
	 */
	private DatagramPacket replyFloor() {
		byte[] data = new byte[100];
		DatagramPacket receivePacket = null;
		try {
			receivePacket = new DatagramPacket(data, data.length);
			System.out.println(this.getClass().getName() + ": Waiting...\n");
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
	private void replyElevator(byte[] reply) {
		byte[] data = new byte[100];
		try {
			DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			System.out.println(this.getClass().getName() + ": Waiting...\n");
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
	private DatagramPacket ackElevator() {
		byte[] data = new byte[100];
		DatagramPacket ackReceivePacket = null;
		try {
			ackReceivePacket = new DatagramPacket(data, data.length);
			System.out.println(this.getClass().getName() + ": Waiting...\n");
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
			System.err.println(this.getClass().getName() + ": Program terminated.");
			e.printStackTrace();
			System.exit(1);
		}
		return ackReceivePacket;
	}
	
	/**
	 * Acknowledge Floor request.
	 * @param ack DatagramPacket, ack packet containing data from Server
	 */
	private void ackFloor(byte[] ack) {
		byte[] data = new byte[100];
		try {
			DatagramPacket receiveAckPacket = new DatagramPacket(data, data.length);
			System.out.println(this.getClass().getName() + ": Waiting...\n");
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
	 * Prints out the information it has put in the packet 
	 * both as a String and as bytes. 
	 * @param packet, DatagramPacket
	 * @param direction, String (i.e. received or sending)
	 */
	private void printPacketContent(DatagramPacket packet, String direction) {
		System.out.println(this.getClass().getName() + ": " + direction);
	    System.out.println("Address: " + packet.getAddress());
	    System.out.println("Port: " + packet.getPort());
	    int len = packet.getLength();
	    System.out.println("Length: " + packet.getLength());
	    System.out.print("Containing: ");
	    String packetStr = new String(packet.getData(), 0, len);
	    System.out.println(packetStr + "\n");
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e ) {
	        e.printStackTrace();
	        System.exit(1);
	    }
	}
	
	/**
	 * Encodes an elevator request object into a byte[] data.
	 * @param elevatorRequest, ElevatorRequest obj
	 * @return message byte[], the encoded elevatorRequest
	 * @throws IOException
	 */
	private byte[] encodeData(ElevatorRequest elevatorRequest) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] message = null;
		try {
			os.write(elevatorRequest.toString().getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		message = os.toByteArray();
		return message;
	}
	
	/**
	 * Decodes byte[] data into an ElevatorRequest object.
	 * @param message byte[], the encoded elevatorRequest
	 * @return elevatorRequest, ElevatorRequest obj
	 * @throws IOException
	 */
	private ElevatorRequest decodeData(DatagramPacket packet) {
		ElevatorRequest elevatorRequest = null;
		Timestamp timestamp = null;
	    String[] line = new String(packet.getData(), 0, packet.getLength()).split(" ");
		
	    try {
	    		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    		Date parsedDate = dateFormat.parse(currentTime.toString().split(" ")[0] + " " + line[0]);
	        timestamp = new Timestamp(parsedDate.getTime());
	    	
	        elevatorRequest = new ElevatorRequest(timestamp, 
	    			Integer.valueOf(line[1]), 
		    		Direction.valueOf(line[2]), 
		    		Integer.valueOf(line[3]));
	    } catch (Exception e) {
	    		return null;
	    }
	    return elevatorRequest;
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
	 * Scheduler signals the elevator to move to the given location and register its new location
	 * @author Bobby Ngo
	 * @param currentFloor of the elevator
	 * @param destinationFloor the new floor its need to go
	 * @return int, the new floor that the scheduler reached
	 */
	public int movingTo(int id, int currentFloor, int destinationFloor) {
		while (currentFloor != destinationFloor) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String loggerStr = String.format("Moving from floor %d -> floor %d \n", currentFloor, destinationFloor);
			logger.info(loggerStr);
			
			if (currentFloor < destinationFloor) {
				currentFloor += 1;
			} else {
				currentFloor -= 1;
			}
			// After the elevator goes to a different floor, update the floor location immediately
			this.registerElevatorLocation(id, currentFloor);
		}
		logger.info(String.format("Arrived at destination floor %d, current floor is %d\n", destinationFloor,  currentFloor));
		return currentFloor;
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

