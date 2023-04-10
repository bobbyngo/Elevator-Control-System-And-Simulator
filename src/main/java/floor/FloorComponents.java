package main.java.floor;

import java.util.HashMap;

import main.java.SimulatorConfiguration;
import main.java.elevator.Direction;

/**
 * An wrapper class containing all the information of components of the floor
 * 
 * @author Bobby Ngo
 * @version 1.0, 03/10/23
 */
public class FloorComponents {

	// Both hashmap size should always be 1
	// Up and Down buttons will use the below logic
	// Each floor will have the components below, if there is the event
	// that trigger when clicking the button, invokes the implemented methods
	private SimulatorConfiguration simulatorConfiguration = new SimulatorConfiguration(
			"./src/main/resources/config.properties");
	private HashMap<Direction, Boolean> buttonLamp = new HashMap<>();
	private HashMap<Integer, Boolean> arrivalSensor = new HashMap<>();
	// Denote the arrival and direction of an elevator at a floor
	private HashMap<Integer, Direction> directionLamp = new HashMap<>();

	/**
	 * Constructor for FloorComponents.
	 */
	public FloorComponents() {
		buttonLamp.put(Direction.UP, false);
		buttonLamp.put(Direction.DOWN, false);
		for (int i = 0; i < simulatorConfiguration.NUM_ELEVATORS; i++) {
			arrivalSensor.put(i + 1, false);
			directionLamp.put(i + 1, Direction.IDLE);
		}
	}

	/**
	 * Get the status of the up or down button
	 * 
	 * @param Direction, the direction of the lamp
	 * @return boolean, true if down button is pressed, else false
	 */
	public boolean getButtonLampStatus(Direction direction) {
		return buttonLamp.get(direction);
	}

	/**
	 * Getter for direction of the lamp
	 * 
	 * @return HashMap, containing the direction
	 */
	public HashMap<Integer, Direction> getDirectionLamp() {
		return this.directionLamp;
	}

	/**
	 * Get the direction of elevator lamp.
	 * 
	 * @param elevatorID int, the elevator id
	 * @return Direction, the direction of the elevator
	 */
	public Direction getDirectionLamp(int elevatorID) {
		return this.directionLamp.get(elevatorID);
	}

	/**
	 * Getter for arrival sensor
	 * 
	 * @return HashMap, the arrival sensor boolean
	 */
	public HashMap<Integer, Boolean> getArrivalSensor() {
		return this.arrivalSensor;
	}

	/**
	 * Get the arrival sensor.
	 * 
	 * @param elevatorID int, the elevator id
	 * @return boolean, true if the sensor has been triggered
	 */
	public boolean getArrivalSensor(int elevatorID) {
		return this.arrivalSensor.get(elevatorID);
	}

	/**
	 * Update the status of either up button or down button If the UP button is
	 * selected, flip the status of UP button If the DOWN button is selected, flip
	 * the status of DOWN button
	 * 
	 * @param direction Direction, the elevator direction
	 * @param status    boolean, the boolean status
	 */
	public void updateButtonDirectionStatus(Direction direction, boolean status) {
		buttonLamp.put(direction, status);
	}

	/**
	 * Update the direction of the elevator at a floor.
	 * 
	 * @param elevatorID    int, the elevator id
	 * @param directionLamp Direction, the direction lamp of the elevator
	 */
	public void updateDirectionLamp(int elevatorID, Direction directionLamp) {
		this.directionLamp.put(elevatorID, directionLamp);
	}

	/**
	 * Flip the arrival sensor.
	 * 
	 * @param elevatorID    int, the elevator id
	 * @param arrivalSensor boolean, the boolean value the trigger has been trigger
	 */
	public void updateArrivalSensor(int elevatorID, boolean arrivalSensor) {
		this.arrivalSensor.put(elevatorID, arrivalSensor);
	}

	/**
	 * toString method.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String floorButtonsStr = "";
		String directionLampsStr = "{";
		String floorSesnsorsStr = "{";

		floorButtonsStr = "{Up ButtonLamp: " + buttonLamp.get(Direction.UP).toString() + ", Down ButtonLamp: "
				+ buttonLamp.get(Direction.DOWN).toString() + "} \n";

		for (int i = 1; i <= simulatorConfiguration.NUM_ELEVATORS; i++) {
			directionLampsStr += "Elevator# " + (i) + " direction lamp: " + getDirectionLamp(i) + ", ";
			floorSesnsorsStr += "Elevator# " + (i) + " arrival sensor: " + getArrivalSensor(i) + ", ";
		}
		directionLampsStr += "} \n";
		floorSesnsorsStr += "}";

		return floorButtonsStr + directionLampsStr + floorSesnsorsStr;
	}

}
