package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.MatchScheme;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.logic.GameLogic;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Steven
 *
 */
public class GameLogicTest {

	File in = new File("GameData/Leagues.xml");
	XMLPlayer xmlplayer = new XMLPlayer(in);
	League league = xmlplayer.readFromFile("Eredivisie");
	
	Team ajax = league.getTeam("Ajax");
	List<Player> ajaxPlayers = ajax.getPlayers();
	Team testTeam = new Team ("Ajax", ajaxPlayers);
	
	Team feyenoord = league.getTeam("Feyenoord");
	List<Player> feyenoordPlayers = feyenoord.getPlayers();
	Team testTeam2 = new Team ("Feyenoord", feyenoordPlayers);
	
	GameState gs = new GameState("Steven", 1, league, testTeam);
	
	@Before
	public void initialize() {
		GameLogic.setSeed(1);
	}
	
	@After
	public void end() {
		
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#GameLogic(nl.tudelft.footballmanager.model.GameState)}.
	 */
	@Test
	public void testGameLogic() {
		assertNotNull(new GameLogic(gs));
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#matchDay()}.
	 */
	@Test
	public void testMatchDayReturnFalse() {
//		MatchScheme.setInFile("XML/GameStateTest3.xml");
		MatchScheme ms = new MatchScheme(league, 0);
		GameLogic.setGameState(gs);
		
		gs.setMatchScheme(ms);
		gs.setGameRound(500);
		
		assertFalse(GameLogic.matchDay());
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#matchDay()}.
	 */
	@Test
	public void testMatchDayReturnTrue() {
//		MatchScheme.setInFile("XML/GameStateTest3.xml");
		MatchScheme ms = new MatchScheme(league, 0);
		GameLogic.setGameState(gs);
		
		gs.setMatchScheme(ms);
		gs.setGameRound(1);
		
		assertTrue(GameLogic.matchDay());
	}
	
	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#matchDay()}.
	 */
	@Test
	public void testMatchDay() {
//		MatchScheme.setInFile("XML/GameStateTest3.xml");
		MatchScheme ms = new MatchScheme(league, 0);
		gs.setMatchScheme(ms);
		
		assertTrue(GameLogic.matchDay());
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#game(nl.tudelft.footballmanager.model.Team, nl.tudelft.footballmanager.model.Team)}.
	 */
	@Test
	public void testGame() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#generateInjury()}.
	 */
	@Test
	public void testGenerateInjury() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#generateRandom(int, int)}.
	 */
	@Test
	public void testGenerateRandom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.tudelft.footballmanager.model.logic.GameLogic#getSeed()}.
	 */
	@Test
	public void testGetSeed() {
		fail("Not yet implemented");
	}

}
