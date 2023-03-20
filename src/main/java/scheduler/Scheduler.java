/**
 * 
 */
package main.java.scheduler;

import main.java.SimulatorConfiguration;

/**
 * @author Zakaria Ismail
 *
 */
public class Scheduler {
	private SchedulerContext schedulerContext;
	private SimulatorConfiguration simulatorConfiguration;
	private Thread pushRequestListener;
	private Thread pullRequestListener;
	private Thread arrivalNotificationListener;
	
	public Scheduler(SimulatorConfiguration config) {
		simulatorConfiguration = config;
	}
	
	public void receiveRequestPush() {
		// receive elevator request push
	}
	
	public void receiveRequestPull() {
		// receive service request
	}
	
	public void receiveArrivalNotification() {
		
	}
	
	public static void main(String[] args) {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/main/resources/config.properties");
		Scheduler s = new Scheduler(sc);
	}
}
