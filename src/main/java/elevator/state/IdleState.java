package main.java.elevator.state;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class IdleState extends IdleMotorState {

	/**
	 * Constructor
	 * @param ctx
	 */
	public IdleState(ElevatorContext ctx) {
		super(ctx);
		
		ctx.setDoors(Door.OPEN);
		ctx.setDirection(Direction.IDLE);
	}

	/**
	 * handleRequestReceived
	 */
	@Override
	public ElevatorState handleRequestReceived() {
		ElevatorContext ctx = this.getContext();
		ctx.unloadPassengers();
		ctx.loadPassengers();
		return new DoorsClosedState(ctx);
	}

	/**
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		return this;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Idle";
	}

	/**
	 * getElevatorStateEnum
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.IDLE;
	}

}
