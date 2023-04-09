package main.java.dto;

import java.io.IOException;
import java.io.Serializable;

import main.java.floor.Floor;

/**
 * Floor entity DTO object to send to GUI
 * @author Zakaria Ismail
 */
public class FloorGuiData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int floorNum;
	
	/**
	 * Constructor for floor GUI data.
	 * @param floor, the Floor object
	 */
	public FloorGuiData(Floor floor) {
		// TODO: implement stub - not implemented yet
	}
	
	/**
	 * Get the floor number.
	 * @return floorNum int, the current floor number
	 */
	public int getFloorNum() {
		return floorNum;
	}
	
	/**
	 * Decoding method.
	 * @param data byte[], data to be encoded
	 * @return FloorGuiData, the decode data object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static FloorGuiData decode(byte[] data) throws ClassNotFoundException, IOException {
		Object decodedObj = SerializableEncoder.decode(data);
		return (FloorGuiData) decodedObj;
	}
	
	/**
	 * Encoding method.
	 * @return byte[], the data to be encode
	 * @throws IOException
	 */
	public byte[] encode() throws IOException {
		byte[] encodedData = SerializableEncoder.encode(this);
		return encodedData;
	}

}
