package test.java.scheduler;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import main.java.SimulatorConfiguration;
import main.java.dto.AssignedElevatorRequest;
import main.java.dto.ElevatorRequest;
import main.java.dto.ElevatorStatus;
import main.java.elevator.Direction;
import main.java.elevator.state.ElevatorStateEnum;
import main.java.scheduler.SchedulerContext;
import main.java.scheduler.SchedulerSubsystem;

/**
 * SchedulerContextTest tests and validates the methods for the SchedulerContext class.
 * @author Bobby Ngo
 * @since 2.0, 03/25/23
 */
public class SchedulerContextTest {
	private SchedulerSubsystem schedulerSubsystem;
	private SchedulerContext scheduler;
	private ElevatorRequest elevatorRequest1;
	private ElevatorRequest elevatorRequest2;
	private ElevatorRequest elevatorRequest3;
	
	/**
	 * Set up the testing environment.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Before
	public void setUp() throws IOException{
		schedulerSubsystem = new SchedulerSubsystem(new SimulatorConfiguration("./src/main/resources/config.properties"));
		scheduler = new SchedulerContext(schedulerSubsystem);
	}
	
	/**
	 * Because the scheduler will need to bind the port, all the testing methods will be inside this so that
	 * it won't get port is in used
	 * Test the scheduler findBestElevatorToAssignRequest method
	 * Test the scheduler addAvailableElevatorStatus method
	 * Test the scheduler addPendingElevatorRequests method
	 * Test the scheduler addCompletedElevatorRequests method
	 * @throws ParseException 
	 */
	@Test
	public void testFindTheAvailableElevator() throws ParseException {
		
		elevatorRequest1 = new ElevatorRequest("00:08:32.0", 3, Direction.UP, 6);
		scheduler.addPendingElevatorRequests(elevatorRequest1);
		//Test addPendingElevatorRequests
		assertEquals(1, scheduler.getPendingElevatorRequests().size());
		
		//init the status to add to the available elevator status
		ElevatorStatus status1 = new ElevatorStatus(1, 3, Direction.UP, 0, ElevatorStateEnum.MOVING_UP);
		ElevatorStatus status10 = new ElevatorStatus(10, 4, Direction.UP, 0, ElevatorStateEnum.MOVING_UP);
		scheduler.addAvailableElevatorStatus(status1);
		scheduler.addAvailableElevatorStatus(status10);
		
		//Test addAvailableElevatorStatus (4 elevators will be added when the scheduler)
		assertEquals(6, scheduler.getAvailableElevatorStatus().size());
		
		AssignedElevatorRequest actual1 = scheduler.findBestElevatorToAssignRequest();		
		AssignedElevatorRequest expected1 = new AssignedElevatorRequest(1, elevatorRequest1);
		
		//Test find best available elevator for case moving up 
		assertEquals(actual1, expected1);
		
		//Test add completed request
		scheduler.addCompletedElevatorRequests(elevatorRequest1);
		assertEquals(1, scheduler.getCompletedElevatorRequests().size());
		
		// Test find best available elevator for case moving down
		elevatorRequest2 = new ElevatorRequest("00:08:33.0", 9, Direction.DOWN, 1);
		scheduler.addPendingElevatorRequests(elevatorRequest2);
		
		//Test addPendingElevatorRequests
		assertEquals(2, scheduler.getPendingElevatorRequests().size());
		
		ElevatorStatus status2 = new ElevatorStatus(2, 10, Direction.DOWN, 0, ElevatorStateEnum.MOVING_DOWN);
		ElevatorStatus status20 = new ElevatorStatus(20, 15, Direction.DOWN, 0, ElevatorStateEnum.MOVING_DOWN);
		scheduler.addAvailableElevatorStatus(status2);
		scheduler.addAvailableElevatorStatus(status20);
		
		//Test addAvailableElevatorStatus (4 elevators will be added when the scheduler)
		assertEquals(8, scheduler.getAvailableElevatorStatus().size());

		
		AssignedElevatorRequest actual2 = scheduler.findBestElevatorToAssignRequest();
		AssignedElevatorRequest expected2 = new AssignedElevatorRequest(2, elevatorRequest2);
		// Test find best available elevator for case going down
		assertEquals(actual2, expected2);
		
		//Test add completed request
		scheduler.addCompletedElevatorRequests(elevatorRequest2);
		assertEquals(2, scheduler.getCompletedElevatorRequests().size());
		
		// Test find best available elevator for case idle
		elevatorRequest3 = new ElevatorRequest("00:08:34.0", 2, Direction.UP, 10); 
		scheduler.addPendingElevatorRequests(elevatorRequest3);
		
		//Test addPendingElevatorRequests
		assertEquals(3, scheduler.getPendingElevatorRequests().size());
		
		ElevatorStatus status3 = new ElevatorStatus(3, 4, Direction.IDLE, 0, ElevatorStateEnum.IDLE);
		ElevatorStatus status30 = new ElevatorStatus(30, 4, Direction.IDLE, 0, ElevatorStateEnum.DOORS_STUCK);
		scheduler.addAvailableElevatorStatus(status3);
		scheduler.addAvailableElevatorStatus(status30);
		//Test addAvailableElevatorStatus (4 elevators will be added when the scheduler)
		assertEquals(10, scheduler.getAvailableElevatorStatus().size());
		
		AssignedElevatorRequest actual3 = scheduler.findBestElevatorToAssignRequest();
		AssignedElevatorRequest expected3 = new AssignedElevatorRequest(3, elevatorRequest3);
		// Test find best available elevator for case idle
		assertEquals(actual3, expected3);
		
		//Test add completed request
		scheduler.addCompletedElevatorRequests(elevatorRequest3);
		assertEquals(3, scheduler.getCompletedElevatorRequests().size());
	}
}
