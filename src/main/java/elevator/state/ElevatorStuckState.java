/**
 * 
 */
package main.java.elevator.state;

import main.java.elevator.ElevatorContext;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorStuckState extends IdleMotorState {

	public ElevatorStuckState(ElevatorContext ctx) {
		super(ctx);
		ctx.notifyArrivalSensor();
		ctx.returnExternalRequests();
	}

	@Override
	public ElevatorState handleRequestReceived() {
		return this;
	}

	@Override
	public ElevatorState handleTimeout() {
		return this;
	}

	@Override
	public String toString() {
		return "ElevatorStuck";
	}

	@Override
	public ElevatorStateEnum getElevatorStateEnum() {
		return ElevatorStateEnum.ELEVATOR_STUCK;
	}

}
