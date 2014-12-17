package logic;

import dataModel.Player;

/**
 * Class to calculate a players statistics.
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public final class PlayerLogic {

	private PlayerLogic() { }

	/**
	 * Calculates a player's offensive score by their position.
	 * @param player The player to calculate the score for.
	 * @return Returns the offensive score of a player.
	 */
	public static final int calculatePlayerOffScore(Player player){
		int playerOff = player.getOffensive();
		String curPosition = player.getCurPosition();

		if (curPosition == "Attacker") {
			playerOff = (int) Math.round(player.getOffensive() * 1.25);
		}

		else if (curPosition == "Defender") {
			playerOff = (int) Math.round(player.getOffensive() * 0.75);
		}

		return playerOff;
	}

	/**
	 * Calculates a player's defensive score by their position.
	 * @param player The player to calculate the score for.
	 * @return Returns the defensive score of a player.
	 */
	public static final int calculatePlayerDefScore(Player player){
		int playerDef = player.getDefensive();
		String curPosition = player.getCurPosition();

		if (curPosition == "Attacker") {
			playerDef = (int) Math.round(player.getDefensive() * 0.75);
		}

		else if (curPosition == "Defender") {
			playerDef = (int) Math.round(player.getDefensive() * 1.25);
		}

		return playerDef;
	}

	/**
	 * Calculates a player's stamina.
	 * @param player The player to calculate the stamina for.
	 * @return Returns the stamina of a player.
	 */
	public static final int calculatePlayerStamina(Player player){
		int stamina = player.getStamina(), result = 0;

		if (stamina >= 80) {
			result = stamina - (3 * player.getPlayedGames());
		}

		else if (stamina < 80 && stamina >= 65) {
			result = stamina - (4 * player.getPlayedGames());
		}

		else if (stamina < 65 && stamina >= 40) {
			result = stamina - (5 * player.getPlayedGames());
		}

		else if (stamina < 40) {
			result = stamina - (6 * player.getPlayedGames());
		}

		return result;
	}

	/**
	 * Calculates the amount of games a player has played.
	 * @param player The player to calculate the number of games for.
	 */
	public static final void calculatePlayedGames(Player player) {
		int playedGames = player.getPlayedGames();
		
		if (player.getCurPosition() != null) {
			player.setPlayedGames(playedGames + 1);
		}
	}
}
