package logic;
import java.io.File;

import filehandling.XMLConfig;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class GameState {
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
	}
	
	/**
	 * @param file XML file to read config from.
	 */
	public GameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);
		
		GameState gameState = xmlConfig.readFromFile();
		
		setCoachName(gameState.getCoachName());
		setRound(gameState.getRound());
		setLeague(gameState.getLeague());
		setTeam(gameState.getTeam());
	}
	
	/**
	 * @param file XML file to read config from.
	 */
	public void loadGameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);
		
		GameState gameState = xmlConfig.readFromFile();
		
		setCoachName(gameState.getCoachName());
		setRound(gameState.getRound());
		setLeague(gameState.getLeague());
		setTeam(gameState.getTeam());
	}
	
	/**
	 * @param file XML file to write config to.
	 */
	public void saveGameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);
		
		xmlConfig.writeToFile(this);		
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
	}
}
