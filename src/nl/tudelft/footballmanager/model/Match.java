package nl.tudelft.footballmanager.model;

import java.io.File;
import java.util.Observable;
import java.util.Scanner;

import nl.tudelft.footballmanager.model.logic.GameLogic;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */

public class Match extends Observable {
	private Team home;
	private Team away;
	private int round;

	/**
	 * @param home
	 * @param away
	 */
	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
	}

	public static void match(GameState gs, String homeName, String awayName) {
		File in = new File("GameData/Leagues/" + gs.getLeague().getLeague() + ".xml");
		XMLPlayer xmlplayer = new XMLPlayer(in);
		League league = xmlplayer.readFromFile(gs.getLeague().getLeague());

		Team home = league.getTeam(homeName);
		Team away = league.getTeam(awayName);

		new GameLogic(home, away, gs);
		GameLogic.game();
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
