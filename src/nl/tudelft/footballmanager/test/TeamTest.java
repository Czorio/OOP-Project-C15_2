package nl.tudelft.footballmanager.test;

import static org.junit.Assert.fail;

import java.io.File;

import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

import org.junit.Test;

public class TeamTest {

	@Test
	public void initialize(){
	
	File in = new File("GameData/Eredivisie.xml");
	XMLPlayer xmlplayer = new XMLPlayer(in);
	League league = xmlplayer.readFromFile("Eredivisie");
	
	Team testTeam = league.getTeam("Ajax");
	
	}
	
	@Test
	public void testTeamStringArrayListOfPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testTeamString() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBudget() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBudget() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByPosition() {
		fail("Not yet implemented");
	}

}
