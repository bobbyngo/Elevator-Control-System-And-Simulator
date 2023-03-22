package main.java.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import main.java.elevator.Direction;

/**
 * The ElevatorRequest class is responsible for storing all the
 * relevant information regarding passenger's elevator requests.
 * @author Patrick Liu
 * @version 1.0, 02/04/23
 */
public class ElevatorRequest implements Serializable {
	
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
	 * Constructor of the ElevatorRequest class. Takes string timestamp as input.
	 * @param timestampString String, a point in time which the passenger pressed the floor button
	 * @param sourceFloor Integer, the floor which the passenger declared his/her traveling intention
	 * @param direction Direction, passenger's declared traveling direction
	 * @param destinationFloor Integer, the destination floor which the passenger entered inside the elevator cart
	 * @throws ParseException
	 * @author Zakaria Ismail
	 */
	public ElevatorRequest(String timestampString, Integer sourceFloor, Direction direction, Integer destinationFloor) throws ParseException {
		this(stringToTimestamp(timestampString), sourceFloor, direction, destinationFloor);
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
	 * @return String, output in console
	 */
	@Override
	public String toString(){ 
		  return timestamp.toString().split(" ")[1] + " " + + sourceFloor + " " +
				  direction + " " + destinationFloor;
	} 
  
    /**
	 * Override equals method for comparing Elevator Object.
	 * @return boolean, true if the object are equal
	 * @author Bobby Ngo
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
	
	/**
	 * Converts string timestamp to a Timestamp object
	 * @param timestamp String, input timestamp string
	 * @return Timestamp, converted string input to Timestamp object
	 * @throws ParseException
	 * @author Zakaria Ismail
	 */
	public static Timestamp stringToTimestamp(String timestampString) throws ParseException {
		Timestamp timestamp = new Timestamp(
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
					.parse(
						new Timestamp(System.currentTimeMillis()).toString().split(" ")[0] +
						" " +
						timestampString)
					.getTime());
		return timestamp;
	}

}
