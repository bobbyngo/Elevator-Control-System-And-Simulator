package test.java.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.floor.parser.Parser;

/**
 * ParserTest tests and validates methods for Parser
 * subsystem in the Elevator Control System & Simulator
 * @author Patrick Liu
 */
public class ParserTest {
	
	ArrayList<ElevatorRequest> elevatorRequestList;
	Parser parser;
	ElevatorRequest singleRequest;
	
	/**
	 * setUp initializes the test environment, the method is run before every Test 
	 * @throws Exception
	 */
	@Before
    public void setUp() throws Exception {
		elevatorRequestList = new ArrayList<>();
		parser = new Parser("./src/test/resources/input.txt");
		elevatorRequestList = parser.requestParser();
		singleRequest = parser.textParser("07:01:15.000 2 UP 6");
    }
	
	/**
	 * testFillTimestampZero validates that an elevator request String has its timestamp 0 correctly filled
	 */
	@Test
	public void testFillTimestampZero() {
		assertEquals("07:01:15.500 2 UP 6", parser.fillTimestampZero("07:01:15.5 2 UP 6"));
		assertEquals("07:01:15.560 2 UP 6", parser.fillTimestampZero("07:01:15.56 2 UP 6"));
		assertEquals("07:01:15.567 2 UP 6", parser.fillTimestampZero("07:01:15.567 2 UP 6"));
	}
	
	/**
	 * testSortListByTimestamp validates that the elevatorRequest objects are inserted into the
	 * Arraylist in the order from smallest to greatest
	 */
	@Test
	public void testSortListByTimestamp() {
		assertEquals("00:08:32.0 7 UP 10", elevatorRequestList.get(0).toString());
		assertEquals("03:02:56.0 10 UP 15", elevatorRequestList.get(1).toString());
		assertEquals("05:35:44.0 1 UP 4", elevatorRequestList.get(2).toString());
		assertEquals("07:01:15.0 2 UP 6", elevatorRequestList.get(3).toString());
		assertEquals("08:16:45.0 3 DOWN 1", elevatorRequestList.get(4).toString());
	}
	
	/**
	 * testTextParser validates that a String is parsed properly into an 
	 * ElevatorRequest object with all parameters being correct
	 * @throws ParseException
	 */
	@Test
	public void testTextParser() throws ParseException {
		assertEquals(ElevatorRequest.stringToTimestamp("07:01:15.000"), singleRequest.getTimestamp());
		assertEquals(Integer.valueOf(2), singleRequest.getSourceFloor());
		assertEquals(Direction.UP, singleRequest.getDirection());
		assertEquals(Integer.valueOf(6), singleRequest.getDestinationFloor());
	}
	
	/**
	 * testTimestamp validates that the timestamp from the input file is properly parsed
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testTimestamp() throws IOException, ParseException {
		assertEquals(ElevatorRequest.stringToTimestamp("00:08:32.000"), elevatorRequestList.get(0).getTimestamp());
	}
	
	/**
	 * testParsingException tests the situation when the provided input file contains
	 * incorrect/illegal argument numbers/types, the list containing the elevator requests 
	 * will be cleared and Exception will be displayed in the terminal
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testParsingException() throws IOException, ParseException {
		Parser parser = new Parser("./src/test/resources/incorrectInput.txt");
		assertEquals(1, parser.requestParser().size());
	}
	
	/**
	 * testSourceFloor validates that the floor source number (Integer)
	 * from the input file is properly parsed
	 */
	@Test
	public void testSourceFloor() {
		assertEquals(Integer.valueOf(7), elevatorRequestList.get(0).getSourceFloor());
	}
	
	/**
	 * testDirectionUp validates that the direction (Direction.UP)
	 * from the input file is properly parsed
	 */
	@Test
	public void testDirectionUp() {
		assertEquals(Direction.UP, elevatorRequestList.get(0).getDirection());
	}
	
	/**
	 * testDirectionDown validates that the direction (Direction.DOWN)
	 * from the input file is properly parsed
	 */
	@Test
	public void testDirectionDown() {
		assertEquals(Direction.DOWN, elevatorRequestList.get(4).getDirection());
	}
	
	/**
	 * testDestinationFloor validates that the floor destination number (Integer)
	 * from the input file is properly parsed
	 */
	@Test
	public void testDestinationFloor() {
		assertEquals(Integer.valueOf(10), elevatorRequestList.get(0).getDestinationFloor());
	}

}
