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
	
	private Timestamp timestamp;
	private Integer sourceFloor;
	private Direction direction;
	private Integer destinationFloor;
	
	/**
	 * Constructor of the ElevatorRequest class
	 * 
	 * @param timestamp is a point in time which the passenger pressed the floor button of type Timestamp
	 * @param sourceFloor is the floor which the passenger declared his/her traveling intention of type Integer
	 * @param direction is passenger's declared traveling direction of type Direction
	 * @param destinationFloor is the destination floor which the passenger entered inside the elevator cart of type Integer
	 */
	public ElevatorRequest(Timestamp timestamp, Integer sourceFloor, Direction direction, Integer destinationFloor) {
		this.timestamp = timestamp;
		this.sourceFloor = sourceFloor;
		this.direction = direction;
		this.destinationFloor = destinationFloor;
	}
	
	/**
	 * getTimestamp returns a point in time which the passenger pressed the floor button
	 * @return the timestamp of type TimeStamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	/**
	 * getSourceFloor returns the floor which the passenger declared his/her traveling intention
	 * @return the source floor of type Integer
	 */
	public Integer getSourceFloor() {
		return sourceFloor;
	}
	
	/**
	 * getDirection returns passenger's declared traveling direction
	 * @return the direction of type Direction (enum)
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * getDestinationFloor returns the destination floor which the passenger entered inside the elevator cart
	 * @return the destination of type Integer
	 */
	public Integer getDestinationFloor() {
		return destinationFloor;
	}
	
	/**
	 * toString combines the object attributes into a readable format
	 * @return the String of the object info
	 */
	@Override
	public String toString(){ 
		  return timestamp.toString().split(" ")[1] + " " + + sourceFloor + " " +
				  direction + " " + destinationFloor;
	} 
  
    /**
	 * @author Bobby Ngo
	 * An override method for comparing Elevator Object
	 * @return the boolean of whether they're equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    
	    ElevatorRequest that = (ElevatorRequest) obj;
	    
	    return 	timestamp.equals(that.getTimestamp())
	    		&& sourceFloor.equals(that.getSourceFloor()) 
	    		&& direction.equals(that.getDirection()) 
	    		&& destinationFloor.equals(that.getDestinationFloor());
	}

}
