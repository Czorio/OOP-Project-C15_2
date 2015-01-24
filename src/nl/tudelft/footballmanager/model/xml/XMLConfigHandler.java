package nl.tudelft.footballmanager.model.xml;
import java.util.Arrays;

import nl.tudelft.footballmanager.model.GameState;
import nl.tudelft.footballmanager.model.League;
import nl.tudelft.footballmanager.model.Match;
import nl.tudelft.footballmanager.model.MatchDay;
import nl.tudelft.footballmanager.model.MatchResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class XMLConfigHandler extends DefaultHandler {

	private GameState gameState;
	
	private String currentElement;
	
	private int currentRound;
	
	private Match currentMatch = null;	
	private League league = null;
	private MatchDay currentMatchDay = null;
	
	
	/**
	 * Constructor
	 * Read the league first, it is 
	 * @param league
	 */
	public XMLConfigHandler(League league) {
		this.league = league;
	}
	
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
			currentElement = "GAMESTATE";
			break;
			
		case "COACHNAME":
			currentElement ="COACHNAME";
			break;
			
		case "ROUND":
			currentElement = "ROUND";
			break;
			
		case "MYTEAM":
			currentElement = "MYTEAM";
			break;
			
		case "MATCHSCHEME":
			currentElement = "MATCHSCHEME";
			break;
		
		case "MATCHDAY":
			currentElement = "MATCHDAY";
			currentRound = Integer.parseInt(XML.getValueIgnoreCase(attributes, "ROUND"));
			break;
			
		case "MATCH":
			currentElement = "MATCH";
			break;
			
		case "HOMETEAM":
			currentElement = "HOMETEAM";
			break;
			
		case "AWAYTEAM":
			currentElement = "AWAYTEAM";
			break;
			
		case "HOMESCORE":
			currentElement = "HOMESCORE";
			break;
			
		case "AWAYSCORE":
			currentElement = "AWAYSCORE";
			break;
			
		default:
			String[] knownElements = new String[] {"PLAYERS", "LEAGUE", "TEAM", "PLAYER", "FIRSTNAME",
					"LASTNAME", "POSITION", "PACE", "SHOOTING", "PASSING", "OFFENSIVE", "DEFENSIVE",
					"STAMINA", "CLUB", "DATEOFBIRTH", "INJURED", "CURRENTPOSITION"};
			
			if(!Arrays.asList(knownElements).contains(qName.toUpperCase())) {
				System.out.println("XML: Unkown element in GameState XML file. --> startElement: " + qName);
			}
									
			break;
		}		
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		
		if(currentElement != null) {
			switch(currentElement) {
			case "GAMESTATE":
				// Add the players at first.
				gameState.setLeague(league);
				break;
				
			case "COACHNAME":
				gameState.setCoachName(new String(ch, start, length));
				break;
				
			case "ROUND":
				gameState.setGameRound(new Integer(new String(ch, start, length)).intValue());
				break;
				
			case "MYTEAM":
				try {
					gameState.setMyTeam(new String(ch, start, length));
				} catch (Exception e) {
					System.out.println("Unable to set MyTeam, it doesn't exist in the League.");
				}
				break;
				
			case "MATCHSCHEME":
				// Nothing to do!
				break;
				
			case "MATCHDAY":
				// Adds the matchday
				currentMatchDay = new MatchDay(currentRound);			
				break;
				
			case "MATCH":
				// Create new match
				currentMatch = new Match(null, null);
				break;
				
			case "HOMETEAM":
				currentMatch.setHome(league.getTeam(new String(ch, start, length)));
				break;
				
			case "AWAYTEAM":
				currentMatch.setAway(league.getTeam(new String(ch, start, length)));
				break;
				
			case "HOMESCORE":
				currentMatch.setPlayed(true);
				currentMatch.setMatchResult(new MatchResult());
				currentMatch.getMatchResult().setHomeScore(new Integer(new String(ch, start, length)).intValue());
				break;
				
			case "AWAYSCORE":
				currentMatch.getMatchResult().setAwayScore(new Integer(new String(ch, start, length)).intValue());
				break;
				
			default:
				System.out.println("XML: Unkown element in GameState XML file. --> characters:" + new String(ch, start, length));
				break;
			}
		}
		
		// currentElement will be set by the next call of startElement().
		currentElement = null;		
	}
	
	public void endElement(String uri, String localName, String qName) { 
		switch(qName.toUpperCase()) {
		case "GAMESTATE":
			break;
			
		case "MATCHSCHEME":
			break;
			
		case "MATCHDAY":
			gameState.getMatchScheme().addMatchDay(currentMatchDay);
			currentMatchDay = null;
			break;
			
		case "MATCH":
			currentMatchDay.addMatch(currentMatch);
			currentMatch = null;
			break;
		
		default:
			break;
		}
	}
}
