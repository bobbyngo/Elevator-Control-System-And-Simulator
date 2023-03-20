/**
 * 
 */
package main.java.scheduler;

/**
 * Starts the two scheduler threads. One listens for floor requests and
 * the the other listens for completed elevator requests
 * 
 * @author Hussein Elmokdad
 * @since 1.0, 03/19/23
 * @version 1.0, 03/19/23
 */
public class SchedulerSubsystem {
	
	public Scheduler schedulerE; // Listens to completed elevator requests
	public Scheduler schedulerF; // Listens to new floor requests
	public Scheduler schedulerED; // For tracking elevator direction, state, and floor number
	public static int floorPortNumber; // The port number of the floor sending the requests. Used for providing the port when sending the completed requests
	public enum SchedulerType {
		ElevatorListener,
		FloorListener,
		ElevatorDataListener 
	}

	public SchedulerSubsystem() {
		schedulerE = new Scheduler(SchedulerType.ElevatorListener);
		schedulerF = new Scheduler(SchedulerType.FloorListener);
		schedulerED = new Scheduler(SchedulerType.ElevatorDataListener);
	}
	
	/**
	 * Main method for the SchedulerSubsystem class.
	 * @param args, default parameters
	 */
	public static void main(String[] args) {
		SchedulerSubsystem schedulerSubsystem = new SchedulerSubsystem();
		new Thread(schedulerSubsystem.schedulerE).start();
		new Thread(schedulerSubsystem.schedulerF).start();
		new Thread(schedulerSubsystem.schedulerED).start();
	}
}
