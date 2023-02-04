/**
 * 
 */
package test.java;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import main.java.dto.Direction;
import main.java.dto.ElevatorRequest;
import main.java.elevator.Elevator;
import main.java.floor.Floor;
import main.java.parser.Parser;
import main.java.scheduler.Scheduler;

/**
 * Test class for validating system interactions
 * between the Scheduler, Elevator, & Floor subsystems
 * @author Zakaria Ismail
 *
 */
public class SystemTest {
	
	private Scheduler scheduler;
	private Elevator elevator;
	private Floor floor;
	private Parser parser;

	@Before
	public void setUp() {
		String filename = "./src/test/resources/single-test-input.txt";
		
		try {
			parser = new Parser(filename);
		} catch (FileNotFoundException e) {
			fail(String.format("Filename %s is not found.", filename));
		}
		
		scheduler = new Scheduler();
		elevator = new Elevator(0, scheduler);
		floor = new Floor(0, scheduler, parser);
	}
	
	/**
	 * Validates that the system can read a text file input and
	 * pass the data from the Floor, to the Scheduler and Elevator,
	 * and then back.
	 * @author Zakaria Ismail
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testPassDataBackAndForth() throws ParseException, IOException, InterruptedException {
		ElevatorRequest actualRequest;
		final ElevatorRequest expectedRequest, threadsafeActualRequest;
		ArrayList<ElevatorRequest> expectedParsedList, actualParsedList = null;
		Thread floorThread, elevatorThread;
		
		// Read file from input and get request
		// Q: would it work w/ .0 instead of .000?
		expectedRequest = new ElevatorRequest("07:01:15.000", 2, Direction.UP, 6);
		expectedParsedList = new ArrayList<>(Arrays.asList(expectedRequest));
		actualParsedList = parser.requestParser();
		actualRequest = actualParsedList.get(0);
		assertEquals(expectedParsedList, actualParsedList);
		assertEquals(expectedRequest, actualRequest);
				
		// Floor sends request to Scheduler and Elevator receives request from Scheduler
		// Two approaches: 
		// - spin up 2 threads and then join
		// - directly call the Scheduler's put/get methods --> This would still get blocked regardless...
		// - Test the existing run() function --> this seems like the intuitive path... although it would require
		// 	that the Elevator & Floor classes maintain a state (store a list of requests each)

		// Define final copy of actual request obj to prevent mutation
		threadsafeActualRequest = new ElevatorRequest(
				actualRequest.getTimestamp(), 
				actualRequest.getSourceFloor(),
				actualRequest.getDirection(),
				actualRequest.getDestinationFloor());

		floorThread = new Thread(new Runnable() {
			@Override
			public void run() {
				ElevatorRequest localActualRequest;
				
				// Send mess
				floor.requestElevator(threadsafeActualRequest);
				localActualRequest = floor.receiveCompletedRequest();
				assertEquals(expectedRequest, localActualRequest);
				return;
			}
		});
		elevatorThread = new Thread(new Runnable() {
			@Override
			public void run() {
				ElevatorRequest localActualRequest;
				
				// Receive message from Scheduler and send reply
				localActualRequest = elevator.serveRequest();
				assertEquals(expectedRequest, localActualRequest);
				elevator.sendCompletedRequest(localActualRequest);
				return;
				
			}
		});
		
		floorThread.join();
		elevatorThread.join();
	}

}
