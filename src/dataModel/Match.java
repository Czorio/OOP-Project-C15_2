package dataModel;

import java.io.File;
import java.util.Observable;
import java.util.Scanner;

import logic.GameLogic;
import xml.XMLPlayer;

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
	
	//TESTMETHOD
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Home team: ");
		String home = sc.nextLine();
		System.out.println("Away team: ");
		String away = sc.nextLine();
		
		GameState testgs = new GameState("Steven", 1, "Eredivisie", "");
		match(testgs, home, away);
		
		sc.close();
	}

	public static void match(GameState gs, String homeName, String awayName) {		
		File in = new File("GameData/Leagues/" + gs.getLeague() + ".xml");
		XMLPlayer xmlplayer = new XMLPlayer(in);
		League league = xmlplayer.readFromFile(gs.getLeague());

		Team home = league.getTeam(homeName);
		Team away = league.getTeam(awayName);
		
		new GameLogic(home, away, gs);
		GameLogic.game(home, away);
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
