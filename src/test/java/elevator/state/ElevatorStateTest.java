/**
 * 
 */
package test.java.elevator.state;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.SimulatorConfiguration;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.state.ElevatorState;
import main.java.elevator.state.IdleState;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorStateTest {
	
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
		elevatorState = ElevatorStateTestUtil.initElevatorState(elevatorSubsystem);
	}

	/**
	 * Test method for {@link main.java.elevator.state.ElevatorState#start(main.java.elevator.ElevatorContext)}.
	 */
	@Test
	public void testStart() {
		assert elevatorState instanceof IdleState;
	}

}
