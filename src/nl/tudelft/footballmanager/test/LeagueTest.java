package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

import org.junit.Test;

public class LeagueTest {

	File in = new File("GameData/Leagues.xml");
	XMLPlayer xmlplayer = new XMLPlayer(in);
	
	League league = xmlplayer.readFromFile("Eredivisie");
	League league2 = xmlplayer.readFromFile("Barclays Premier League");
	
	Team ajaxEredivisie = league.getTeam("Ajax");
	List<Player> ajax = ajaxEredivisie.getPlayers();
	Team testTeam = new Team ("Ajax", ajax);
	
	
	
	League testLeague = new League("Eredivisie", league.getTeams());
	League testLeague2 = new League("Barclays Premier League", league2.getTeams());
	
	/**
	 * League constructor Test
	 */
	@Test
	public void testLeagueStringArrayListOfTeam() {
		assertEquals(testLeague.getTeam("Ajax"),testTeam);
	}

	/**
	 * addTeam Test
	 */
	@Test
	public void testAddTeam() {
		testLeague2.addTeam(testTeam);
		assertEquals(testLeague.getTeam("Ajax"),testTeam);
	}

	/**
	 * removeTeam Test
	 */
	@Test
	public void testRemoveTeam() {
		testLeague.removeTeam(testTeam);
		assertEquals(testLeague.getTeam("Ajax"),null);
	}

	/**
	 * equalsObject Test
	 */
	@Test
	public void testEqualsObject() {
		assertNotEquals(testLeague,testLeague2);
	}

	/**
	 * setLeague Test
	 */
	@Test
	public void testSetLeague() {
		testLeague.setLeague("Premier League");
		assertEquals(testLeague.getLeague(),"Premier League");
	}

	/**
	 * setTeams Test
	 */
	@Test
	public void testSetTeams() {
		testLeague.setTeams(league.getTeams());
		assertEquals(testLeague.getTeam("Ajax"),testTeam);
	}

	/**
	 * getPossibleMatches Test
	 */
	@Test
	public void testGetPossibleMatches() {
		assertNotEquals(testLeague.getPossibleMatches(league),testLeague2.getPossibleMatches(league2));
	}

}
