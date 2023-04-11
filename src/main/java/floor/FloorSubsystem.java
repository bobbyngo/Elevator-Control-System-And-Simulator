package main.java.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
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
import main.java.dto.FloorGuiData;
import main.java.elevator.Direction;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.floor.parser.Parser;
import main.java.gui.LogConsole;

/**
 * Responsible for sending elevator requests and handling incoming requests
 * 
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
	private LogConsole logConsole;

	/**
	 * Constructor for the FloorSubsystem class.
	 * 
	 * @param config SimulatorConfiguration, simulator configuration parameters
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
		} catch (FileNotFoundException e) {
		}
		try {
			// Filename after compilation
			String FILENAME = System.getProperty("user.dir") + file.getPath().substring(4);
			this.parser = new Parser(FILENAME);
		} catch (FileNotFoundException e) {
		}
		floorArr = new Floor[numOfFloors];
		for (int i = 0; i < numOfFloors; i++) {
			floorArr[i] = new Floor(i + 1);
		}
		logConsole = new LogConsole(this.getClass().getSimpleName());
	}

	/**
	 * FloorSubsystem override run() method.
	 * 
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
					} catch (Exception e) {
					}
				}
			});

			Thread completedReqListenerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							listenToCompletedRequests();
						}
					} catch (Exception e) {
					}
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
	 * Sends the series of elevator requests to the Scheduler
	 * 
	 * @param elevatorRequests ArrayList, the list of elevator request object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void addRequestsToQueue(ArrayList<ElevatorRequest> elevatorRequests)
			throws IOException, InterruptedException, ParseException {
		if (!elevatorRequests.isEmpty()) {
			Timer requestsTimer = new Timer();
			for (ElevatorRequest req : elevatorRequests) {
				byte[] data = req.encode();
				requestsTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						UDPClient udpSendReq = new UDPClient();
						try {
							udpSendReq.sendMessage(data, simulatorConfiguration.SCHEDULER_HOST,
									simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
							ElevatorRequest elevatorRequest = ElevatorRequest.decode(data);
							Floor floor = floorArr[elevatorRequest.getSourceFloor() - 1];
							// If the lamp associated with that direction is off, turn it on
							if (elevatorRequest.getDirection() == Direction.UP && floor.getFloorUpLamp() == false) {
								floor.setFloorUpLamp(true);
							} else if (elevatorRequest.getDirection() == Direction.DOWN
									&& floor.getFloorDownLamp() == false) {
								floor.setFloorDownLamp(true);
							}
							printLog(String.format("REQUEST_SENT              -- %s", req.toString()));
							System.out.println(floor.toString());
							System.out.println("--------------------------------------------------");
							sendGuiNotification(new FloorGuiData(floor.getFloorNum(), floor.getUpButtonLamp(),
									floor.getDownButtonLamp()));
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
					}
				}, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(req.getTimestamp().toString()));
			}
		}
	}

	/**
	 * Listens to arrival requests from the scheduler and updates the floor
	 * components.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void listenToArrivalRequests() throws ClassNotFoundException, IOException {
		DatagramPacket receivedReqPacket = udpArrivalRequestsReceiver.receiveMessage();
		ElevatorStatus elevatorStatus = ElevatorStatus.decode(receivedReqPacket.getData());

		int elevatorNum = elevatorStatus.getElevatorId();
		int floorNum = elevatorStatus.getFloor();
		Floor floor = floorArr[floorNum - 1];
		ElevatorStateEnum elevatorState = elevatorStatus.getState();
		Direction elevatorDirection = elevatorStatus.getDirection();
		int elevatorId = elevatorStatus.getElevatorId();
		// Sets the sensors of the same shaft to false on all floors
		updateAllSensorsStatus(elevatorId);
		floor.setFloorSensor(elevatorId, true);

		updateAllElevatorLamps(elevatorId, elevatorDirection);

		if (elevatorState == ElevatorStateEnum.DOORS_OPEN) {
			printLog(String.format("ARRIVAL_NOTIFICATION -- Elevator %d :: Floor %d", elevatorNum, floorNum));
			if (elevatorDirection == Direction.DOWN)
				floor.setFloorDownLamp(false);
			else
				floor.setFloorUpLamp(false);
			System.out.println(floor.toString());
			System.out.println("--------------------------------------------------");
		}
		sendGuiNotification(new FloorGuiData(floor.getFloorNum(), floor.getUpButtonLamp(), floor.getDownButtonLamp()));
	}

	/**
	 * Updates the direction lamp for an elevator shaft on all the floors
	 * 
	 * @param elevatorId the int of the elevator id
	 * @param direction  the Direction enum to change the lamp's status to
	 */
	private void updateAllElevatorLamps(int elevatorId, Direction elevatorDirection) {
		for (int i = 0; i < floorArr.length; i++) {
			floorArr[i].setElevatorDirectionLamp(elevatorId, elevatorDirection);
		}
	}

	/**
	 * Sets the sensor status of a sensor id to false on all floors
	 * 
	 * @param sensorId the int of the sensor ID (equal to the elevator ID)
	 */
	private void updateAllSensorsStatus(int sensorId) {
		for (int i = 0; i < floorArr.length; i++) {
			floorArr[i].setFloorSensor(sensorId, false);
		}
	}

	/**
	 * Listens to completed requests from the scheduler.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void listenToCompletedRequests() throws ClassNotFoundException, IOException {
		// TODO: Fix the issue with receiving the same completed request multiple times
		DatagramPacket receivedReqPacket = udpCompletedRequestsReceiver.receiveMessage();
		ElevatorRequest elevatorRequest = ElevatorRequest.decode(receivedReqPacket.getData());
		printLog(String.format("REQUEST_COMPLETED -- %s", elevatorRequest.toString()));
		System.out.println(floorArr[elevatorRequest.getDestinationFloor() - 1].toString());
		System.out.println("--------------------------------------------------");
	}

	/**
	 * Parse user requests.
	 * 
	 * @return elevatorRequests ArrayList, a list of elevator requests
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
	 * Sends notification to the graphical user interface.
	 * 
	 * @param data FloorGuiData, data for the floor GUI
	 */
	private void sendGuiNotification(FloorGuiData data) {
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(data.encode(), simulatorConfiguration.GUI_HOST,
					simulatorConfiguration.GUI_FLOOR_DTO_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageClient.close();
	}

	/**
	 * Print the logs to the console text area.
	 * 
	 * @param message String, the message to the displayed
	 */
	private void printLog(String message) {
		System.out.println(message);
		logConsole.appendLog(" " + message + "\n");
	}

	/**
	 * Main method.
	 * 
	 * @param args, default parameters
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
