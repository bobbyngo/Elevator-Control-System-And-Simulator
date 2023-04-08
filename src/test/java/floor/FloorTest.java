package test.java.floor;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import main.java.SimulatorConfiguration;
import main.java.floor.FloorSubsystem;

/**
 * FloorTest tests and validates the methods for the Floor class
 * @author Hussein El Mokdad
 * @version 1.0, 02/04/23
 * @since 1.0, 02/04/23
 */
public class FloorTest {
	
	private FloorSubsystem floorSubsystem;
	
	/**
	 * Responsible for setting up the test environment 
	 * @throws Exception
	 */
	@Before
    	public void setUp() throws Exception {
		SimulatorConfiguration sc = new SimulatorConfiguration("./src/test/resources/config.properties");
		floorSubsystem = new FloorSubsystem(sc);
    	}
	
	/**
	 * Tests getting the floor number
	 * @throws IOException
	 */
	@Test
	public void testGetFloorNumber() throws IOException {
		// FIXME: implement tests here
	}
	
}
