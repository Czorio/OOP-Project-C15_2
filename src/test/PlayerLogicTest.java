package test;

import static org.junit.Assert.*;

import java.util.Date;

import logic.PlayerLogic;

import org.junit.Test;

import dataModel.Player;

/**
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class PlayerLogicTest {
	
	Player testPlayer = new Player(206, "Pieter", "van Bergen", "ADO Den Haag", "Nederlands", new Date(25/12/1993), "Midfielder", 83, 73, 70, 76, 63, 86);

	/**
	 * Test method for {@link logic.PlayerLogic#PlayerLogic()}.
	 */
	@Test
	public void testPlayerLogic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerOffScore(dataModel.Player)}.
	 * Tests Attacker position.
	 */
	@Test
	public void testCalculatePlayerOffScoreAttacker() {
		testPlayer.setCurPosition("Attacker");
		
		assertEquals((int) Math.round(testPlayer.getOffensive() * 1.25), PlayerLogic.calculatePlayerOffScore(testPlayer));
	}
	
	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerOffScore(dataModel.Player)}.
	 * Tests Defender position.
	 */
	@Test
	public void testCalculatePlayerOffScoreDefender() {
		testPlayer.setCurPosition("Defender");
		
		assertEquals((int) Math.round(testPlayer.getOffensive() * 0.75), PlayerLogic.calculatePlayerOffScore(testPlayer));
	}
	
	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerOffScore(dataModel.Player)}.
	 * Tests other position.
	 */
	@Test
	public void testCalculatePlayerOffScoreElse() {
		testPlayer.setCurPosition("Midfielder");
		
		assertEquals(testPlayer.getOffensive(), PlayerLogic.calculatePlayerOffScore(testPlayer));
	}

	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerDefScore(dataModel.Player)}.
	 * Tests Attacker position.
	 */
	@Test
	public void testCalculatePlayerDefScoreAttacker() {
		testPlayer.setCurPosition("Attacker");
		
		assertEquals((int) Math.round(testPlayer.getDefensive() * 0.75), PlayerLogic.calculatePlayerDefScore(testPlayer));
	}
	
	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerDefScore(dataModel.Player)}.
	 * Tests Defender position.
	 */
	@Test
	public void testCalculatePlayerDefScoreDefender() {
		testPlayer.setCurPosition("Defender");
		
		assertEquals((int) Math.round(testPlayer.getDefensive() * 1.25), PlayerLogic.calculatePlayerDefScore(testPlayer));
	}
	
	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerDefScore(dataModel.Player)}.
	 * Tests other position.
	 */
	@Test
	public void testCalculatePlayerDefScoreElse() {
		testPlayer.setCurPosition("Midfielder");
		
		assertEquals(testPlayer.getDefensive(), PlayerLogic.calculatePlayerDefScore(testPlayer));
	}

	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayerStamina(dataModel.Player)}.
	 */
	@Test
	public void testCalculatePlayerStamina() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.PlayerLogic#calculatePlayedGames(dataModel.Player)}.
	 */
	@Test
	public void testCalculatePlayedGames() {
		fail("Not yet implemented");
	}

}
