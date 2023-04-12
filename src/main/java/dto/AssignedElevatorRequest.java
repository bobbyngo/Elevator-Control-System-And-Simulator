package main.java.dto;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

import main.java.elevator.Direction;
import main.java.elevator.ElevatorError;

/**
 * This class models an assigned elevator request.
 * 
 * @author Zakaria Ismail
 */
public class AssignedElevatorRequest extends ElevatorRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private int elevatorId;

	/**
	 * Constructor method for assigned elevator request.
	 * 
	 * @param assignedElevatorId int, the assigned elevator id
	 * @param timestampString    String, the timestamp
	 * @param sourceFloor        Integer, the source floor number
	 * @param direction          Direction, the direction of the elevator
	 * @param destinationFloor   Integer, the destination floor number
	 * @param elevatorError      ElevatorError, the elevator error
	 * @throws ParseException
	 */
	public AssignedElevatorRequest(int assignedElevatorId, String timestampString, Integer sourceFloor,
			Direction direction, Integer destinationFloor, ElevatorError elevatorError) throws ParseException {
		super(timestampString, sourceFloor, direction, destinationFloor, elevatorError);
		elevatorId = assignedElevatorId;
	}

	/**
	 * Constructor method for the assigned elevator request.
	 * 
	 * @param assignedElevatorId int, the assigned elevator id
	 * @param request            ElevatorRequest, the elevator request object
	 */
	public AssignedElevatorRequest(int assignedElevatorId, ElevatorRequest request) {
		super(request.getTimestamp(), request.getSourceFloor(), request.getDirection(), request.getDestinationFloor(),
				request.getElevatorError());
		elevatorId = assignedElevatorId;
	}

	/**
	 * Constructor method for assigned elevator request.
	 * 
	 * @param assignedElevatorId int, the assigned elevator id
	 * @param timestampString    String, the timestamp
	 * @param sourceFloor        Integer, the source floor number
	 * @param direction          Direction, the direction of the elevator
	 * @param destinationFloor   Integer, the destination floor number
	 * @throws ParseException
	 */
	public AssignedElevatorRequest(int assignedElevatorId, String timestampString, Integer sourceFloor,
			Direction direction, Integer destinationFloor) throws ParseException {
		this(assignedElevatorId, timestampString, sourceFloor, direction, destinationFloor, null);
	}

	/**
	 * Getter for elevator id.
	 * 
	 * @return elevatorId int, the elevator id
	 */
	public int getElevatorId() {
		return elevatorId;
	}

	/**
	 * Serializable decode data method.
	 * 
	 * @param data byte[], the data to be decoded
	 * @return AssignedElevatorRequest, the assigned elevator request object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static AssignedElevatorRequest decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (AssignedElevatorRequest) decodedObj;
	}

	/**
	 * Serializable encode method.
	 * 
	 * @return byte[], the data encoded
	 */
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s", elevatorId, super.toString());
	}

}
