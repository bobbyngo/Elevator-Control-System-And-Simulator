/**
 * 
 */
package main.java;

import java.io.IOException;
import java.util.ArrayList;

import main.java.dto.ElevatorRequest;
import main.java.parser.Parser;

/**
 * Hosts the starting point of execution for the
 * application.
 * @author Zakaria Ismail, 101143497
 *
 */
public class Main {

	/**
	 * Starting point of execution for the application.
	 * @param args	String[], command line arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		ArrayList<ElevatorRequest> elevatorRequestList = new ArrayList<>();
		
		//Parser parser = new Parser("<Full_File_Path>");
		Parser parser = new Parser("/Users/liuyu/eclipse-workspace/Elevator_Project/src/main/resources/input.txt");
		
		elevatorRequestList = parser.requestParser();
		
		for(ElevatorRequest request: elevatorRequestList) {
			System.out.println(request.getTimestamp().toString() + " " + request.getFloorRequest() + " " +
					request.getDirection() + " " + request.getFloorDestination());
		}
		
		return;
	}

}
