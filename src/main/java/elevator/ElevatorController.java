package main.java.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorState;

/**
 * This class represents the control system for the elevator requests.
 * @author Trong Nguyen
 * @version 1.0
 */
public class ElevatorController implements Runnable {

	private final static int MAX_ELEVATORS = 20;
	private final static int MAX_FLOORS = 50; // Put here for now.
	private static List<Elevator> elevatorList;
	
	/**
	 * Constructor for ElevatorController.
	 */
	public ElevatorController() {
		elevatorList = new ArrayList<>();
		initializeElevators();
	}
	
	/**
	 * Initialize the elevator threads in the system.
	 */
	private void initializeElevators() {
		for (int i = 0; i < MAX_ELEVATORS; i++) {
			Elevator elevator = new Elevator(i);
			Thread thread = new Thread(elevator);
			thread.start();
			elevatorList.add(elevator);
		}
	}
	
	/**
	 * Selects the closest elevator available to the requested floor.
	 * @param elevatorRequest
	 * @return elevator
	 */
	public synchronized Elevator selectElevator(ElevatorRequest elevatorRequest) {
		Elevator elevator = null;
		ElevatorState elevatorState = elevatorRequest.getDirection();
		int requestFloor = elevatorRequest.getFloorRequest();
		int destinationFloor = elevatorRequest.getFloorDestination();
		elevator = findElevator(elevatorState, requestFloor, destinationFloor);
		
		notifyAll();
		return elevator;
	}
	
	/**
	 * Finds the closest elevator to the current floor.
	 * @param elevatorState
	 * @param requestFloor
	 * @param desinationFloor
	 * @return nearestElevator
	 */
	private static Elevator findElevator(ElevatorState elevatorState, int requestFloor, int desinationFloor) {
		// For iteration 1: Finds the closes elevator no matter the direction.
		Elevator nearestElevator = null;
		int nearestDistance = desinationFloor;
		for (Elevator elevator : elevatorList) {
			int currentFloor = elevator.getCurrentFloor();
			int distance = Math.abs(desinationFloor - currentFloor);
			if (distance < nearestDistance) {
				nearestDistance = distance;
				nearestElevator = elevator;
			}
		}
		return nearestElevator;
	}
	
	/**
	 * Scan the list of requested floors by an elevator and determines the traversal path.
	 * @param requestList
	 * @param currentFloor
	 * @param direction
	 */
	private void scan(List<Integer> requestList, int currentFloor, ElevatorState direction) {
		int stopCounter = 0;
		int destinationFloor;
		List<Integer> upRequest = new ArrayList<>();
		List<Integer> downRequest = new ArrayList<>();
		List<Integer> traversePath = new ArrayList<>();
		
		if (direction.equals(ElevatorState.UP)) {
			upRequest.add(0);
		} else if (direction.equals(ElevatorState.DOWN)) {
			downRequest.add(MAX_ELEVATORS - 1);
		}
		
		for (int i = 0; i < requestList.size(); i++) {
			if (requestList.get(i) < currentFloor) {
				upRequest.add(requestList.get(i));
			} else if (requestList.get(i) > currentFloor) {
				downRequest.add(requestList.get(i));
			}
		}
		
		Collections.sort(upRequest);
		Collections.sort(downRequest);
		
		int passes = 2;
		while (passes-- > 0) {
			if (direction.equals(ElevatorState.UP)) {
				for (int i = upRequest.size() - 1; i >= 0; i--) {
					destinationFloor = upRequest.get(i);
					traversePath.add(destinationFloor);
					stopCounter++;
					currentFloor = destinationFloor;
				}
				direction = ElevatorState.DOWN;
			} else if (direction.equals(ElevatorState.DOWN)) {
				for (int i = 0; i < downRequest.size(); i++) {
					destinationFloor = downRequest.get(i);
					traversePath.add(destinationFloor);
					stopCounter++;
					currentFloor = destinationFloor;
				}
				direction = ElevatorState.UP;
			}
		}
		System.out.println("Total number of floors stops: " + stopCounter);
		System.out.println("Traverse path is: ");
		for (int i = 0; i < traversePath.size(); i++) {
			System.out.print(traversePath.get(i));
		}
	}
	
	
	/**
	 * Run method override from Runnable class.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
