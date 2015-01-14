package nl.tudelft.footballmanager.model;

import java.util.Observable;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */

public class Match extends Observable {
	private Team home;
	private Team away;
	private MatchResult matchResult;
	private int round;

	/**
	 * @param home
	 * @param away
	 */
	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
	}
	
	/**
	 * @param matchResult the matchResult for this game.
	 */
	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
	
	/**
	 * @return the matchResult for this game.
	 */
	public MatchResult getMatchResult() {
		return matchResult;
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

	/**
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * @param round the round to set
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Match [home=" + home.getTeam() + ", away=" + away.getTeam() + ", round=" + round
				+ "]";
	}


}
