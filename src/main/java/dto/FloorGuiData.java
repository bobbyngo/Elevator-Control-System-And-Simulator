package main.java.dto;

import java.io.IOException;
import java.io.Serializable;

/**
 * Floor entity DTO object to send to GUI.
 * @author Hussein El Mokdad
 */
public class FloorGuiData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int floorNum;
	private boolean upButtonLamp;
	private boolean downButtonLamp;
	
	/**
	 * Constructor for floor GUI data.
	 * @param floor, the Floor object
	 */
	public FloorGuiData(int floorNum, boolean upButtonLamp, boolean downButtonLamp) {
		this.floorNum = floorNum;
		this.upButtonLamp = upButtonLamp;
		this.downButtonLamp = downButtonLamp;
	}
	
	/**
	 * Get the floor number.
	 * @return floorNum int, the current floor number
	 */
	public int getFloorNum() {
		return floorNum;
	}
	
	/**
	 * Get the state of the button pointing in the upwards direction.
	 * @return the boolean of whether the button is on (true) or off (false) 
	 */
	public boolean getUpButtonLamp() {
		return upButtonLamp;
	}
	
	/**
	 * Get the state of the button pointing in the downwards direction.
	 * @return the boolean of whether the button is on (true) or off (false) 
	 */
	public boolean getDownButtonLamp() {
		return downButtonLamp;
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