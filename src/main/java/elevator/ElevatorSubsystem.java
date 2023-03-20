/**
 * 
 */
package main.java.elevator;

import java.util.ArrayList;

import main.java.SimulatorConfiguration;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;

// FIXME: remove @author automatically generated
/**
 * Controller. Routes requests to respective elevators.
 * Handles communication aspect.
 * @author Zakaria Ismail
 *
 */
public class ElevatorSubsystem {
	// TODO: change to hashmap to not deal with indexing issues
	private ArrayList<ElevatorContext> elevators;
	private SimulatorConfiguration simulatorConfiguration;
	private Thread requestListenerThread;
	//private UDPClient udpClient;
	
	public ElevatorSubsystem(SimulatorConfiguration config) {
		ElevatorContext elevator;
		
		elevators = new ArrayList<>();
		simulatorConfiguration = config;
		
		// 1-index elevator identification
		// FIXME: change to concurrent initialization? (TBD)
		for (int i=1; i<simulatorConfiguration.NUM_ELEVATORS; i++) {
			elevator = new ElevatorContext(this, i);
			elevator.startElevator();
			elevators.add(elevator);
		}
		
		// Start request fetching
		requestListenerThread = new Thread(new RequestListenerTask(this));
		requestListenerThread.start();
	}
	
	public SimulatorConfiguration getConfig() {
		return simulatorConfiguration;
	}
	
	public void receiveElevatorRequest() {
		// get elevator request: called by request receiver task
		//udp.send()
		//udp.receive()
		routeElevatorRequest(null);
		return;
	}
	
	private void routeElevatorRequest(AssignedElevatorRequest request) {
		int elevatorId;
		ElevatorContext ctx;
		
		elevatorId = request.getElevatorId();
		ctx = elevators.get(elevatorId-1);
		ctx.addExternalRequest(request);
	}
	
	public void sendCompletedElevatorRequest(ElevatorRequest request) {
		// send elevator request: called by context
	}
	
	public void sendArrivalNotification(ElevatorContext ctx) {
		// send arrival notification: 
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
