package nl.tudelft.footballmanager.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 * 
 * Match result and score
 */
public class MatchResult  extends Observable {
	private int homeScore;
	private int awayScore;	
	private Map<Integer, Player> homeGoals;
	private Map<Integer, Player> awayGoals;
	
	public MatchResult() {
		homeScore = 0;
		awayScore = 0;
		homeGoals = new HashMap<Integer, Player>();
		awayGoals = new HashMap<Integer, Player>();	
	}
	
	/**
	 * @param time to add to the homeScoreTimes
	 */
	public void addHomeGoal(Integer time, Player player) {
		homeGoals.put(time, player);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @param time to add to the awayScoreTimes
	 */
	public void addAwayGoal(Integer time, Player player) {
		awayGoals.put(time, player);
		
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return the homeScore
	 */
	public int getHomeScore() {
		return homeScore;
	}

	/**
	 * @param homeScore the homeScore to set
	 */
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return the awayScore
	 */
	public int getAwayScore() {
		return awayScore;
	}

	/**
	 * @param awayScore the awayScore to set
	 */
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Gets the home goals.
	 * @return Returns the home goals.
	 */
	public Map<Integer, Player> getHomeGoals() {
		return this.homeGoals;
	}
	
	/**
	 * Sets the home goals.
	 * @param homeGoals The goals to set.
	 */
	public void setHomeGoals(Map<Integer, Player> homeGoals) {
		this.homeGoals = homeGoals;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Gets the away goals.
	 * @return Returns the away goals.
	 */
	public Map<Integer, Player> getAwayGoals() {
		return this.awayGoals;
	}
	
	/**
	 * Sets the away goals.
	 * @param awayGoals The goals to set.
	 */
	public void setAwayGoals(Map<Integer, Player> awayGoals) {
		this.awayGoals = awayGoals;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Gets the output of a match, in a readable way.
	 * @return Returns the output.
	 */
	public String getReadableScore() {
		return String.format("%d - %d", this.homeScore, this.awayScore);
	}
	
		
}
