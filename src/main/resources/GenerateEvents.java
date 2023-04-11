package main.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import main.java.SimulatorConfiguration;

/**
 * Generates random elevator request events.
 * 
 * @author Trong Nguyen
 */
public class GenerateEvents {

	/**
	 * Main method for GenerateEvent class.
	 * 
	 * @param args, default arguments
	 */
	public static void main(String[] args) {
		SimulatorConfiguration configuration = new SimulatorConfiguration("./src/main/resources/config.properties");
		try {
			generateEvents(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates txt file with random elevator request events start from current
	 * 
	 * @param config SimulatorConfiguration, configuration parameters
	 * @throws IOException
	 */
	public static void generateEvents(SimulatorConfiguration config) throws IOException {
		int NUM_EVENT = 30;
		int MAX_FLOOR = config.NUM_FLOORS;
		int MIN_FLOOR = 1;
		int RANGE_FLOOR = MAX_FLOOR - MIN_FLOOR + 1;

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int MIN_HH = (int) calendar.get(Calendar.HOUR_OF_DAY);
		int MAX_HH = (MIN_HH) % 24;
		int RANGE_HH = MAX_HH - MIN_HH + 1;
		NumberFormat formatter_xx = new DecimalFormat("00");

		int MIN_mm = (int) calendar.get(Calendar.MINUTE);
		int MAX_mm = (MIN_mm + 3) % 60;
		int RANGE_mm = MAX_mm - MIN_mm + 1;

		int MIN_ss = 0;
		int MAX_ss = 59;
		int RANGE_ss = MAX_ss - MIN_ss + 1;

		int MIN_SSS = 000;
		int MAX_SSS = 999;
		int RANGE_SSS = MAX_SSS - MIN_SSS + 1;
		NumberFormat formatter_xxx = new DecimalFormat("000");

		File file = new File("./src/main/resources/input.txt");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (int i = 0; i < NUM_EVENT; i++) {
			int hh = (int) (Math.random() * RANGE_HH) + MIN_HH;
			int mm = (int) (Math.random() * RANGE_mm) + MIN_mm;
			int ss = (int) (Math.random() * RANGE_ss) + MIN_ss;
			int SSS = (int) (Math.random() * RANGE_SSS) + MIN_SSS;

			// Time format - hh:mm:ss.SSS
			String time = String.format("%s:%s:%s.%s", formatter_xx.format(hh), formatter_xx.format(mm),
					formatter_xx.format(ss), formatter_xxx.format(SSS));

			int startFloor = (int) (Math.random() * RANGE_FLOOR) + MIN_FLOOR;
			int destFloor = (int) (Math.random() * RANGE_FLOOR) + MIN_FLOOR;
			String direction = (startFloor < destFloor) ? "UP" : "DOWN";
			// Event format - 03:08:32.000 15 DOWN 10
			String event = String.format("%s %s %s %s", time, startFloor, direction, destFloor);

			bw.write(event);
			bw.newLine();
		}
		bw.close();
	}
}