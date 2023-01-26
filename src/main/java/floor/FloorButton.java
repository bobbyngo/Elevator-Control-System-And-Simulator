/**
 * 
 */
package main.java.floor;

import main.java.dto.Direction;

/**
 * The class that holds information about 
 * the floor the button is on and the direction
 * the button points to 
 * 
 * @author Hussein El Mokdad, 101171490
 */
public class FloorButton {
	private FloorButton floor;
	private Direction direction;
	
	/**
	 * The constructor for the FloorButton class
	 * 
	 * @param floor the floor the button is on of type FloorButton
	 * @param direction the enum of the whether the button points up or down
	 */
	public FloorButton(FloorButton floor, Direction direction) {
		this.floor = floor;
		this.direction = direction;
	}
	
	/**
	 * Presses the floor button
	 */
	public void pressButton() {
		// What exactly goes into this?
	}
}
