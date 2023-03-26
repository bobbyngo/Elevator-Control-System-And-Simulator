package test.java.dto;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;

/**
 * ElevatorRequestTest tests and validates methods in the data transfer object
 * @author Patrick Liu
 */
public class ElevatorRequestTest {
	
	ElevatorRequest elevatorRequest;
	Timestamp timestamp;

	/**
	 * setUp initializes the test environment, the method is run before every Test 
	 * @throws ParseException 
	 */
	@Before
	public void setUp() throws ParseException {
		timestamp = new Timestamp(new SimpleDateFormat("hh:mm:ss.SSS").parse("07:01:15.000").getTime());
		elevatorRequest = new ElevatorRequest(timestamp, 1, Direction.UP, 5);
	}
	
	/**
	 * testGetTimestamp validates that the returned timestamp value is correct
	 */
	@Test
	public void testGetTimestamp() {
		assertEquals(timestamp, elevatorRequest.getTimestamp());
	}
	
	/**
	 * testGetSourceFloor validates that the returned floorRequest value is correct
	 */
	@Test
	public void testGetSourceFloor() {
		assertEquals(Integer.valueOf(1), elevatorRequest.getSourceFloor());
	}
	
	/**
	 * testGetDirection validates that the returned Direction enum value is correct
	 */
	@Test
	public void testGetDirection() {
		assertEquals(Direction.UP, elevatorRequest.getDirection());
	}
	
	/**
	 * testGetDestinationFloor validates that the returned floorDestination value is correct
	 */
	@Test
	public void testGetDestinationFloor() {
		assertEquals(Integer.valueOf(5), elevatorRequest.getDestinationFloor());
	}
	
	/**
	 * testToString validates that the returned String value is contains the correct value and 
	 * is in the required format
	 */
	@Test
	public void testToString() {
		assertEquals("07:01:15.0 1 UP 5", elevatorRequest.toString());
	}
	
	/**
	 * testEqual validates that two elevatorRequest object are the
	 * same or have the equal values of their attributes
	 */
	@Test
	public void testEquals() {
		ElevatorRequest elevatorRequest2 = new ElevatorRequest(timestamp, 1, Direction.UP, 5);
		assertEquals(true, elevatorRequest2.equals(elevatorRequest));
	}
	
	/**
	 * testStringToTimestamp validates that the converted Timestamp from String has
	 * the correct format and is equal value when compared to the Timestamp object
	 * @throws IOException
	 * @throws ParseException
	 */
	public void testStringToTimestamp() throws IOException, ParseException {
		assertEquals(ElevatorRequest.stringToTimestamp("07:01:15.000"), elevatorRequest.getTimestamp());
	}
	
}
