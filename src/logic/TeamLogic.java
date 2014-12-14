package logic;

import java.util.List;

import dataModel.Player;
import dataModel.Team;

/**
* Class to calculate the statistics of a team.
* @author Steven Meijer <stevenmeijer9@gmail.com>
*
*/
public final class TeamLogic {

	private List<Player> playingPlayers;
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
	public final int calculateTeamOffScore(Team team) {
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
	public final int calculateTeamDefScore(Team team) {
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
	public final int calculateTeamTotalScore(Team team) {
		//For now only based on the offensive and defensive score of a team.
		//TODO Add parameters to calculate the final score of a team
		return calculateTeamOffScore(team) + calculateTeamDefScore(team);
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
