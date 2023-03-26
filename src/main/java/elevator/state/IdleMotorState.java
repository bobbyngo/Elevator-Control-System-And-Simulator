/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;
import main.java.elevator.Motor;

/**
 * @author Zakaria Ismail
 *
 */
public abstract class IdleMotorState extends ElevatorState {
	/**
	 * Constructor
	 * @param ctx
	 */
	public IdleMotorState(ElevatorContext ctx) {
		super(ctx);
		ctx.setMotor(Motor.IDLE);
	}
}
