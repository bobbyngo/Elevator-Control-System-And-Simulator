package main.java.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class is responsible for the method to encode and decode serializable data.
 * @author Zakaria Ismail
 */
public class SerializableEncoder {
	
	/**
	 * Encode data method.
	 * @param object Serializable, a serializable object to be encoded
	 * @return byte[], the encoded data
	 * @throws IOException
	 */
	public static byte[] encode(Serializable object) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		byte[] encodedArray = null;
		
		out = new ObjectOutputStream(byteStream);
		out.writeObject(object);
		//out.flush();
		encodedArray = byteStream.toByteArray();
		return encodedArray;
	}
	
	/**
	 * Decode data method
	 * @param data byte[], the data to be decoded
	 * @return Object, the decoded object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object decode(byte[] data) throws IOException, ClassNotFoundException {
		Object decodedObject = null;
		ObjectInput in = null;
		ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
		in = new ObjectInputStream(byteStream);
		decodedObject = in.readObject();
		byteStream.close();
		return decodedObject;
	}
}