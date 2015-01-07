/**
 * 
 */
package logic;

import dataModel.Player;

/**
 * @author Mathijs de Boer <czorio4@gmail.com>
 *
 */
public class GameEvent {
	private int minute;
	private Player player;
	private String card;
	private String injury;

	public GameEvent(int minute, Player player, String card, String injury) {
		this.minute = minute;
		this.player = player;
		this.card = card;
		this.injury = injury;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameEvent other = (GameEvent) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (injury == null) {
			if (other.injury != null)
				return false;
		} else if (!injury.equals(other.injury))
			return false;
		if (minute != other.minute)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}



	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getInjury() {
		return injury;
	}

	public void setInjury(String injury) {
		this.injury = injury;
	}

}
