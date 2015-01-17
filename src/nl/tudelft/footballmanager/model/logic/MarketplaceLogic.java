package nl.tudelft.footballmanager.model.logic;

import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.Team;


/**
 * Logic for the marketplace. Includes methods for determining what players are offered a contract and for which price. Also has functions to change players from teams. 
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public final class MarketplaceLogic {
	private MarketplaceLogic(){};
	
	/**
	 * @param fromTeam	Team from which the player needs to be removed.
	 * @param toTeam	Team to which the players needs to be moved.
	 * @param player	The player
	 */
	public static final boolean transferPlayer(Team fromTeam, Team toTeam, Player player, int currentRound) {
		boolean ret = false;
		
		// Transfermarket not open.
		if(!isTransferWindow(currentRound))
			return false;
		
		// Budget not sufficient
		if(toTeam.getBudget() < player.getPrice())
			return false;
		
		// A team cannot have less then 7 players.
		if(fromTeam.getPlayers().size() <= 7)
			return false;
		
		// Change players and set new budgets.
		if(fromTeam.removePlayer(player)) {
			if(toTeam.addPlayer(player)) {
				fromTeam.setBudget(fromTeam.getBudget() + player.getPrice());				
				toTeam.setBudget(toTeam.getBudget() - player.getPrice());
				ret = true;
			}
			else {
				fromTeam.addPlayer(player);
				ret = false;
			}
		}		
		return ret;
	}
	

	/**
	 * @param currentRound	The round we currently play in.
	 * @param league		The league object.
	 * @param myTeam		The team you're coaching.
	 */
	public static final void RoundOffer(int currentRound, League league, Team myTeam) {
		if(isTransferWindow(currentRound)) {
			// Add random player transfer function between teams
		}		
	}
	
	public static final boolean isTransferWindow(int currentRound) {
		// Transfermarkt is open van 1 juli tot en met 31 augustus,
		// welke overeenkomt met de eerste 4 weken van de Eredivisie.
		// De 2de termijn is van 1 januari tot en met 31 januari,
		// welke overeenkomt met de 18de tot en met 20ste speelronde
		if(currentRound >= 1 && currentRound <= 4)
			return true;
		else if(currentRound >= 18 && currentRound <= 20)
			return true;
		else
			return false;
	}
}