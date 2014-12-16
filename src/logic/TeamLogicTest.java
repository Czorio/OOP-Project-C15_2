/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataModel.Player;
import dataModel.Team;

/**
 * @author Steven
 *
 */
public class TeamLogicTest {

	@Before
	public void initialize() {
		//Player(Integer id, String firstname, String lastname, String club, String nationality, Date dateOfBirth, String position,
		//		 int pace, int shooting, int passing, int offensive, int defensive, int stamina) 
		ArrayList<Player> testPlayers = new ArrayList<Player>();
		Player testPlayer1 = new Player(1, "Henk", "de Graaf", "Ajax", "Nederlands", new Date(23/11/1990), "Attacker", 75, 79, 68, 75, 67, 86);
		
		Team testTeam = new Team("Ajax", testPlayers);
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
