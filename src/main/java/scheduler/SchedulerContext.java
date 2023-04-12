package main.java.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.Direction;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.scheduler.state.SchedulerState;

/**
 * Responsible for routing each elevator to requested floors and coordinating
 * elevators in such a way to minimize waiting times for people moving between
 * floors (avoiding starvation).
 * 
 * @author Bobby Ngo, Patrick Liu
 */
public class SchedulerContext {

	private SchedulerSubsystem schedulerSubsystem;

	// storing all the elevators that are available
	private List<ElevatorStatus> availableElevatorStatus;
	// pending elevators requests
	private List<ElevatorRequest> pendingElevatorRequests;
	// elevator requests that completed
	private List<ElevatorRequest> completedElevatorRequests;
	private Map<Integer,Integer> sameSrcUpCache; // key: srcFloor, value: elevator id, cache for up reqs
	private Map<Integer,Integer> sameSrcDownCache; // key: srcFloor, value: elevator id, cache for down reqs
	private SchedulerState currentState;

	/**
	 * Constructor for Scheduler Context.
	 * 
	 * @param schedulerSubsystem SchedulerSubsystem, the elevator subsystem
	 */
	public SchedulerContext(SchedulerSubsystem schedulerSubsystem) {
		this.schedulerSubsystem = schedulerSubsystem;
		// ensure that 4 scheduler threads use the same instance of these 3 array list
		availableElevatorStatus = Collections.synchronizedList(new ArrayList<>());
		pendingElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		completedElevatorRequests = Collections.synchronizedList(new ArrayList<>());
		
		sameSrcUpCache = Collections.synchronizedMap(new HashMap<Integer,Integer>());
		sameSrcDownCache = Collections.synchronizedMap(new HashMap<Integer,Integer>());

		for (int i = 1; i <= schedulerSubsystem.getSimulatorConfiguration().NUM_ELEVATORS; i++) {
			availableElevatorStatus.add(new ElevatorStatus(i));
		}
		currentState = SchedulerState.start(this);
		//System.out.println(String.format("Current state: %s", currentState));
	}

	/**
	 * Finds the closest elevator to the requested floor.
	 * 
	 * @param elevators   ArrayList, the list of elevators
	 * @param sourceFloor int, the source floor number
	 * @return ElevatorStatus, the status of the elevator
	 */
	public ElevatorStatus findTheClosestElevatorToRequestFloor(List<ElevatorStatus> elevators, int sourceFloor) {
		ElevatorStatus chosenElevatorStatus = null;
		// init the min value to the total floors
		int closestElevator = schedulerSubsystem.getSimulatorConfiguration().NUM_FLOORS;

		// find the smallest distance between the floor that the elevator locates and
		// the floor that request elevator
		for (ElevatorStatus status : elevators) {
			int differentBetweenElevatorAndRequest = Math.abs(status.getFloor() - sourceFloor);

			if (closestElevator > differentBetweenElevatorAndRequest) {
				differentBetweenElevatorAndRequest = closestElevator = differentBetweenElevatorAndRequest;
				chosenElevatorStatus = status;
			}
		}
		return chosenElevatorStatus;
	}

	/**
	 * Find the available idle elevator.
	 * 
	 * @param request ElevatorRequest, the elevator request object
	 * @return ElevatorStatus, the status of the elevator
	 */
	private ElevatorStatus findTheAvailableIdleElevator(ElevatorRequest request) {
		ArrayList<ElevatorStatus> idleElevatorStatus = new ArrayList<>();
		for (ElevatorStatus status : availableElevatorStatus) {
			if (status.getDirection() == Direction.IDLE && 
					status.getState() != ElevatorStateEnum.ELEVATOR_STUCK &&
					status.getState() != ElevatorStateEnum.DOORS_STUCK) {
				idleElevatorStatus.add(status);
			}
		}
		ElevatorStatus chosenElevatorStatus = null;

		// beginning of the program, all the elevators are idle, return a first elevator
		// FIXME: WHY!!!!!!!!!!!!
		if (idleElevatorStatus.size() == schedulerSubsystem.getSimulatorConfiguration().NUM_ELEVATORS) {
			chosenElevatorStatus = availableElevatorStatus.get(0);

		} else {
			chosenElevatorStatus = findTheClosestElevatorToRequestFloor(idleElevatorStatus, request.getSourceFloor());
		}
		return chosenElevatorStatus;
	}

	/**
	 * Method for finding all the elevators that are moving and make sure that its
	 * current distance is closest the most to the floor requesting the request.
	 * 
	 * @param direction             Direction, the direction of the elevator
	 * @param newRequestSourceFloor int, the new request source floor number
	 * @return ElevatorStatus, the status if the elevator
	 */
	private ElevatorStatus findTheAvailableMovingElevator(Direction direction, int newRequestSourceFloor) {
		// checked the available is empty already so no need to check again
		ElevatorStatus chosenElevatorStatus = null;
		ArrayList<ElevatorStatus> movingUpElevatorStatus = new ArrayList<>();
		ArrayList<ElevatorStatus> movingDownElevatorStatus = new ArrayList<>();
		for (ElevatorStatus status : availableElevatorStatus) {
			if (status.getState() != ElevatorStateEnum.DOORS_STUCK
					|| status.getState() != ElevatorStateEnum.ELEVATOR_STUCK) {
				// 1st priority: Elevator that is moving up and current floor <= source floor
				if (status.getDirection() == direction && direction == Direction.UP
						&& status.getFloor() <= newRequestSourceFloor) {
					movingUpElevatorStatus.add(status);
					// chosenElevatorStatus = status;
				}
				// 2nd priority: Elevator that is moving down and current floor >= source floor
				else if (status.getDirection() == direction && direction == Direction.DOWN
						&& status.getFloor() >= newRequestSourceFloor) {
					movingDownElevatorStatus.add(status);
					// chosenElevatorStatus = status;
				}
			}
		}
		// Find the closest distance of the elevator that is moving up and its current
		// floor is closest to the floor that request the elevator
		if (direction == Direction.UP) {
			chosenElevatorStatus = findTheClosestElevatorToRequestFloor(movingUpElevatorStatus, newRequestSourceFloor);
		} else if (direction == Direction.DOWN) {
			// Find the closest distance of the elevator that is moving down and its current
			// floor is closest to the floor that request the elevator
			chosenElevatorStatus = findTheClosestElevatorToRequestFloor(movingDownElevatorStatus,
					newRequestSourceFloor);
		}
		return chosenElevatorStatus;
	}

	/**
	 * Method for finding the best elevator following by the priority
	 * 
	 * @return AssignedElevatorRequest, the assigned elevator request
	 */
	public synchronized AssignedElevatorRequest findBestElevatorToAssignRequest() {
		AssignedElevatorRequest assignedElevatorRequest = null;
		if (availableElevatorStatus.size() == 0) {
			//System.out.println(this.getClass().getSimpleName() + ": There are no available elevators.");
		}
		if (pendingElevatorRequests.size() == 0) {
			//System.out.println(this.getClass().getSimpleName() + ": There are no elevator requests.");
		} else {
			ElevatorStatus chosenElevatorStatus = null;

			synchronized (pendingElevatorRequests) {
				ElevatorRequest request = null, selectedRequest = null;
				// Find the moving elevators
				for (int i = 0; i < pendingElevatorRequests.size(); i++) {
					request = pendingElevatorRequests.get(i);
					// use cache here; if there is cache hit, then set the elevator
					// status here and then break out of the loop
					chosenElevatorStatus = getSameSrcCacheElevator(request);
					if (chosenElevatorStatus != null) {
						System.out.println("Cache hit!");
						selectedRequest = request;
						break;
					}
					
					chosenElevatorStatus = findTheAvailableMovingElevator(request.getDirection(),
							request.getSourceFloor());

					if (chosenElevatorStatus != null) {
						System.out.println("Found available moving elevator1");
						assignedElevatorRequest = new AssignedElevatorRequest(chosenElevatorStatus.getElevatorId(),
								request);
						// set cache here
						setSameSrcCache(assignedElevatorRequest);
						selectedRequest = request;
						break;
					}
				}
				// Find the idle elevators
				if (chosenElevatorStatus == null) {
					for (int i = 0; i < pendingElevatorRequests.size(); i++) {
						request = pendingElevatorRequests.get(i);

						chosenElevatorStatus = findTheAvailableIdleElevator(request);

						if (chosenElevatorStatus != null) {
							System.out.println("Found available idle elevator!");
							assignedElevatorRequest = new AssignedElevatorRequest(chosenElevatorStatus.getElevatorId(),
									request);
							// set cache here
							setSameSrcCache(assignedElevatorRequest);
							selectedRequest = request;
							break;
						}
					}
				}
				
				if (chosenElevatorStatus == null) {
					// packet ping-pong time :3 - nvm fuck packet ping po
					System.out.println("Scheduler is picking FIRST AVAILABLE NON-STUCK elevator!");
					//int numOfElevators = schedulerSubsystem.getSimulatorConfiguration().NUM_ELEVATORS;
					//assignedElevatorRequest = new AssignedElevatorRequest((int)(Math.random() * numOfElevators + 1) , request);
					selectedRequest = pendingElevatorRequests.get(0);
					//for (ElevatorStatus elevatorStatus : availableElevatorStatus) {
					for (int i=0; i<availableElevatorStatus.size(); i++) {
						ElevatorStatus elevatorStatus = availableElevatorStatus.get(i);
						if (elevatorStatus.getState() != ElevatorStateEnum.ELEVATOR_STUCK) {
							assignedElevatorRequest = new AssignedElevatorRequest(elevatorStatus.getElevatorId() , request);
							break;
						}
					}
				}
				
				System.out.println("Scheduler selected: " + assignedElevatorRequest + " " + selectedRequest);
				if (selectedRequest != null && assignedElevatorRequest != null) {
					pendingElevatorRequests.remove(selectedRequest);
				}
			}
		}
		return assignedElevatorRequest;
	}
	
	private void setSameSrcCache(AssignedElevatorRequest assignedElevatorRequest) {
		if (assignedElevatorRequest.getDirection() == Direction.UP) {
			sameSrcUpCache.put(assignedElevatorRequest.getSourceFloor(), assignedElevatorRequest.getElevatorId());
		} else if (assignedElevatorRequest.getDirection() == Direction.DOWN) {
			sameSrcDownCache.put(assignedElevatorRequest.getSourceFloor(), assignedElevatorRequest.getElevatorId());
		}
	}
	
	private ElevatorStatus getSameSrcCacheElevator(ElevatorRequest request) {
		ElevatorStatus chosenElevatorStatus = null;
		if (request.getDirection() == Direction.UP && sameSrcUpCache.get(request.getSourceFloor()) != null) {
			// refer to sameSrcUpCache
			// cache hit!
			int elevatorIndex = sameSrcUpCache.get(request.getSourceFloor()) - 1;
			chosenElevatorStatus = availableElevatorStatus.get(elevatorIndex);
		} else if (request.getDirection() == Direction.DOWN && sameSrcDownCache.get(request.getSourceFloor()) != null) {
			// refer to sameSrcDownCache
			// cache hit!
			int elevatorIndex = sameSrcDownCache.get(request.getSourceFloor()) - 1;
			chosenElevatorStatus = availableElevatorStatus.get(elevatorIndex);
		}
		return chosenElevatorStatus;
	}

	/**
	 * Assign the next best elevator request.
	 * 
	 * @throws IOException
	 */
	public void assignNextBestElevatorRequest() throws IOException {
		AssignedElevatorRequest request = this.findBestElevatorToAssignRequest();
		if (request != null) {
			schedulerSubsystem.sendPendingRequest(request);
		}
	}

	/**
	 * Adding method for availableElevatorStatus list.
	 * 
	 * @param elevatorStatus ElevatorStatus, the elevator status
	 */
	public void addAvailableElevatorStatus(ElevatorStatus elevatorStatus) {
		availableElevatorStatus.add(elevatorStatus);
	}

	/**
	 * Method for modifying the availableElevatorStatus list
	 * 
	 * @param index          int, the index of the elevator map
	 * @param elevatorStatus ElevatorStatus, the status of the elevator
	 */
	public void modifyAvailableElevatorStatus(int index, ElevatorStatus elevatorStatus) {
		int elevatorId = elevatorStatus.getElevatorId();
		int elevatorFloor = elevatorStatus.getFloor();
		ElevatorStateEnum elevatorState = elevatorStatus.getState();
		Direction elevatorDirection = elevatorStatus.getDirection();
		
		availableElevatorStatus.set(index, elevatorStatus);
		
		// clear cache when elevator arrives at a floor w/ its doors open
		// doors open -> DOORS_OPEN
		if (elevatorState == ElevatorStateEnum.DOORS_OPEN) {
			if (elevatorDirection == Direction.UP) {
				// update sameSrcUpCache
				sameSrcUpCache.put(elevatorFloor, null);
			} else if (elevatorDirection == Direction.DOWN) {
				// update sameSrcDownCache
				sameSrcDownCache.put(elevatorFloor, null);
			}
		}
		
		// clear elevator from cache if it is at DOOR_STUCK/ELEVATOR_STUCK state
		Integer upCacheValue, downCacheValue;
		if (elevatorState == ElevatorStateEnum.ELEVATOR_STUCK || elevatorState == ElevatorStateEnum.DOORS_STUCK) {
			for (Integer floor=1; floor<schedulerSubsystem.getSimulatorConfiguration().NUM_FLOORS; floor++) {
				upCacheValue = sameSrcUpCache.get(floor);
				downCacheValue = sameSrcDownCache.get(floor);
				if (upCacheValue != null && upCacheValue == elevatorId) {
					sameSrcUpCache.put(floor, null);
				}
				if (downCacheValue != null && downCacheValue == elevatorId) {
					sameSrcDownCache.put(floor, null);
				}
			}
		}
		
	}

	/**
	 * Adding method for pendingElevatorRequests list.
	 * 
	 * @param elevatorRequest ElevatorRequest, the elevator request object
	 */
	public void addPendingElevatorRequests(ElevatorRequest elevatorRequest) {
			pendingElevatorRequests.add(elevatorRequest);
			onRequestReceived();
	}

	/**
	 * Adding method for completedElevatorRequests list.
	 * 
	 * @param elevatorRequest ElevatorRequest, the elevator request object
	 */
	public void addCompletedElevatorRequests(ElevatorRequest elevatorRequest) {
			completedElevatorRequests.add(elevatorRequest);
			onRequestReceived();
	}

	/**
	 * Method for handling on request received.
	 */
	public void onRequestReceived() {
		synchronized (currentState) {
			//System.out.println("Event: Request Received");
			currentState = currentState.handleRequestReceived();
			//System.out.println(String.format("Current state: %s, # Completed: %d", currentState, this.completedElevatorRequests.size()));
		}
	}

	/**
	 * Method for handling on request sent.
	 */
	public void onRequestSent() {
		synchronized (currentState) {
			//System.out.println("Event: Request Sent");
			currentState = currentState.handleRequestSent();
			//System.out.println(String.format("Current state: %s, # Completed: %d", currentState, this.completedElevatorRequests.size()));
		}
	}

	/**
	 * Checking if scheduler should be idle.
	 * 
	 * @return boolean, true if the scheduler is idle, otherwise false
	 */
	public boolean isSchedulerIdle() {
		return false;
	}

	/**
	 * Handle the process completed elevator request.
	 * 
	 * @throws IOException
	 */
	public void processCompletedElevatorRequest() throws IOException {
		ElevatorRequest nextCompletedRequest;
		if (completedElevatorRequests.size() > 0) {
			nextCompletedRequest = completedElevatorRequests.remove(0);
			schedulerSubsystem.sendCompletedElevatorRequest(nextCompletedRequest);
		}
	}

	/**
	 * Getter for availableElevatorStatus.
	 * 
	 * @return availableElevatorStatus List, all available elevators status
	 */
	public List<ElevatorStatus> getAvailableElevatorStatus() {
		return availableElevatorStatus;
	}

	/**
	 * Getter for pendingElevatorRequests
	 * 
	 * @return pendingElevatorRequests List, list of pending elevator requests
	 */
	public List<ElevatorRequest> getPendingElevatorRequests() {
		return pendingElevatorRequests;
	}

	/**
	 * Getter for completedElevatorRequests
	 * 
	 * @return completedElevatorRequests List, list of completed elevator requests
	 */
	public List<ElevatorRequest> getCompletedElevatorRequests() {
		return completedElevatorRequests;
	}

	/**
	 * Get the current scheduler state.
	 * 
	 * @return SchedulerState, the state of the scheduler
	 */
	public SchedulerState getCurrentState() {
		return currentState;
	}

}
