/**
 * 
 */
package test.java.elevator.state;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.state.ElevatorState;

/**
 * @author Zakaria Ismail
 *
 */
public class MovingDownStateTest {
	private ElevatorState elevatorState;
	private static ElevatorSubsystem elevatorSubsystem;

	@BeforeClass
	public void classSetUp() {
		elevatorSubsystem = ElevatorStateTestUtil.initElevatorSubsystem();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		elevatorState = ElevatorStateTestUtil.initElevatorState(elevatorSubsystem);
		assertTrue(elevatorState.getContext().incrementCurrentFloor()); // Start elevator @ 2nd floor
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		elevatorState = null;
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#handleRequestReceived()}.
	 */
	@Test
	public void testHandleRequestReceived() {
		// Case: elevator is passing by floor with request going in the DOWN direction
		ElevatorContext ctx = elevatorState.getContext();
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#handleTimeout()}.
	 */
	@Test
	public void testHandleTimeout() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#getElevatorStateEnum()}.
	 */
	@Test
	public void testGetElevatorStateEnum() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#MovingDownState(main.java.elevator.ElevatorContext)}.
	 */
	@Test
	public void testMovingDownState() {
		fail("Not yet implemented");
	}

}
