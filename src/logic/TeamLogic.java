package logic;

/**
* Class to calculate the statistics of a team
* @author Steven Meijer <stevenmeijer9@gmail.com>
*
*/
public class TeamLogic {

	private int offScore, defScore, stamina;
	
	public TeamLogic(int offScore, int defScore, int stamina) {
		this.offScore = offScore;
		this.defScore = defScore;
		this.stamina = stamina;
		
	}
	
	public int generateOffStats(){
		//..
		
		return offScore;
	}
	
	public int generateDefStats(){
		//..
		
		return defScore;
	}

	public int generateStamina(){
		//..
		
		return stamina;
	}


}
