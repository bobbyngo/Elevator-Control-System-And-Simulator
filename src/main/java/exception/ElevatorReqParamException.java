package main.java.exception;

/**
 * IncorrectElevatorRequestParameterNumberException is thrown when the incorrect
 * numbers of Elevator Request parameter are provided in the input file.
 * @author Patrick Liu
 * @version 1.0, 02/04/23
 */
@SuppressWarnings("serial")
public class ElevatorReqParamException extends Exception {
	
	/**
	 * Elevator required parameter exception.
	 * 
	 * @param errorMessage String, error message
	 */
	public ElevatorReqParamException (String errorMessage) {
		super("Invalid number of elevator request parameters. " +  errorMessage);
		
	}
	
}
