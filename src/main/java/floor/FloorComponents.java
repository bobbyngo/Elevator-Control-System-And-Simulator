package main.java.floor;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.SimulatorConfiguration;
import main.java.dto.ElevatorStatus;
import main.java.elevator.Direction;
/**
 * An wrapper class containing all the information of components of the floor
 * @author Bobby Ngo
 * @version 1.0, 03/10/23
 */
public class FloorComponents {
	
	// Both hashmap size should always be 1
	// Up and Down buttons will use the below logic
	// Each floor will have the components below, if there is the event
	// that trigger when clicking the button, invokes the implemented methods
	private SimulatorConfiguration simulatorConfiguration = new SimulatorConfiguration("./src/main/resources/config.properties");
	private HashMap<Direction, Boolean> upButtonHashMap = new HashMap<>();
	private HashMap<Direction, Boolean> downButtonHashMap = new HashMap<>();
	private HashMap<Integer, Boolean> arrivalSensor = new HashMap<>();
	// Denote the arrival and direction of an elevator at a floor
	private HashMap<Integer, Direction> directionLamp = new HashMap<>();
	// Detecting elevator arrived to the floor
	
	/**
	 * Constructor for FloorComponents
	 * @param direction
	 */
	public FloorComponents(Direction direction) {
		// init all the component is not selected
		upButtonHashMap.put(Direction.UP, false);
		downButtonHashMap.put(Direction.DOWN, false);
		
		for(int i = 0; i < simulatorConfiguration.NUM_ELEVATORS; i++) {
			arrivalSensor.put(i, false);
			directionLamp.put(i, Direction.IDLE);
		}
	}
	
	/**
	 * Get the status of the up or down button
	 * @param direction UP or DOWN
	 * @return true if down button is pressed, else false
	 */
	public boolean getButtonLampStatus(Direction direction) {
		boolean isSelected = false;
		if (direction == Direction.DOWN) {
			isSelected = downButtonHashMap.get(Direction.DOWN);
		} else if (direction == Direction.UP) {
			isSelected = upButtonHashMap.get(Direction.UP);
		}
		return isSelected;
	}
	/**
	 * Getter for direction of the lamp
	 * @return Direction.UP or Direction.DOWN
	 */
	public HashMap<Integer, Direction> getDirectionLamp() {
		return this.directionLamp;
	}
	
	public Direction getDirectionLamp(int elevatorID) {
		return this.directionLamp.get(elevatorID);
	}
	
	/**
	 * Getter for arrival sensor
	 * @return arrival sensor boolean
	 */
	public HashMap<Integer, Boolean> getArrivalSensor() {
		return this.arrivalSensor;
	}
	
	public Boolean getArrivalSensor(int elevatorID) {
		return this.arrivalSensor.get(elevatorID);
	}
	
	/**
	 * Update the status of either up button or down button
	 * If the UP button is selected, flipping the status of UP button
	 * If the DOWN button is selected, flipping the status of DOWN button
	 * @param direction UP or DOWN
	 */
	public void updateButtonDirectionStatus(Direction direction) {
		boolean isSelected = this.getButtonLampStatus(direction);
		
		if (direction == Direction.DOWN) {
			downButtonHashMap.put(Direction.DOWN, !isSelected);
		} else if (direction == Direction.UP) {
			upButtonHashMap.put(Direction.UP, !isSelected);
		}
	}
	
	/**
	 * Update the direction of the elevator at a floor
	 * @param directionLamp
	 */
	public void updateDirectionLamp(int elevatorID, Direction directionLamp) {
		this.directionLamp.put(elevatorID, directionLamp);
	}
	
	/**
	 * Flipping the arrival sensor
	 * @param true if the elevator present at the floor, else false
	 */
	public void updateArrivalSensor(int elevatorID, boolean arrivalSensor) {
		this.arrivalSensor.put(elevatorID, arrivalSensor);
	}
}
