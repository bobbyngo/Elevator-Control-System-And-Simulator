/**
 * 
 */
package main.java.dto;

import java.io.IOException;
import java.io.Serializable;

import main.java.SerializableEncoder;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.state.ElevatorStateEnum;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int elevatorId;
	private int floor;
	private Direction direction;
	private ElevatorStateEnum state;
	int numRequests;
	
	public ElevatorStatus(ElevatorContext ctx) {
		elevatorId = ctx.getId();
		floor = ctx.getCurrentFloor();
		direction = ctx.getDirection();
		numRequests = ctx.getNumRequests();
		state = ctx.getCurrentState().getElevatorStateEnum();
	}
	
	public ElevatorStatus(int id) {
		elevatorId = id;
		floor = 1;
		direction = Direction.IDLE;
		numRequests = 0;
		state = ElevatorStateEnum.IDLE;
	}
	
	public int getElevatorId() {return elevatorId;}
	public int getFloor() {return floor;}
	public Direction getDirection() {return direction;}
	public int getNumRequests() {return numRequests;}
	public ElevatorStateEnum getState() {return state;}
	
	public static ElevatorStatus decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (ElevatorStatus) decodedObj;
	}
	
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}
}
