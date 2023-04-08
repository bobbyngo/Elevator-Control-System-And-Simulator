package main.java.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;
import main.java.elevator.state.ElevatorStateEnum;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorGuiData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ElevatorStateEnum currentState;
	private int currentFloor;
	private Motor motor;
	private Direction direction;
	private Door door;
	private int internalRequests;
	// XXX: might include external & internal requests if it is needed
	
	public ElevatorGuiData(ElevatorContext ctx) {
		id = ctx.getId();
		currentState = ctx.getCurrentState().getElevatorStateEnum();
		currentFloor = ctx.getCurrentFloor();
		direction = ctx.getDirection();
		door = ctx.getDoors();
		motor = ctx.getMotor();
		internalRequests = ctx.getInternalRequests().size();
	}
	
	/**
	 * Decoding method
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ElevatorGuiData decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (ElevatorGuiData) decodedObj;
	}
	
	/**
	 * Encoding method
	 * @return
	 * @throws IOException
	 */
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}
	
	public int getId() {
		return id;
	}

	public ElevatorStateEnum getCurrentState() {
		return currentState;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public Motor getMotor() {
		return motor;
	}

	public Direction getDirection() {
		return direction;
	}

	public Door getDoor() {
		return door;
	}

	public int getQueueSize() {
		return internalRequests;
	}
}
