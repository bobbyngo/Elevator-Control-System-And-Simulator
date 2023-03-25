/**
 * 
 */
package main.java.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.ElevatorRequest;
import main.java.elevator.ElevatorContext;

/**
 * @author Bobby Ngo
 *
 */
public class SchedulerSubsystem {
	private SchedulerContext schedulerContext;
	private SimulatorConfiguration simulatorConfiguration;
	
	// 4 sockets and 4 threads for listening to the request
	private UDPClient floorRequestSocket;
	private UDPClient pendingRequestSocket;
	private UDPClient arrivalRequestSocket;
	private UDPClient completedRequestSocket;
	
	// Unsure about this
	private UDPClient notifySocket;
	
	private Thread floorRequestListenerThread;
	private Thread pendingRequestListenerThread;
	private Thread arrivalRequestListenerThread;
	private Thread completedRequestListenerThread;
	
	// storing all the elevators that are available
	private List<ElevatorContext> availableElevatorContexts;
	// pending elevators requests
	private List<ElevatorRequest> pendingElevatorRequests;
	// Elevator requests that completed
	private List<ElevatorRequest> completedElevatorRequests;
	
	public SchedulerSubsystem(SimulatorConfiguration config) {
		simulatorConfiguration = config;
		// Registering the listening port for the socket
		floorRequestSocket = new UDPClient(config.SCHEDULER_FLOOR_REQ_PORT);
		pendingRequestSocket = new UDPClient(config.SCHEDULER_PENDING_REQ_PORT);
		arrivalRequestSocket = new UDPClient(config.SCHEDULER_ARRIVAL_REQ_PORT);
		completedRequestSocket = new UDPClient(config.SCHEDULER_COMPLETED_REQ_PORT);
		
		// make sure that 4 scheduler threads use the same instance of these 3 array list
		availableElevatorContexts = Collections.synchronizedList(new ArrayList<>());
		pendingElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		completedElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		
	}
	
	public void receiveFloorRequest() {
		// receive elevator request 
	}
	
	public void sendFloorRequest() {
		// send floor request
	}
	
	public void receivePendingRequest() {
		// receive pending request 
	}
	
	public void sendPendingRequest() {
		// send pending request
	}
	
	public void receiveArrivalNotification() {
		// receive arrival noti 
	}
	
	public void sendArrivalNotification() {
		// send arrival noti
	}
	
	public void receiveCompletedElevatorRequest() {
		// receive completed request 
	}
	
	public void sendCompletedElevatorRequest() {
		// send completed request
	}

	public Thread getFloorRequestListenerThread() {
		return floorRequestListenerThread;
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

	public List<ElevatorContext> getAvailableElevatorContexts() {
		return availableElevatorContexts;
	}

	public List<ElevatorRequest> getPendingElevatorRequests() {
		return pendingElevatorRequests;
	}

	public List<ElevatorRequest> getCompletedElevatorRequests() {
		return completedElevatorRequests;
	}

	public static void main(String[] args) {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/main/resources/config.properties");
		SchedulerSubsystem s = new SchedulerSubsystem(sc);
	}
}
