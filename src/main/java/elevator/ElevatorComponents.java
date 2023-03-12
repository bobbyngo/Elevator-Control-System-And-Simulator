package main.java.elevator;

import java.util.HashMap;

import main.java.Main;

/**
 * An wrapper class containing all the information of components of the elevator
 * @author Bobby Ngo
 * @version 1.0, 03/06/23
 */
public class ElevatorComponents {
	// For I5: make JButton that has text as floor number and status of the lamp
	// Mapping the elevator button displaying floor number with the lamp, if the floor is selected, the lamp status is true
	// TODO: Is this a good choice since hashmap is not ordered? another solution is 2 arraylist or array for floor button and lamp status
	private HashMap<Integer, Boolean> elevatorButtonBoard = new HashMap<>();
	
	// elevator is running then motor is true
	private boolean motor;
	// Door is open = true, door closed = false
	private boolean isDoorOpen;
	
	/**
	 * Constructor for ElevatorComponents
	 * @param totalFloor
	 * @param motor
	 * @param isDoorOpen
	 */
	public ElevatorComponents(boolean motor, boolean isDoorOpen) {
		// init elevator button and elevator lamp mapping
		for (int i = 1; i <= Main.NUM_OF_FLOORS; i ++) {
			elevatorButtonBoard.put(i, false);
		}
		this.motor = motor;
		this.isDoorOpen = isDoorOpen;
	}
	
	/**
	 * Update the Elevator Lamp Light status by flipping the current status of the floor lamp
	 * @param elevatorButtonNumber the floor number that needs to update
	 */
	public void updateElevatorLampLight(Integer elevatorButtonNumber) {
		if (elevatorButtonBoard.containsKey(elevatorButtonNumber)) {
			boolean currentLampStatus = elevatorButtonBoard.get(elevatorButtonNumber);
			elevatorButtonBoard.put(elevatorButtonNumber, !currentLampStatus);
		}
	}
	
	/**
	 * Get the Elevator Lamp Light status
	 * @param elevatorButtonNumber the button number 
	 * @return boolean, true when lamp is on, false when lamp is off
	 */
	public boolean getElevatorLampStatus(Integer elevatorButtonNumber) {
		return elevatorButtonBoard.get(elevatorButtonNumber);
	}
	
	/**
	 * Method that getting all the selected floors in the elevator
	 * @return a hashmap containing the all the selected floors
	 */
	public HashMap<Integer, Boolean> getAllSelectedFloors(){
		HashMap<Integer, Boolean> allSelectedFloors = new HashMap<>();
		for (Integer floorNumber : elevatorButtonBoard.keySet()) {
			if (elevatorButtonBoard.get(floorNumber)) {
				allSelectedFloors.put(floorNumber, elevatorButtonBoard.get(floorNumber));
			}
		}
		// could return a null hashmap
		return allSelectedFloors;
	}
	
	/**
	 * Getter for motor
	 * @return true if motor is moving, else false
	 */
	public boolean isMotor() {
		return motor;
	}
	
	/**
	 * Switching motor state
	 */
	public void updateMotor() {
		this.motor = !motor;
	}

	/**
	 * Getter for isDoor Open
	 * @return true means door is open, closed means false
	 */
	public boolean isDoorOpen() {
		return isDoorOpen;
	}

	/**
	 * Switching door status
	 */
	public void updateDoorOpen() {
		this.isDoorOpen = !isDoorOpen;
	}
}
