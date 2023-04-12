package main.java.scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.gui.LogConsole;

/**
 * Representing the Scheduler Subsystem.
 * 
 * @author Bobby Ngo, Patrick Liu
 */
public class SchedulerSubsystem implements Runnable {
	private SchedulerContext schedulerContext;
	private SimulatorConfiguration simulatorConfiguration;
	// 3 sockets and 3 threads for listening to the request
	private UDPClient pendingRequestSocket;
	private UDPClient arrivalRequestSocket;
	private UDPClient completedRequestSocket;
	// private Thread floorRequestListenerThread;
	private Thread pendingRequestListenerThread;
	private Thread arrivalRequestListenerThread;
	private Thread completedRequestListenerThread;

	private LogConsole logConsole;

	/**
	 * Main method invoked as a thread.
	 * 
	 * @param args, default parameters
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args)
			throws ParseException, InterruptedException, UnknownHostException, IOException {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/main/resources/config.properties");
		SchedulerSubsystem s = new SchedulerSubsystem(sc);
		Thread sThread = new Thread(s);
		sThread.start();
	}

	/**
	 * Constructor for the scheduler subsystem.
	 * 
	 * @param config SimulatorConfiguration, the configurations parameters
	 */
	public SchedulerSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		schedulerContext = new SchedulerContext(this);
		// Registering the listening port for the socket
		pendingRequestSocket = new UDPClient(config.SCHEDULER_PENDING_REQ_PORT);
		arrivalRequestSocket = new UDPClient(config.SCHEDULER_ARRIVAL_REQ_PORT);
		completedRequestSocket = new UDPClient(config.SCHEDULER_COMPLETED_REQ_PORT);
		logConsole = new LogConsole(this.getClass().getSimpleName());
	}

	/**
	 * Starting threads with following sequence of steps.
	 */
	public void run() {
		pendingRequestListenerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						receivePendingRequest();
					} catch (Exception e) {
						System.out.println("Error receiving req occurred!!");
						e.printStackTrace();
					}
				}
			}
		});
		arrivalRequestListenerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						receiveArrivalNotification();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		completedRequestListenerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						receiveCompletedElevatorRequest();
					} catch (Exception e) {
						System.out.println("Error receiving completed req occurred!");
						e.printStackTrace();
					}
				}
			}
		});

		this.pendingRequestListenerThread.start();
		this.arrivalRequestListenerThread.start();
		this.completedRequestListenerThread.start();
	}

	/**
	 * Receiving pending request from Floor method.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receivePendingRequest() throws ClassNotFoundException, IOException {
		Thread task;
		DatagramPacket packetFromFloor = pendingRequestSocket.receiveMessage();
		byte[] floorRequestData = UDPClient.readPacketData(packetFromFloor);
		ElevatorRequest floorRequest = ElevatorRequest.decode(floorRequestData);
		System.out.println("Scheduler received: " + floorRequest);
		task = new Thread(new Runnable() {
			@Override
			public void run() {
				schedulerContext.addPendingElevatorRequests(floorRequest);
			}
		});
		task.start();
	}

	/**
	 * Sending pending request to the elevator method.
	 * 
	 * @param assignedRequest AssignedElevatorRequest, an assigned elevator request object
	 * @throws IOException
	 */
	public void sendPendingRequest(AssignedElevatorRequest assignedRequest) throws IOException {
		Thread task;

		task = new Thread(new Runnable() {
			@Override
			public void run() {
				if (assignedRequest != null) {
					byte[] data;
					try {
						data = assignedRequest.encode();
						UDPClient socket = new UDPClient();
						socket.sendMessage(data, simulatorConfiguration.ELEVATOR_SUBSYSTEM_HOST,
								simulatorConfiguration.ELEVATOR_SUBSYSTEM_REQ_PORT);
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					printLog(String.format("SENT_ASSIGNED           -- %s", assignedRequest));
					schedulerContext.onRequestSent();
				}
			}
		});
		task.start();
	}

	/**
	 * Receiving arrival notification from elevator method.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveArrivalNotification() throws ClassNotFoundException, IOException {
		Thread task;
		DatagramPacket packetFromElevator = arrivalRequestSocket.receiveMessage();
		byte[] arrivalNotificationData = UDPClient.readPacketData(packetFromElevator);
		ElevatorStatus arrivalNotification = ElevatorStatus.decode(arrivalNotificationData);

		task = new Thread(new Runnable() {
			@Override
			public void run() {
				schedulerContext.modifyAvailableElevatorStatus(arrivalNotification.getElevatorId() - 1,
						arrivalNotification);
				schedulerContext.onRequestReceived();
				try {
					sendArrivalNotification(arrivalNotification);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		task.start();
	}

	/**
	 * Sending arrival notification to Floor method.
	 * 
	 * @param arrivalNotification ElevatorStatus, the status of the elevator
	 * @throws IOException
	 */
	private void sendArrivalNotification(ElevatorStatus arrivalNotification) throws IOException {
		byte[] data = arrivalNotification.encode();

		UDPClient socket = new UDPClient();
		socket.sendMessage(data, simulatorConfiguration.FLOOR_SUBSYSTEM_HOST,
				simulatorConfiguration.FLOOR_SUBSYSTEM_ARRIVAL_REQ_PORT);
		socket.close();
	}

	/**
	 * Receiving completed request method from the elevator.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveCompletedElevatorRequest() throws ClassNotFoundException, IOException {
		DatagramPacket packetFromElevator = completedRequestSocket.receiveMessage();
		byte[] completedRequestData = UDPClient.readPacketData(packetFromElevator);
		ElevatorRequest completedRequest = ElevatorRequest.decode(completedRequestData);
		printLog(String.format("RECEIVE_COMPLETED -- %s", completedRequest));
		schedulerContext.addCompletedElevatorRequests(completedRequest);
	}

	/**
	 * Send the completed request to the floor.
	 * 
	 * @param completedRequest ElevatorRequest, elevator request object
	 * @throws IOException
	 */
	public void sendCompletedElevatorRequest(ElevatorRequest completedRequest) throws IOException {
		Thread task;
		task = new Thread(new Runnable() {
			@Override
			public void run() {
				byte[] data;
				try {
					UDPClient socket = new UDPClient();
					data = completedRequest.encode();
					socket.sendMessage(data, simulatorConfiguration.FLOOR_SUBSYSTEM_HOST,
							simulatorConfiguration.FLOOR_SUBSYSTEM_COMPLETED_REQ_PORT);
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				schedulerContext.onRequestSent();
			}
		});
		task.start();
	}

	/**
	 * Getter for pendingRequestListenerThread.
	 * 
	 * @return Thread, pending request listener thread
	 */
	public Thread getPendingRequestListenerThread() {
		return pendingRequestListenerThread;
	}

	/**
	 * Getter for arrivalRequestListenerThread.
	 * 
	 * @return Thread, arrival request listener thread
	 */
	public Thread getArrivalRequestListenerThread() {
		return arrivalRequestListenerThread;
	}

	/**
	 * Getter for completedRequestListenerThread.
	 * 
	 * @return Thread, completed request listener thread
	 */
	public Thread getCompletedRequestListenerThread() {
		return completedRequestListenerThread;
	}

	/**
	 * Getter for the configuration of this class.
	 * 
	 * @return SimulatorConfiguration, configuration parameters required
	 */
	public SimulatorConfiguration getSimulatorConfiguration() {
		return simulatorConfiguration;
	}

	/**
	 * Prints the console log to a text area.
	 * 
	 * @param message String, the string to be displayed
	 */
	private void printLog(String message) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		String output = String.format("[%s] : %s\n", currentTime, message);
		//System.out.println(output);
		logConsole.appendLog(output);
	}
	
}
