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
    Player testPlayer2 = new Player(5,"Jasper","Cillesen","Ajax","Eredivisie","Dutch", new Date(5/5/1950),"GK",0,0,0,0,70,70);
	
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
	 * Stamina > 80
	 */
	@Test
	public void testCalculatePlayerStamina() {
		testPlayer.setStamina(90);
		assertEquals(testPlayer.getStamina(),PlayerLogic.calculatePlayerStamina(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerStamina(nl.tudelft.footballmanager.model.Player)}
	 * Stamina between 80 and 65
	 */
	@Test
	public void testCalculatePlayerStaminaElseIf(){
		testPlayer.setStamina(70);
		assertEquals(testPlayer.getStamina(),PlayerLogic.calculatePlayerStamina(testPlayer));
		testPlayer.setStamina(30);
		testPlayer2.setStamina(85);
		assertNotEquals(testPlayer.getStamina(),PlayerLogic.calculatePlayerStamina(testPlayer2));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerStamina(nl.tudelft.footballmanager.model.Player)}
	 * Stamina between 65 and 40
	 */
	@Test
	public void testCalulatePlayerStaminaElseIf2(){
		testPlayer.setStamina(50);
		assertEquals(testPlayer.getStamina(),PlayerLogic.calculatePlayerStamina(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePlayerStamina(nl.tudelft.footballmanager.model.Player)}
	 * Stamina below 40
	 */
	@Test
	public void testCalculatePlayerStaminaElse(){
		testPlayer.setStamina(30);
		testPlayer2.setStamina(30);
		assertEquals(PlayerLogic.calculatePlayerStamina(testPlayer2),PlayerLogic.calculatePlayerStamina(testPlayer));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePrice(nl.tudelft.footballmanager.model.Player)}.
	 * Stats above 125
	 */
	@Test
	public void testCalculatePrice() {
		testPlayer.setDefensive(75);
		testPlayer.setOffensive(75);
		testPlayer.setStamina(75);
		//int stats = (testPlayer.getDefensive() + testPlayer.getOffensive() + testPlayer.getStamina());
		assertEquals(1125000,PlayerLogic.calculatePrice(testPlayer));
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.PlayerLogic#calculatePrice(nl.tudelft.footballmanager.model.Player)}. 
	 */
	@Test
	public void testCalculatePriceElse(){
		testPlayer.setDefensive(10);
		testPlayer.setOffensive(10);
		testPlayer.setStamina(10);
		assertEquals(90000,PlayerLogic.calculatePrice(testPlayer));
	}

}
