/**
 * 
 */
package test.java.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.dto.ElevatorRequest;
import main.java.dto.Direction;
import main.java.parser.Parser;

/**
 * ParserTest tests and validates methods for Parser
 * subsystem in the Elevator Control System & Simulator
 * 
 * @author Patrick Liu
 */
public class ParserTest {
	
	ArrayList<ElevatorRequest> elevatorRequestList;
	Parser parser;
	
	/**
	 * setUp initializes the test environment, the method is run before every Test 
	 * 
	 * @throws Exception
	 * 
	 * @author Patrick Liu
	 */
	@Before
    public void setUp() throws Exception {
		elevatorRequestList = new ArrayList<>();
		parser = new Parser("./src/test/resources/input.txt");
		elevatorRequestList = parser.requestParser();
    }
	
	/**
	 * testTimestamp validates that the timestamp from the input file is properly parsed
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testTimestamp() throws IOException, ParseException {
		assertEquals(new Timestamp(new SimpleDateFormat("hh:mm:ss.SSS").parse("07:01:15.000").getTime()), 
				elevatorRequestList.get(0).getTimestamp());
	}
	
	/**
	 * testTimestampException tests the situation when the provided input file contains
	 * invalid Timestamp, the list containing the elevator requests will be cleared and
	 * "Timestamp format is incorrect" will be displayed in the terminal
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testTimestampException() throws IOException, ParseException {
		Parser parser = new Parser("./src/test/resources/incorrectInput.txt");
		assertTrue(parser.requestParser().isEmpty());
	}
	
	/**
	 * testSourceFloor validates that the floor source number (Integer)
	 * from the input file is properly parsed
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testSourceFloor() {
		assertEquals(Integer.valueOf(2), elevatorRequestList.get(0).getSourceFloor());
	}
	
	/**
	 *
	 * testDirectionUp validates that the direction (Direction.UP)
	 * from the input file is properly parsed
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testDirectionUp() {
		assertEquals(Direction.UP, elevatorRequestList.get(0).getDirection());
	}
	
	/**
	 * testDirectionDown validates that the direction (Direction.DOWN)
	 * from the input file is properly parsed
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testDirectionDown() {
		assertEquals(Direction.DOWN, elevatorRequestList.get(1).getDirection());
	}
	
	/**
	 * testDestinationFloor validates that the floor destination number (Integer)
	 * from the input file is properly parsed
	 * 
	 * @author Patrick Liu
	 */
	@Test
	public void testDestinationFloor() {
		assertEquals(Integer.valueOf(6), elevatorRequestList.get(0).getDestinationFloor());
	}

}
