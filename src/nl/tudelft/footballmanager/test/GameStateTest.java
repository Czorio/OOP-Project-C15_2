/**
 * Test for GameState class
 */
package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.io.File;

import nl.tudelft.footballmanager.model.GameState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 * @version 03/12/14
 */
public class GameStateTest {

	File loadFile, loadFile2, saveFile;
	GameState testGameState1, testGameState2, testGameState3, testGameState4, testGameState5;
	
	@Before 
	public void initialize() {
		loadFile = new File("XML/GameStateTest.xml");
		loadFile2 = new File("XML/GameStateTest2.xml");
		saveFile = new File("XML/SaveGameStateTest.xml");
		testGameState1 = new GameState("Karel Janssen", 7, "Eredivisie", "ADO Den Haag");
		testGameState2 = new GameState("Pjotter Karelson", 1, "Eredivisie", "Feyenoord");
		testGameState3 = new GameState(loadFile);
		testGameState4 = new GameState(loadFile2);
		testGameState5 = new GameState(saveFile);
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Constructor.
	 */
	@Test
	public void testGameStateStringIntStringStringConstructor() {
		assertNotNull(testGameState1);
		assertNotNull(testGameState2);
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Coach name.
	 */
	@Test
	public void testGameStateStringIntStringStringCoachName() {
		assertEquals("Karel Janssen", testGameState1.getCoachName());
		assertEquals("Pjotter Karelson", testGameState2.getCoachName());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Round number.
	 */
	@Test
	public void testGameStateStringIntStringStringRound() {
		assertEquals(7, testGameState1.getGameRound());
		assertEquals(1, testGameState2.getGameRound());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests League.
	 */
	@Test
	public void testGameStateStringIntStringStringLeague() {
		assertEquals("Eredivisie", testGameState1.getLeague().getLeague());
		assertEquals("Eredivisie", testGameState2.getLeague().getLeague());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.lang.String, int, java.lang.String, java.lang.String)}.
	 * Tests Team.
	 */
	@Test
	public void testGameStateStringIntStringStringTeam() {
		assertEquals("ADO Den Haag", testGameState1.getMyTeam().getTeam());
		assertEquals("Feyenoord", testGameState2.getMyTeam().getTeam());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.io.File)}.
	 * Tests Constructor.
	 */
	@Test
	public void testGameStateFileContructor() {
		assertNotNull(testGameState3);	
		assertNotNull(testGameState4);
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.io.File)}.
	 * Tests Coach Name.
	 */
	@Test
	public void testGameStateFileCoachName() {
		assertEquals("Steven Meijer", testGameState3.getCoachName());
		assertEquals("Boris Schrijver", testGameState4.getCoachName());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.io.File)}.
	 * Tests Round number.
	 */
	@Test
	public void testGameStateFileRound() {
		assertEquals(4, testGameState3.getGameRound());
		assertEquals(5, testGameState4.getGameRound());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.io.File)}.
	 * Tests League.
	 */
	@Test
	public void testGameStateFileLeague() {
		assertEquals("Eredivisie", testGameState3.getLeague().getLeague());
		assertEquals("Eredivisie", testGameState4.getLeague().getLeague());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#GameState(java.io.File)}.
	 * Tests Team.
	 */
	@Test
	public void testGameStateFileTeam() {
		assertEquals("Feyenoord", testGameState3.getMyTeam().getTeam());
		assertEquals("Ajax", testGameState4.getMyTeam().getTeam());
	}

	//TODO Test GameState.LoadGameState(file)
//	/**
//	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#loadGameState(java.io.File)}.
//	 * Tests Coach name.
//	 */
//	@Test
//	public void testLoadGameStateFileCoachName() {
//		
//		assertEquals("Steven Meijer", testGameState2.getCoachName());
//		assertNotEquals("Boris Schrijver", testGameState2.getTeam());
//	}
//
//	/**
//	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#loadGameState(java.io.File)}.
//	 * Tests Round number.
//	 */
//	@Test
//	public void testLoadGameStateFileRound() {
//		assertEquals(4, testGameState2.getRound());
//		assertNotEquals(5, testGameState2.getRound());
//	}
//
//	/**
//	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#loadGameState(java.io.File)}.
//	 * Tests League.
//	 */
//	@Test
//	public void testLoadGameStateFileLeague() {
//		assertEquals("Eredivisie", testGameState2.getLeague());
//		assertNotEquals("Eerste Divisie", testGameState2.getLeague());
//	}
//
//	/**
//	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#loadGameState(java.io.File)}.
//	 * Tests Team.
//	 */
//	@Test
//	public void testLoadGameStateFileTeam() {
//		assertEquals("Feyenoord", testGameState2.getTeam());
//		assertNotEquals("Ajax", testGameState2.getTeam());
//	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#saveGameState(java.io.File)}.
	 * Tests saving of Coach name
	 */
	@Test
	public void testSaveGameStateCoachName() {
		GameState.save(testGameState1, saveFile);

		assertTrue(testGameState5.getCoachName().equals(testGameState1.getCoachName()));
		
		saveFile.delete();
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#saveGameState(java.io.File)}.
	 * Tests saving of Round number
	 */
	@Test
	public void testSaveGameStateRound() {
		GameState.save(testGameState1, saveFile);

		assertEquals(testGameState5.getGameRound(), testGameState1.getGameRound());
		
		saveFile.delete();
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#saveGameState(java.io.File)}.
	 * Tests saving of League
	 */
	@Test
	public void testSaveGameStateLeague() {
		GameState.save(testGameState1, saveFile);

		assertTrue(testGameState5.getLeague().equals(testGameState1.getLeague()));
		
		saveFile.delete();
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.GameState#saveGameState(java.io.File)}.
	 * Tests saving of Team name
	 */
	@Test
	public void testSaveGameStateTeam() {
		GameState.save(testGameState1, saveFile);

		assertTrue(testGameState5.getMyTeam().equals(testGameState1.getMyTeam()));
		
		saveFile.delete();
	}
	
	//TODO Fix delete file
	@After
	public void deleteOutput() {
		saveFile.delete();
	}

}
