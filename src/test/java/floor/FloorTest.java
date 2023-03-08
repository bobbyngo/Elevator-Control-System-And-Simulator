package test.java.floor;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import main.java.floor.Floor;

/**
 * FloorTest tests and validates the methods for the Floor class
 * @author Hussein El Mokdad
 * @version 1.0, 02/04/23
 * @since 1.0, 02/04/23
 */
public class FloorTest {
	
	private Floor floor1;
	private Floor floor2;
	private Floor floor3;
	private Floor floor4;
	
	/**
	 * Responsible for setting up the test environment 
	 * @throws Exception
	 */
	@Before
    	public void setUp() throws Exception {
		floor1 = new Floor(1);
		floor2 = new Floor(2);
		floor3 = new Floor(6);
		floor4 = new Floor(10);
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
