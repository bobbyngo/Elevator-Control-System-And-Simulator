/**
 * 
 */
package main.java.dto;

import java.io.IOException;
import java.io.Serializable;

import main.java.floor.Floor;

/**
 * Floor entity DTO object to send to GUI
 * @author Zakaria Ismail
 *
 */
public class FloorGuiData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int floorNum;
	
	public FloorGuiData(Floor floor) {
		// TODO: implement stub - not implemented yet
	}
	
	public int getFloorNum() {
		return floorNum;
	}
	
	/**
	 * Decode FloorGuiData object
	 * @param data
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static FloorGuiData decode(byte[] data) throws ClassNotFoundException, IOException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (FloorGuiData) decodedObj;
	}
	
	/**
	 * Encode FloorGuiData object
	 * @return
	 * @throws IOException
	 */
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}

}
