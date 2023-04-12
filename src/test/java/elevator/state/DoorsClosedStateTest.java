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
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.Motor;
import main.java.elevator.state.DoorsClosedState;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.elevator.state.IdleState;
import main.java.elevator.state.MovingDownState;
import main.java.elevator.state.MovingUpState;
import main.java.elevator.state.StoppedState;

/**
 * @author Zakaria Ismail
 *
 */
public class DoorsClosedStateTest {
	
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
		elevatorState = new DoorsClosedState(ElevatorStateTestUtil.initElevatorContext(elevatorSubsystem));
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		elevatorState = null;
	}

	/**
	 * Test method for {@link main.java.elevator.state.DoorsClosedState#handleRequestReceived(ElevatorRequest)}.
	 * @throws ParseException 
	 */
	
	@Test
	public void testHandleRequestReceived() throws ParseException {
		ElevatorState expectedState = elevatorState;
		assertSame(expectedState, 
				elevatorState.handleRequestReceived(
						ElevatorStateTestUtil.initElevatorRequest(2, Direction.UP, 3)));
	}
	

	/**
	 * Test method for {@link main.java.elevator.state.DoorsClosedState#handleTimeout()}.
	 * @throws ParseException 
	 */
	@Test
	public void testHandleTimeoutToStopped() throws ParseException {
		// transition from DoorsClosed to Stopped if there exists a request to serve @ current floor, where
		//	- current direction matches request direction, or
		//	- current direction is Idle
		ElevatorContext ctx = elevatorState.getContext();
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(1, Direction.UP, 2));
		assert elevatorState.handleTimeout() instanceof StoppedState;
	}
	
	@Test
	public void testHandleTimeoutToMovingUp() throws ParseException {
		ElevatorContext ctx = elevatorState.getContext();
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(2, Direction.UP, 3));
		assert elevatorState.handleTimeout() instanceof MovingUpState;
	}
	
	@Test
	public void testHandleTimeoutToMovingDown() throws ParseException {
		ElevatorContext ctx = elevatorState.getContext();
		assertTrue(ctx.incrementCurrentFloor());
		assertTrue(ctx.incrementCurrentFloor());
		assertEquals(3, ctx.getCurrentFloor());
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(2, Direction.DOWN, 1));
		assert elevatorState.handleTimeout() instanceof MovingDownState;
	}
	
	@Test
	public void testHandleTimeoutToIdle() {
		assert elevatorState.handleTimeout() instanceof IdleState;
	}

	/**
	 * Test method for {@link main.java.elevator.state.DoorsClosedState#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("DoorsClosed", elevatorState.toString());
	}

	/**
	 * Test method for {@link main.java.elevator.state.DoorsClosedState#getElevatorStateEnum()}.
	 */
	@Test
	public void testGetElevatorStateEnum() {
		assertEquals(ElevatorStateEnum.DOORS_CLOSED, elevatorState.getElevatorStateEnum());
	}

	/**
	 * Test method for {@link main.java.elevator.state.DoorsClosedState#DoorsClosedState(main.java.elevator.ElevatorContext)}.
	 */
	@Test
	public void testDoorsClosedState() {
		assertEquals(Door.CLOSED, elevatorState.getContext().getDoors());
		assertEquals(Motor.IDLE, elevatorState.getContext().getMotor());
	}

}
