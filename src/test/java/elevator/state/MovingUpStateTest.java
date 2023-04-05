package test.java.elevator.state;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.elevator.Direction;
import main.java.elevator.Door;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.Motor;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.elevator.state.MovingUpState;
import main.java.elevator.state.StoppedState;

public class MovingUpStateTest {
	private ElevatorState elevatorState;
	private static ElevatorSubsystem elevatorSubsystem;

	@BeforeClass
	public static void classSetUp() {
		elevatorSubsystem = ElevatorStateTestUtil.initElevatorSubsystem();
	}

	@Before
	public void setUp() throws Exception {
		elevatorState = new MovingUpState(ElevatorStateTestUtil.initElevatorContext(elevatorSubsystem));
	}

	@Test
	public void testHandleRequestReceived() throws ParseException {
		// Case: elevator is passing by floor with request going in the UP direction
		ElevatorContext ctx = elevatorState.getContext();
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(1, Direction.UP, 2));
		assert elevatorState.handleRequestReceived() instanceof StoppedState;
		assertEquals(1, ctx.getCurrentFloor());
	}
	
	@Test
	public void testHandleRequestReceived2() throws ParseException {
		// Case: elevator is passing by floor with request going in DOWN direction
		// and it has no other UP requests to serve
		ElevatorContext ctx = elevatorState.getContext();
		assertTrue(ctx.incrementCurrentFloor());
		assertEquals(2, ctx.getCurrentFloor());
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(2, Direction.DOWN, 1));
		assert elevatorState.handleRequestReceived() instanceof StoppedState;
		assertEquals(2, ctx.getCurrentFloor());
	}
	
	@Test
	public void testHandleRequestReceivedToMovingUp() throws ParseException {
		// Case: elevator is passing by floor with request going in the DOWN direction while it still requests
		// to sweep up toward
		ElevatorContext ctx = elevatorState.getContext();
		assertTrue(ctx.incrementCurrentFloor());
		assertEquals(2, ctx.getCurrentFloor());
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(3, Direction.UP, 4));
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(2, Direction.DOWN, 1));
		assertSame(elevatorState, elevatorState.handleRequestReceived());
		assertEquals(2, ctx.getCurrentFloor());
	}

	@Test
	public void testHandleTimeoutToStoppedAtTopFloor() {
		// Case: elevator is at highest floor while moving up & should stop
		ElevatorContext ctx = elevatorState.getContext();
		int expectedFloor = 1;
		
		// Setup elevator context to the highest floor
		assertEquals(expectedFloor, ctx.getCurrentFloor());
		while (expectedFloor < ctx.getConfig().NUM_FLOORS) {
			expectedFloor++;
			assertTrue(ctx.incrementCurrentFloor());
			assertEquals(expectedFloor, ctx.getCurrentFloor());
		}
		assertEquals(ctx.getConfig().NUM_FLOORS, ctx.getCurrentFloor());
		assert elevatorState.handleTimeout() instanceof StoppedState;
		assertEquals(ctx.getConfig().NUM_FLOORS, ctx.getCurrentFloor());
	}

	@Test
	public void testHandleTimeoutToStopped() throws ParseException {
		// Case: elevator is passing by floor with request going in the UP direction
		ElevatorContext ctx = elevatorState.getContext();
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(2, Direction.UP, 3));
		assert elevatorState.handleTimeout() instanceof StoppedState;
	}
	
	@Test
	public void testHandleTimeoutToStopped2() throws ParseException {
		// Case: elevator is passing by floor with request going DOWN and has no other
		// requests above to serve
		ElevatorContext ctx = elevatorState.getContext();
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(3, Direction.DOWN, 1));
		assertTrue(ctx.incrementCurrentFloor());
		assertEquals(2, ctx.getCurrentFloor());
		assert elevatorState.handleTimeout() instanceof StoppedState;
		assertEquals(3, ctx.getCurrentFloor());
	}
	
	@Test
	public void testHandleTimeoutToMovingUp() throws ParseException {
		// Case: elevator is passing by floor with request going in DOWN direction
		// and has other requests above to serve
		ElevatorContext ctx = elevatorState.getContext();
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(4, Direction.UP, 5));
		ctx.addExternalRequest(ElevatorStateTestUtil.initElevatorRequest(3, Direction.DOWN, 1));
		assertTrue(ctx.incrementCurrentFloor());
		assertEquals(2, ctx.getCurrentFloor());
		// handleTimeout() will increment the current floor
		assert elevatorState.handleTimeout() instanceof MovingUpState;
		assertEquals(3, ctx.getCurrentFloor());
	}

	@Test
	public void testToString() {
		assertEquals("MovingUp", elevatorState.toString());
	}

	@Test
	public void testGetElevatorStateEnum() {
		assertEquals(ElevatorStateEnum.MOVING_UP, elevatorState.getElevatorStateEnum());
	}

	@Test
	public void testMovingUpState() {
		assertEquals(Motor.THROTTLE_UP, elevatorState.getContext().getMotor());
		assertEquals(Direction.UP, elevatorState.getContext().getDirection());
		assertEquals(Door.CLOSED, elevatorState.getContext().getDoors());
	}

}
