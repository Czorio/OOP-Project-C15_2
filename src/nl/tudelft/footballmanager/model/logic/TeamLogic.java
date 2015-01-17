package nl.tudelft.footballmanager.model.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;

/**
 * Class used to generate an AI team and calculate the score of a team.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public final class TeamLogic {

	private static List<Player> playingPlayers = new ArrayList<Player>();
	private static GameState gs; //Will be used to get players own team
	private static String teamSetup;

	/**
	 * Constructs and initializes a playing team.
	 * @param team All the players of a team.
	 */
	public TeamLogic(Team team, GameState gs) {
		TeamLogic.gs = gs;
	}

	/**
	 * Calculates the offensive score of a team.
	 * @param team The team to calculate the score of.
	 * @return Returns the total offensive score.
	 */
	public static final int calculateTeamOffScore(Team team) {
		int offScore = 0;
		
		for (Player p : getPlayersPerTeam(team)) {
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
		
		for (Player p : getPlayersPerTeam(team)) {
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
		
		for (Player p : getPlayersPerTeam(team)) {
			stamScore += PlayerLogic.calculatePlayerStamina(p);
		}
		
		return Math.round(stamScore/11);
	}

	/**
	 * Calculates the total score of a Team.
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
	 * Requirements: Team should have at least 1 goalkeeper and a total of 11 players.
	 * @param team The team to create.
	 */
	public static void createAIActivePlayers(Team team) {
		createSetup();
		Scanner sc = new Scanner(teamSetup);

//		System.out.println("The used setup for team " + team.getName() + " is " + teamSetup);
//		Random random = new Random(System.currentTimeMillis());
		
		int nrDefenders = sc.nextInt();
		int nrMidfielders = sc.nextInt();
		int nrAttackers = sc.nextInt();
		
		sc.close();

		List<Player> goalkeepers = team.getByPosition("Goalkeeper");
		List<Player> defenders = team.getByPosition("Defender");
		List<Player> midfielders = team.getByPosition("Midfielder");
		List<Player> attackers = team.getByPosition("Attacker");
		
//		System.out.println("Goalkeepers: " + goalkeepers.size());
//		System.out.println("Defenders: " + defenders.size());
//		System.out.println("Midfielders: " + midfielders.size());
//		System.out.println("Attackers: " + attackers.size());
		
		//Sets all players current position to "None"
		for (Player p : team.getPlayers()) {
			p.setCurPosition("None");
		}
		
		//Add a goalkeeper to the team.
		Collections.shuffle(goalkeepers);
		try {
			Player gk = goalkeepers.get(0);
			gk.setCurPosition("Goalkeeper");
			playingPlayers.add(gk);
		}
		
		catch (IndexOutOfBoundsException a) {
			System.out.println("");
		}
		
		//Add defenders to the team.
		Collections.shuffle(defenders);
		for(int i = 0; i < nrDefenders; i++) {
			try {
				Player def = defenders.get(i);
				def.setCurPosition("Defender");
				playingPlayers.add(def);
			} 
			
			catch (IndexOutOfBoundsException a) {
				System.out.println("Not enough defenders!");
				//TODO Handle exception
			}
		}
		
		//Add midfielders to the team.
		Collections.shuffle(midfielders);
		for(int i = 0; i < nrMidfielders; i++) {
			try {
				Player mid = midfielders.get(i);
				mid.setCurPosition("Midfielder");
				playingPlayers.add(mid);
			} 
			
			catch (IndexOutOfBoundsException a) {
				System.out.println("Not enough midfielders!");
				//TODO Handle exception
			}
		}
		
		//Add attackers to the team.
		Collections.shuffle(attackers);
		for(int i = 0; i < nrAttackers; i++) {
			try {
				Player at = attackers.get(i);
				at.setCurPosition("Attackers");
				playingPlayers.add(at);
			} 
			
			catch (IndexOutOfBoundsException a) {
				System.out.println("Not enough attackers!");
				//TODO Handle exception
			}
		}

		////////OLD
//		int randomKeeper = random.nextInt(goalkeepers.size());
//		goalkeepers.get(randomKeeper).setCurPosition("Goalkeeper");
//		playingPlayers.add(goalkeepers.get(randomKeeper));
//
//		counter = 0;
//		while (nrDefenders != 0 && counter < maxCounter) {
//			int randomNumber = random.nextInt(defenders.size());
//
//			if (defenders.get(randomNumber).getCurPosition().equals("None")) {
//				defenders.get(randomNumber).setCurPosition("Defender");
//				playingPlayers.add(defenders.get(randomNumber));
//				nrDefenders--;
//			}
//			
//			counter++;
//		}
//
//		counter = 0;
//		while (nrMidfielders != 0 && counter < maxCounter) {
//			int randomNumber = random.nextInt(midfielders.size());
//			
//			//Failsafe if the amount of midfielders needed is greater that the amount of midfielders.
//			while (nrMidfielders > midfielders.size() && counter < maxCounter) {
//				int randomNumber1 =  random.nextInt(defenders.size());
//				if (defenders.get(randomNumber1).getCurPosition().equals("None")) {
//					defenders.get(randomNumber1).setCurPosition("Midfielder");
//					playingPlayers.add(defenders.get(randomNumber1));
//					System.out.println(team.getTeam() + ": Too few midfielders for setup, adding defender as midfielder...");
//					
//					nrMidfielders--;
//					counter++;
//				}
//			}
//			
//			if (midfielders.get(randomNumber).getCurPosition().equals("None")) {
//				midfielders.get(randomNumber).setCurPosition("Midfielder");
//				playingPlayers.add(midfielders.get(randomNumber));
//				nrMidfielders--;
//			}
//			
//			counter++;
//		}
//
//		counter = 0;
//		while (nrAttackers != 0 && counter < maxCounter) {
//			int randomNumber = random.nextInt(attackers.size());
//			
//			//Failsafe if the amount of attackers needed is greater that the amount of attackers.
//			while (nrAttackers > attackers.size() && counter < maxCounter) {
//				int randomNumber1 = random.nextInt(midfielders.size());
//				if (midfielders.get(randomNumber1).getCurPosition().equals("None")) {
//					midfielders.get(randomNumber1).setCurPosition("Attacker");
//					playingPlayers.add(midfielders.get(randomNumber1));
//					System.out.println(team.getTeam() + ": Too few attackers for setup, adding midfielder as attacker...");
//					
//					nrAttackers--;
//					counter++;
//				}
//			}
//			
//			if (attackers.get(randomNumber).getCurPosition().equals("None")) {
//				attackers.get(randomNumber).setCurPosition("Attacker");
//				playingPlayers.add(attackers.get(randomNumber));
//				nrAttackers--;
//			}
//			
//			counter++;
//		}
		//////////END OF OLD
		
		//TODO Implement player choosing his own players
		//TODO implement stamina
		//Sets the amount of played games for each playing player to + 1.
//		for(Player p : playingPlayers) {
//			p.setPlayedGames(p.getPlayedGames() + 1);
//		}
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

		Random random = new Random();
		teamSetup = setup.get(random.nextInt(setup.size()));
	}
	
	public static List<Player> getPlayersPerTeam(Team team) {
		List<Player> players = new ArrayList<Player>(); 
		
		for (Player p : playingPlayers) {
			if (p.getClub().equals(team.getName())) {
				players.add(p);
			}
		 }
		
		return players;
	}
	
	public static void clearPlayers() {
		playingPlayers.clear();
	}

	public static final int gamesPlayed(Team team) {
		return -1;
	}

	public static final int gamesWon(Team team) {
		return -1;
	}

	public static final int gamesLost(Team team) {
		return -1;
	}

	public static final int gamesDraw(Team team) {
		return -1;
	}
}
