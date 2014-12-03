package logic;

import dataModel.Player;

/**
 * Class to calculate a players statistics
 * @author Steven Meijer <stevenmeijer9@gmail.com>
 *
 */
public final class PlayerLogic {
	
	private PlayerLogic(){ 
		
	}
	
	public static int calculatePlayerOffScore(Player player){
		int output, posModifier = 0;
		if(player.getPosition().equals(player.getCurPosition())){
			posModifier = 1;
		}
		
		output = player.getOffensive() * posModifier;
		
		return output;
	}
	
	public int calculatePlayerDefScore(Player player){
		int output, posModifier = 0;
		if(player.getPosition() == player.getCurPosition()){
			posModifier = 1;
		}
		
		output = player.getDefensive() * posModifier;
		
		return output;
	}
	
//	public int calculatePlayerStamina(){
//		int staminaDiscount = playedGames * 2;
//		
//		stamina -= staminaDiscount;
//		return stamina;
//	}
}
