package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.util.Date;

import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.logic.PlayerLogic;

import org.junit.Test;

/**
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class PlayerLogicTest {
	
	Player testPlayer = new Player(206, "Pieter", "van Bergen", "ADO Den Haag", "Nederlands", null, new Date(25/12/1993), "Midfielder", 83, 73, 70, 76, 63, 86);

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#PlayerLogic()}.
	 */
	@Test
	public void testPlayerLogic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerOffScore(nl.tudelft.footballmanager.model.Player)}.
	 * Tests Attacker position.
	 */
	@Test
	public void testCalculatePlayerOffScoreAttacker() {
		testPlayer.setCurPosition("Attacker");
		
		assertEquals((int) Math.round(testPlayer.getOffensive() * 1.25), PlayerLogic.calculatePlayerOffScore(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerOffScore(nl.tudelft.footballmanager.model.Player)}.
	 * Tests Defender position.
	 */
	@Test
	public void testCalculatePlayerOffScoreDefender() {
		testPlayer.setCurPosition("Defender");
		
		assertEquals((int) Math.round(testPlayer.getOffensive() * 0.75), PlayerLogic.calculatePlayerOffScore(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerOffScore(nl.tudelft.footballmanager.model.Player)}.
	 * Tests other position.
	 */
	@Test
	public void testCalculatePlayerOffScoreElse() {
		testPlayer.setCurPosition("Midfielder");
		
		assertEquals(testPlayer.getOffensive(), PlayerLogic.calculatePlayerOffScore(testPlayer));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerDefScore(nl.tudelft.footballmanager.model.Player)}.
	 * Tests Attacker position.
	 */
	@Test
	public void testCalculatePlayerDefScoreAttacker() {
		testPlayer.setCurPosition("Attacker");
		
		assertEquals((int) Math.round(testPlayer.getDefensive() * 0.75), PlayerLogic.calculatePlayerDefScore(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerDefScore(nl.tudelft.footballmanager.model.Player)}.
	 * Tests Defender position.
	 */
	@Test
	public void testCalculatePlayerDefScoreDefender() {
		testPlayer.setCurPosition("Defender");
		
		assertEquals((int) Math.round(testPlayer.getDefensive() * 1.25), PlayerLogic.calculatePlayerDefScore(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerDefScore(nl.tudelft.footballmanager.model.Player)}.
	 * Tests other position.
	 */
	@Test
	public void testCalculatePlayerDefScoreElse() {
		testPlayer.setCurPosition("Midfielder");
		
		assertEquals(testPlayer.getDefensive(), PlayerLogic.calculatePlayerDefScore(testPlayer));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerStamina(nl.tudelft.footballmanager.model.Player)}.
	 */
	@Test
	public void testCalculatePlayerStamina() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayedGames(nl.tudelft.footballmanager.model.Player)}.
	 */
	@Test
	public void testCalculatePlayedGames() {
		fail("Not yet implemented");
	}

}
