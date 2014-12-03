package logic;

import java.util.Random;

/**
 * Logic used to determine winner of a game
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class GameLogic {

	private String homeTeam, awayTeam, winner;
	
	private Random randomNumber = new Random(System.currentTimeMillis());
	
	public GameLogic(String homeTeam, String awayTeam){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public String generateWinner(){
		//..
		
		return winner;
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
	
}
