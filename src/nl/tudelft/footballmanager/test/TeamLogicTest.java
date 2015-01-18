package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.logic.TeamLogic;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class TeamLogicTest {

	File in = new File("GameData/Leagues.xml");
	XMLPlayer xmlplayer = new XMLPlayer(in);
	League league = xmlplayer.readFromFile("Eredivisie");
	
	Team ajax = league.getTeam("Ajax");
	List<Player> ajaxPlayers = ajax.getPlayers();
	
	Team testTeam = new Team ("Ajax", ajaxPlayers);
	
	@Before
	public void initialize() {
		TeamLogic.setSeed(1);
		TeamLogic.setTeamSetup("4 3 3");
		TeamLogic.setIsTesting(true);
	}
	
	@After
	public void end() {
		TeamLogic.clearPlayers();
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#TeamLogic()}.
	 */
	@Test
	public void testTeamLogic() {
		assertNull(new TeamLogic());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamOffScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamOffScore() {
		TeamLogic.createAIActivePlayers(ajax);
		assertEquals(47, TeamLogic.calculateTeamOffScore(ajax));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamDefScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamDefScore() {
		TeamLogic.createAIActivePlayers(ajax);
		assertEquals(60, TeamLogic.calculateTeamDefScore(ajax));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamStaminaScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamStaminaScore() {
		TeamLogic.createAIActivePlayers(ajax);
		assertEquals(63, TeamLogic.calculateTeamStaminaScore(ajax));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#calculateTeamTotalScore(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCalculateTeamTotalScore() {
		TeamLogic.createAIActivePlayers(ajax);
		assertEquals(170, TeamLogic.calculateTeamTotalScore(ajax));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#createAIActivePlayers(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testCreateAIActivePlayers() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests invalid amound of goalkeepers.
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#createAIActivePlayers(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testCreateAIInvalidGK() {
		System.out.println(ajax.getByPosition("Goalkeeper"));
		Player player1 = ajax.getPlayer(1053);
		Player player2 = ajax.getPlayer(2958);
		Player player3 = ajax.getPlayer(5646);
		
		ajax.removePlayer(player1);
		ajax.removePlayer(player2);
		ajax.removePlayer(player3);
		
		TeamLogic.createAIActivePlayers(ajax);
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#createSetup()}.
	 */
	@Test
	public void testCreateSetup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#getPlayingPlayersPerTeam(nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testGetPlayingPlayersPerTeam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#clearPlayers()}.
	 */
	@Test
	public void testClearPlayers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#getPlayingPlayers()}.
	 */
	@Test
	public void testGetPlayingPlayers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#setPlayingPlayers(java.util.List)}.
	 */
	@Test
	public void testSetPlayingPlayers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#getTeamSetup()}.
	 */
	@Test
	public void testGetTeamSetup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#setTeamSetup(java.lang.String)}.
	 */
	@Test
	public void testSetTeamSetup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#getSeed()}.
	 */
	@Test
	public void testGetSeed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.TeamLogic#setSeed(long)}.
	 */
	@Test
	public void testSetSeed() {
		fail("Not yet implemented");
	}

}
