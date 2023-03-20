package main.java.scheduler;

/**
 * SchedulerStateOld enum class holds the possible states of the SchedulerOld.
 * @author Trong Nguyen
 * @version 2.0, 02/27/23
 */
public enum SchedulerStateOld {
	
	Idle {
		@Override
		public SchedulerStateOld nextState() {
			return Ready;
		}
	},
	
	Ready {
		@Override
		public SchedulerStateOld nextState() {
			return InService;
		}
	},
	
	InService {
		@Override
		public SchedulerStateOld nextState() {
			return Ready;
		}
	};
	
	/**
	 * Moves the scheduler to the next state.
	 * @return SchedulerStateOld, the enum state of th
	 */
	public abstract SchedulerStateOld nextState();
}
