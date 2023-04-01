/**
 * 
 */
package main.java.floor;

/**
 * Responsible for controlling the floor components
 * @author Hussein El Mokdad
 * @since 1.0, 04/01/23
 * @version 1.0, 04/01/23
 */
public class Floor {
	private int floorNum;
	private boolean floorUpLamp;
	private boolean floorDownLamp;
	
	public Floor(int floorNum) {
		this.floorNum = floorNum;
		this.floorUpLamp = false;
		this.floorDownLamp = false;
	}
	
	/**
	 * Turns the floor up lamp on or off
	 * @param mode the boolean of whether to turn the lamp on or off (true: on / false: off)
	 */
	public void setFloorUpLamp(boolean mode) {
		floorUpLamp = mode;
	}
	
	/**
	 * Turns the floor down lamp on or off
	 * @param mode the boolean of whether to turn the lamp on or off (true: on / false: off)
	 */
	public void setFloorDownLamp(boolean mode) {
		floorDownLamp = mode;
	}
	
	/**
	 * Gets the status of the floor up lamp (on or off)
	 * @return the boolean of whether the lamp is on or off (true: on / false: off)
	 */
	public boolean getFloorUpLamp() {
		return floorUpLamp;
	}
	
	/**
	 * Gets the status of the floor down lamp (on or off)
	 * @return the boolean of whether the lamp is on or off (true: on / false: off)
	 */
	public boolean getFloorDownLamp() {
		return floorDownLamp;
	}
}
