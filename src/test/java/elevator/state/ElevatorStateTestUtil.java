/**
 * 
 */
package test.java.elevator.state;

import static org.junit.Assert.fail;

import java.text.ParseException;

import main.java.SimulatorConfiguration;
import main.java.dto.ElevatorRequest;
import main.java.elevator.Direction;
import main.java.elevator.ElevatorContext;
import main.java.elevator.ElevatorSubsystem;
import main.java.elevator.state.ElevatorState;

/**
 * @author Zakaria Ismail
 *
 */
public class ElevatorStateTestUtil {
	public static ElevatorState initElevatorState() {
		ElevatorState elevatorState = ElevatorState.start(initElevatorContext());
		return elevatorState;
	}
	
	public static ElevatorState initElevatorState(ElevatorSubsystem subsystem) {
		ElevatorState elevatorState = ElevatorState.start(initElevatorContext(subsystem));
		return elevatorState;
	}
	
	public static ElevatorContext initElevatorContext() {
		ElevatorContext context = new ElevatorContext(initElevatorSubsystem(), 1);
		return context;
	}
	
	public static ElevatorContext initElevatorContext(ElevatorSubsystem subsystem) {
		ElevatorContext context = new ElevatorContext(subsystem, 1);
		return context;
	}
	
	public static ElevatorSubsystem initElevatorSubsystem() {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/test/resources/config.properties");
		ElevatorSubsystem subsystem = new ElevatorSubsystem(sc);
		return subsystem;
	}
	
	public static ElevatorRequest initElevatorRequest(int srcFloor, Direction direction, int destFloor) {
		try {
			return new ElevatorRequest("07:01:15.000", srcFloor, direction, destFloor);
		} catch (ParseException e) {
			fail();
			e.printStackTrace();
		}
		return null;
	}
}
