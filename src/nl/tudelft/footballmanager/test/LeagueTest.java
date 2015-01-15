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
	Team feyenoordEredivisie = league.getTeam("Feyenoord");
	List<Player> ajax = ajaxEredivisie.getPlayers();
	List<Player> feyenoord = feyenoordEredivisie.getPlayers();
	Team testTeam = new Team ("Ajax", ajax);
	Team testTeam2 = new Team("Feyenoord", feyenoord );
	
	
	
	League testLeague = new League("Eredivisie", league.getTeams());
	League testLeague2 = new League("Barclays Premier League", league2.getTeams());
	League testLeague3 = new League("Eredivisie", league.getTeams());
	League testLeague4 = new League("Eredivisie", league2.getTeams());
	
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
		testLeague2.addTeam(testTeam);
		assertNotEquals(testLeague2.getTeam("Ajax"),testTeam2);
	}

	/**
	 * removeTeam Test
	 */
	@Test
	public void testRemoveTeam() {
		testLeague.removeTeam(testTeam);
		assertEquals(testLeague.getTeam("Ajax"),null);
		testLeague.removeTeam(testTeam);
		assertNotEquals(testLeague.getTeam("Ajax"),testTeam2);
	}

	/**
	 * equalsObject Test
	 */
	@Test
	public void testEqualsObject() {
		assertEquals(testLeague,testLeague3);
		assertNotEquals(testLeague,testLeague2);
		assertNotEquals(testLeague,testTeam);
		assertNotEquals(testLeague,testLeague4);       
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
