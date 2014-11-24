package dataModel;
import java.util.ArrayList;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 *
 */
public class League {
	private String league;
	private ArrayList<Team> teams;
	

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
	
	/**
	 * Add team to this league, only if their doesn't exist a team with the same name.
	 * @param team
	 */
	public void addTeam(Team team) {
		boolean bExists = false;
		for(int i = 0; i < teams.size(); i++) {
			if(teams.get(i).getTeam().equals(teams.get(i).getTeam())) bExists = true;  
		}
		
		if(!bExists) {
			teams.add(team);
		}
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
	}	
}
