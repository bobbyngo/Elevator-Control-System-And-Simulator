/**
 * 
 */
package main.java.elevator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.ConfigurationSpi;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;

/**
 * Controller. Routes requests to respective elevators.
 * Handles communication aspect.
 * @author Zakaria Ismail
 *
 */
public class ElevatorSubsystem implements Runnable {
	// TODO: change to hashmap to not deal with indexing issues
	private HashMap<Integer, ElevatorContext> elevators;
	private SimulatorConfiguration simulatorConfiguration;
	private Thread requestListenerThread;
	private UDPClient udpRequestReceiver;
	
	/**
	 * Constructor for Elevator Subsystem
	 * @param config
	 */
	public ElevatorSubsystem(SimulatorConfiguration config) {
		ElevatorContext elevator;
		
		elevators = new HashMap<>();
		simulatorConfiguration = config;
		udpRequestReceiver = new UDPClient(config.ELEVATOR_SUBSYSTEM_REQ_PORT);
		
		// 1-index elevator identification
		// FIXME: change to concurrent initialization? (TBD)
		for (int i=1; i<=simulatorConfiguration.NUM_ELEVATORS; i++) {
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
	 * Getter for the configuration of this class
	 * @return
	 */
	public SimulatorConfiguration getConfig() {
		return simulatorConfiguration;
	}
	
	/**
	 * Receiving request method
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveElevatorRequest() throws ClassNotFoundException, IOException {
		// NOTE: this is only called by receiving listener thread
		// raise all exceptions to the calling thread
		DatagramPacket receivePacket;
		AssignedElevatorRequest assignedRequest;	// TODO
		Thread requestHandler;

			// XXX: what do I put in the byte?
		//udpRequestReceiver.sendMessage(new byte[] {}, InetAddress.getByName(simulatorConfiguration.SCHEDULER_HOST), 
		//		simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
		receivePacket = udpRequestReceiver.receiveMessage();	// TODO: add a timeout perhaps? this would allow any-order bootup
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
	 * Update the request to the Elevator context
	 * @param request
	 */
	private void routeElevatorRequest(AssignedElevatorRequest request) {
		int elevatorId;
		ElevatorContext ctx;
		
		elevatorId = request.getElevatorId();
		ctx = elevators.get(elevatorId);
		ctx.addExternalRequest(request);
	}
	
	/**
	 * Sending completed request message method to the Scheduler
	 * @param request
	 */
	public void sendCompletedElevatorRequest(ElevatorRequest request) {
		// send elevator request: called by context
		// TODO: spin up a new thread to run this code
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(request.encode(), simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_COMPLETED_REQ_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageClient.close();
	}
	
	/**
	 * Sending arrival notification method to the Scheduler
	 * @param status
	 */
	public void sendArrivalNotification(ElevatorStatus status) {
		// send arrival notification: 
		// TODO: spin up a new thread to run this code
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(status.encode(), simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_ARRIVAL_REQ_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageClient.close();
	}
	
	/**
	 * Send the elevator requests to the Scheduler
	 * @param requests
	 */
	public void returnElevatorRequests(List<ElevatorRequest> requests) {
		UDPClient messageClient = new UDPClient();
		
		for (ElevatorRequest request : requests) {
			try {
				messageClient.sendMessage(request.encode(), simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		messageClient.close();
	}
	
	/**
	 * @param args
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
