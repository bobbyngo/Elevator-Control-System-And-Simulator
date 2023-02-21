package main.java.elevator;

import main.java.dto.ElevatorRequest;

/**
 * ElevatorState enum class holds the possible states of the Elevator.
 * @author Trong Nguyen
 * @author Bobby Ngo
 * @version 2.0, 02/27/23
 */
public enum ElevatorState {

	Idle {
		@Override
		public ElevatorState nextState() {
			return AwaitRequest;
		}

		@Override
		public String displayCurrentState(int id, ElevatorRequest request) {
			return String.format("Elevator# %d State: %s", id, this);
		}
	},
	
	AwaitRequest {
		@Override
		public ElevatorState nextState() {
			return Moving;
		}

		@Override
		public String displayCurrentState(int id, ElevatorRequest request) {
			return  String.format("Elevator# %d State: %s", id, this);
		}
	},
	
	Moving {
		@Override
		public ElevatorState nextState() {
			return Stop;
		}

		@Override
		public String displayCurrentState(int id, ElevatorRequest request) {
			return String.format("Elevator# %d State: %s > Direction: %s from floor %d -> floor %d", 
					id, this, request.getDirection(), request.getSourceFloor(), request.getDestinationFloor());
		}
	},
	
	Stop {
		@Override
		public ElevatorState nextState() {
			return DoorsOpen;
		}

		@Override
		public String displayCurrentState(int id, ElevatorRequest request) {
			return String.format("Elevator# %d State: %s arrived at floor %d", id, this, request.getDestinationFloor()); 
		}
	},
	
	DoorsOpen {
		@Override
		public ElevatorState nextState() {
			return DoorsClose;
		}

		@Override
		public String displayCurrentState(int id, ElevatorRequest request) {
			return  String.format("Elevator# %d State: %s", id, this);
		}
	},
	
	DoorsClose {
		@Override
		public ElevatorState nextState() {
			return AwaitRequest;
		}

		@Override
		public String displayCurrentState(int id, ElevatorRequest request) {
			return  String.format("Elevator# %d State: %s", id, this);
		}
	};
	
	/**
	 * Method responsible for changing the current state
	 * @return void
	 */
	public abstract ElevatorState nextState(); 
	
	/**
	 * Method that display information of the current state and request
	 * @param id
	 * @param request
	 * @return String
	 */
	public abstract String displayCurrentState(int id, ElevatorRequest request);	
}
