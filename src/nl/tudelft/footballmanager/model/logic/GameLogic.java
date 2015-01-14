package nl.tudelft.footballmanager.model.logic;

import java.util.List;
import java.util.Random;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.MatchScheme;
import nl.tudelft.footballmanager.model.Team;

/**
 * Logic used to play a gameday, games are played between a home team and an away team, based on the current matchscheme.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class GameLogic {

	private static GameState gs;
	private static long seed = System.currentTimeMillis();
	private static Random random = new Random(seed);
	

	public GameLogic(GameState gs){
		GameLogic.gs = gs;
	}
	
	/**
	 * Plays all the games that should be played on the current matchday.
	 */
	public static void matchDay() {
		MatchScheme ms = gs.getMatchScheme();
		int matchDay = gs.getGameRound();
		
		List<Match> todaysMatches = ms.getMatchdays().get(matchDay).getMatches();
		
		for(Match m : todaysMatches) {
			new TeamLogic(m.getHome(), gs);
			TeamLogic.createAIActivePlayers(m.getHome());
			new TeamLogic(m.getAway(), gs);
			TeamLogic.createAIActivePlayers(m.getAway());
			
			game(m.getHome(), m.getAway());
		}
	}
	
	/**
	 * Logic for the game itself. Determines when a team scores and the outcome of a match.
	 * A goal can only be scored every 2-8 minutes. A maximum of 10 goals per team can be scored.
	 * 
	 * Every match takes 90 + 0-6 minutes.
	 * 
	 * @param home The home team to play.
	 * @param away The away team to play.
	 * @return Returns the result of the match.
	 */
	public static void game(Team home, Team away){
		int homeGoals = 0;
		int awayGoals = 0;
		int lastGoal = 0; //Minutes since last goal.
		int randomInterval = generateRandom(2, 8); //Interval where no goals can be scored.
		int extraTime = generateRandom(0, 6); //Extra game time.
		int homeScoreChance = TeamLogic.calculateTeamTotalScore(home);
		int awayScoreChance = TeamLogic.calculateTeamTotalScore(away);
		
		System.out.println("\nHome total score: " + homeScoreChance); //TESTCODE
		System.out.println("Away total score: " + awayScoreChance);
		System.out.println("\nPlay game!");
		
		//Match starts here
		//TODO: Add injuries, cards, ...?
		//TODO Balance score values.
		for (int i = 1; i <= (90 + extraTime); i++) {
			if(homeScoreChance + generateRandom(0, 80) > 215 && homeGoals < 10 && lastGoal >= randomInterval && generateRandom(0, 30) >= 29) {
				homeGoals++;
				System.out.println(i + ": Team " + home.getTeam() + " scored a goal! (" + homeGoals + " - " + awayGoals + ")");
				lastGoal = 0;
			}
			
			if(awayScoreChance + generateRandom(0, 80) > 215 && awayGoals < 10 && lastGoal >= randomInterval && generateRandom(0, 30) >= 29) {
				awayGoals++;
				System.out.println(i + ": Team " + away.getTeam() + " scored a goal! (" + homeGoals + " - " + awayGoals + ")");
				lastGoal = 0;
			}
			
			lastGoal++;
			
		}
		//Match ends here
		
		System.out.println("\nFinal result: " + home.getTeam() + " " + homeGoals + " - " + awayGoals + " " + away.getTeam());
	}

	/**
	 * Generates a pseudo-random number between a min and max value.
	 * 
	 * @param min The minimum value.
	 * @param max The maximum value. Must be larger than min.
	 * @return Returns a semi-random number.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int generateRandom(int min, int max){
		return random.nextInt((max - min) + 1) + min;
	}
	
	//TODO set seed

}
