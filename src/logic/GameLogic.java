package logic;

import java.util.Random;

import dataModel.Team;

/**
 * Logic used to determine the winner of a game.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class GameLogic {

	private Team home, away;
	private Random randomNumber = new Random(System.currentTimeMillis());

	public GameLogic(Team home, Team away){
		this.home = home;
		this.away = away;
	}
	
	/**
	 * Logic for the game itself. Determines when a team scores and the outcome of a match.
	 * A goal can only be scored every 2-8 minutes. A maximum of 10 goals per team can be scored.
	 * 
	 * Every match takes 90 + 0-6 minutes.
	 * 
	 * @return Returns the result of the match.
	 */
	public String game(Team home, Team away){
		int homeGoals = 0;
		int awayGoals = 0;
		int lastGoal = 0; //Minutes since last goal.
		int randomInterval = generateRandom(2, 8); //Interval where no goals can be scored.
		int extraTime = generateRandom(0, 6); //Extra game time.
		
		//Match starts here
		//TODO: Add injuries, cards, ...?
		for (int i = 1; i <= 90 + extraTime; i++) {
			//TODO Implement for player games only
			System.out.println("Minute: " + i);
			
			//TODO Test values
			if(scoreChance(home) > 120 && homeGoals <= 10 && lastGoal >= randomInterval && generateRandom(0,10) >= 8) {
				homeGoals++;
				System.out.println("Team " + home + " scored a goal!");
				System.out.println("The score is now " + homeGoals + " - " + awayGoals + "!");
				lastGoal = 0;
			}
			
			if(scoreChance(away) > 120 && awayGoals <= 10 && lastGoal >= randomInterval && generateRandom(0,10) >= 8) {
				homeGoals++;
				System.out.println("Team " + away + " scored a goal!");
				System.out.println("The score is now " + homeGoals + " - " + awayGoals + "!");
				lastGoal = 0;
			}
			
			lastGoal++;
			
		}
		//Match ends here
		
		return homeGoals + " - " + awayGoals;
	}
	
	/**
	 * Determines the probability a team can score.
	 * @param team The team to calculate the score for.
	 * @return Returns the probability a team scores.
	 */
	public int scoreChance(Team team) {
		return (TeamLogic.calculateTeamTotalScore(team) + generateRandom(0, 50));
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
	public int generateRandom(int min, int max){
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
		this.randomNumber = randomNumber;
	}

}