package main.java.elevator.state;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;

/**
 * This substate represent the idle state.
 * @author Zakaria Ismail
 */
public class IdleState extends IdleMotorState {

	/**
	 * Constructor for this state.
	 * @param ctx  ElevatorContext, the context of the elevator
	 */
	public IdleState(ElevatorContext ctx) {
		super(ctx);
		
		ctx.setDoors(Door.OPEN);
		ctx.setDirection(Direction.IDLE);
	}

	/**
	 * Handle the request received
	 * @return ElevatorState, the state of the elevator
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
	 * Handle the Timeout event.
	 * @return ElevatorState, the stateo of the elevator
	 */
	@Override
	public ElevatorState handleTimeout() {
		// there shouldn't be timeouts happening at this state
		return this;
	}


	/**
	 * toString method
	 * @return String
	 */
	@Override
	public String toString() {
		return "Idle";
	}
	
	/**
	 * Get the ElevatorStateEnum.
	 * @return ElevatorStateEnum, the state of the elevator
	 */
	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.IDLE;
	}

}
