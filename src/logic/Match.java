package logic;
/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */

public class Match {
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
	}
	
	
}
