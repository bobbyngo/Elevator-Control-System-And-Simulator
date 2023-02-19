package main.java.scheduler;

/**
 * SchedulerState enum class holds the possible states of the Scheduler.
 * @author Trong Nguyen
 * @version 2.0, 02/27/23
 */
public enum SchedulerState {
	Idle,
	AddRequest,
	ServerRequest,
	OutOfService
}
