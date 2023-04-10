package main.java.elevator.state;

/**
 * This class models the timeout event.
 * 
 * @author Zakaria Ismail
 */
public enum TimeoutEvent {
	DOORS_OPEN("Doors Open"),
	DOORS_CLOSE("Doors Close"),
	MOTOR_THROTTLE("Motor Throttle"),
	MOTOR_STOP("Motor Stop"), 
	DOORS_UNSTUCK("Doors Unstuck");
	
	private String eventMsg;
	
	/**
	 * TimeoutEvent
	 * 
	 * @param event String, the timeout event name
	 */
	private TimeoutEvent(String event) {
		eventMsg = "Event: " + event;
	}
	
	/**
	 * toString method.
	 * 
	 * @return String, the string of the event message
	 */
	@Override
	public String toString() {
		return eventMsg;
		
	}
	
}
