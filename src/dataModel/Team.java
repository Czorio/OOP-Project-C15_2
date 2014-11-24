package dataModel;
import java.util.ArrayList;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Team {
	private String team;
	private ArrayList<Player> players;
	

	/**
	 * Construct a team with given name and playerlist.
	 * @param team
	 * @param players
	 */
	public Team(String team, ArrayList<Player> players) {
		this.team = team;
		this.players = players;
	}
	
	/**
	 * Construct an empty Team
	 * @param team Name of the Team
	 */
	public Team(String team) {
		this(team, new ArrayList<Player>());
	}
	
	/**
	 * Add player to this team, only if their doesn't exist a player with the same id.
	 * @param player
	 */
	public void addPlayer(Player player) {
		boolean bExists = false;
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getId() == player.getId()) bExists = true;  
		}
		
		if(!bExists) {
			players.add(player);
		}
	}
	
	/**
	 * Remove player, if the player exists, based on Player ID.
	 * @param player
	 */
	public void removePlayer(Player player) {
		boolean bExists = false;
		int index = 0;
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getId() == player.getId()) {
				bExists = true;
				index = i;
			}
		}
		
		if(bExists) {
			players.remove(index);
		}
	}
	
	/**
	 * equals method
	 */
	public boolean equals(Object other) {
		if(other instanceof Team) {
			Team that = (Team)other;
			
			return this.team.equals(that.team) &&
					this.players.equals(that.players);
		}
		
		return false;
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	
	/**
	 * Get Player based on first and last name
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public Player getPlayer(String firstName, String lastName) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getFirstname().equals(firstName) && players.get(i).getLastname().equals(lastName)) {
				return players.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Get Player based on Player ID.
	 * @param id
	 * @return
	 */
	public Player getPlayer(int id) {
		for(int i = 0; i < players.size(); i ++) {
			if(players.get(i).getId() == id) {
				return players.get(i);
			}
		}
		
		return null;
	}

	/**
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}	
}
