/**
 * 
 */
package test.java.elevator.state;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.MovingDownState;
import main.java.elevator.state.StoppedState;

/**
 * @author Zakaria Ismail
 *
 */
public class MovingDownStateTest {
	private ElevatorState elevatorState;
	private static ElevatorSubsystem elevatorSubsystem;

	@BeforeClass
	public static void classSetUp() {
		elevatorSubsystem = ElevatorStateTestUtil.initElevatorSubsystem();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		elevatorState = new MovingDownState(ElevatorStateTestUtil.initElevatorContext(elevatorSubsystem));
		assertTrue(elevatorState.getContext().incrementCurrentFloor()); // Start elevator @ 2nd floor
		assertEquals(2, elevatorState.getContext().getCurrentFloor());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		elevatorState = null;
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#handleRequestReceived(ElevatorRequest)}.
	 * @throws ParseException 
	 */
	@Test
	public void testHandleRequestReceivedDownToStopped() throws ParseException {
		// Case: elevator is passing by floor with request going in the DOWN direction
		ElevatorContext ctx = elevatorState.getContext();
		ElevatorRequest req = ElevatorStateTestUtil.initElevatorRequest(2, Direction.DOWN, 1);
		assert elevatorState.handleRequestReceived(req) instanceof StoppedState;
		assertEquals(2, ctx.getCurrentFloor());
	}
	
	public void testHandleRequestReceivedUpToMovingDown() throws ParseException {
		// Case: elevator is passing by floor with request going in the UP direction
		ElevatorContext ctx = elevatorState.getContext();
		ElevatorRequest req = ElevatorStateTestUtil.initElevatorRequest(2, Direction.UP, 3);
		ElevatorState expectedState = elevatorState;
		assertSame(expectedState, elevatorState.handleRequestReceived(req));
		assertEquals(2, ctx.getCurrentFloor());
	}

	/**
	 * Test method for {@link main.java.elevator.state.MovingDownState#handleTimeout()}.
	 * @throws ParseException 
	 */
	@Test
	public void testHandleTimeoutToStopped() throws ParseException {
		// Case: elevator is passing by floor with a DOWN request
		
		elevatorState.getContext().addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(1, Direction.DOWN, 1));
	}
	
	@Test
	public void testHandleTimeoutAtMinFloorToStopped() {
		
	}
	
	@Test
	public void testHandleTimeoutToMovingDown() {
		
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
