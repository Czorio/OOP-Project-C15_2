package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import xml.XMLPlayer;
import dataModel.GameState;
import dataModel.League;
import dataModel.Player;
import dataModel.Team;

/**
 * Class to calculate the statistics of a team.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public final class TeamLogic {

	private static List<Player> playingPlayers;
	private List<Player> teamPlayers;

	/**
	 * Constructs and initializes a playing team.
	 * @param team All the players of a team.
	 */
	public TeamLogic(Team team) {
		this.teamPlayers = team.getPlayers();
		composeActivePlayers(team);
	}

	/**
	 * Gets all the playing players from a team for the next match.
	 * @param team The team to get the players from.
	 * @return Returns a list of active players.
	 */
	public List<Player> composeActivePlayers(Team team) {
		for (int i = 0; i < teamPlayers.size(); i++) {
			//TODO: Implement null curPos when not playing or change this
			if (teamPlayers.get(i).getCurPosition() != null) {
				playingPlayers.add(teamPlayers.get(i));
			}
		}

		if (playingPlayers.size() != 11) {
			System.out.println("The amount of players is not 11, but " + playingPlayers.size());
		}

		return playingPlayers;
	}

	/**
	 * Calculates the offensive score of a team.
	 * @param team The team to calculate the score of.
	 * @return Returns the total offensive score.
	 */
	public final static int calculateTeamOffScore(Team team) {
		int offScore = 0;

		for (int i = 0; i < playingPlayers.size(); i++) {
			offScore += PlayerLogic.calculatePlayerOffScore(playingPlayers.get(i));
		}

		return Math.round(offScore/11);
	}

	/**
	 * Calculates the defensive score of a team.
	 * @param team The team to calculate the score of.
	 * @return Returns the total defensive score.
	 */
	public final static int calculateTeamDefScore(Team team) {
		int defScore = 0;

		for (int i = 0; i < playingPlayers.size(); i++) {
			defScore += PlayerLogic.calculatePlayerDefScore(playingPlayers.get(i));
		}

		return Math.round(defScore/11);
	}

	/**
	 * Calculates the total score of a Team.
	 * @param team The team to calculate the score of.
	 * @return
	 */
	public final static int calculateTeamTotalScore(Team team) {
		//For now only based on the offensive and defensive score of a team.
		//TODO Add parameters to calculate the final score of a team
		return calculateTeamOffScore(team) + calculateTeamDefScore(team);
	}

	/**
	 * Creates a team for every club that is not the club the user is playing.
	 * @param gs The current gamestate.
	 */
	public static void createAITeam(GameState gs) {
		File in = new File("GameData/Eredivisie.xml");
		XMLPlayer xmlplayer = new XMLPlayer(in);
		League league = xmlplayer.readFromFile("Eredivisie");

		String playerTeam = gs.getTeam();
		Team team = league.getTeam(playerTeam);

		for (int i = 0; i < league.getTeams().size(); i++) {
			if(league.getTeams().get(i) != team) {
				createAIActivePlayers(league.getTeams().get(i));
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
		Random random = new Random();

		int nrDefenders = sc.nextInt();
		int nrMidfielders = sc.nextInt();
		int nrAttackers = sc.nextInt();

		List<Player> goalkeepers = team.getByPosition("Goalkeeper");
		List<Player> defenders = team.getByPosition("Defender");
		List<Player> midfielders = team.getByPosition("Midfielder");
		List<Player> attackers = team.getByPosition("Attacker");

		goalkeepers.get(random.nextInt(goalkeepers.size())).setCurPosition("Goalkeeper");

		while (nrDefenders != 0) {
			int randomNumber = random.nextInt(defenders.size());

			if (defenders.get(randomNumber).getCurPosition() == null) {
				defenders.get(randomNumber).setCurPosition("Defender");
				nrDefenders--;
			}
		}

		while (nrMidfielders != 0) {
			int randomNumber = random.nextInt(midfielders.size());

			if (midfielders.get(randomNumber).getCurPosition() == null) {
				midfielders.get(randomNumber).setCurPosition("Midfielder");
				nrMidfielders--;
			}
		}

		while (nrAttackers != 0) {
			int randomNumber = random.nextInt(attackers.size());

			if (attackers.get(randomNumber).getCurPosition() == null) {
				attackers.get(randomNumber).setCurPosition("Attacker");
				nrAttackers--;
			}
		}

		sc.close();
	}

	/**
	 * Creates a random setup for AI games.
	 * @return Returns a random setup from the list.
	 */
	public static String createSetup() {
		List<String> setup = new ArrayList<String>();
		setup.add("4-3-3");
		setup.add("4-4-2");
		setup.add("5-3-2");
		setup.add("3-4-3");
		setup.add("4-5-1");

		Random random = new Random();

		return setup.get(random.nextInt(setup.size()));
	}

	public final int gamesPlayed() {
		return -1;
	}

	public final int gamesWon() {
		return -1;
	}

	public static final int gamesLost() {
		return -1;
	}

	public static final int gamesDraw() {
		return -1;
	}
}
