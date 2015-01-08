/**
 * 
 */
package nl.tudelft.footballmanager.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class MatchDay extends Observable {
	private ArrayList<Match> matches;
	private int round;

	/**
	 * @param matches
	 */
	public MatchDay(int round, ArrayList<Match> matches) {
		this.matches = matches;
		this.round = round;
	}
	
	public MatchDay(int round) {
		this(round, new ArrayList<Match>());
	}
	
	public ArrayList<Team> getTodaysTeams() {
		ArrayList<Team> t = new ArrayList<Team>();
		for (Match m : matches) {
			t.add(m.getAway());
			t.add(m.getHome());
		}
		
		return t;
	}
	
	public boolean teamCanPlayToday(Team t) {
		if (getTodaysTeams().contains(t)) return false;
		return true;
	}
	
	public boolean matchCanBePlayedToday(Match m) {
		if (teamCanPlayToday(m.getHome()) && teamCanPlayToday(m.getAway())) return true;
		return false;
	}

	/**
	 * @return the matches
	 */
	public ArrayList<Match> getMatches() {
		return matches;
	}

	/**
	 * @param matches the matches to set
	 */
	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
		
		setChanged();
		notifyObservers(this);
	}
	
	public void addMatch(Match m) {
		this.matches.add(m);
		
		setChanged();
		notifyObservers(this);
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
		
		setChanged();
		notifyObservers(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MatchDay [matches=" + matches + ", round=" + round + "]";
	}

	/**
	 * @return
	 */
	public int getMatchCount() {
		return this.matches.size();
	}
}
