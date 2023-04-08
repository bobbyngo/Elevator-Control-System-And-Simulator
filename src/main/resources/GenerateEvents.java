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

/**
 * Generates random elevator request events.
 * @author Trong Nguyen
 */
public class GenerateEvents {
	
	public static void main (String[] args) {
		try {
			generateEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates .txt file with random elevator request events.
	 * @throws IOException
	 */
	public static void generateEvents() throws IOException {
		int NUM_EVENT = 100;
		int MAX_FLOOR = 22;
		int MIN_FLOOR = 1;
		int RANGE_FLOOR = MAX_FLOOR - MIN_FLOOR + 1;
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
        int MAX_hh = (int) calendar.get(Calendar.HOUR_OF_DAY) + 1 % 24;
        int MIN_hh = (int) calendar.get(Calendar.HOUR_OF_DAY);
        int RANGE_hh = MAX_hh - MIN_hh + 1;
        NumberFormat formatter_xx = new DecimalFormat("00");
        
        int MAX_mm = 59;
        int MIN_mm = (int) calendar.get(Calendar.MINUTE);
        int RANGE_mm = MAX_mm - MIN_mm + 1;
        
        int MAX_ss = 59;
        int MIN_ss = (int) calendar.get(Calendar.SECOND);
        int RANGE_ss = MAX_ss - MIN_ss + 1;
        
        int MAX_SSS = 999;
        int MIN_SSS = 000;
        int RANGE_SSS = MAX_SSS - MIN_SSS + 1;
        NumberFormat formatter_xxx = new DecimalFormat("000");

        File file = new File("./src/main/resources/input.txt");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < NUM_EVENT; i++) {
        		int hh = (int)(Math.random() * RANGE_hh) + MIN_hh;
        		int mm = (int)(Math.random() * RANGE_mm) + MIN_mm;
        		int ss = (int)(Math.random() * RANGE_ss) + MIN_ss;
        		int SSS = (int)(Math.random() *RANGE_SSS) + MIN_SSS;
        		
        		// Time format - hh:mm:ss.SSS
        		String time = String.format("%s:%s:%s.%s", formatter_xx.format(hh), formatter_xx.format(mm), formatter_xx.format(ss), formatter_xxx.format(SSS));
        		
        		int startFloor = (int)(Math.random() * RANGE_FLOOR) + MIN_FLOOR;
        		int destFloor = (int)(Math.random() * RANGE_FLOOR) + MIN_FLOOR;
        		String direction = (startFloor < destFloor) ? "UP" : "DOWN";
        		// Event format - 03:08:32.000 15 DOWN 10
        		String event = String.format("%s %s %s %s", time, startFloor, direction, destFloor);
        		
            bw.write(event);
            bw.newLine();
        		System.out.println(event);
        }
        bw.close();
	}
}