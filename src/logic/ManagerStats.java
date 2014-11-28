/**
 * 
 */
package logic;

/**
 *personal stats of the current player.
 * @author Mathijs de Boer
 *
 */
public interface ManagerStats {
	// This is more for the UI statistics tab. Bitches love statistics.
	public int getGamesPlayed();
	public int getGamesWon();
	public int getGamesLost();
	public int getGamesDraw();
}
