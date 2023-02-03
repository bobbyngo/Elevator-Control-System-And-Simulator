package main.java.dto;

import java.sql.Timestamp;

/**
 * The ElevatorRequest class is responsible for storing all the
 * relevant information regarding passenger's elevator requests.
 * @author Patrick Liu
 * @version 1.0, 02/04/23
 */
public class ElevatorRequest {
	
	private Timestamp timestamp;
	private Integer sourceFloor;
	private Direction direction;
	private Integer destinationFloor;
	
	/**
	 * Constructor of the ElevatorRequest class.
	 * @param timestamp Timestamp, a point in time which the passenger pressed the floor button
	 * @param sourceFloor Integer, the floor which the passenger declared his/her traveling intention
	 * @param direction Direction, passenger's declared traveling direction
	 * @param destinationFloor Integer, the destination floor which the passenger entered inside the elevator cart
	 */
	public ElevatorRequest(Timestamp timestamp, Integer sourceFloor, Direction direction, Integer destinationFloor) {
		this.timestamp = timestamp;
		this.sourceFloor = sourceFloor;
		this.direction = direction;
		this.destinationFloor = destinationFloor;
	}
	
	/**
	 * Get the point in time which the passenger pressed the floor button.
	 * @return Timestamp, time which the passenger pressed the floor button
	 * @see java.sql.Timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Get the floor which the passenger declared their traveling intention.
	 * @return Integer, the floor number chosen by the passenger
	 */
	public Integer getSourceFloor() {
		return sourceFloor;
	}
	
	/**
	 * Get traveling direction declared by the passenger.
	 * @return direction Direction, enum
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Get the destination floor which the passenger entered inside the elevator cart
	 * @return Integer, the destination floor chosen by the passenger
	 */
	public Integer getDestinationFloor() {
		return destinationFloor;
	}
	
	/**
	 * toString method for a readable form of object attributes
	 * @return String
	 */
	@Override
	public String toString(){ 
		  return timestamp.toString().split(" ")[1] + " " + + sourceFloor + " " +
				  direction + " " + destinationFloor;
	} 
  
    /**
	 * @author Bobby Ngo
	 * Override equals method for comparing Elevator Object.
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) {
	    		return false;
	    }
	    
	    ElevatorRequest that = (ElevatorRequest) obj;
	    
	    return timestamp.equals(that.timestamp)
	    		&& sourceFloor.equals(that.sourceFloor) 
	    		&& direction.equals(that.direction) 
	    		&& destinationFloor.equals(that.destinationFloor);
	}

}
