package test.java.elevator;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import main.java.scheduler.Scheduler;
import main.java.elevator.Elevator;

/**
 * ElevatorTest tests and validates the methods for the Elevator class
 * @author Hussein El Mokdad
 * @version 1.0, 02/04/23
 * @since 1.0, 02/04/23
 */
public class ElevatorTest {
	
	private Elevator elevator1;
	private Elevator elevator2;
	private Elevator elevator3;
	private Elevator elevator4;
	private Scheduler scheduler;
	
	/**
	 * Responsible for setting up the test environment 
	 * @throws Exception
	 */
	@Before
    	public void setUp() throws Exception {
		// If we don't init this we will get NPE cause when we create Elevator instances, scheduler will register the floor for it
		scheduler = new Scheduler();
		elevator1 = new Elevator(10, scheduler);
		elevator2 = new Elevator(1200, scheduler);
		elevator3 = new Elevator(0, scheduler);
		elevator4 = new Elevator(123, scheduler);
    	}
	
	/**
	 * Tests getting the elevator id
	 * @throws IOException
	 */
	@Test
	public void testGetElevatorId() throws IOException {
		assertEquals(10, elevator1.getElevatorId());
		assertEquals(1200, elevator2.getElevatorId());
		assertEquals(0, elevator3.getElevatorId());
		assertEquals(123, elevator4.getElevatorId());
	}

}
