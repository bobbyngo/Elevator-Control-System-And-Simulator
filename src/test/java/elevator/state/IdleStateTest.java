/**
 * 
 */
package test.java.elevator.state;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.Motor;
import main.java.elevator.state.DoorsClosedState;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.elevator.state.IdleState;

/**
 * @author Zakaria Ismail
 *
 */
public class IdleStateTest {

	private ElevatorState elevatorState;
	private static ElevatorSubsystem elevatorSubsystem;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void classSetUp() throws Exception {
		elevatorSubsystem = ElevatorStateTestUtil.initElevatorSubsystem();
	}
	
	@Before
	public void setUp() {
		elevatorState = new IdleState(ElevatorStateTestUtil.initElevatorContext(elevatorSubsystem));
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		elevatorState = null;
	}

	/**
	 * Test method for {@link main.java.elevator.state.IdleState#handleRequestReceived()}.
	 */
	@Test
	public void testHandleRequestReceived() {
		assert elevatorState.handleRequestReceived() instanceof DoorsClosedState;
	}

	/**
	 * Test method for {@link main.java.elevator.state.IdleState#handleTimeout()}.
	 */
	@Test
	public void testHandleTimeout() {
		assertSame(elevatorState, elevatorState.handleTimeout());
	}

	/**
	 * Test method for {@link main.java.elevator.state.IdleState#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("Idle", elevatorState.toString());
	}

	/**
	 * Test method for {@link main.java.elevator.state.IdleState#getElevatorStateEnum()}.
	 */
	@Test
	public void testGetElevatorStateEnum() {
		assertEquals(ElevatorStateEnum.IDLE, elevatorState.getElevatorStateEnum());
	}

	/**
	 * Test method for {@link main.java.elevator.state.IdleState#IdleState(main.java.elevator.ElevatorContext)}.
	 */
	@Test
	public void testIdleState() {
		assertEquals(Door.OPEN, elevatorState.getContext().getDoors());
		assertEquals(Direction.IDLE, elevatorState.getContext().getDirection());
		assertEquals(Motor.IDLE, elevatorState.getContext().getMotor());
	}

}
