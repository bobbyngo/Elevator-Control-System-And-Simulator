/**
 * 
 */
package main.java.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;

import main.java.elevator.Direction;

/**
 * @author Zakaria Ismail
 *
 */
public class AssignedElevatorRequest extends ElevatorRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int elevatorId;
	
	public AssignedElevatorRequest(int assignedElevatorId, ElevatorRequest request) {
		super(request.getTimestamp(), request.getSourceFloor(), request.getDirection(), request.getDestinationFloor());
		elevatorId = assignedElevatorId;
	}
	
	public AssignedElevatorRequest(int assignedElevatorId, String timestampString, Integer sourceFloor, 
			Direction direction, Integer destinationFloor) throws ParseException {
		super(timestampString, sourceFloor, direction, destinationFloor);
		elevatorId = assignedElevatorId;
	}
	
	public int getElevatorId() {
		return elevatorId;
	}
	
	public static AssignedElevatorRequest decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObject = null;
		ObjectInput in = null;
		ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
		System.out.println(data);
		in = new ObjectInputStream(byteStream);
		decodedObject = in.readObject();
		byteStream.close();
		return (AssignedElevatorRequest) decodedObject;
	}
	
	public byte[] encode() throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		byte[] encodedArray = null;
		
		out = new ObjectOutputStream(byteStream);
		out.writeObject(this);
		//out.flush();
		encodedArray = byteStream.toByteArray();
		return encodedArray;
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
