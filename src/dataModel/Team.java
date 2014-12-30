package dataModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Team extends Observable {
	private String team;
	private List<Player> players;
	private int budget;
	

	/**
	 * Construct a team with given name and playerlist.
	 * @param team
	 * @param players
	 */
	public Team(String team, ArrayList<Player> players) {
		this.team = team;
		this.players = players;
		this.budget = 0;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Construct an empty Team
	 * @param team Name of the Team
	 */
	public Team(String team) {
		this(team, new ArrayList<Player>());
	}
	
	// Just an example
	public String toString() {
		String res = this.getTeam() + " (" + this.getBudget() + ") " + "\n";
		for (Player p : this.getPlayers()) {
			res += p.getFirstName() + " " + p.getLastName() + "\n";
		}
		
		return res;
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
	
	public static boolean transferPlayer(Player player, Team seller, Team buyer) {
		assert(player != null);
		assert(seller != null);
		assert(buyer != null);
		
		int price = player.getPrice();
		
		if (price > buyer.getBudget()) return false;
		
		seller.setBudget(seller.getBudget() + price);
		seller.removePlayer(player);
		
		buyer.setBudget(buyer.getBudget() - price);
		buyer.addPlayer(player);
		
		return true;
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
			if(players.get(i).getFirstName().equals(firstName) && players.get(i).getLastName().equals(lastName)) {
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
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/**
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	/**
	 * Get players with a certain position.
	 * @param position The position that the player must have.
	 * @return Returns a list with the players with the requested position.
	 */
	public List<Player> getByPosition(String position) {
		List<Player> certainPosition = new ArrayList<Player>();
		
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getPosition() == position) {
				certainPosition.add(players.get(i));
			}
		}
		
		return certainPosition;
	}
}
