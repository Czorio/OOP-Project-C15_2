package logic;

import java.util.Random;

import dataModel.GameState;
import dataModel.Team;

/**
 * Logic used to determine the winner of a game.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class GameLogic {

	private Team home, away;
	private static Random randomNumber = new Random(System.currentTimeMillis());

	public GameLogic(Team home, Team away, GameState gs){
		this.home = home;
		this.away = away;
		
		new TeamLogic(home, gs);
		new TeamLogic(away, gs);
	}
	
	/**
	 * Logic for the game itself. Determines when a team scores and the outcome of a match.
	 * A goal can only be scored every 2-8 minutes. A maximum of 10 goals per team can be scored.
	 * 
	 * Every match takes 90 + 0-6 minutes.
	 * 
	 * @return Returns the result of the match.
	 */
	public static String game(Team home, Team away){
		int homeGoals = 0;
		int awayGoals = 0;
		int lastGoal = 0; //Minutes since last goal.
		int randomInterval = generateRandom(2, 8); //Interval where no goals can be scored.
		int extraTime = generateRandom(0, 6); //Extra game time.
		int homeScoreChance = TeamLogic.calculateTeamTotalScore(home);
		int awayScoreChance = TeamLogic.calculateTeamTotalScore(away);
		
		System.out.println("\nHome total score: " + TeamLogic.calculateTeamTotalScore(home)); //TESTCODE
		System.out.println("Away total score: " + TeamLogic.calculateTeamTotalScore(away));
		System.out.println("\nPlay game!");
		
		//Match starts here
		//TODO: Add injuries, cards, ...?
		for (int i = 1; i <= (90 + extraTime); i++) {
			//TODO Implement for player games only
			System.out.println("Minute: " + i);
			
			//TODO Test values
			if(homeScoreChance + generateRandom(0,70) > 170 && homeGoals < 10 && lastGoal >= randomInterval && generateRandom(0,20) >= 19) {
				homeGoals++;
				System.out.println("Team " + home.getTeam() + " scored a goal!");
				System.out.println("The score is now " + homeGoals + " - " + awayGoals + "!");
				lastGoal = 0;
			}
			
			if(awayScoreChance + generateRandom(0,70) > 170 && awayGoals < 10 && lastGoal >= randomInterval && generateRandom(0,20) >= 19) {
				awayGoals++;
				System.out.println("Team " + away.getTeam() + " scored a goal!");
				System.out.println("The score is now " + homeGoals + " - " + awayGoals + "!");
				lastGoal = 0;
			}
			
			lastGoal++;
			
		}
		//Match ends here
		
		System.out.println("\nFinal result: " + home.getTeam() + " " + homeGoals + " - " + awayGoals + " " + away.getTeam());
		
		return homeGoals + " - " + awayGoals;
	}

	/**
	 * Generates a pseudo-random number with seed, between a min and max value.
	 * 
	 * @param seed A seed to use with the random generator, to generate a pseudo-random number for testing.
	 * @param min The minimum value.
	 * @param max The maximum value. Must be larger than min.
	 * @return Returns a semi-random number.
	 * @see java.util.Random#nextInt(int)
	 */
	public int generateRandomWithSeed(long seed, int min, int max){
		Random generator = new Random(seed);
		return generator.nextInt((max - min) + 1) + min;
	}

	/**
	 * Generates a pseudo-random random number between min and max, inclusive.
	 * 
	 * @param min The minimum value.
	 * @param max The maximum value. Must be larger than min.
	 * @return Returns a random number.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int generateRandom(int min, int max){
		return randomNumber.nextInt((max - min) + 1) + min;
	}

	/**
	 * @return the homeTeam
	 */
	public Team getHomeTeam() {
		return home;
	}

	/**
	 * @param homeTeam the homeTeam to set
	 */
	public void setHomeTeam(Team homeTeam) {
		this.home = homeTeam;
	}

	/**
	 * @return the awayTeam
	 */
	public Team getAwayTeam() {
		return away;
	}

	/**
	 * @param awayTeam the awayTeam to set
	 */
	public void setAwayTeam(Team awayTeam) {
		this.away = awayTeam;
	}

	/**
	 * @return the randomNumber
	 */
	public Random getRandomNumber() {
		return randomNumber;
	}

	/**
	 * @param randomNumber the randomNumber to set
	 */
	public void setRandomNumber(Random randomNumber) {
		GameLogic.randomNumber = randomNumber;
	}

}