package main.java.dto;

import java.io.IOException;
import java.io.Serializable;

import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.state.ElevatorStateEnum;

/**
 * The Elevator Object that is used to share the information of Elevator Context with the Scheduler through UDP Communication
 * @author Zakaria Ismail
 */
public class ElevatorStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int elevatorId;
	private int floor;
	private Direction direction;
	private ElevatorStateEnum state;
	int numRequests;
	
	/**
	 * Constructor for Elevator Status
	 * @param ctx ElevatorContext, the elevator context
	 */
	public ElevatorStatus(ElevatorContext ctx) {
		elevatorId = ctx.getId();
		floor = ctx.getCurrentFloor();
		direction = ctx.getDirection();
		numRequests = ctx.getNumRequests();
		state = ctx.getCurrentState().getElevatorStateEnum();
	}
	
	/**
	 * Constructor Elevator Status
	 * @param id int, the elevator id
	 */
	public ElevatorStatus(int id) {
		elevatorId = id;
		floor = 1;
		direction = Direction.IDLE;
		numRequests = 0;
		state = ElevatorStateEnum.IDLE;
	}
	
	/**
	 * Constructor Elevator Status
	 * @param id int, the elevator id
	 * @param floor int, the floor number
	 * @param direction Direction, the elevator direction
	 * @param numRequests int, the number of requests
	 * @param state ElevatorStateEnum, the state of the elevator
	 */
	public ElevatorStatus(int id, int floor, Direction direction, int numRequests, ElevatorStateEnum state) {
		elevatorId = id;
		this.floor = floor;
		this.direction = direction;
		this.numRequests = numRequests;
		this.state = state;
	}
	
	/**
	 * Getter for Elevator Id
	 * @return elevator id
	 */
	public int getElevatorId() {return elevatorId;}
	
	/**
	 * Getter for the floor
	 * @return current floor
	 */
	public int getFloor() {return floor;}
	
	/**
	 * Getter for direction
	 * @return
	 */
	public Direction getDirection() {return direction;}
	
	/**
	 * Getter for number requests
	 * @return number requests
	 */
	public int getNumRequests() {return numRequests;}
	public ElevatorStateEnum getState() {return state;}
	
	/**
	 * Decoding method.
	 * @param data byte[], data to be encoded
	 * @return ElevatorStatus, the decode data object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ElevatorStatus decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (ElevatorStatus) decodedObj;
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
	 * Overriding toString method
	 */
	@Override
	public String toString() {
		return String.format("ElevatorStatus{%d,%d,%s,%s,%d}", elevatorId, floor, direction, state, numRequests);
	}
}
