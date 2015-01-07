/**
 * 
 */
package nl.tudelft.footballmanager.ui.controller;

import java.util.Observable;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Player;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Context extends Observable {
	private static final Context instance = new Context();
	
	private GameState gameState = null;
	private League league = null;
	private Player selectedPlayer = null;

	/**
	 * @return the instance
	 */
	public static Context getInstance() {
		return instance;
	}

	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * @param gameState the gameState to set
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		
		setChanged();
		notifyObservers(this.gameState);
	}

	/**
	 * @return the league
	 */
	public League getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(League league) {
		this.league = league;
		
		setChanged();
		notifyObservers(this.league);
	}

	/**
	 * @return the selectedPlayer
	 */
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	/**
	 * @param selectedPlayer the selectedPlayer to set
	 */
	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
		
		setChanged();
		notifyObservers(this.selectedPlayer);
	}
	
	
}
