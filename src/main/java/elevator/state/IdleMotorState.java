package main.java.elevator.state;

import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * This substate represent the motor idle state.
 * 
 * @author Zakaria Ismail
 */
public abstract class IdleMotorState extends ElevatorState {

	/**
	 * Constructor for this state.
	 * 
	 * @param ctx ElevatorContext, the context of the elevator
	 */
	public IdleMotorState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.IDLE);
	}

}
