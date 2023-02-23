package test.java.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.scheduler.Scheduler;
import main.java.scheduler.SchedulerState;

/**
 * SchedulerStateTest tests and validates the methods for the SchedulerState class.
 * @author Trong Nguyen
 * @since 2.0, 02/27/23
 */
public class SchedulerStateTest {
	
	private SchedulerState schedulerState;
	private Scheduler scheduler;
	
	/**
	 * Set up the testing environment.
	 */
	@Before
	public void setUp() {
		scheduler = new Scheduler();
		schedulerState = SchedulerState.Idle;
	}
	
	/**
	 * Test the scheduler getSchedulerState method is a valid enum.
	 */
	@Test
	public void testElevatorStateInstance() {
		assertTrue(scheduler.getSchedulerState() instanceof SchedulerState);
	}
	
	/**
	 * Test the initial state of a scheduler upon initialization.
	 */
	@Test
	public void testElevatorConstructorState() {
		assertEquals(scheduler.getSchedulerState(), SchedulerState.Idle);
	}
	
	/**
	 * Test SchedulerState next state.
	 */
	@Test
	public void testNextState() {
		assertEquals(schedulerState, SchedulerState.Idle);
		schedulerState = schedulerState.nextState();
		assertEquals(schedulerState, SchedulerState.Ready);
		schedulerState = schedulerState.nextState();
		assertEquals(schedulerState, SchedulerState.InService);
		schedulerState = schedulerState.nextState();
	}
	
}
