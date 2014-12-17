/**
 * 
 */
package ui;

import dataModel.GameState;
import dataModel.League;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class Context {
	private static final Context instance = new Context();
	
	private GameState gameState;
	private League league;

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
	}
	
	
}
