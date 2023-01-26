/**
 * 
 */
package test.java.dto;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;

/**
 * ElevatorRequestTest tests and validates methods in the data transfer object
 * 
 * @author Patrick Liu
 */
public class ElevatorRequestTest {
	
	ElevatorRequest elevatorRequest;
	Timestamp timestamp;

	/**
	 * setUp initializes the test environment, the method is run before every Test 
	 * 
	 * @throws ParseException 
	 * 
	 * @author Patrick Liu
	 */
	@Before
	public void setUp() throws ParseException {
		timestamp = new Timestamp(new SimpleDateFormat("hh:mm:ss.SSS").parse("07:01:15.000").getTime());
		elevatorRequest = new ElevatorRequest(timestamp, 1, Direction.UP, 5);
	}
	
	/**
	 * testGetTimestamp validates that the returned timestamp value is correct
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testGetTimestamp() {
		assertEquals(timestamp, elevatorRequest.getTimestamp());
	}
	
	/**
	 * testGetSourceFloor validates that the returned floorRequest value is correct
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testGetSourceFloor() {
		assertEquals(Integer.valueOf(1), elevatorRequest.getSourceFloor());
	}
	
	/**
	 * testGetDirection validates that the returned Direction enum value is correct
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testGetDirection() {
		assertEquals(Direction.UP, elevatorRequest.getDirection());
	}
	
	/**
	 * testGetDestinationFloor validates that the returned floorDestination value is correct
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testGetDestinationFloor() {
		assertEquals(Integer.valueOf(5), elevatorRequest.getDestinationFloor());
	}
	
	/**
	 * testToString validates that the returned String value is contains the correct value and 
	 * is in the required format
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testToString() {
		assertEquals("07:01:15.0 1 UP 5", elevatorRequest.toString());
	}

}
