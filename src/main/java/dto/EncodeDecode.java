/**
 * 
 */
package main.java.dto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Contains the encoder and decoder methods for the transmission
 * of elevator requests
 * 
 * @author Trong Nguyen
 * @version 1.0, 03/17/23
 * @since 1.0, 03/17/23
 */
public class EncodeDecode {
	
	/**
	 * Encodes an elevator request object into a byte[] data.
	 * @param elevatorRequest, ElevatorRequest obj
	 * @return message byte[], the encoded elevatorRequest
	 * @throws IOException
	 */
	public static byte[] encodeData(ElevatorRequest elevatorRequest) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] message = null;
		try {
			os.write(elevatorRequest.toString().getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		message = os.toByteArray();
		return message;
	}
	
	/**
	 * Decodes byte[] data into an ElevatorRequest object.
	 * @param message byte[], the encoded elevatorRequest
	 * @return elevatorRequest, ElevatorRequest obj
	 * @throws IOException
	 */
	public static ElevatorRequest decodeData(DatagramPacket packet) {
		ElevatorRequest elevatorRequest = null;
		Timestamp timestamp = null;
	    String[] line = new String(packet.getData(), 0, packet.getLength()).split(" ");
		
	    try {
	    		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    		Date parsedDate = dateFormat.parse(currentTime.toString().split(" ")[0] + " " + line[0]);
	        timestamp = new Timestamp(parsedDate.getTime());
	    	
	        elevatorRequest = new ElevatorRequest(timestamp, 
	    			Integer.valueOf(line[1]), 
		    		Direction.valueOf(line[2]), 
		    		Integer.valueOf(line[3]));
	    } catch (NullPointerException npe) {
	    		return null;
	    } catch (ParseException pe) {
    			return null;
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
	    return elevatorRequest;
	}
}
