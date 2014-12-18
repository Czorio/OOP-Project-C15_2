/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import logic.TeamLogic;

import org.junit.Before;
import org.junit.Test;

import xml.XMLPlayer;
import dataModel.GameState;
import dataModel.League;
import dataModel.Player;
import dataModel.Team;

/**
 * @author Steven
 *
 */
public class TeamLogicTest {

	@Before
	public void initialize() {
	
		File in = new File("GameData/Eredivisie.xml");
		XMLPlayer xmlplayer = new XMLPlayer(in);
		League league = xmlplayer.readFromFile("Eredivisie");
		System.out.println(league.getTeam("Feyenoord").getPlayers());

		Team testTeam = league.getTeam("Ajax");
	}
	
	/**
	 * Test method for {@link logic.TeamLogic#TeamLogic(dataModel.Team)}.
	 */
	@Test
	public void testTeamLogic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#composeActivePlayers(dataModel.Team)}.
	 */
	@Test
	public void testComposeActivePlayers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#calculateTeamOffScore(dataModel.Team)}.
	 */
	@Test
	public void testCalculateTeamOffScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#calculateTeamDefScore(dataModel.Team)}.
	 */
	@Test
	public void testCalculateTeamDefScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#calculateTeamTotalScore(dataModel.Team)}.
	 */
	@Test
	public void testCalculateTeamTotalScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#gamesPlayed()}.
	 */
	@Test
	public void testGamesPlayed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#gamesWon()}.
	 */
	@Test
	public void testGamesWon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#gamesLost()}.
	 */
	@Test
	public void testGamesLost() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link logic.TeamLogic#gamesDraw()}.
	 */
	@Test
	public void testGamesDraw() {
		fail("Not yet implemented");
	}

}
