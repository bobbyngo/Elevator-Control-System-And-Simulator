package test.java.floor;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import main.java.floor.Floor;
import main.java.floor.parser.Parser;
import main.java.scheduler.Scheduler;


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
	private Scheduler scheduler;
	private Parser parser;
	
	/**
	 * Responsible for setting up the test environment 
	 * @throws Exception
	 */
	@Before
    	public void setUp() throws Exception {
		floor1 = new Floor(1, scheduler, parser);
		floor2 = new Floor(2, scheduler, parser);
		floor3 = new Floor(6, scheduler, parser);
		floor4 = new Floor(10, scheduler, parser);
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
