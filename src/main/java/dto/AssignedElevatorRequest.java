/**
 * 
 */
package main.java.dto;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

import main.java.elevator.Direction;

/**
 * 
 * @author Zakaria Ismail
 *
 */
public class AssignedElevatorRequest extends ElevatorRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private int elevatorId;
	
	/**
	 * Constructor method
	 * @param assignedElevatorId
	 * @param request
	 */
	public AssignedElevatorRequest(int assignedElevatorId, ElevatorRequest request) {
		super(request.getTimestamp(), request.getSourceFloor(), request.getDirection(), request.getDestinationFloor());
		elevatorId = assignedElevatorId;
	}
	
	/**
	 * Constructor method
	 * @param assignedElevatorId
	 * @param timestampString
	 * @param sourceFloor
	 * @param direction
	 * @param destinationFloor
	 * @throws ParseException
	 */
	public AssignedElevatorRequest(int assignedElevatorId, String timestampString, Integer sourceFloor, 
			Direction direction, Integer destinationFloor) throws ParseException {
		super(timestampString, sourceFloor, direction, destinationFloor);
		elevatorId = assignedElevatorId;
	}
	
	/**
	 * Getter for elevatorId
	 * @return
	 */
	public int getElevatorId() {
		return elevatorId;
	}
	
	/**
	 * Decode the data method
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static AssignedElevatorRequest decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (AssignedElevatorRequest) decodedObj;
	}
	
	/**
	 * Encode method
	 */
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}
	
	public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {
		// small test
		AssignedElevatorRequest testObj = new AssignedElevatorRequest(
				1, "07:01:15.000", 3, Direction.UP, 5
			);
		AssignedElevatorRequest rcvObj;
		byte[] data = testObj.encode();
		System.out.println(data);
		rcvObj = AssignedElevatorRequest.decode(data);
		System.out.println(testObj);
		System.out.println(rcvObj);
	}
	
}
