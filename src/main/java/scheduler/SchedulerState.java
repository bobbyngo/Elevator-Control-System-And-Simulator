package main.java.scheduler;

/**
 * SchedulerState enum class holds the possible states of the Scheduler.
 * @author Trong Nguyen
 * @version 2.0, 02/27/23
 */
public enum SchedulerState {
	
	Idle {
		@Override
		public SchedulerState nextState() {
			return Ready;
		}
	},
	
	Ready {
		@Override
		public SchedulerState nextState() {
			return InService;
		}
	},
	
	InService {
		@Override
		public SchedulerState nextState() {
			return this;
		}
	};
	
	/**
	 * Moves the scheduler to the next state.
	 * @return SchedulerState, the enum state of th
	 */
	public abstract SchedulerState nextState();
}
