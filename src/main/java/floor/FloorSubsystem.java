package main.java.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.Direction;
import main.java.floor.parser.Parser;

/**
 * Responsible for sending elevator requests and handling 
 * incoming requests
 * @author Hussein El Mokdad
 * @since 1.0, 02/04/23
 * @version 4.0, 04/01/23
 */
public class FloorSubsystem implements Runnable {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final File file = new File("/src/main/resources/input.txt");
	private SimulatorConfiguration simulatorConfiguration;
	private Parser parser;
	private UDPClient udpArrivalRequestsReceiver;
	private UDPClient udpCompletedRequestsReceiver;
	private Floor[] floorArr;
	private int numOfFloors;
	
	/**
	 * Constructor for the FloorSubsystem class.
	 */
	public FloorSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		udpArrivalRequestsReceiver = new UDPClient(simulatorConfiguration.FLOOR_SUBSYSTEM_ARRIVAL_REQ_PORT);
		udpCompletedRequestsReceiver = new UDPClient(simulatorConfiguration.FLOOR_SUBSYSTEM_COMPLETED_REQ_PORT);
		numOfFloors = simulatorConfiguration.NUM_FLOORS;
		logger.setLevel(Level.INFO);
		try {
			// Filename before compilation
			String FILENAME = System.getProperty("user.dir") + file.getPath();
			this.parser = new Parser(FILENAME);
		} catch (FileNotFoundException e) {}
		try {
			// Filename after compilation
			String FILENAME = System.getProperty("user.dir") + file.getPath().substring(4);
			this.parser = new Parser(FILENAME);
		} catch (FileNotFoundException e) {}
		floorArr = new Floor[numOfFloors];
		for (int i = 0; i < numOfFloors; i++) {
			floorArr[i] = new Floor(i + 1); 
		}
	}
	
	/**
	 * FloorSubsystem override run() method.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			
			Thread arrivalReqListenerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							listenToArrivalRequests();
						}
					} catch (Exception e) {}
				}
			});
			
			Thread completedReqListenerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							listenToCompletedRequests();
						}
					} catch (Exception e) {}
				}
			});
			
			arrivalReqListenerThread.start();
			completedReqListenerThread.start();
			
			ArrayList<ElevatorRequest> elevatorRequests = getElevatorRequests();
			addRequestsToQueue(elevatorRequests);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends the series of elevator requests to the SchedulerOld.
	 * @param elevatorRequests
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void addRequestsToQueue(ArrayList<ElevatorRequest> elevatorRequests) throws IOException, InterruptedException, ParseException {
		if (!elevatorRequests.isEmpty()) {
			Timer requestsTimer = new Timer();
			for (ElevatorRequest req : elevatorRequests) {
				byte[] data = req.encode();
				requestsTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						UDPClient udpSendReq = new UDPClient();
						try {
							udpSendReq.sendMessage(data, simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
							ElevatorRequest elevatorRequest = ElevatorRequest.decode(data);
							Floor floor = floorArr[elevatorRequest.getSourceFloor() - 1];
							// If the lamp associated with that direction is off, turn it on
							if (elevatorRequest.getDirection() == Direction.UP && floor.getFloorUpLamp() == false) {
								floor.setFloorUpLamp(true);
								System.out.println("Turned floor up lamp on");
							}
							else if (elevatorRequest.getDirection() == Direction.DOWN && floor.getFloorDownLamp() == false){
								floor.setFloorDownLamp(true);
								System.out.println("Turned floor down lamp on");
							}
							
							System.out.println(floor.toString());
							
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
						
						System.out.println("Sending request " + req.toString());
						
						System.out.println("--------------------------------------------------");
					}
				}, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(req.getTimestamp().toString()));
			}
		}
	}
	
	/**
	 * Listens to arrival requests from the scheduler
	 */
	private void listenToArrivalRequests() throws ClassNotFoundException, IOException {
		// TODO: Only listen to requests that arrive at a waiting floor
		DatagramPacket receivedReqPacket = udpArrivalRequestsReceiver.receiveMessage();
		int elevatorNum = ElevatorStatus.decode(receivedReqPacket.getData()).getElevatorId();
		int floorNum = ElevatorStatus.decode(receivedReqPacket.getData()).getFloor();
		// Check for elevator direction and turn off the lamp associated with it
		// System.out.println("Elevator " + elevatorNum + " arrived at floor " + floorNum + ". Turning off lamp [determine lamp to turn off]");
		// floorArr[floorNum - 1].setFloorUpLamp(false);
		// floorArr[floorNum - 1].setFloorDownLamp(false);
		// System.out.println("--------------------------------------------------");
	}
	
	/**
	 * Listens to completed requests from the scheduler
	 */
	private void listenToCompletedRequests() throws ClassNotFoundException, IOException {
		// TODO: Fix the issue with receiving the same completed request multiple times
		DatagramPacket receivedReqPacket = udpCompletedRequestsReceiver.receiveMessage();
		System.out.println("Request " + ElevatorRequest.decode(receivedReqPacket.getData()).toString() + " has been completed");
		System.out.println("--------------------------------------------------");
	}

	/**
	 * Parse user requests.
	 * @return elevatorRequests ArrayList<>, a list of elevator requests
	 */
	private ArrayList<ElevatorRequest> getElevatorRequests() {
		ArrayList<ElevatorRequest> elevatorRequests = null;
		try {
			elevatorRequests = parser.requestParser();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return elevatorRequests;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatorConfiguration configuration;
		FloorSubsystem floorSubsystem;
		Thread floorSubsystemThread;
		
		configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		floorSubsystem = new FloorSubsystem(configuration);
		floorSubsystemThread = new Thread(floorSubsystem);
		floorSubsystemThread.start();
	}
	
}
