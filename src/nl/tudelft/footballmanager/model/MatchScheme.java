/**
 * 
 */
package nl.tudelft.footballmanager.model;

import java.io.File;
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

	public static void main(String[] args) {
		MatchScheme s = new MatchScheme(new GameState(new File("XML/GameState.xml")), 0);
		System.out.println(s);
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
			
			System.out.println(String.format("[%d] Actual num of games today: %d", matchDay.getRound(), matchDay.getMatchCount()));
//			System.out.println(String.format("%d: %s", round, matchDay));
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MatchScheme [matchdays=" + matchdays + "]";
	}
}
