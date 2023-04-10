package main.java.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.TreeSet;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;
import main.java.elevator.state.ElevatorStateEnum;

/**
 * This class represents the elevator GUI data object.
 * @author Zakaria Ismail
 */
public class ElevatorGuiData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private ElevatorStateEnum currentState;
	private int currentFloor;
	private Motor motor;
	private Direction direction;
	private Door door;
	private TreeSet<Integer> destinationFloors;
	private TreeSet<Integer> sourceFloors;
	// XXX: might include external & internal requests if it is needed
	
	/**
	 * Elevator GUI data constructor.
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public ElevatorGuiData(ElevatorContext ctx) {
		id = ctx.getId();
		currentState = ctx.getCurrentState().getElevatorStateEnum();
		currentFloor = ctx.getCurrentFloor();
		direction = ctx.getDirection();
		door = ctx.getDoors();
		motor = ctx.getMotor();
		destinationFloors = new TreeSet<>();
		sourceFloors = new TreeSet<>();
		for (ElevatorRequest elevatorRequest : ctx.getInternalRequests()) {
			destinationFloors.add(elevatorRequest.getDestinationFloor());
		}
		for (ElevatorRequest elevatorRequest : ctx.getExternalRequests()) {
			sourceFloors.add(elevatorRequest.getSourceFloor());
		}
	}
	
	/**
	 * Decoding method.
	 * @param data byte[], data to be encoded
	 * @return ElevatorGuiData, the decode data object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ElevatorGuiData decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (ElevatorGuiData) decodedObj;
	}
	
	/**
	 * Encoding method.
	 * @return byte[], the data to be encode
	 * @throws IOException
	 */
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}
	
	/**
	 * Get elevator id.
	 * @return int, the elevator id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the current elevator state.
	 * @return ElevatorStateEnum, the current elevator state
	 */
	public ElevatorStateEnum getCurrentState() {
		return currentState;
	}

	/**
	 * Get the current floor.
	 * @return int, the current floor stop
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Get the motor status.
	 * @return Motor, the motor enum
	 */
	public Motor getMotor() {
		return motor;
	}

	/**
	 * Get the direction of the moving elevator.
	 * @return Direction, the direction enum
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Get the elevator door status.
	 * @return Door, the door enum
	 */
	public Door getDoor() {
		return door;
	}

	/**
	 * Get the elevator destination floors.
	 * @return TreeSet<Integer> of the destination floors
	 */
	public TreeSet<Integer> getDestinationFloors() {
		return destinationFloors;
	}
	
	/**
	 * Get the elevator source floors.
	 * @return TreeSet<Integer> of the source floors
	 */
	public TreeSet<Integer> getSourceFloors() {
		return sourceFloors;
	}
}
