import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Boris Schrijver
 */
public class XMLConfigHandler extends DefaultHandler {

	private GameState gameState;
	
	private String currentElement;
	
	/**
	 * @return The GameState object.
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		switch(qName.toUpperCase()) {
		case "GAMESTATE":
			// Initialize GameState object if null
			if(gameState == null) gameState = new GameState(null, 0, null, null);
			break;
			
		case "COACHNAME":
			currentElement ="COACHNAME";
			break;
			
		case "ROUND":
			currentElement = "ROUND";
			break;
			
		case "LEAGUE":
			currentElement = "LEAGUE";
			break;
			
		case "TEAM":
			currentElement = "TEAM";
			break;
			
		default:
			System.out.println("XML: Unkown element in GameState XML file. --> XMLConfigHandler.startElement()");
			break;
		}		
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		
		if(currentElement != null) {
			switch(currentElement) {
			case "GAMESTATE":
				// Nothing to do!
				break;
				
			case "COACHNAME":
				gameState.setCoachName(new String(ch, start, length));
				break;
				
			case "ROUND":
				//System.out.println(new String(ch, start, length));
				gameState.setRound(new Integer(new String(ch, start, length)).intValue());
				break;
				
			case "LEAGUE":
				gameState.setLeague(new String(ch, start, length));
				break;
				
			case "TEAM":
				gameState.setTeam(new String(ch, start, length));
				break;
				
			default:
				System.out.println("XML: Unkown element in GameState XML file. --> XMLConfigHandler.characters()");
				break;
			}
		}
		
		// currentElement will be set by the next call of startElement().
		currentElement = null;		
	}
}
