/**
 * 
 */
package main.java.elevator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import main.java.SimulatorConfiguration;
import main.java.dto.ElevatorRequest;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.TimeoutEvent;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorContext {
	private int id;
	private ArrayList<ElevatorRequest> externalRequests;
	private ArrayList<ElevatorRequest> internalRequests;
	private ElevatorState currentState;
	private int currentFloor;
	private Motor motor;
	private Direction direction;
	private Door door;
	private boolean[] buttonLamps;
	private boolean[] buttons;
	private Timer timer;
	
	private ElevatorSubsystem elevatorSubsystem;
	
	public ElevatorContext(ElevatorSubsystem subsystem, int id) {
		elevatorSubsystem = subsystem;
		
		// Initialize context
		this.id = id;
		currentFloor = 1;
		externalRequests = new ArrayList<>();
		internalRequests = new ArrayList<>();
		setDoors(Door.OPEN);
		setDirection(Direction.IDLE);
		setMotor(Motor.IDLE);
		buttonLamps = new boolean[subsystem.getConfig().NUM_FLOORS];
		buttons = new boolean[subsystem.getConfig().NUM_FLOORS];
		
		// notify position & start state machine in another func
	}
	
	public void startElevator() {
		// MUST CALL THIS FUNCTION TO START STATE MACHINE
		currentState = ElevatorState.start(this);
		System.out.println(this);
		// notify starting position
	}
	
	public void addExternalRequest(ElevatorRequest request) {
		externalRequests.add(request);
		onRequestReceived();
	}
	
	public void onRequestReceived() {
		synchronized (currentState) {
			System.out.println(String.format("Elevator#%d Event: Request Received", id));
			currentState = currentState.handleRequestReceived();
			System.out.println(this);
			// update view...
		}
	}
	
	public void onTimeout(TimeoutEvent event) {
		synchronized (currentState) {
			System.out.println(String.format("Elevator#%d %s", id, event));
			currentState = currentState.handleTimeout();
			System.out.println(this);
			// update view...
		}
	}
	
	public void loadPassengers() {
		// when passengers are loaded, press button
		return;
	}
	
	public void unloadPassengers() {
		// clear button at current floor
		return;
	}
	
	private void pressElevatorButton(int floor) {
		buttons[floor-1] = true;
		buttonLamps[floor-1] = true;
	}
	
	private void clearElevatorButton(int floor) {
		buttons[floor-1] = false;
		buttonLamps[floor-1] = false;
	}
	
	public void setTimer(TimerTask task, int delay) {
		if (timer != null) {
			// a timer is already set... call killTimer() first
			return;
		}
		
		timer = new Timer();
		timer.schedule(task, delay);
	}
	
	public void killTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}
	
	public void setDirection(Direction d) {
		direction = d;
	}
	
	public void setDoors(Door d) {
		door = d;
	}
	
	public void setMotor(Motor m) {
		motor = m;
	}
	
	public void incrementCurrentFloor() {
		// TODO: add condition checks and throw exception
		// if invalid floor?... the state machine should not
		// allow transition to MOVING state if floor above
		// doesn't exist...
		currentFloor++;
	}
	
	public void decrementCurrentFloor() {
		currentFloor--;
	}
	
	public SimulatorConfiguration getConfig() {
		return elevatorSubsystem.getConfig();
	}
	
	@Override
	public String toString() {
		return String.format(
				"Elevator#%d{%d,%s,%s,%s,%s}",
				id, currentFloor, currentState, direction, motor, door);
	}
	
	public static void main(String[] args) {
		// small visual test
		ElevatorSubsystem s = new ElevatorSubsystem(new SimulatorConfiguration("./src/main/resources/config.properties"));
	}
}
