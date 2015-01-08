package nl.tudelft.footballmanager.model;
import java.io.File;
import java.util.Observable;

import nl.tudelft.footballmanager.model.xml.XMLConfig;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class GameState extends Observable {
	private String coachName;
	private int gameRound;

	private League league;
	private Team myTeam;

	/**
	 * @param coachName
	 * @param gameRound
	 * @param league
	 * @param myTeam
	 */
	public GameState(String coachName, int gameRound, League league, Team myTeam) {
		this.coachName = coachName;
		this.gameRound = gameRound;
		this.league = league;
		this.myTeam = myTeam;

		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * @param file XML file to read config from.
	 */
	public GameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);

		GameState gameState = xmlConfig.readFromFile();

		this.coachName = gameState.getCoachName();
		this.gameRound = gameState.getGameRound();
		this.league = gameState.getLeague();
		this.myTeam = gameState.getMyTeam();

		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * @param file XML file to read config from.
	 */
	public void loadGameState(File file) {
		XMLConfig xmlConfig = new XMLConfig(file);

		GameState gameState = xmlConfig.readFromFile();

		this.coachName = gameState.getCoachName();
		this.gameRound = gameState.getGameRound();
		this.league = gameState.getLeague();
		this.myTeam = gameState.getMyTeam();

		this.setChanged();
		this.notifyObservers(this);
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
				|| gameState.getMyTeam() == null;
	}

	public void nextRound() {
		this.setGameRound(getGameRound() + 1);
	}

	public String getMyTeamName() {
		return getMyTeam().getTeam();
	}

	public String getLeagueName() {
		return getLeague().getLeague();
	}

	@Override
	public String toString() {
		return "GameState [coachName=" + coachName + ", round=" + gameRound
				+ ", league=" + league + ", team=" + myTeam + "]";
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
	 * @return the gameRound
	 */
	public int getGameRound() {
		return gameRound;
	}

	/**
	 * @param gameRound the gameRound to set
	 */
	public void setGameRound(int gameRound) {
		this.gameRound = gameRound;

		setChanged();
		notifyObservers(this);
	}

	/**
	 * @return the league
	 */
	public League getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(League league) {
		this.league = league;

		setChanged();
		notifyObservers(this);
	}

	public void setLeague(String leagueName) {
		this.league = League.readFromFile(new File("GameData/Leagues/" + leagueName + ".xml"));

		setChanged();
		notifyObservers(this);
	}

	/**
	 * @return the myTeam
	 */
	public Team getMyTeam() {
		return myTeam;
	}

	/**
	 * @param myTeam the myTeam to set
	 */
	public void setMyTeam(Team myTeam) {
		this.myTeam = myTeam;

		setChanged();
		notifyObservers(this);
	}
	/**
	 * Sets team according to name.
	 * Cannot set team if league is not set
	 * @param myTeamName
	 * @throws Exception 
	 */
	public void setMyTeam(String myTeamName) throws Exception {
		if(this.league != null) {
			this.myTeam = this.league.getTeam(myTeamName);

			setChanged();
			notifyObservers(this);
		} else {
			throw new Exception("LEAGUE NOT SET");
		}
	}
}
