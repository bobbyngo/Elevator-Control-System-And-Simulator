/**
 * 
 */
package main.java.floor;

import main.java.elevator.Direction;

/**
 * Responsible for controlling the floor components
 * @author Hussein El Mokdad
 * @since 1.0, 04/01/23
 * @version 1.0, 04/01/23
 */
public class Floor {
	private int floorNum;
	private FloorComponents components;
	
	public Floor(int floorNum) {
		this.floorNum = floorNum;
		this.components = new FloorComponents();	
	}
	
	/**
	 * Turns the floor up lamp on or off
	 * @param status the boolean of whether to turn the lamp on or off (true: on / false: off)
	 */
	public void setFloorUpLamp(boolean status) {
		components.updateButtonDirectionStatus(Direction.UP, status);
	}
	
	/**
	 * Turns the floor down lamp on or off
	 * @param status the boolean of whether to turn the lamp on or off (true: on / false: off)
	 */
	public void setFloorDownLamp(boolean status) {
		components.updateButtonDirectionStatus(Direction.DOWN, status);
	}
	
	/**
	 * Gets the status of the floor up lamp (on or off)
	 * @return the boolean of whether the lamp is on or off (true: on / false: off)
	 */
	public boolean getFloorUpLamp() {
		return components.getButtonLampStatus(Direction.UP);
	}
	
	/**
	 * Gets the status of the floor down lamp (on or off)
	 * @return the boolean of whether the lamp is on or off (true: on / false: off)
	 */
	public boolean getFloorDownLamp() {
		return components.getButtonLampStatus(Direction.DOWN);
	}
	
	/**
	 * Updates the sensor at an elevator shaft
	 * @param sensorId the int of the sensor ID (equal to the elevator ID)
	 * @param status the boolean of whether the sensor detects an elevator (true) or not (false)
	 */
	public void setFloorSensor(int sensorId, boolean status) {
		components.updateArrivalSensor(sensorId, status);
	}
	
	/**
	 * Updates the direction lamp for an elevator shaft
	 * @param elevatorId the int of the elevator id 
	 * @param direction the Direction enum to change the lamp's status to
	 */
	public void setElevatorDirectionLamp(int elevatorId, Direction direction) {
		components.updateDirectionLamp(elevatorId, direction);
	}
	
	@Override
	public String toString() {
		return "Floor " + floorNum + "\n" + components.toString();
	}
}
