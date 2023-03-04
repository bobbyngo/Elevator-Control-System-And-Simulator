package test.java.elevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.elevator.Elevator;
import main.java.elevator.ElevatorState;
import main.java.scheduler.Scheduler;

/**
 * ElevatorStateTest tests and validates the methods for the ElevatorState class.
 * @author Trong Nguyen
 * @since 2.0, 02/27/23
 */
public class ElevatorStateTest {
	
	private ElevatorState elevatorState;
	private Elevator elevator1;
	private Scheduler scheduler;
	
	
	/**
	 * Set up the testing environment.
	 */
	@Before
	public void setUp() {
		// If we don't init this we will get NPE cause when we create Elevator instances, scheduler will register the floor for it
		scheduler = new Scheduler();
		elevator1 = new Elevator(1, scheduler);
		elevatorState = ElevatorState.Idle;
	}
	
	/**
	 * Test the elevator getElevatorState method is a valid enum.
	 */
	@Test
	public void testElevatorStateInstance() {
		assertTrue(elevator1.getElevatorState() instanceof ElevatorState);
	}
	
	/**
	 * Test ElevatorsState next state.
	 */
	@Test
	public void testNextState() {
		assertEquals(elevatorState, ElevatorState.Idle);
		elevatorState = elevatorState.nextState();
		assertEquals(elevatorState, ElevatorState.AwaitRequest);
		elevatorState = elevatorState.nextState();
		assertEquals(elevatorState, ElevatorState.Moving);
		elevatorState = elevatorState.nextState();
		assertEquals(elevatorState, ElevatorState.Stop);
		elevatorState = elevatorState.nextState();
		assertEquals(elevatorState, ElevatorState.DoorsOpen);
		elevatorState = elevatorState.nextState();
		assertEquals(elevatorState, ElevatorState.DoorsClose);
		elevatorState = elevatorState.nextState();
		assertEquals(elevatorState, ElevatorState.AwaitRequest);
	}
	
	/**
	 * Test the initial state of an elevator upon initialization.
	 */
	@Test
	public void testElevatorConstructorState() {
		assertEquals(elevator1.getElevatorState(), ElevatorState.Idle);
	}
}
