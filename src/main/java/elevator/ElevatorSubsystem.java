/**
 * 
 */
package main.java.elevator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;

// FIXME: remove @author automatically generated
/**
 * Controller. Routes requests to respective elevators.
 * Handles communication aspect.
 * @author Zakaria Ismail
 *
 */
public class ElevatorSubsystem {
	// TODO: change to hashmap to not deal with indexing issues
	private HashMap<Integer, ElevatorContext> elevators;
	private SimulatorConfiguration simulatorConfiguration;
	private Thread requestListenerThread;
	private UDPClient udpRequestReceiver;
	//private UDPClient udpClient;
	
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
	
	public void startElevatorSubsystem() {
		// Start request fetching
		requestListenerThread = new Thread(new RequestListenerTask(this));
		requestListenerThread.start();
	}
	
	public SimulatorConfiguration getConfig() {
		return simulatorConfiguration;
	}
	
	public void receiveElevatorRequest() throws ClassNotFoundException, IOException {
		// NOTE: this is only called by receiving listener thread
		// raise all exceptions to the calling thread
		DatagramPacket receivePacket;
		AssignedElevatorRequest assignedRequest;	// TODO

			// XXX: what do I put in the byte?
		udpRequestReceiver.sendMessage(new byte[] {}, InetAddress.getByName(simulatorConfiguration.SCHEDULER_HOST), 
				simulatorConfiguration.SCHEDULER_PENDING_REQ_PORT);
		receivePacket = udpRequestReceiver.receiveMessage();	// TODO: add a timeout perhaps? this would allow any-order bootup
		assignedRequest = AssignedElevatorRequest.decode(UDPClient.readPacketData(receivePacket));
		//System.out.println(receivePacket.getData());
		//System.out.println(assignedRequest);
		routeElevatorRequest(assignedRequest);
		return;
	}
	
	private void routeElevatorRequest(AssignedElevatorRequest request) {
		int elevatorId;
		ElevatorContext ctx;
		
		elevatorId = request.getElevatorId();
		ctx = elevators.get(elevatorId);
		ctx.addExternalRequest(request);
	}
	
	public void sendCompletedElevatorRequest(ElevatorRequest request) {
		// send elevator request: called by context
		// TODO: spin up a new thread to run this code
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(request.encode(), simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_COMPLETED_REQ_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendArrivalNotification(ElevatorStatus status) {
		// send arrival notification: 
		// TODO: spin up a new thread to run this code
		UDPClient messageClient = new UDPClient();
		try {
			messageClient.sendMessage(status.encode(), simulatorConfiguration.SCHEDULER_HOST, simulatorConfiguration.SCHEDULER_ARRIVAL_REQ_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatorConfiguration configuration;
		ElevatorSubsystem subsystem;
		
		configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		subsystem = new ElevatorSubsystem(configuration);
	}

}
