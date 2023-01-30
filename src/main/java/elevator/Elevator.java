package main.java.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorState;
import main.java.scheduler.Scheduler;

/**
 * This class represents an Elevator.
 * @author Trong Nguyen
 * @version 1.0
 */
public class Elevator implements Runnable {
	
	private int id;
	private int currentFloor;
	private ElevatorState elevatorState;
	private boolean inOperation;
	private List<ElevatorRequest> requestsQueue;
	private Scheduler scheduler;
		
	/**
	 * Constructor for Elevator class.
	 * @param id	 int, the elevator id
	 */
	public Elevator(int id) {
		this.id = id;
		setInOperation(true);
		requestsQueue = Collections.synchronizedList(new ArrayList<>());
	}
	
	/**
	 * Get elevator id.
	 * @return int, elevator id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Get the current floor where the elevator is located.
	 * @return int, current elevator floor
	 */
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	/**
	 * Get elevator state.
	 * @return ElevatorState, the state of the elevator
	 */
	public ElevatorState getElevatorState( ) {
		return this.elevatorState;
	}
	
	/**
	 * Set the current elevator floor.
	 * @param currentFloor, the current floor
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	/**
	 * Set the elevator state.
	 * @param elevatorState, the state of the elevator
	 */
	public void setElevatorState(ElevatorState elevatorState) {
		this.elevatorState = elevatorState;
	}
	
	/**
	 * Check whether the elevation is operational.
	 * @return boolean, true if the elevation is in operation
	 */
	public boolean inOperation() {
		return this.inOperation;
	}
	
	/**
	 * Set the elevator state in operation.
	 * @param operational elevatorState, the state of the elevator
	 */
	public void setInOperation(boolean operational) {
		this.inOperation = operational;
		
		if (!(operational)) {
			setElevatorState(ElevatorState.STATIONARY);
		}
	}
	
	/**
	 * Move the elevator UP or DOWN.
	 */
	public synchronized void move() {
		while (requestsQueue.size() != 0) {
			scheduler.dispatchRequest();
		}
		this.setElevatorState(ElevatorState.IDLE);
		return;
	}
	
	/**
	 * Run method override from Runnable class.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			if (inOperation()) {
				try {
					move();
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
	}

}
