/**
 * 
 */
package main.java.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.parser.ElevatorRequest.Direction;

/**
 * The Parser class reads through a standard text file 
 * and exports the information in a specified format
 * 
 * @author Patrick Liu, 101142730
 * @since   2023-01-23
 */
public class Parser {
	private FileReader input;
	private BufferedReader reader;
	private String lineEntry;
	private ArrayList<ElevatorRequest> elevatorRequestList;
	
	/**
	 * Constructor of the Parser class
	 * 
	 * @param fileName is the name of the provided file
	 * @throws FileNotFoundException when the file with the provided name can not be found
	 */
	public Parser(String fileName) throws FileNotFoundException {
		input = new FileReader(fileName);
		reader = new BufferedReader(input);
		lineEntry = null;
		elevatorRequestList = new ArrayList<>();
	}
	
	/**
	 * RequestParser is responsible for parsing the text file and storing the extracted
	 * information in ElevatorRequest objects 
	 * 
	 * @return an ArrayList of ElevatorRequest object
	 * @throws IOException when input/output error is encountered
	 */
	public ArrayList<ElevatorRequest> RequestParser() throws IOException {
		while ( (lineEntry = reader.readLine()) != null){
		    String[] line = lineEntry.split(" ");		    
		    java.sql.Timestamp timestamp = null;
		    
		    try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		        Date parsedDate = dateFormat.parse(line[0] + " " + line[1]);
		        timestamp = new  java.sql.Timestamp(parsedDate.getTime());
		    } catch(Exception e) {
		    	System.out.println(e);
		    }
		    
		    elevatorRequestList.add(new ElevatorRequest(timestamp, Integer.valueOf(line[2]), 
		    		Direction.valueOf(line[3]), Integer.valueOf(line[4])));

		}
		return elevatorRequestList;
		
	}
}
