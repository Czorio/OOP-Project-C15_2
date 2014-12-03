package logic;

/**
 * Class to calculate a players statistics
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public class PlayerLogic {
	
	private int offScore, defScore, stamina, posModifier, staminaModifier, playedGames;
	private String position, placedPosition;
	
	public PlayerLogic(int offScore, int defScore, int stamina, String position, String placedPosition){
		this.offScore = offScore;
		this.defScore = defScore;
		this.stamina = stamina;
		this.position = position;
		this.placedPosition = placedPosition;
	}
	
	public int calculatePlayerOffScore(int playerID){
		int output;
		if(position == placedPosition){
			posModifier = 1;
		}
		
		output = offScore * posModifier * staminaModifier;
		
		return output;
	}
	
	public int calculatePlayerDefScore(int playerID){
		int output;
		if(position == placedPosition){
			posModifier = 1;
		}
		
		output = defScore * posModifier * staminaModifier;
		
		return output;
	}
	
	public int calculatePlayerStamina(){
		int staminaDiscount = playedGames * 2;
		
		stamina -= staminaDiscount;
		return stamina;
	}
}
