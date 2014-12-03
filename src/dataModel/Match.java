package dataModel;

import java.util.Observable;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */

public class Match extends Observable {
	private Team home;
	private Team away;
	
	/**
	 * @param home
	 * @param away
	 */
	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
	}

	/**
	 * @return the home
	 */
	public Team getHome() {
		return home;
	}

	/**
	 * @param home the home to set
	 */
	public void setHome(Team home) {
		this.home = home;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return the away
	 */
	public Team getAway() {
		return away;
	}

	/**
	 * @param away the away to set
	 */
	public void setAway(Team away) {
		this.away = away;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	
}
