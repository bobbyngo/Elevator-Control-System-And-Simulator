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
public class IncorrectElevatorRequestParameterNumberException extends Exception {

	public IncorrectElevatorRequestParameterNumberException (String errorMessage) {
		super(errorMessage);
		
	}
}
