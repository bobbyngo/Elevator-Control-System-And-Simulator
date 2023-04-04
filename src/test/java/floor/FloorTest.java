package test.java.floor;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import main.java.floor.FloorSubsystem;

/**
 * FloorTest tests and validates the methods for the Floor class
 * @author Hussein El Mokdad
 * @version 1.0, 02/04/23
 * @since 1.0, 02/04/23
 */
public class FloorTest {
	
	private FloorSubsystem floor1;
	private FloorSubsystem floor2;
	private FloorSubsystem floor3;
	private FloorSubsystem floor4;
	
	/**
	 * Responsible for setting up the test environment 
	 * @throws Exception
	 */
	@Before
    	public void setUp() throws Exception {
		floor1 = new FloorSubsystem(1);
		floor2 = new FloorSubsystem(2);
		floor3 = new FloorSubsystem(6);
		floor4 = new FloorSubsystem(10);
    	}
	
	/**
	 * Tests getting the floor number
	 * @throws IOException
	 */
	@Test
	public void testGetFloorNumber() throws IOException {
		assertEquals(1, floor1.getFloorNumber());
		assertEquals(2, floor2.getFloorNumber());
		assertEquals(6, floor3.getFloorNumber());
		assertEquals(10, floor4.getFloorNumber());
	}
	
}
