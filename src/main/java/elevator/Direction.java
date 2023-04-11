package main.java.elevator;

import java.io.Serializable;

/**
 * This Direction enum contains directions of the elevator cart.
 * 
 * @author Patrick Liu
 * @version 1.0, 02/04/23
 */
public enum Direction implements Serializable {
	UP("UP"), 
	DOWN("DOWN"), 
	IDLE("IDLE");
	
	private String name;
	private Direction(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
