package nl.tudelft.footballmanager.model.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.MatchResult;
import nl.tudelft.footballmanager.model.MatchScheme;
import nl.tudelft.footballmanager.model.Player;
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
	private static List<Player> playingPlayers = TeamLogic.getPlayingPlayers();

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

		for(Match m : todaysMatches) {		
			TeamLogic.createAIActivePlayers(m.getHome());
			TeamLogic.createAIActivePlayers(m.getAway());

			m.setMatchResult(game(m.getHome(), m.getAway()));
		}

		//Sets the amount of played games for each playing player to + 1.
		//Resets every 9th playing day to keep scoring possible for AI.
		for(Player p : playingPlayers) {
			if(gs.getGameRound() % 9 == 0) {
				p.setPlayedGames(0);
			} else {
				p.setPlayedGames(p.getPlayedGames() + 1);
			}
		}

		TeamLogic.clearPlayers();
		gs.nextRound();
	}

	/**
	 * Logic for the game itself. Determines when a team scores and the outcome of a match.
	 * A goal can only be scored every 5-12 minutes. A maximum of 10 goals per team can be scored.
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
		
		List<Player> playersHome = TeamLogic.getPlayingPlayersPerTeam(home);
		List<Player> playersAway = TeamLogic.getPlayingPlayersPerTeam(away);

//		System.out.println("\n" + home.getName() + " total score: " + homeScoreChance); //TESTCODE
//		System.out.println(away.getName() + " total score: " + awayScoreChance);

		//Match starts here
		//TODO Balance score values.
		for (int i = 1; i <= (90 + extraTime); i++) {
			if ((homeScoreChance - awayScoreChance) + generateRandom(100, 200) > homeScoreChance 
					&& homeGoals < 10 
					&& lastGoal >= randomInterval 
					&& generateRandom(0, 30) == 9) {
				homeGoals++;
				lastGoal = 0;
				matchResult.addHomeGoal(i, playersHome.get(generateRandom(0, playersHome.size() - 1)));
			}

			if ((awayScoreChance - homeScoreChance) + generateRandom(100, 200) > awayScoreChance 
					&& awayGoals < 10 
					&& lastGoal >= randomInterval 
					&& generateRandom(0, 30) == 9) {
				awayGoals++;
				lastGoal = 0;
				matchResult.addAwayGoal(i, playersAway.get(generateRandom(0, playersAway.size() - 1)));
			}
			
			//Generates a random injury and gives it to a random player.
			int injuryChance = generateRandom(0, 10000);
			if (injuryChance == 990) {
				String injury = generateInjury();
				Player p = playersHome.get(random.nextInt(playersHome.size()));
				
				p.setInjury(injury);
			}
			
			else if (injuryChance == 991) {
				String injury = generateInjury();
				Player p = playersAway.get(random.nextInt(playersAway.size()));
				
				p.setInjury(injury);
				p.setDisabled(true);
			}

			lastGoal++;

		}
		//Match ends here

		matchResult.setHomeScore(homeGoals);
		matchResult.setAwayScore(awayGoals);

//		System.out.println("Final result: " + home.getName() + " " + homeGoals + " - " + awayGoals + " " + away.getName() + "\n");

		return matchResult;
	}
	
	/**
	 * Generates a random injury.
	 * @return Returns a random injury.
	 */
	public static String generateInjury() {
		List<String> injuries = new ArrayList<String>();
		injuries.add("Achilles Tendon Rupture");
		injuries.add("Sprained Ankle");
		injuries.add("Back Muscle Pain");
		injuries.add("Bursitis Knee");
		injuries.add("Dislocated Shoulder");
		injuries.add("Hamstring Strain");
		injuries.add("High Ankle Sprain");
		injuries.add("Knee Arthritis");
		injuries.add("Meniscus Tear");
		injuries.add("Pinched Nerve");
		injuries.add("Shin Splints");
		injuries.add("Shoulder Impingement");
		injuries.add("Thigh Strain");
		injuries.add("Stress Fracture");
		injuries.add("Wry Neck");
		injuries.add("Ruptured left testicle");
		injuries.add("Broken back");
		
		return injuries.get(random.nextInt(injuries.size()));
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
	 * Returns the used seed.
	 * @return The seed used.
	 */
	public static long getSeed() {
		return seed;
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
	
	/**
	 * Gets the current gamestate.
	 * @return Returns the gameState.
	 */
	public static GameState getGameState() {
		return GameLogic.gs;
	}
	
	/**
	 * Sets the currens gamestate.
	 * @param gs The gamestate to set.
	 */
	public static void setGameState(GameState gs) {
		GameLogic.gs = gs;
	}

}
