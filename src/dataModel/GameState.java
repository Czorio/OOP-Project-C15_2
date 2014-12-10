package dataModel;
import java.io.File;
import java.util.Observable;

import xml.XMLConfig;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class GameState extends Observable {
	private String coachName;
	private int round;
	private String league;
	private String team;	

	/**
	 * @param coachName
	 * @param round
	 * @param league
	 * @param team
	 */
	public GameState(String coachName, int round, String league, String team) {
		this.coachName = coachName;
		this.round = round;
		this.league = league;
		this.team = team;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @param file XML file to read config from.
	 */
	public GameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);
		
		GameState gameState = xmlConfig.readFromFile();
		
//		setCoachName(gameState.getCoachName());
//		setRound(gameState.getRound());
//		setLeague(gameState.getLeague());
//		setTeam(gameState.getTeam());
		
		/** 
		 * Changed this above to below because above methods all call
		 * the setChanged() method, which triggers all Observers 5 times.
		 */
		
		this.coachName = gameState.getCoachName();
		this.round = gameState.getRound();
		this.league = gameState.getLeague();
		this.team = gameState.getTeam();

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @param file XML file to read config from.
	 */
	public void loadGameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);
		
		GameState gameState = xmlConfig.readFromFile();
		
//		setCoachName(gameState.getCoachName());
//		setRound(gameState.getRound());
//		setLeague(gameState.getLeague());
//		setTeam(gameState.getTeam());
		
		/** 
		 * Changed this above to below because above methods all call
		 * the setChanged() method, which triggers all Observers 5 times.
		 */
		
		this.coachName = gameState.getCoachName();
		this.round = gameState.getRound();
		this.league = gameState.getLeague();
		this.team = gameState.getTeam();
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @param file XML file to write config to.
	 */
	public void saveGameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);
		
		xmlConfig.writeToFile(this);		
	}
	
	public static boolean isUseless(GameState gameState) {
		return gameState == null
				|| gameState.getCoachName() == null
				|| gameState.getLeague() == null
				|| gameState.getTeam() == null;
	}
	
	public void nextRound() {
		this.setRound(getRound() + 1);
	}

	/**
	 * @return the coachName
	 */
	public String getCoachName() {
		return coachName;
	}

	/**
	 * @param coachName the coachName to set
	 */
	public void setCoachName(String coachName) {
		this.coachName = coachName;
		
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

	/**
	 * @return the league
	 */
	public String getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(String league) {
		this.league = league;
		
		setChanged();
		notifyObservers(this);
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
		
		setChanged();
		notifyObservers(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameState [coachName=" + coachName + ", round=" + round
				+ ", league=" + league + ", team=" + team + "]";
	}
}
