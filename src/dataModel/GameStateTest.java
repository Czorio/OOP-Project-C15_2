/**
 * Test for GameState class
 */
package dataModel;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 * @version 26/11/14
 */
public class GameStateTest {

	File loadFile = new File("src/dataModel/tests/GameStateTest.xml");
	File saveFile = new File("src/dataModel/tests/SaveGameStateTest.xml");
	GameState testGameState1 = new GameState("Karel Janssen", 7, "Eredivisie", "ADO Den Haag");
	GameState testGameState2 = new GameState(loadFile);
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests contructor.
	 */
	@Test
	public void testGameStateStringIntStringStringConstructor() {
		assertNotNull(testGameState1);
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Coach name.
	 */
	@Test
	public void testGameStateStringIntStringStringCoachName() {
		assertEquals("Karel Janssen", testGameState1.getCoachName());
		assertNotEquals("Pjotter Karelson", testGameState1.getCoachName());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Round number.
	 */
	@Test
	public void testGameStateStringIntStringStringRound() {
		assertEquals(7, testGameState1.getRound());
		assertNotEquals(1, testGameState1.getRound());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests League.
	 */
	@Test
	public void testGameStateStringIntStringStringLeague() {
		assertEquals("Eredivisie", testGameState1.getLeague());
		assertNotEquals("Eerste divisie", testGameState1.getLeague());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Team.
	 */
	@Test
	public void testGameStateStringIntStringStringTeam() {
		assertEquals("ADO Den Haag", testGameState1.getTeam());
		assertNotEquals("Feyenoord", testGameState1.getTeam());
	}

	/**
	 * Test method for {@link dataModel.GameState#GameState(java.io.File)}.
	 * Tests Constructor.
	 */
	@Test
	public void testGameStateFileContructor() {
		assertNotNull(testGameState2);	
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.io.File)}.
	 * Tests Coach Name.
	 */
	@Test
	public void testGameStateFileCoachName() {
		assertEquals("Steven Meijer", testGameState2.getCoachName());
		assertNotEquals("Boris Schrijver", testGameState2.getTeam());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.io.File)}.
	 * Tests Round number.
	 */
	@Test
	public void testGameStateFileRound() {
		assertEquals(4, testGameState2.getRound());
		assertNotEquals(5, testGameState2.getRound());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.io.File)}.
	 * Tests League.
	 */
	@Test
	public void testGameStateFileLeague() {
		assertEquals("Eredivisie", testGameState2.getLeague());
		assertNotEquals("Eerste Divisie", testGameState2.getLeague());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#GameState(java.io.File)}.
	 * Tests Team.
	 */
	@Test
	public void testGameStateFileTeam() {
		assertEquals("Feyenoord", testGameState2.getTeam());
		assertNotEquals("Ajax", testGameState2.getTeam());
	}

	/**
	 * Test method for {@link dataModel.GameState#loadGameState(java.io.File)}.
	 * Tests Coach name.
	 */
	@Test
	public void testLoadGameStateFileCoachName() {
		assertEquals("Steven Meijer", testGameState2.getCoachName());
		assertNotEquals("Boris Schrijver", testGameState2.getTeam());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#loadGameState(java.io.File)}.
	 * Tests Round number.
	 */
	@Test
	public void testLoadGameStateFileRound() {
		assertEquals(4, testGameState2.getRound());
		assertNotEquals(5, testGameState2.getRound());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#loadGameState(java.io.File)}.
	 * Tests League.
	 */
	@Test
	public void testLoadGameStateFileLeague() {
		assertEquals("Eredivisie", testGameState2.getLeague());
		assertNotEquals("Eerste Divisie", testGameState2.getLeague());
	}
	
	/**
	 * Test method for {@link dataModel.GameState#loadGameState(java.io.File)}.
	 * Tests Team.
	 */
	@Test
	public void testLoadGameStateFileTeam() {
		assertEquals("Feyenoord", testGameState2.getTeam());
		assertNotEquals("Ajax", testGameState2.getTeam());
	}

	/**
	 * Test method for {@link dataModel.GameState#saveGameState(java.io.File)}.
	 */
	@Test
	public void testSaveGameState() {
		testGameState1.saveGameState(saveFile);
		GameState testGameState3 = new GameState(saveFile);
		
		assertEquals(testGameState3, testGameState3);
	}

}
