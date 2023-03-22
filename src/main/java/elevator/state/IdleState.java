/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public class IdleState extends IdleMotorState {

	public IdleState(ElevatorContext ctx) {
		super(ctx);
		
		ctx.setDoors(Door.OPEN);
		ctx.setDirection(Direction.IDLE);
	}

	@Override
	public ElevatorState handleRequestReceived() {
		ElevatorContext ctx = this.getContext();
		ctx.unloadPassengers();
		ctx.loadPassengers();
		return new DoorsClosedState(ctx);
	}

	@Override
	public ElevatorState handleTimeout() {
		return this;
	}

	@Override
	public String toString() {
		return "Idle";
	}

}
