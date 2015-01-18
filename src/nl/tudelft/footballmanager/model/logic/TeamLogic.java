package nl.tudelft.footballmanager.model.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;

/**
 * Class used to generate an AI team and calculate the score of a team.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public final class TeamLogic {

	private static List<Player> playingPlayers = new ArrayList<Player>();
	private static String teamSetup;
	private static long seed = System.currentTimeMillis();
	private static Random random = new Random(seed);

	public TeamLogic() { }

	/**
	 * Calculates the offensive score of a team.
	 * @param team The team to calculate the score of.
	 * @return Returns the total offensive score.
	 */
	public static final int calculateTeamOffScore(Team team) {
		int offScore = 0;
		
		for (Player p : getPlayingPlayersPerTeam(team)) {
			offScore += PlayerLogic.calculatePlayerOffScore(p);
		}
		
		return Math.round(offScore/11);
	}

	/**
	 * Calculates the defensive score of a team.
	 * @param team The team to calculate the score of.
	 * @return Returns the total defensive score.
	 */
	public static final int calculateTeamDefScore(Team team) {
		int defScore = 0;
		
		for (Player p : getPlayingPlayersPerTeam(team)) {
			defScore += PlayerLogic.calculatePlayerDefScore(p);
		}

		return Math.round(defScore/11);
	}
	
	/**
	 * Calculates the stamina score of a team. 
	 * @param team to calculate score
	 * @return calculated stamina of whole team
	 */
	public static final int calculateTeamStaminaScore(Team team){
		int stamScore = 0;
		
		for (Player p : getPlayingPlayersPerTeam(team)) {
			stamScore += PlayerLogic.calculatePlayerStamina(p);
		}
		
		return Math.round(stamScore/11);
	}

	/**
	 * Calculates the total score of a team.
	 * @param team The team to calculate the score of.
	 * @return Returns the total score of a certain team.
	 */
	public static final int calculateTeamTotalScore(Team team) {
		//For now only based on the offensive, defensive and stamina score of a team.
		//TODO Add parameters to calculate the final score of a team
		return (calculateTeamOffScore(team) + calculateTeamDefScore(team) + calculateTeamStaminaScore(team));
	}

	/**
	 * Creates an AI team. Picks random players and places them on their positions.
	 * 
	 * Requirements: Team should have at least a total of 11 players.
	 * @param team The team to create.
	 */
	public static void createAIActivePlayers(Team team) {
		createSetup();
		Scanner sc = new Scanner(teamSetup);

//		System.out.println("The used setup for team " + team.getName() + " is " + teamSetup);
		
		int nrGoalkeepers = 1;
		int nrDefenders = sc.nextInt();
		int nrMidfielders = sc.nextInt();
		int nrAttackers = sc.nextInt();
		int missingPlayers = 0;
		
		sc.close();

		List<Player> goalkeepers = team.getByPosition("Goalkeeper");
		List<Player> defenders = team.getByPosition("Defender");
		List<Player> midfielders = team.getByPosition("Midfielder");
		List<Player> attackers = team.getByPosition("Attacker");
		List<Player> allPlayers = team.getPlayers();
		
		//Sets all players current position to "None"
		for (Player p : team.getPlayers()) {
			p.setCurPosition("None");
		}
		
		Collections.shuffle(goalkeepers);
		Collections.shuffle(defenders);
		Collections.shuffle(midfielders);
		Collections.shuffle(attackers);
		
		//Add a goalkeeper to the team.
		try {
			Player gk = goalkeepers.get(0);
			gk.setCurPosition("Goalkeeper");
			playingPlayers.add(gk);
		}
		
		catch (IndexOutOfBoundsException a) {
			System.out.println("No goalkeeper available for team " + team.getName());
			missingPlayers++;
		}
		
		//Add defenders to the team.
		for(int i = 0; i < nrDefenders; i++) {
			try {
				Player def = defenders.get(i);
				def.setCurPosition("Defender");
				playingPlayers.add(def);
			} 
			
			catch (IndexOutOfBoundsException a) {
				System.out.println("Not enough defenders available for team " + team.getName());
				missingPlayers++;
			}
		}
		
		//Add midfielders to the team.
		for(int i = 0; i < nrMidfielders; i++) {
			try {
				Player mid = midfielders.get(i);
				mid.setCurPosition("Midfielder");
				playingPlayers.add(mid);
			} 
			
			catch (IndexOutOfBoundsException a) {
				System.out.println("Not enough midfielders available for team " + team.getName());
				missingPlayers++;
			}
		}
		
		//Add attackers to the team.
		for(int i = 0; i < nrAttackers; i++) {
			try {
				Player at = attackers.get(i);
				at.setCurPosition("Attackers");
				playingPlayers.add(at);
			} 
			
			catch (IndexOutOfBoundsException a) {
				System.out.println("Not enough attackers available for team " + team.getName());
				missingPlayers++;
			}
		}
		
		//Fills team as needed.
		for (int i = 0; i < missingPlayers; i++) {
			int randomNumber = random.nextInt(allPlayers.size());
			Player batlik = allPlayers.get(randomNumber);
			
			if (batlik.getCurPosition() != "None") {
				if (nrGoalkeepers - goalkeepers.size() > 0) {
					batlik.setCurPosition("Goalkeeper");
					System.out.println("Added gk");
					nrGoalkeepers++;
				}
				
				else if (nrDefenders - defenders.size() > 0) {
					batlik.setCurPosition("Defender");
					System.out.println("Added def");
					nrDefenders++;
				}
				
				else if (nrMidfielders - midfielders.size() > 0) {
					batlik.setCurPosition("Midfielder");
					System.out.println("Added mid");
					nrMidfielders++;
				}
				
				else if (nrAttackers - attackers.size() > 0) {
					batlik.setCurPosition("Attacker");
					System.out.println("Added att");
					nrAttackers++;
				}
			}
			
			else {
				System.out.println("Still missing players");
				i--;
			}
		}
	}

	/**
	 * Creates a random setup for AI games.
	 * @return Returns a random setup from the list.
	 */
	public static void createSetup() {
		List<String> setup = new ArrayList<String>();
		setup.add("4 3 3");
		setup.add("4 4 2");
		setup.add("5 3 2");
		setup.add("3 4 3");
		setup.add("4 5 1");

		teamSetup = setup.get(random.nextInt(setup.size()));
	}
	
	/**
	 * Gets all the playing players for a certain team.
	 * @param team The team to check for.
	 * @return Returns a list of playing players for a certain team.
	 */
	public static List<Player> getPlayingPlayersPerTeam(Team team) {
		List<Player> players = new ArrayList<Player>(); 
		
		for (Player p : playingPlayers) {
			if (p.getClub().equals(team.getName())) {
				players.add(p);
			}
		 }
		return players;
	}
	
	/**
	 * Clears the playingPlayer list for next game round.
	 */
	public static void clearPlayers() {
		playingPlayers.clear();
	}

	/**
	 * Gets the playingPlayers list.
	 * @return playingPlayers.
	 */
	public static List<Player> getPlayingPlayers() {
		return playingPlayers;
	}

	/**
	 * Sets the playingPlayers list.
	 * @param playingPlayers The players to be set.
	 */
	public static void setPlayingPlayers(List<Player> playingPlayers) {
		TeamLogic.playingPlayers = playingPlayers;
	}

	/**
	 * Gets the setup used for a team.
	 * @return Returns the used setup.
	 */
	public static String getTeamSetup() {
		return teamSetup;
	}

	/**
	 * Sets the setup to be used by a team.
	 * @param teamSetup The setup to use.
	 */
	public static void setTeamSetup(String teamSetup) {
		TeamLogic.teamSetup = teamSetup;
	}
	
	/**
	 * Gets the used seed.
	 * @return The used seed.
	 */
	public static long getSeed() {
		return seed;
	}

	/**
	 * Sets the seed to be used.
	 * @param seed The seed to use.
	 */
	public static void setSeed(long seed) {
		TeamLogic.seed = seed;
	}
}
