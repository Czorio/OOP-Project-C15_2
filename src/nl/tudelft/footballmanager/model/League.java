package nl.tudelft.footballmanager.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
//import java.util.Set;



import nl.tudelft.footballmanager.model.logic.PlayerLogic;
import nl.tudelft.footballmanager.model.xml.XMLPlayer;
// Toevoegen aan build path **Notitie (MdB): Staat in de lib folder als commons-io-2.4.jar**



/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 *
 */
public class League extends Observable {
	private String name;
	private ArrayList<Team> teams; // Wellicht handig om een Set te maken, elementen zijn tenslotte distinct

	private static final String LEAGUES_FOLDER_NAME = "GameData/Leagues/";
	private static final File LEAGUES_FILE = new File("GameData/Leagues.xml");
	

	/**
	 * Construct a name with given name and teamlist.
	 * @param name
	 * @param teams
	 */
	public League(String name, ArrayList<Team> teams) {
		this.name = name;
		this.teams = teams;
	}

	/**
	 * Construct an empty League
	 * @param League Name of the League
	 */
	public League(String name) {
		this(name, new ArrayList<Team>());
	}

	public static League readOne(String leagueName) throws FileNotFoundException {
		File leagueFile = getLeagueFile(leagueName);
		XMLPlayer xmlplayer = new XMLPlayer(leagueFile);
		return xmlplayer.readFromFile(leagueName);
	}
	
	public static ArrayList<League> readMany(String[] leagueNames) {
		ArrayList<String> selection = new ArrayList<String>();
		for (String s : leagueNames) {
			selection.add(s);
		}
		
		return new XMLPlayer(LEAGUES_FILE).readFromFile(selection);
	}
	
	public static List<League> readAll() {
		List<League> leagues = new XMLPlayer(LEAGUES_FILE).readFromFile();
		Collections.sort(leagues, NAME_COMPARATOR);
		return leagues;
	}
	
	public static final Comparator<League> NAME_COMPARATOR = new Comparator<League>() {
		public int compare(League l1, League l2) {
			return l1.getName().compareToIgnoreCase(l2.getName());
		}
	};
	
	private static File getLeagueFile(String leagueName) {
		return new File(LEAGUES_FOLDER_NAME + leagueName + ".xml");
	}

	/**
	 * Add team to this name, only if their doesn't exist a team with the same name.
	 * @param team
	 */
	public void addTeam(Team team) {
		boolean bExists = false;
		for(Team t : this.teams) {
			if(team.getName().equals(t.getName())) {
				bExists = true;  
				break;
			}
		}

		if(!bExists) {
			teams.add(team);
		}

		// Is dit niet veel handiger?
		//		if (this.getTeams().contains(team) == false) {
		//			this.teams.add(team);
		//			
		//			this.setChanged();
		//			this.notifyObservers(this);
		//		}
	}

	/**
	 * Remove team, if the team exists, based on Team name.
	 * @param team
	 */
	public void removeTeam(Team team) {
		boolean bExists = false;
		int index = 0;
		for(int i = 0; i < teams.size(); i++) {
			if(teams.get(i).getName().equals(team.getName())) {
				bExists = true;
				index = i;
			}
		}

		if(bExists) {
			teams.remove(index);
		}

		// Dit lijkt me ook beter
		//		int index;
		//		if ((index = this.teams.indexOf(team)) != -1) {
		//			this.teams.remove(index);
		//			
		//			this.setChanged();
		//			this.notifyObservers();
		//		}
	}

	/**
	 * equals method
	 */
	public boolean equals(Object other) {
		if(other instanceof League) {
			League that = (League)other;

			return this.name.equals(that.name) &&
					this.teams.equals(that.teams);
		}

		return false;
	}

	/**
	 * @return the name name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name name to set
	 */
	public void setName(String name) {
		this.name = name;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Get Team based on name
	 * @param name
	 * @return
	 */
	public Team getTeam(String name) {
		for(int i = 0; i < teams.size(); i++) {
			if(teams.get(i).getName().equals(name)) {
				return teams.get(i);
			}
		}

		return null;
	}

	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return
	 */
	public ArrayList<SchemableMatch> getPossibleMatches(League league) {
		ArrayList<SchemableMatch> matches = new ArrayList<SchemableMatch>();
		
		for (Team a : league.getTeams()) {
			for (Team b : league.getTeams()) {
				if (a.equals(b)) continue;

				matches.add(new SchemableMatch(a, b, true));
			}
		}
		
		return matches;
	}
	
	public static List<League> checkNumbersAndAddPrice(List<League> leagues, int minTeams, int minPlayers) {
		List<League> checkedLeagues = new ArrayList<League>();
		int[] numOfPlayersOnIndex = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		for (League l : leagues) {
			League lCopy = new League(l.getName());
			for (Team t : l.getTeams()) {
				for (Player p : t.getPlayers()) {
					PlayerLogic.calculatePrice(p);
				}
				if (t.getPlayers().size() < 11) {
//					System.out.println(String.format("Team %s has %d players and is therefore ignored", t.getName(), t.getPlayers().size()));
					numOfPlayersOnIndex[t.getPlayers().size()] += 1;
					continue;
				}
				lCopy.addTeam(t);
			}
			if (lCopy.getTeams().size() < 2) {
//				System.out.println(String.format("League %s has %d teams and is therefore ignored", lCopy.getName(), lCopy.getTeams().size()));
				continue;
			}
			checkedLeagues.add(lCopy);
		}
		
		for (int i = 0; i < numOfPlayersOnIndex.length; i++) {
			System.out.println(String.format("%d teams have %d players", numOfPlayersOnIndex[i], i));
		}
		
		System.out.println("\nStats before/after check:");
		System.out.println(String.format("Number of leagues: %d / %d", leagues.size(), checkedLeagues.size()));
		System.out.println(String.format("Number of teams: %d / %d", League.getTeamCount(leagues), League.getTeamCount(checkedLeagues)));
		System.out.println(String.format("Number of players: %d / %d", League.getPlayerCount(leagues), League.getPlayerCount(checkedLeagues)));
		
		return checkedLeagues;
	}
	
	public int getTeamCount() {
		return this.getTeams().size();
	}
	
	public int getPlayerCount() {
		int playerCount = 0;
		for (Team t : this.getTeams()) {
			playerCount += t.getPlayerCount();
		}
		
		return playerCount;
	}
	
	public static int getPlayerCount(List<League> leagues) {
		int playerCount = 0;
		for (League l : leagues) {
			playerCount += l.getPlayerCount();
		}
		
		return playerCount;
	}
	
	public static int getTeamCount(List<League> leagues) {
		int teamCount = 0;
		for (League l : leagues) {
			teamCount += l.getTeamCount();
		}
		
		return teamCount;
	}
}
