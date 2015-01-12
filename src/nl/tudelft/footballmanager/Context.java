/**
 * 
 */
package nl.tudelft.footballmanager;

import java.util.ArrayList;
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
	private League myLeague = null;
	private ArrayList<League> allLeagues;
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
	 * @return the myLeague
	 */
	public League getLeague() {
		return myLeague;
	}

	/**
	 * @param myLeague the myLeague to set
	 */
	public void setLeague(League league) {
		this.myLeague = league;
		
		setChanged();
		notifyObservers(this.myLeague);
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

	/**
	 * @return the allLeagues
	 */
	public ArrayList<League> getAllLeagues() {
		return allLeagues;
	}

	/**
	 * @param allLeagues the allLeagues to set
	 */
	public void setAllLeagues(ArrayList<League> allLeagues) {
		this.allLeagues = allLeagues;
		
		setChanged();
		notifyObservers(this.allLeagues);
	}
	
	
}
