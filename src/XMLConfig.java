import java.io.File;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class XMLConfig extends XML {

	/**
	 * @param file
	 */
	XMLConfig(File file) {
		super(file);
	}
	
	public GameState ReadFromFile() {
		GameState gameState = new GameState();
		
		gameState.setCoachName("Boris Schrijver");
		gameState.setLeague("Eredivisie");
		gameState.setRound(0);
		gameState.setTeam("Feyenoord");
		
		return gameState;
	}
	
	public boolean WriteToFile() {
		
		return true;
	}

}
