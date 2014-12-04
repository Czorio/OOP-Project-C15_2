package dataModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
//import java.util.Set;

import org.apache.commons.io.FilenameUtils; // Toevoegen aan build path **Notitie (MdB): Staat in de lib folder als commons-io-2.4.jar**



/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 *
 */
public class League extends Observable {
	private String league;
	private ArrayList<Team> teams; // Wellicht handig om een Set te maken, elementen zijn tenslotte distinct
	//	private Set<Team> teams;


	/**
	 * Construct a league with given name and teamlist.
	 * @param league
	 * @param teams
	 */
	public League(String league, ArrayList<Team> teams) {
		this.league = league;
		this.teams = teams;
	}

	/**
	 * Construct an empty League
	 * @param League Name of the League
	 */
	public League(String league) {
		this(league, new ArrayList<Team>());
	}

	public static League readFromFile(File inFile) {
		String leagueName = FilenameUtils.removeExtension(inFile.getName());
		XMLPlayer xmlplayer = new XMLPlayer(inFile);
		return xmlplayer.readFromFile(leagueName);
	}

	/**
	 * Add team to this league, only if their doesn't exist a team with the same name.
	 * @param team
	 */
	public void addTeam(Team team) {
		boolean bExists = false;
		for(Team t : this.teams) {
			if(team.getTeam().equals(t.getTeam())) {
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
			if(teams.get(i).getTeam().equals(team.getTeam())) {
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

			return this.league.equals(that.league) &&
					this.teams.equals(that.teams);
		}

		return false;
	}

	/**
	 * @return the league name
	 */
	public String getLeague() {
		return league;
	}

	/**
	 * @param league the league name to set
	 */
	public void setLeague(String league) {
		this.league = league;

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
			if(teams.get(i).getTeam().equals(name)) {
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
}
