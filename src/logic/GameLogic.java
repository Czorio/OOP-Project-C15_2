package logic;

import java.util.Random;

import dataModel.Team;

/**
 * Logic used to determine the winner of a game.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class GameLogic {

	private Team homeTeam, awayTeam;
	private Random randomNumber = new Random(System.currentTimeMillis());
	
	public GameLogic(Team homeTeam, Team awayTeam){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public String generateOutcome(Team home, Team away){
		int homeScore = TeamLogic.calculateTeamTotalScore(home) + generateRandom();
		int awayScore = TeamLogic.calculateTeamTotalScore(away) + generateRandom();
		
		if (homeScore > awayScore) {
			return home.getTeam();
		}
		
		else if (homeScore < awayScore) {
			return home.getTeam();
		}
		
		else if (homeScore == awayScore) {
			return "draw";
		}
		
		return null;
	}

	/**
	 * Generates a semi-random number
	 * @param seed A seed to use with the random generator, to generate a semi-random number for testing
	 * @return Returns a semi-random number
	 */
	public int generateRandomWithSeed(long seed){
		Random generator = new Random(seed);
		int num = generator.nextInt(101);

		return num;
	}

	/**
	 * Generates a truely random number
	 * @return Returns a random number to use for the matchresult
	 */
	public int generateRandom(){
		return randomNumber.nextInt(101);
	}

	/**
	 * @return the homeTeam
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}

	/**
	 * @param homeTeam the homeTeam to set
	 */
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * @return the awayTeam
	 */
	public Team getAwayTeam() {
		return awayTeam;
	}

	/**
	 * @param awayTeam the awayTeam to set
	 */
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
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
