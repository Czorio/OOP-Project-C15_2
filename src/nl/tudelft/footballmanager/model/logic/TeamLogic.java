package nl.tudelft.footballmanager.model.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

/**
 * Class used to generate an AI team and calculate the score of a team.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public final class TeamLogic {

	private static List<Player> playingPlayers = new ArrayList<Player>();
	private static GameState gs;

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
		
		for (Player p : playingPlayers) {
			if (p.getClub().equals(team.getTeam())) {
				offScore += PlayerLogic.calculatePlayerOffScore(p);
			}
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
		
		for (Player p : playingPlayers) {
			if (p.getClub().equals(team.getTeam())) {
				defScore += PlayerLogic.calculatePlayerDefScore(p);
			}
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
		
		for (Player p : playingPlayers) {
			if (p.getClub().equals(team.getTeam())) {
				stamScore += PlayerLogic.calculatePlayerStamina(p);
			}
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
	 * Creates a team for every club that is not the club the user is playing.
	 * @param gs The current gamestate.
	 */
	public static void createAITeam() {
		File in = new File("GameData/Leagues/" + gs.getLeague().getLeague() + ".xml");
		XMLPlayer xmlplayer = new XMLPlayer(in);
		League league = xmlplayer.readFromFile(gs.getLeague().getLeague());

		String pTeam = gs.getMyTeam().getTeam();
		Team playerTeam = league.getTeam(pTeam);
		
		for (Team team : league.getTeams()) {
			if(team != playerTeam) {
				createAIActivePlayers(team);
			}
		}
	}

	/**
	 * Creates an AI team. Picks random players and places them on their positions.
	 * @param team The team to create.
	 */
	public static void createAIActivePlayers(Team team) {
		String setup = createSetup();
		Scanner sc = new Scanner(setup);
		Random random = new Random(System.currentTimeMillis());
		System.out.println("The used setup for team " + team.getTeam() + " is " + setup);

		int nrDefenders = sc.nextInt();
		int nrMidfielders = sc.nextInt();
		int nrAttackers = sc.nextInt();

		List<Player> goalkeepers = team.getByPosition("Goalkeeper");
		List<Player> defenders = team.getByPosition("Defender");
		List<Player> midfielders = team.getByPosition("Midfielder");
		List<Player> attackers = team.getByPosition("Attacker");
		
		System.out.println("Goalkeepers: " + goalkeepers.size());
		System.out.println("Defenders: " + defenders.size());
		System.out.println("Midfielders: " + midfielders.size());
		System.out.println("Attackers: " + attackers.size());
		
		//Sets all players current position to "None"
		for (Player p : team.getPlayers()) {
			p.setCurPosition("None");
		}
		
		int randomKeeper = random.nextInt(goalkeepers.size());
		goalkeepers.get(randomKeeper).setCurPosition("Goalkeeper");
		playingPlayers.add(goalkeepers.get(randomKeeper));
		//System.out.println("Added goalkeeper");

		while (nrDefenders != 0) {
			int randomNumber = random.nextInt(defenders.size());

			if (defenders.get(randomNumber).getCurPosition().equals("None")) {
				defenders.get(randomNumber).setCurPosition("Defender");
				playingPlayers.add(defenders.get(randomNumber));
				nrDefenders--;
				//System.out.println("Added defender");
			}
		}

		while (nrMidfielders != 0) {
			int randomNumber = random.nextInt(midfielders.size());
			
			//Failsafe if the amount of midfielders needed is greater that the amount of midfielders.
			while (nrMidfielders > midfielders.size()) {
				int randomNumber1 =  random.nextInt(defenders.size());
				if (defenders.get(randomNumber1).getCurPosition().equals("None")) {
					defenders.get(randomNumber1).setCurPosition("Midfielder");
					playingPlayers.add(defenders.get(randomNumber1));
					nrMidfielders--;
					System.out.println(team.getTeam() + ": Too few midfielders for setup, adding defender as midfielder...");
				}
			}
			
			if (midfielders.get(randomNumber).getCurPosition().equals("None")) {
				midfielders.get(randomNumber).setCurPosition("Midfielder");
				playingPlayers.add(midfielders.get(randomNumber));
				nrMidfielders--;
				//System.out.println("Added midfielder");
			}
		}

		while (nrAttackers != 0) {
			int randomNumber = random.nextInt(attackers.size());
			
			//Failsafe if the amount of attackers needed is greater that the amount of attackers.
			while (nrAttackers > attackers.size()) {
				int randomNumber1 = random.nextInt(midfielders.size());
				if (midfielders.get(randomNumber1).getCurPosition().equals("None")) {
					midfielders.get(randomNumber1).setCurPosition("Attacker");
					playingPlayers.add(midfielders.get(randomNumber1));
					nrAttackers--;
					System.out.println(team.getTeam() + ": Too few attackers for setup, adding midfielder as attacker...");
				}
			}
			
			if (attackers.get(randomNumber).getCurPosition().equals("None")) {
				attackers.get(randomNumber).setCurPosition("Attacker");
				playingPlayers.add(attackers.get(randomNumber));
				nrAttackers--;
				//System.out.println("Added attacker");
			}
		}
		
		//TODO Write amount of games to file.
		//Sets the amount of played games for each playing player to + 1.
//		for(Player p : playingPlayers) {
//			p.setPlayedGames(p.getPlayedGames() + 1);
//		}

		sc.close();
	}

	/**
	 * Creates a random setup for AI games.
	 * @return Returns a random setup from the list.
	 */
	public static String createSetup() {
		List<String> setup = new ArrayList<String>();
		setup.add("4 3 3");
		setup.add("4 4 2");
		setup.add("5 3 2");
		setup.add("3 4 3");
		setup.add("4 5 1");

		Random random = new Random();
		return setup.get(random.nextInt(setup.size()));
	}

	public static final int gamesPlayed() {
		return -1;
	}

	public static final int gamesWon() {
		return -1;
	}

	public static final int gamesLost() {
		return -1;
	}

	public static final int gamesDraw() {
		return -1;
	}
}
