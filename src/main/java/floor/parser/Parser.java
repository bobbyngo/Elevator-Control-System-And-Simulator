package main.java.floor.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import main.java.dto.ElevatorRequest;
import main.java.exception.*;
import main.java.dto.Direction;

/**
 * The Parser class reads through a standard text file 
 * and exports the information in a specified format
 * @author Patrick Liu
 * @since 1.0, 02/04/23
 * @version 2.0, 02/27/23
 */
public class Parser {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private FileReader input;
	private BufferedReader reader;
	private String lineEntry;
	private ArrayList<ElevatorRequest> elevatorRequestList;

	/**
	 * Constructor of the Parser class.
	 * @param fileName String, the name of the provided file
	 * @throws FileNotFoundException, when the file with the provided name can not be found
	 */
	public Parser(String fileName) throws FileNotFoundException {
		input = new FileReader(fileName);
		reader = new BufferedReader(input);
		lineEntry = null;
		elevatorRequestList = new ArrayList<>();
		logger.setLevel(Level.INFO);
	}
	
	/**
	 * fillTimestampZero is responsible for filling the elevator request's timestamp milliseconds position to 3 digits
	 * @param textRequest String, containing text information about the request
	 * @return an elevator request String
	 */
	public String fillTimestampZero(String textRequest) {
		Pattern formatOne = Pattern.compile("^([0-9]{2}+):([0-9]{2}+):([0-9]{2}+)\\.([0-9]{1}+)\\s([0-9]+)\\s(UP|DOWN)\\s([0-9]+)$");
		Pattern formatTwo = Pattern.compile("^([0-9]{2}+):([0-9]{2}+):([0-9]{2}+)\\.([0-9]{2}+)\\s([0-9]+)\\s(UP|DOWN)\\s([0-9]+)$");
		Pattern formatThree = Pattern.compile("^([0-9]{2}+):([0-9]{2}+):([0-9]{2}+)\\.([0-9]{3}+)\\s([0-9]+)\\s(UP|DOWN)\\s([0-9]+)$");
		
		Matcher m = formatOne.matcher(textRequest);
		if (m.matches()) {
			String parts[] = textRequest.split(" ", 2);
			return parts[0] + "00 " + parts[1];
		}
		
		m = formatTwo.matcher(textRequest);
		if (m.matches()) {
			String parts[] = textRequest.split(" ", 2);
			return parts[0] + "0 " + parts[1];
		}
		
		m = formatThree.matcher(textRequest);
		if (m.matches()) {
			return textRequest;
		}
		
		logger.severe("Incorrect Elevator Request String Provided");
		return null;
	}
	
	/**
	 * textParser is responsible for parsing a String and storing 
	 * the extracted information in an ElevatorRequest object.
	 * @param textRequest String, containing text information about the request
	 * @return an ElevatorRequest object
	 */
	public ElevatorRequest textParser(String textRequest) {
        ElevatorRequest elevatorRequest = null;
        String[] line = textRequest.split(" ");
        Timestamp timestamp = null;
        
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(currentTime.toString().split(" ")[0] + " " + line[0]);
            timestamp = new Timestamp(parsedDate.getTime());
            
            if(line.length != 4) {
	    		throw new ElevatorReqParamException("");
	    	}
            
            elevatorRequest = new ElevatorRequest(timestamp, 
                    Integer.valueOf(line[1]), 
                    Direction.valueOf(line[2]), 
                    Integer.valueOf(line[3]));
        
        } catch (ParseException e) {
	    	logger.severe(e.getMessage());
        } catch(ElevatorReqParamException e) {
        	logger.severe(e.getMessage());
        } catch (NumberFormatException e) {
        	logger.severe(e.getMessage());
        } catch (IllegalArgumentException e) {
        	logger.severe(e.getMessage());
        }
        return elevatorRequest;
    }
	
	/**
	 * RequestParser is responsible for parsing the text file and 
	 * storing the extracted information in ElevatorRequest object.
	 * @return ArrayList, containing ElevatorRequest object
	 * @throws IOException, when input/output error is encountered
	 */
	public ArrayList<ElevatorRequest> requestParser() throws IOException {
		
		int lineNumber = 0;
		boolean parsingSuccess = true;
		ElevatorRequest request = null;
		
		while ((lineEntry = reader.readLine()) != null) {
		    String[] line = lineEntry.split(" ");		    
		    Timestamp timestamp = null;
		    lineNumber ++;
		    
		    try {
		    	parsingSuccess = true;
		    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		    	
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		        Date parsedDate = dateFormat.parse(currentTime.toString().split(" ")[0] + " " + line[0]);
		        timestamp = new Timestamp(parsedDate.getTime());
		    	
		    	if(line.length != 4) {
		    		throw new ElevatorReqParamException(
		    				"Line " + lineNumber);
		    	}
		    	
		    	request = new ElevatorRequest(timestamp, 
		    			Integer.valueOf(line[1]), 
			    		Direction.valueOf(line[2]), 
			    		Integer.valueOf(line[3]));
		    	elevatorRequestList.add(request);
		    	
		    } catch (ParseException e) {
			    	logger.severe(String.format("%s on line %d", e.getMessage(), lineNumber));
			    	parsingSuccess = false;
		    } catch(ElevatorReqParamException e) {
		    		logger.severe(String.format("%s on line %d", e.getMessage(), lineNumber));
			    	parsingSuccess = false;
		    } catch (NumberFormatException e) {
		    		logger.severe(String.format("%s on line %d", e.getMessage(), lineNumber));
			    	parsingSuccess = false;
		    } catch (IllegalArgumentException e) {
		    		logger.severe(String.format("%s on line %d", e.getMessage(), lineNumber));
			    	parsingSuccess = false;
		    } finally {			   
			    	if(parsingSuccess) {
            /*
			    		logger.info(String.format("Request %s %s %s %s added to the list \n",
			    				request.getTimestamp(), 
			    				request.getSourceFloor(), 
			    				request.getDirection(), 
			    				request.getDestinationFloor()));
			    				*/
			    	}
		    }
		}
		return elevatorRequestList;	
	}

}