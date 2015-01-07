package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.Team;

import org.junit.Test;

public class MatchTest {

	Team t1 = new Team("Ajax");
	Team t2 = new Team("Feyenoord");
	Team t3 = new Team("Ado Den Haag");
	Team t4 = new Team("AZ Alkmaar");
	Match m1 = new Match(t1,t2);
	Match m2 = new Match(t1,t4);
	
	/**Constructor Match nl.tudelft.footballmanager.test
	 * Test the constructor with getters.
	 */
	@Test
	public void testMatch() {
		assertEquals(m1.getHome(),t1);
		assertEquals(m1.getAway(),t2);
		assertNotEquals(m1.getHome(),t2);
		assertNotEquals(m1.getAway(),t3);
	}

	/**setHome Test
	 * Use sethome method to set new home team. Use getHome to compare and nl.tudelft.footballmanager.test.
	 */
	@Test
	public void testSetHome() {
		m1.setHome(t4);
		m2.setHome(t2);
		assertEquals(m2.getHome(),t2);
		assertEquals(m1.getHome(),t4);
		assertNotEquals(m2.getHome(),t3);
	}

	/**setAway Test
	 * Use setAway method to set new away team. Use getAway to compare and nl.tudelft.footballmanager.test.
	 */
	@Test
	public void testSetAway() {
		m1.setAway(t2);
		m2.setAway(t3);
		assertEquals(m1.getAway(),t2);
		assertEquals(m2.getAway(),t3);
		assertNotEquals(m1.getAway(),t1);
		assertNotEquals(m1.getAway(),t4);		
	}

}
