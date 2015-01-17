package nl.tudelft.footballmanager.model.xml;
import nl.tudelft.footballmanager.model.GameState;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
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
			if(gameState == null) gameState = new GameState(null, 0, (String)null, (String)null);
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
				gameState.setGameRound(new Integer(new String(ch, start, length)).intValue());
				break;
				
			case "LEAGUE":
				gameState.setLeague(new String(ch, start, length));
				break;
				
			case "TEAM":
				try {
					gameState.setMyTeam(new String(ch, start, length));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			default:
				System.out.println("XML: Unkown element in GameState XML file. --> XMLConfigHandler.characters()");
				break;
			}
		}
		
		// currentElement will be set by the next call of startElement().
		currentElement = null;		
	}
	
	public void endElement(String uri, String localName, String qName) { 
		// Not needed yet.
	}
}
