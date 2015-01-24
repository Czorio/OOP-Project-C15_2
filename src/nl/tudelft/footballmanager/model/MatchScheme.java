/**
 * 
 */
package nl.tudelft.footballmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class MatchScheme extends Observable {
	private ArrayList<MatchDay> matchdays;
	private static String inFile = "XML/GameState.xml";

	public MatchScheme(GameState state, int maxMatchesPerDay) {
		this(state.getLeague(), maxMatchesPerDay);
	}

	/**
	 * @param league
	 * @param maxMatchesPerDay
	 */
	public MatchScheme(League league, int maxMatchesPerDay) {
		matchdays = new ArrayList<MatchDay>();
		if (league != null)
			this.populate(league, maxMatchesPerDay);
	}

	/**
	 * @param league
	 */
	public MatchScheme() {
		matchdays = new ArrayList<MatchDay>();
	}

	/**
	 * Populates a MatchScheme with Matches, with constraints:
	 * - a single team can play only one Match on a MatchDay
	 * - if a number higher than 0 is passed to maxMatchesPerDay, this is the maximum number of Matches a day can have.
	 * - the actual number of Matches per MatchDay is random
	 * @param league The league for which the GameScheme needs to be created.
	 * @param maxMatchesPerDay The maximum number of Matches a day.
	 */
	public void populate(League league, int maxMatchesPerDay) {
		ArrayList<SchemableMatch> possibleMatches = new ArrayList<SchemableMatch>();
		Random random = new Random(System.nanoTime());
		
		possibleMatches = league.getPossibleMatches(league);
		Collections.shuffle(possibleMatches, random);
		
		int round = 1;
		while (possibleMatches
				.stream()
				.filter(match -> match.isSchemable() == true)
				.count() > 0) {
			MatchDay matchDay = new MatchDay(round);
			
			int maxToday = (maxMatchesPerDay == 0 ? 0 : random.nextInt(maxMatchesPerDay - 1) + 2);
			for (SchemableMatch m : possibleMatches) {
				if ((matchDay.getMatchCount() < maxToday || maxToday <= 0) && matchDay.matchCanBePlayedToday(m) && m.isSchemable()) {
					m.setRound(round);
					matchDay.addMatch((Match)m);
					m.setSchemable(false);
				}
			}

			this.matchdays.add(matchDay);
			round++;
		}
	}

	/**
	 * @return the matchdays
	 */
	public ArrayList<MatchDay> getMatchdays() {
		return matchdays;
	}

	/**
	 * @param matchdays the matchdays to set
	 */
	public void setMatchdays(ArrayList<MatchDay> matchdays) {
		this.matchdays = matchdays;

		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Add a single match day to the list.
	 * @param matchDay
	 * @return
	 */
	public boolean addMatchDay(MatchDay matchDay) {
		for(MatchDay m : matchdays) {
			if(m.getRound() == matchDay.getRound())
				return false;				
		}
		
		this.matchdays.add(matchDay);
		return true;
	}
	
	/**
	 * Return a single matchday if it exists
	 * @param round
	 * @return
	 */
	public MatchDay getMatchDay(int round) {
		for(MatchDay m : matchdays) {
			if(m.getRound() == round)
				return m;				
		}
		return null;
	}
	
	/**
	 * Check if matchday exists.
	 * @param matchDay
	 * @return
	 */
	public boolean matchDayExists(int round) {
		for(MatchDay m : matchdays) {
			if(m.getRound() == round)
				return true;				
		}
		return false;
	}

	@Override
	public String toString() {
		return "MatchScheme [matchdays=" + matchdays + "]";
	}
	
	/**
	 * @return the inFile
	 */
	public static String getInFile() {
		return inFile;
	}

	/**
	 * @param inFile the inFile to set
	 */
	public static void setInFile(String inFile) {
		MatchScheme.inFile = inFile;
	}
}
