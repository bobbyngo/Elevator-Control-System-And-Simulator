/**
 * 
 */
package main.java.elevator.state;

/**
 * @author Zakaria Ismail
 *
 */
public enum TimeoutEvent {
	DOORS_OPEN("Doors Open"),
	DOORS_CLOSE("Doors Close"),
	MOTOR_THROTTLE("Motor Throttle"),
	MOTOR_STOP("Motor Stop"), 
	DOORS_UNSTUCK("Doors Unstuck");
	
	private String eventMsg;
	
	private TimeoutEvent(String event) {
		eventMsg = "Event: " + event;
	}
	
	@Override
	public String toString() {
		return eventMsg;
	}
}
