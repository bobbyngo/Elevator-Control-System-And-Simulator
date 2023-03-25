/**
 * 
 */
package main.java.scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.ElevatorContext;

/**
 * @author Bobby Ngo
 *
 */
public class SchedulerSubsystem {
	private SchedulerContext schedulerContext;
	private SimulatorConfiguration simulatorConfiguration;
	
	// 3 sockets and 3 threads for listening to the request
	private UDPClient pendingRequestSocket;
	private UDPClient arrivalRequestSocket;
	private UDPClient completedRequestSocket;
	
	// Unsure about this
	private UDPClient notifySocket;
	
	// private Thread floorRequestListenerThread;
	private Thread pendingRequestListenerThread;
	private Thread arrivalRequestListenerThread;
	private Thread completedRequestListenerThread;
	
	public SchedulerSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		// Registering the listening port for the socket
		pendingRequestSocket = new UDPClient(config.SCHEDULER_PENDING_REQ_PORT);
		arrivalRequestSocket = new UDPClient(config.SCHEDULER_ARRIVAL_REQ_PORT);
		completedRequestSocket = new UDPClient(config.SCHEDULER_COMPLETED_REQ_PORT);
	}
	
	private void initializeThreads() {
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
	}
	
	public void receivePendingRequest() throws ClassNotFoundException, IOException {
		DatagramPacket packetFromFloor = pendingRequestSocket.receiveMessage();
		byte[] floorRequestData = UDPClient.readPacketData(packetFromFloor);
		ElevatorRequest floorRequest = ElevatorRequest.decode(floorRequestData);
					
		schedulerContext.addPendingElevatorRequests(floorRequest);
	}
	
	public void sendPendingRequest() throws IOException {
		AssignedElevatorRequest request = schedulerContext.findBestElevatorToAssignRequest();
		
		if (request != null) {
			byte[] data = request.encode();
			
			UDPClient socket = new UDPClient();
			socket.sendMessage(data, simulatorConfiguration.ELEVATOR_SUBSYSTEM_HOST, 
					simulatorConfiguration.ELEVATOR_SUBSYSTEM_REQ_PORT);
		}
	}
	
	public void receiveArrivalNotification() throws ClassNotFoundException, IOException {
		DatagramPacket packetFromElevator = arrivalRequestSocket.receiveMessage();
		byte[] arrivalNotificationData = UDPClient.readPacketData(packetFromElevator);
		ElevatorStatus arrivalNotification = ElevatorStatus.decode(arrivalNotificationData);
		
		schedulerContext.modifyAvailableElevatorContexts(arrivalNotification.getElevatorId() - 1, arrivalNotification);
		sendArrivalNotification(arrivalNotification);
	}
	
	private void sendArrivalNotification(ElevatorStatus arrivalNotification) throws IOException {
		byte[] data = arrivalNotification.encode();
		
		UDPClient socket = new UDPClient();
		socket.sendMessage(data, simulatorConfiguration.FLOOR_SUBSYSTEM_HOST, 
				simulatorConfiguration.FLOOR_SUBSYSTEM_REQ_PORT);
	}
	
	public void receiveCompletedElevatorRequest() throws ClassNotFoundException, IOException {
		DatagramPacket packetFromElevator =  completedRequestSocket.receiveMessage();
		byte[] completedRequestData = UDPClient.readPacketData(packetFromElevator);
		ElevatorRequest completedRequest = ElevatorRequest.decode(completedRequestData);
		
		schedulerContext.addCompletedElevatorRequests(completedRequest);
		sendCompletedElevatorRequest(completedRequest);
	}
	
	private void sendCompletedElevatorRequest(ElevatorRequest completedRequest) throws IOException {
		byte[] data = completedRequest.encode();
		
		UDPClient socket = new UDPClient();
		socket.sendMessage(data, simulatorConfiguration.FLOOR_SUBSYSTEM_HOST, 
				simulatorConfiguration.FLOOR_SUBSYSTEM_REQ_PORT);
	}

	public Thread getPendingRequestListenerThread() {
		return pendingRequestListenerThread;
	}


	public Thread getArrivalRequestListenerThread() {
		return arrivalRequestListenerThread;
	}


	public Thread getCompletedRequestListenerThread() {
		return completedRequestListenerThread;
	}
	
	public SimulatorConfiguration getSimulatorConfiguration() {
		return simulatorConfiguration;
	}

	public static void main(String[] args) {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/main/resources/config.properties");
		SchedulerSubsystem s = new SchedulerSubsystem(sc);
	}
}
