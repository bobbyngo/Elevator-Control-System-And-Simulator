package main.java.elevator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.List;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorGuiData;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;

/**
 * Controller. Routes requests to respective elevators. Handles communication
 * aspect.
 * 
 * @author Zakaria Ismail
 */
public class ElevatorSubsystem implements Runnable {

	private HashMap<Integer, ElevatorContext> elevators;
	private SimulatorConfiguration simulatorConfiguration;
	private Thread requestListenerThread;
	private UDPClient udpRequestReceiver;

	/**
	 * Constructor for Elevator Subsystem
	 * 
	 * @param config SimulatorConfiguration, the simulator configurations
	 */
	public ElevatorSubsystem(SimulatorConfiguration config) {
		ElevatorContext elevator;

		elevators = new HashMap<>();
		simulatorConfiguration = config;
		udpRequestReceiver = new UDPClient(config.ELEVATOR_SUBSYSTEM_REQ_PORT);

		for (int i = 1; i <= config.NUM_ELEVATORS; i++) {
			elevator = new ElevatorContext(this, i);
			elevator.startElevator();
			elevators.put(i, elevator);
		}
	}

	/**
	 * Start the requestListenerThread to listen to the requests from the Scheduler
	 */
	public void run() {
		// Start request fetching
		requestListenerThread = new Thread(new RequestListenerTask(this));
		requestListenerThread.start();
	}

	/**
	 * Getter for the configuration of this class.
	 * 
	 * @return SimulatorConfiguration, the simulator configurations
	 */
	public SimulatorConfiguration getConfig() {
		return simulatorConfiguration;
	}

	/**
	 * Receiving request method.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveElevatorRequest() throws ClassNotFoundException, IOException {
		// Called by receiving listener thread raise all exceptions to the calling
		// thread
		DatagramPacket receivePacket;
		AssignedElevatorRequest assignedRequest;
		Thread requestHandler;

		receivePacket = udpRequestReceiver.receiveMessage();
		assignedRequest = AssignedElevatorRequest.decode(UDPClient.readPacketData(receivePacket));
		requestHandler = new Thread(new Runnable() {
			@Override
			public void run() {
				routeElevatorRequest(assignedRequest);
			}
		});
		requestHandler.start();
		return;
	}

	/**
	 * Update the request to the Elevator context.
	 * 
	 * @param request AssignedElevatorRequest, the assigned elevator request object
	 */
	private void routeElevatorRequest(AssignedElevatorRequest request) {
		int elevatorId;
		ElevatorContext ctx;

		elevatorId = request.getElevatorId();
		ctx = elevators.get(elevatorId);
		ctx.addExternalRequest(request);
	}

	/**
	 * Sending completed request message method to the Scheduler.
	 * 
	 * @param request ElevatorRequest, the elevator request object
	 */
	public void sendCompletedElevatorRequest(ElevatorRequest request) {
		// send elevator request to be called by context
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(request.encode(), simulatorConfiguration.SCHEDULER_HOST,
					simulatorConfiguration.SCHEDULER_COMPLETED_REQ_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageClient.close();
	}

	/**
	 * Notify the elevator context of updates.
	 * 
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public void notifyContextUpdate(ElevatorContext ctx) {
		new Thread(() -> sendArrivalNotification(new ElevatorStatus(ctx))).start();
		new Thread(() -> sendGuiNotification(new ElevatorGuiData(ctx))).start();
		return;
	}

	/**
	 * Sending arrival notification method to the Scheduler.
	 * 
	 * @param status ElevatorStatus, the status of the elevator
	 */
	private void sendArrivalNotification(ElevatorStatus status) {
		// send arrival notification
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(status.encode(), simulatorConfiguration.SCHEDULER_HOST,
					simulatorConfiguration.SCHEDULER_ARRIVAL_REQ_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageClient.close();
	}

	/**
	 * Sends notification to the graphical user interface.
	 * 
	 * @param data ElevatorGuiData, data for the elevator GUI
	 */
	private void sendGuiNotification(ElevatorGuiData data) {
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(data.encode(), simulatorConfiguration.GUI_HOST,
					simulatorConfiguration.GUI_ELEVATOR_DTO_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageClient.close();
	}

	/**
	 * Send the elevator requests to the Scheduler.
	 * 
	 * @param requests List, the list of elevator requests
	 */
	public void returnElevatorRequests(List<ElevatorRequest> requests) {
		UDPClient messageClient = new UDPClient();

		for (ElevatorRequest request : requests) {
			try {
				messageClient.sendMessage(request.encode(), simulatorConfiguration.SCHEDULER_HOST,
						simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		messageClient.close();
	}

	/**
	 * Main method.
	 * 
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		SimulatorConfiguration configuration;
		ElevatorSubsystem subsystem;
		Thread subsystemThread;

		configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		subsystem = new ElevatorSubsystem(configuration);
		subsystemThread = new Thread(subsystem);
		subsystemThread.start();
	}

}
