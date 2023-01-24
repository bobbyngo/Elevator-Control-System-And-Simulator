/**
 * 
 */
package main.java.dto;

import java.sql.Timestamp;

/**
 * The ElevatorRequest class is responsible for storing all the
 * relevant information regarding passenger's elevator requests
 * 
 * @author Patrick Liu, 101142730
 * @since   2023-01-23
 */
public class ElevatorRequest {
	
	public enum Direction {
		  UP,
		  DOWN,
	}
	
	private Timestamp timestamp;
	private Integer floorRequest;
	private Direction direction;
	private Integer floorDestination;
	
	/**
	 * Constructor of the ElevatorRequest class
	 * 
	 * @param timestamp is a point in time which the passenger pressed the floor button
	 * @param floorRequest is the floor which the passenger declared his/her traveling intention
	 * @param direction is passenger's declared traveling direction
	 * @param floorDestination is the destination floor which the passenger entered inside the elevator cart
	 */
	public ElevatorRequest(Timestamp timestamp, Integer floorRequest, Direction direction, Integer floorDestination) {
		this.timestamp = timestamp;
		this.floorRequest = floorRequest;
		this.direction = direction;
		this.floorDestination = floorDestination;
	}
	
	/**
	 * getTimestamp returns a point in time which the passenger pressed the floor button
	 * @return java.sql.Timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	/**
	 * getFloorRequest returns the floor which the passenger declared his/her traveling intention
	 * @return Integer
	 */
	public Integer getFloorRequest() {
		return floorRequest;
	}
	
	/**
	 * getDirection returns passenger's declared traveling direction
	 * @return enum Direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * getFloorDestination returns the destination floor which the passenger entered inside the elevator cart
	 * @return Integer
	 */
	public Integer getFloorDestination() {
		return floorDestination;
	}
	
}
