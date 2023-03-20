package test.java.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.scheduler.SchedulerOld;
import main.java.scheduler.SchedulerStateOld;

/**
 * SchedulerStateTest tests and validates the methods for the SchedulerStateOld class.
 * @author Trong Nguyen
 * @since 2.0, 02/27/23
 */
public class SchedulerStateTest {
	
	private SchedulerStateOld schedulerState;
	private SchedulerOld scheduler;
	
	/**
	 * Set up the testing environment.
	 */
	@Before
	public void setUp() {
		scheduler = new SchedulerOld();
		schedulerState = SchedulerStateOld.Idle;
	}
	
	/**
	 * Test the scheduler getSchedulerState method is a valid enum.
	 */
	@Test
	public void testElevatorStateInstance() {
		assertTrue(scheduler.getSchedulerState() instanceof SchedulerStateOld);
	}
	
	/**
	 * Test the initial state of a scheduler upon initialization.
	 */
	@Test
	public void testElevatorConstructorState() {
		assertEquals(scheduler.getSchedulerState(), SchedulerStateOld.Idle);
	}
	
	/**
	 * Test SchedulerStateOld next state.
	 */
	@Test
	public void testNextState() {
		assertEquals(schedulerState, SchedulerStateOld.Idle);
		schedulerState = schedulerState.nextState();
		assertEquals(schedulerState, SchedulerStateOld.Ready);
		schedulerState = schedulerState.nextState();
		assertEquals(schedulerState, SchedulerStateOld.InService);
		schedulerState = schedulerState.nextState();
	}
	
}
