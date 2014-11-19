import java.util.ArrayList;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Team {
	private String name;
	private ArrayList<Player> players;
	

	public Team(String name, ArrayList<Player> players) {
		this.name = name;
		this.players = players;
	}
	
	public Team(String name) {
		this(name, new ArrayList<Player>());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
