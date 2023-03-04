package test.java.scheduler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.scheduler.Scheduler;

/**
 * SchedulerTest tests and validates the methods for the Scheduler class.
 * @author Bobby Ngo
 * @since 2.0, 03/04/23
 */
public class SchedulerTest {
private Scheduler scheduler;
	
	/**
	 * Set up the testing environment.
	 */
	@Before
	public void setUp() {
		scheduler = new Scheduler();
	}
	
	/**
	 * Test the scheduler movingTo method
	 */
	@Test
	public void testMovingTo() {
		// init the location of elevator id 100 at floor 1
		scheduler.registerElevatorLocation(100, 1);
		
		// Test moving up floor 1 to 7
		assertEquals(7, scheduler.movingTo(100, scheduler.displayElevatorLocation(100), 7));
		// Test elevator floor is updated 
		assertEquals(7, scheduler.displayElevatorLocation(100), 7);
		
		// Test elevator floor is moving down from 7 to 4 
		assertEquals(4, scheduler.movingTo(100, scheduler.displayElevatorLocation(100), 4));
		// Test elevator floor is updated 
		assertEquals(4, scheduler.displayElevatorLocation(100), 4);
		
		// Test elevator move to the same floor as its currently in
		assertEquals(4, scheduler.movingTo(100, scheduler.displayElevatorLocation(100), 4));
		// Test elevator floor is updated 
		assertEquals(4, scheduler.displayElevatorLocation(100), 4);
	}
}
