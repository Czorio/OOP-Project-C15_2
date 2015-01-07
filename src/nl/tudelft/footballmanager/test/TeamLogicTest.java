/**
 * 
 */
package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.io.File;

import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

import org.junit.Before;
import org.junit.Test;

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
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#TeamLogic(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testTeamLogic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#composeActivePlayers(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testComposeActivePlayers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamOffScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamOffScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamDefScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamDefScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamTotalScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamTotalScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#gamesPlayed()}.
	 */
	@Test
	public void testGamesPlayed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#gamesWon()}.
	 */
	@Test
	public void testGamesWon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#gamesLost()}.
	 */
	@Test
	public void testGamesLost() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#gamesDraw()}.
	 */
	@Test
	public void testGamesDraw() {
		fail("Not yet implemented");
	}

}
