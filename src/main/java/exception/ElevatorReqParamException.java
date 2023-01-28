/**
 * 
 */
package main.java.exception;

/**
 * IncorrectElevatorRequestParameterNumberException is thrown when the incorrect
 * numbers of Elevator Request parameter are provided in the input file
 * 
 * @author Patrick Liu, 101142730
 */
@SuppressWarnings("serial")
public class ElevatorReqParamException extends Exception {
	
	public ElevatorReqParamException (String errorMessage) {
		super("Invalid number of elevator request parameters. " +  errorMessage);
		
	}
}
