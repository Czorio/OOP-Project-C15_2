package nl.tudelft.footballmanager.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Team object, containing players and properties of a team.
 * @author Toine Hartman <tjbhartman@gmail.com>
 */
public class Team extends Observable {
	private String name;
	private ObservableList<Player> players;
	private SimpleIntegerProperty budget, gamesWon, gamesDraw, gamesLost, gamesPlayed;

	/**
	 * Construct a team with given name and playerlist.
	 * 
	 * @param name
	 * @param players
	 */
	public Team(String name, List<Player> players) {
		this.name = name;
		this.players = FXCollections.observableList(players);
		this.budget = new SimpleIntegerProperty(100000);
		this.gamesWon = new SimpleIntegerProperty(0);
		this.gamesDraw = new SimpleIntegerProperty(0);
		this.gamesLost = new SimpleIntegerProperty(0);
		this.gamesPlayed = new SimpleIntegerProperty(0);

		for (Player player : this.players) {
			player.setTeam(this);
		}

		setChanged();
		notifyObservers(this);
	}

	/**
	 * Construct an empty Team
	 * @param name Name of the Team
	 */
	public Team(String name) {
		this(name, new ArrayList<Player>());
	}

	/**
	 * Alters the budget by a given amount
	 * @param mutation Amount to add (positive number) or subtract (negative number)
	 */
	public void alterBudget(int mutation) {
		setBudget(getBudget() + mutation);
	}
	
	public String toString() {
		return this.name;
	}

	public final static Comparator<Team> NAME_SORTER = new Comparator<Team>() {
		@Override
		public int compare(Team t1, Team t2) {
			return t1.getName().compareToIgnoreCase(t2.getName());
		}

	};

	/**
	 * Add player to this name, only if their doesn't exist a player with the
	 * same id.
	 * 
	 * @param player
	 */
	public boolean addPlayer(Player player) {
		boolean bExists = false;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == player.getId())
				bExists = true;
			else if (players.get(i).getFirstName()
					.equals(player.getFirstName())
					&& players.get(i).getLastName()
							.equals(player.getLastName()))
				bExists = true;
		}

		if (!bExists) {
			players.add(player);
			player.setTeam(this);

			setChanged();
			notifyObservers(this);
		}

		return !bExists;
	}
	
	/**
	 * Get the number of players with a set playing position
	 * @return the number
	 */
	public int getNumOfPlayingPlayers() {
		int count = 0;
		for (Player p : this.getPlayers()) {
			if (p.getCurPosition() != null) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Remove player, if the player exists, based on Player ID.
	 * @param player
	 */
	public boolean removePlayer(Player player) {
		boolean bExists = false;
		int index = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == player.getId()) {
				bExists = true;
				index = i;
			}
		}

		if (bExists) {
			players.remove(index);
			player.setTeam(null);

			setChanged();
			notifyObservers(this);
		}

		return bExists;
	}

	/**
	 * equals method
	 */
	public boolean equals(Object other) {
		if (other instanceof Team) {
			Team that = (Team) other;

			return this.name.equals(that.name)
					&& this.players.equals(that.players);
		}

		return false;
	}
	
	public SimpleIntegerProperty budgetProperty() {
		return this.budget;
	}
	
	public SimpleIntegerProperty gamesPlayedProperty() {
		return this.gamesPlayed;
	}
	
	public SimpleIntegerProperty gamesWonProperty() {
		return this.gamesWon;
	}
	
	public SimpleIntegerProperty gamesDrawProperty() {
		return this.gamesDraw;
	}
	
	public SimpleIntegerProperty gamesLostProperty() {
		return this.gamesLost;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String team) {
		this.name = team;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Get Player based on first and last name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public Player getPlayer(String firstName, String lastName) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getFirstName().equals(firstName)
					&& players.get(i).getLastName().equals(lastName)) {
				return players.get(i);
			}
		}

		return null;
	}

	/**
	 * Get Player based on Player ID.
	 * 
	 * @param id
	 * @return
	 */
	public Player getPlayer(int id) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == id) {
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
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = FXCollections.observableList(players);
		setChanged();
		notifyObservers(this.players);
	}

	/**
	 * @return the budget
	 */
	public int getBudget() {
		return budget.get();
	}

	/**
	 * @param budget
	 *            the budget to set
	 */
	public void setBudget(int budget) {
		this.budget.set(budget);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Get players with a certain position.
	 * 
	 * @param position
	 *            The position that the player must have.
	 * @return Returns a list with the players with the requested position.
	 */
	public List<Player> getByPosition(String position) {
		List<Player> certainPosition = new ArrayList<Player>();

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getReadablePosition() == position && players.get(i).getDisabledFor() == 0) {
				certainPosition.add(players.get(i));
			}
		}

		return certainPosition;
	}
	
	/**
	 * Gets the amount of players in the team.
	 * @return The amount of players.
	 */
	public int getPlayerCount() {
		return this.getPlayers().size();
	}
	
	/**
	 * Gets the amount of playingPlayers.
	 * @return
	 */
	public int getPlayingPlayers() {
		int playing = 0;
		
		for (Player player : players) {
			if(player.getCurPosition() != null) {
				playing++;
			}
		}
		return playing;
	}

	/**
	 * Gets the amount of games won.
	 * @return Games won.
	 */
	public int getGamesWon() {
		return gamesWon.get();
	}

	/**
	 * Sets the amount of games won.
	 * @param gamesWon The amount to set to.
	 */
	public void setGamesWon(int gamesWon) {
		this.gamesWon.set(gamesWon);
	}

	/**
	 * Gets the amount of games draw.
	 * @return Games draw.
	 */
	public int getGamesDraw() {
		return gamesDraw.get();
	}

	/**
	 * Sets the amount of games draw.
	 * @param gamesDraw The amount to set to.
	 */
	public void setGamesDraw(int gamesDraw) {
		this.gamesDraw.set(gamesDraw);
	}

	/**
	 * Gets the amount of games lost.
	 * @return Games lost.
	 */
	public int getGamesLost() {
		return gamesLost.get();
	}

	/**
	 * Sets the amount of games lost.
	 * @param gamesLost The amount to set.
	 */
	public void setGamesLost(int gamesLost) {
		this.gamesLost.set(gamesLost);
	}

	/**
	 * Gets the amount of games played.
	 * @return Games played.
	 */
	public int getGamesPlayed() {
		return gamesPlayed.get();
	}

	/**
	 * Sets the amount of games played.
	 * @param gamesPlayed The amount te set to.
	 */
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed.set(gamesPlayed);
	}
}
