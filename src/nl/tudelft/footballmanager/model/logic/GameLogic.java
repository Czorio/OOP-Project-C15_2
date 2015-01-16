package nl.tudelft.footballmanager.model.logic;

import java.util.List;
import java.util.Random;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.MatchResult;
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
	
	/**
	 * Creates and initializes a GameLogic instance.
	 * @param gs The current gamestate to use.
	 */
	public GameLogic(GameState gs){
		GameLogic.gs = gs;
	}
	
	/**
	 * Plays all the games that should be played on the current matchday.
	 */
	public static void matchDay() {
		MatchScheme ms = gs.getMatchScheme();
		int matchDay = gs.getGameRound();
		
		// No more rounds
		if(matchDay >= gs.getMatchScheme().getMatchdays().size()) return;
		
		List<Match> todaysMatches = ms.getMatchdays().get(matchDay).getMatches();
		
		// Print todays matches.
		System.out.println("Todays matches are:");
		for(Match m : todaysMatches) {
			System.out.println(m.getHome().getTeam() + " - " + m.getAway().getTeam());
		}
		
		for(Match m : todaysMatches) {		
			TeamLogic.createAIActivePlayers(m.getHome());
			TeamLogic.createAIActivePlayers(m.getAway());
			
			m.setMatchResult(game(m.getHome(), m.getAway()));
		}
		
		TeamLogic.clearPlayers();
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
	public static MatchResult game(Team home, Team away){
		MatchResult matchResult = new MatchResult();
		int homeGoals = 0;
		int awayGoals = 0;
		int lastGoal = 0; //Minutes since last goal.
		int randomInterval = generateRandom(5, 12); //Interval where no goals can be scored.
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
			if(homeScoreChance + generateRandom(0, 80) > 220 && homeGoals < 10 && lastGoal >= randomInterval && generateRandom(0, 30) == 29) {
				homeGoals++;
				System.out.println(i + ": Team " + home.getTeam() + " scored a goal! (" + homeGoals + " - " + awayGoals + ")");
				lastGoal = 0;
				matchResult.addHomeScoreTime(i);
			}
			
			if(awayScoreChance + generateRandom(0, 80) > 220 && awayGoals < 10 && lastGoal >= randomInterval && generateRandom(0, 30) == 29) {
				awayGoals++;
				System.out.println(i + ": Team " + away.getTeam() + " scored a goal! (" + homeGoals + " - " + awayGoals + ")");
				lastGoal = 0;
				matchResult.addAwayScoreTime(i);
			}
			
			lastGoal++;
			
		}
		//Match ends here
		matchResult.setHomeScore(homeGoals);
		matchResult.setAwayScore(awayGoals);
		
		System.out.println("Final result: " + home.getTeam() + " " + homeGoals + " - " + awayGoals + " " + away.getTeam() + "\n");
		
		return matchResult;
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
	
	/**
	 * Set the seed used in the random number generator.
	 * 
	 * This method should only be used for testing purposed.
	 * @param seed The seed to be used
	 */
	public static void setSeed(long seed) {
		GameLogic.seed = seed;
	}

}
