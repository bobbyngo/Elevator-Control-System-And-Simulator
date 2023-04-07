package main.java.scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.swing.JTextArea;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.gui.GUI;

/**
 * Representing the Scheduler Subsystem
 * @author Bobby Ngo, Patrick Liu
 *
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
	
	private JTextArea schedulerLog;
	private GUI gui;
	
	public SchedulerSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		schedulerContext = new SchedulerContext(this);
		// Registering the listening port for the socket
		pendingRequestSocket = new UDPClient(config.SCHEDULER_PENDING_REQ_PORT);
		arrivalRequestSocket = new UDPClient(config.SCHEDULER_ARRIVAL_REQ_PORT);
		completedRequestSocket = new UDPClient(config.SCHEDULER_COMPLETED_REQ_PORT);
		
		schedulerLog = new JTextArea();
		gui = new GUI(simulatorConfiguration);
		gui.displayConsole(this.getClass().getSimpleName(), schedulerLog);
		gui.displayGUI();
	}
	
	/**
	 * Starting threads with the followed steps
	 */
	public void run() {
		pendingRequestListenerThread =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						receivePendingRequest();
						//sendPendingRequest();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		arrivalRequestListenerThread =  new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						receiveArrivalNotification();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		completedRequestListenerThread =  new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						receiveCompletedElevatorRequest();
					} catch (Exception e) {
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
	 * Receiving pending request from Floor method
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receivePendingRequest() throws ClassNotFoundException, IOException {
		Thread task;
		DatagramPacket packetFromFloor = pendingRequestSocket.receiveMessage();
		byte[] floorRequestData = UDPClient.readPacketData(packetFromFloor);
		ElevatorRequest floorRequest = ElevatorRequest.decode(floorRequestData);
		
		task = new Thread(new Runnable() {
			@Override
			public void run() {
				schedulerContext.addPendingElevatorRequests(floorRequest);
			}
		});
		task.start();
	}
	
	/**
	 * Sending pending request to the elevator method
	 * @throws IOException
	 */
	public void sendPendingRequest(AssignedElevatorRequest request) throws IOException {
		//AssignedElevatorRequest request = schedulerContext.findBestElevatorToAssignRequest();
		Thread task;
		
		task = new Thread(new Runnable() {
			@Override
			public void run() {
				if (request != null) {
					byte[] data;
					try {
						data = request.encode();
						UDPClient socket = new UDPClient();
						socket.sendMessage(data, simulatorConfiguration.ELEVATOR_SUBSYSTEM_HOST, 
								simulatorConfiguration.ELEVATOR_SUBSYSTEM_REQ_PORT);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					print(String.format("Sent AssignedElevatorRequest: %s", request));
					schedulerContext.onRequestSent();
					
				}
			}
		});
		task.start();
		
	}
	
	/**
	 * Receiving arrival notification from elevator method
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
				schedulerContext.modifyAvailableElevatorStatus(arrivalNotification.getElevatorId() - 1, arrivalNotification);
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
	 * Sending arrival notification to Floor method
	 * @param arrivalNotification
	 * @throws IOException
	 */
	private void sendArrivalNotification(ElevatorStatus arrivalNotification) throws IOException {
		byte[] data = arrivalNotification.encode();
		
		UDPClient socket = new UDPClient();
		socket.sendMessage(data, simulatorConfiguration.FLOOR_SUBSYSTEM_HOST, 
				simulatorConfiguration.FLOOR_SUBSYSTEM_REQ_PORT);
	}
	
	/**
	 * Receiving completed request method from the elevator
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveCompletedElevatorRequest() throws ClassNotFoundException, IOException {
		Thread task;
		DatagramPacket packetFromElevator =  completedRequestSocket.receiveMessage();
		byte[] completedRequestData = UDPClient.readPacketData(packetFromElevator);
		ElevatorRequest completedRequest = ElevatorRequest.decode(completedRequestData);
		
//		task = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("Received completed request %s" + completedRequest);
//				schedulerContext.addCompletedElevatorRequests(completedRequest);
//			}
//		});
//		task.start();
		print("Received completed request " + completedRequest);
		schedulerContext.addCompletedElevatorRequests(completedRequest);
	}
	
	/**
	 * Send the completed request to the floor
	 * @param completedRequest
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
							simulatorConfiguration.FLOOR_SUBSYSTEM_REQ_PORT);
				} catch (IOException e) {
					e.printStackTrace();
				}
				schedulerContext.onRequestSent();
			}
		});
		task.start();
	}

	/**
	 * Getter for pendingRequestListenerThread()
	 * @return
	 */
	public Thread getPendingRequestListenerThread() {
		return pendingRequestListenerThread;
	}


	/**
	 * Getter for arrivalRequestListenerThread
	 * @return
	 */
	public Thread getArrivalRequestListenerThread() {
		return arrivalRequestListenerThread;
	}


	/**
	 * Getter for completedRequestListenerThread
	 * @return
	 */
	public Thread getCompletedRequestListenerThread() {
		return completedRequestListenerThread;
	}
	
	/** 
	 * Getter for the configuration of this class
	 * @return
	 */
	public SimulatorConfiguration getSimulatorConfiguration() {
		return simulatorConfiguration;
	}

	public static void main(String[] args) throws ParseException, InterruptedException, UnknownHostException, IOException {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/main/resources/config.properties");
		SchedulerSubsystem s = new SchedulerSubsystem(sc);
		Thread sThread = new Thread(s);
		sThread.start();
	}
	
	public void print(String message) {
		System.out.println(message);
		schedulerLog.append(" " + message + "\n");
	}
	
}
