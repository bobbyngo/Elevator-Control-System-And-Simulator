/**
 * 
 */
package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

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
	public ElevatorState handleRequestReceived(ElevatorRequest request) {
		ElevatorContext ctx = this.getContext();
		ctx.unloadPassengers();
		ctx.loadPassengers();
		ctx.setDirection(request.getDirection());
		return new DoorsClosedState(ctx);
	}

	/**
	 * handleTimeout
	 */
	@Override
	public ElevatorState handleTimeout() {
		// there shouldn't be timeouts happening at this state
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
