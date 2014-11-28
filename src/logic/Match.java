/**
 * 
 */
package logic;

import dataModel.Team;

/**
 * @author Mathijs de Boer
 *
 */
public interface Match {
	// Two teams enter, one team leaves.
	public Team winner(Team team1, Team team2);
	
	// Get the total score of Offensive and Defensive stats on the position where they matter.
	public int getTotalOffScore(Team team);
	public int getTotalDefScore(Team team);
	
	// Because reasons
	public int generateRandom();
}
