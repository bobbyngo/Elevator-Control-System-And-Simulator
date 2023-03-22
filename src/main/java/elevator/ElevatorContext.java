/**
 * 
 */
package main.java.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.java.SimulatorConfiguration;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.TimeoutEvent;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorContext {
	private int id;
	private List<ElevatorRequest> externalRequests;
	private List<ElevatorRequest> internalRequests;
	private ElevatorState currentState;
	private int currentFloor;
	private Motor motor;
	private Direction direction;
	private Door door;
	// TODO: change to hashmap,. i dont like this
	private boolean[] buttonLamps;
	private boolean[] buttons;
	private HashMap<Integer, Boolean> elevatorButtonBoard = new HashMap<>();
	private Timer timer;
	
	private ElevatorSubsystem elevatorSubsystem;
	
	public ElevatorContext(ElevatorSubsystem subsystem, int id) {
		elevatorSubsystem = subsystem;
		
		// Initialize context
		this.id = id;
		currentFloor = 1;
		externalRequests = Collections.synchronizedList(new ArrayList<ElevatorRequest>());
		internalRequests = Collections.synchronizedList(new ArrayList<ElevatorRequest>());	// this is not needed, added anyways
		setDoors(Door.OPEN);
		setDirection(Direction.IDLE);
		setMotor(Motor.IDLE);
		buttonLamps = new boolean[subsystem.getConfig().NUM_FLOORS];
		buttons = new boolean[subsystem.getConfig().NUM_FLOORS];
		
		for (int i = 1; i <= elevatorSubsystem.getConfig().NUM_FLOORS; i ++) {
			elevatorButtonBoard.put(i, false);
		}
		
		// notify position & start state machine in another func
	}
	
	public void startElevator() {
		// XXX: make this a run() function? something to think about...
		// MUST CALL THIS FUNCTION TO START STATE MACHINE
		ElevatorStatus status;
		
		currentState = ElevatorState.start(this);
		System.out.println(this);
		// notify starting position
		elevatorSubsystem.sendArrivalNotification(new ElevatorStatus(this));
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
		// external requests @ current floor are moved to internal requests
		synchronized (externalRequests) {
			for (ElevatorRequest req : externalRequests) {
				if (req.getSourceFloor() == currentFloor) {
					externalRequests.remove(req);
					internalRequests.add(req);
					pressElevatorButton(req.getDestinationFloor());
				}
			}
		}
		return;
	}
	
	public void unloadPassengers() {
		// clear button at current floor
		// internal requests @ current floor are removed...
		// removed internal requests are sent to scheduler as completed requests
		for (ElevatorRequest req : internalRequests) {
			if (req.getDestinationFloor() == currentFloor) {
				internalRequests.remove(req);
				elevatorSubsystem.sendCompletedElevatorRequest(req);
			}
		}
		clearElevatorButton(currentFloor);
		return;
	}
	
	private void pressElevatorButton(int floor) {
		elevatorButtonBoard.put(floor, true);
	}
	
	private void clearElevatorButton(int floor) {
		elevatorButtonBoard.put(floor, false);
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

	public int getId() {
		return id;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getNumRequests() {
		// i should probably synchronize this...
		return internalRequests.size() + externalRequests.size();
	}
	
	public List<ElevatorRequest> getExternalRequests() {
		return externalRequests;
	}
	
	public List<ElevatorRequest> getInternalRequests() {
		return internalRequests;
	}
	
	/**
	 * Update the Elevator Lamp Light status by flipping the current status of the floor lamp
	 * @param floor the floor number that needs to update
	 */
	private void updateElevatorLampLight(Integer floor) {
		boolean currentLampStatus;
		if (elevatorButtonBoard.containsKey(floor)) {
			currentLampStatus = elevatorButtonBoard.get(floor);
			elevatorButtonBoard.put(floor, !currentLampStatus);
		}
	}
	
	private void updateElevatorLampLight(Integer floor, Boolean val) {
		if (elevatorButtonBoard.containsKey(floor) ) {
			elevatorButtonBoard.put(floor, val);
		}
	}
	
	/**
	 * Get the Elevator Lamp Light status
	 * @param floor the button number 
	 * @return boolean, true when lamp is on, false when lamp is off
	 */
	public boolean getElevatorLampStatus(Integer floor) {
		return elevatorButtonBoard.get(floor);
	}
	
	/**
	 * Method that getting all the selected floors in the elevator
	 * @return a hashmap containing the all the selected floors
	 */
	private ArrayList<Integer> getAllSelectedFloors(){
		ArrayList<Integer> allSelectedFloors = new ArrayList<>();
		for (Integer floorNumber : elevatorButtonBoard.keySet()) {
			if (elevatorButtonBoard.get(floorNumber)) {
				allSelectedFloors.add(floorNumber);
			}
		}
		// could return a null hashmap
		return allSelectedFloors;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public Direction calculateNextDirection() {
		// approach:
		// if going up, you want to keep going up
		//	stop going up when there are no more requests above you going UP
		// if going down, you want to keep going down
		//	stop going down when there are no more requests below you going DOWN
		// if idle, ... TBD
		Direction newDirection;
		boolean continueSweepingUp, continueSweepingDown;
		
		continueSweepingUp = shouldContinueSweepingUp();
		continueSweepingDown = shouldContinueSweepingDown();
		
		switch (direction) {
		case UP:
			if (continueSweepingUp) return Direction.UP;
			if (continueSweepingDown) return Direction.DOWN;
		case DOWN:
			if (continueSweepingDown) return Direction.DOWN;
			if (continueSweepingUp) return Direction.UP;
		case IDLE:
			// TODO: pick based on # of jobs? copying UP for now
			if (continueSweepingUp) return Direction.UP;
			if (continueSweepingDown) return Direction.DOWN;
		default:
			return Direction.IDLE;
		}
	}
	
	private boolean shouldContinueSweepingUp() {
		// check internal (using button board) and external
		ArrayList<Integer> selectedFloors = getAllSelectedFloors();
		boolean continueSweeping = false;
		
		for (int selectedFloor : selectedFloors) {
			if (selectedFloor > currentFloor) {
				return true;
			}
		}
		
		synchronized (externalRequests) {
			for (ElevatorRequest pendingReq : externalRequests) {
				if (pendingReq.getSourceFloor() > currentFloor && pendingReq.getDirection() == Direction.UP) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean shouldContinueSweepingDown() {
		ArrayList<Integer> selectedFloors = getAllSelectedFloors();
		boolean continueSweeping = false;
		
		for (int selectedFloor : selectedFloors) {
			if (selectedFloor < currentFloor) {
				return true;
			}
		}
		
		synchronized (externalRequests) {
			for (ElevatorRequest pendingReq : externalRequests) {
				if (pendingReq.getSourceFloor() < currentFloor && pendingReq.getDirection() == Direction.DOWN) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// small visual test
		ElevatorSubsystem s = new ElevatorSubsystem(new SimulatorConfiguration("./src/main/resources/config.properties"));
	}
}
