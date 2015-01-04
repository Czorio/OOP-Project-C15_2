/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;

import logic.GameLogic;
import org.junit.Test;

import xml.XMLPlayer;
import dataModel.GameState;
import dataModel.League;
import dataModel.Team;

/**
 * @author Steven
 *
 */
public class GameLogicTest {

	/**
	 * Test method for {@link logic.GameLogic#GameLogic(dataModel.Team, dataModel.Team)}.
	 */
	@Test
	public void testGameLogic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#game(dataModel.Team, dataModel.Team)}.
	 */
	@Test
	public void testGame() {
		File in = new File("GameData/Leagues/Eredivisie.xml");
		XMLPlayer xmlplayer = new XMLPlayer(in);
		League league = xmlplayer.readFromFile("Eredivisie");

		Team team1 = league.getTeam("Ajax");
		Team team2 = league.getTeam("FC Groningen");
		
		GameState gs = new GameState("Steven", 1, "Eredivisie", "ADO Den Haag");
		
		new GameLogic(team1, team2, gs);
		
		GameLogic.game(team1, team2);
		
		
		
		
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#scoreChance(dataModel.Team)}.
	 */
	@Test
	public void testScoreChance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#generateRandomWithSeed(long, int, int)}.
	 */
	@Test
	public void testGenerateRandomWithSeed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#generateRandom(int, int)}.
	 */
	@Test
	public void testGenerateRandom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#getHomeTeam()}.
	 */
	@Test
	public void testGetHomeTeam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#setHomeTeam(dataModel.Team)}.
	 */
	@Test
	public void testSetHomeTeam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#getAwayTeam()}.
	 */
	@Test
	public void testGetAwayTeam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#setAwayTeam(dataModel.Team)}.
	 */
	@Test
	public void testSetAwayTeam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#getRandomNumber()}.
	 */
	@Test
	public void testGetRandomNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.GameLogic#setRandomNumber(java.util.Random)}.
	 */
	@Test
	public void testSetRandomNumber() {
		fail("Not yet implemented");
	}

}
