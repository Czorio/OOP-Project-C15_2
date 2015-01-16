package nl.tudelft.footballmanager.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 * 
 * Match result and score
 */
public class MatchResult  extends Observable {
	private int homeScore;
	private int awayScore;	
	private ArrayList<Integer> homeScoreTimes;
	private ArrayList<Integer> awayScoreTimes;
	
	public MatchResult() {
		homeScore = 0;
		awayScore = 0;
		homeScoreTimes = new ArrayList<Integer>();
		awayScoreTimes = new ArrayList<Integer>();
	}
	
	/**
	 * @param time to add to the homeScoreTimes
	 */
	public void addHomeScoreTime(int time) {
		homeScoreTimes.add(time);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @param time to add to the awayScoreTimes
	 */
	public void addAwayScoreTime(int time) {
		awayScoreTimes.add(time);
		
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
	 * @return the homeScoreTimes
	 */
	public ArrayList<Integer> getHomeScoreTimes() {
		return homeScoreTimes;
	}

	/**
	 * @param homeScoreTimes the homeScoreTimes to set
	 */
	public void setHomeScoreTimes(ArrayList<Integer> homeScoreTimes) {
		this.homeScoreTimes = homeScoreTimes;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return the awayScoreTimes
	 */
	public ArrayList<Integer> getAwayScoreTimes() {
		return awayScoreTimes;
	}

	/**
	 * @param awayScoreTimes the awayScoreTimes to set
	 */
	public void setAwayScoreTimes(ArrayList<Integer> awayScoreTimes) {
		this.awayScoreTimes = awayScoreTimes;

		this.setChanged();
		this.notifyObservers();
	}
	
	public String getReadableScore() {
		return String.format("%d - %d", this.homeScore, this.awayScore);
	}
	
		
}
