/**
 * 
 */
package main.java.scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;

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
	
	public SchedulerSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		schedulerContext = new SchedulerContext(this);
		// Registering the listening port for the socket
		pendingRequestSocket = new UDPClient(config.SCHEDULER_PENDING_REQ_PORT);
		arrivalRequestSocket = new UDPClient(config.SCHEDULER_ARRIVAL_REQ_PORT);
		completedRequestSocket = new UDPClient(config.SCHEDULER_COMPLETED_REQ_PORT);
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
						sendPendingRequest();
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
		DatagramPacket packetFromFloor = pendingRequestSocket.receiveMessage();
		byte[] floorRequestData = UDPClient.readPacketData(packetFromFloor);
		ElevatorRequest floorRequest = ElevatorRequest.decode(floorRequestData);
					
		schedulerContext.addPendingElevatorRequests(floorRequest);
	}
	
	/**
	 * Sending pending request to the elevator method
	 * @throws IOException
	 */
	public void sendPendingRequest() throws IOException {
		AssignedElevatorRequest request = schedulerContext.findBestElevatorToAssignRequest();
		
		if (request != null) {
			byte[] data = request.encode();
			
			UDPClient socket = new UDPClient();
			socket.sendMessage(data, simulatorConfiguration.ELEVATOR_SUBSYSTEM_HOST, 
					simulatorConfiguration.ELEVATOR_SUBSYSTEM_REQ_PORT);
		}
	}
	
	/**
	 * Receiving arrival notification from elevator method
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveArrivalNotification() throws ClassNotFoundException, IOException {
		DatagramPacket packetFromElevator = arrivalRequestSocket.receiveMessage();
		byte[] arrivalNotificationData = UDPClient.readPacketData(packetFromElevator);
		ElevatorStatus arrivalNotification = ElevatorStatus.decode(arrivalNotificationData);
		
		schedulerContext.modifyAvailableElevatorStatus(arrivalNotification.getElevatorId() - 1, arrivalNotification);
		sendArrivalNotification(arrivalNotification);
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
		DatagramPacket packetFromElevator =  completedRequestSocket.receiveMessage();
		byte[] completedRequestData = UDPClient.readPacketData(packetFromElevator);
		ElevatorRequest completedRequest = ElevatorRequest.decode(completedRequestData);
		
		schedulerContext.addCompletedElevatorRequests(completedRequest);
		sendCompletedElevatorRequest(completedRequest);
	}
	
	/**
	 * Send the completed request to the floor
	 * @param completedRequest
	 * @throws IOException
	 */
	private void sendCompletedElevatorRequest(ElevatorRequest completedRequest) throws IOException {
		byte[] data = completedRequest.encode();
		
		UDPClient socket = new UDPClient();
		socket.sendMessage(data, simulatorConfiguration.FLOOR_SUBSYSTEM_HOST, 
				simulatorConfiguration.FLOOR_SUBSYSTEM_REQ_PORT);
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
		UDPClient floorSim = new UDPClient(sc.FLOOR_SUBSYSTEM_REQ_PORT);
		ElevatorRequest req1 = new ElevatorRequest("07:01:15.000", 3, Direction.UP, 5);
		
		sThread.start();
		
		Thread.sleep(1000);
		floorSim.sendMessage(req1.encode(), sc.SCHEDULER_HOST, sc.SCHEDULER_PENDING_REQ_PORT);
	}
}
