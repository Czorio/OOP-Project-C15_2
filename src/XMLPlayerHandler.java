import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class XMLPlayerHandler extends DefaultHandler {
	private ArrayList<String> leagueNames;
	private ArrayList<League> leagueObjects;
	
	private League currentLeague;
	private Team currentTeam;
	private Player currentPlayer;
	private String currentPlayerElement;
	private ArrayList<String> PlayerElements;
	
	private boolean bInsidePlayers;
	private boolean bInsideLeague;
	private boolean bInsideCorrectLeague;
	private boolean bInsideTeam;
	private boolean bInsidePlayer;
	
	/**
	 * @param league
	 */
	public XMLPlayerHandler(ArrayList<String> leagueNames) {
		super();
		this.leagueNames = leagueNames;
		this.leagueObjects = new ArrayList<League>();
		this.PlayerElements = fillPlayerElementsList();
		
		this.currentPlayerElement = null;
		
		this.bInsidePlayers = false;
		this.bInsideLeague = false;
		this.bInsideCorrectLeague = false;
		this.bInsidePlayer = false;
		this.bInsideTeam = false;
		
		
	}
	
	public ArrayList<League> getLeagueObjects() {
		return leagueObjects;
	}	

	/** (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		//System.out.println("[startElement][uri: " + uri + "][localName: " + localName + "][qName: " + qName + "][attr name: " + getValueIgnoreCase(attributes, "NAME") + "][attr id: " + getValueIgnoreCase(attributes, "ID") + "]");
		
		// Enter players node.
		if(!bInsidePlayers && qName.equalsIgnoreCase("PLAYERS")) {
			bInsidePlayers = true;
			return;
		}
		
		// Enter league
		if(bInsidePlayers && !bInsideLeague && qName.equalsIgnoreCase("LEAGUE")) {
			bInsideLeague = true;	// Inside a league
			
			boolean bLeagueExists = false;
			
			for(int i = 0; i < leagueObjects.size(); i++) {
				if(leagueObjects.get(i).getLeague().equals(getValueIgnoreCase(attributes, "NAME"))) {
					bLeagueExists = true;
					System.out.println("XMLPlayerHandler: XML File contains multiple leagues with shared names.");
				}
			}
			
			if(leagueNames.contains(getValueIgnoreCase(attributes, "NAME")) && !bLeagueExists) {
				bInsideCorrectLeague = true;	// Inside the correct league
				
				currentLeague = new League(getValueIgnoreCase(attributes, "NAME"));
			}
			return;
		}
		
		// Enter team
		if(bInsideCorrectLeague && !bInsideTeam && qName.equalsIgnoreCase("TEAM")) {
			bInsideTeam = true;
			
			currentTeam = new Team(getValueIgnoreCase(attributes, "NAME"));
			return;			
		}
		
		// Enter player
		if(bInsideTeam && !bInsidePlayer && qName.equalsIgnoreCase("PLAYER")) {
			bInsidePlayer = true;
			
			currentPlayer = new Player(Integer.parseInt(getValueIgnoreCase(attributes, "ID")));
			return;
		}
		
		// Set CurrentPlayerElement
		if(bInsidePlayer && this.PlayerElements.contains(qName.toUpperCase())) {
			currentPlayerElement = qName.toUpperCase();
		}
		else if (bInsidePlayer) {
			System.out.println("XMLPlayerHandler: Unkown element in Player, ID: " + currentPlayer.getId() + " --> " + "\"" + qName + "\"");
		}	
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		
		//System.out.println("[characters][start: " + start + "][length: " + length + "]");
		
		if(bInsidePlayer && currentPlayerElement != null) {
			switch(currentPlayerElement) {
			case "FIRSTNAME":
				currentPlayer.setFirstname(new String(ch, start, length));
				break;
				
			case "LASTNAME":
				currentPlayer.setLastname(new String(ch, start, length));
				break;
				
			case "POSITION":
				currentPlayer.setPosition(new String(ch, start, length));
				break;
				
			case "PACE":
				currentPlayer.setPace(Integer.parseInt(new String(ch, start, length)));
				break;
				
			case "SHOOTING":
				currentPlayer.setShooting(Integer.parseInt(new String(ch, start, length)));
				break;
				
			case "PASSING":
				currentPlayer.setPassing(Integer.parseInt(new String(ch, start, length)));
				break;
				
			case "OFFENSIVE":
				currentPlayer.setOffensive(Integer.parseInt(new String(ch, start, length)));
				break;
				
			case "DEFENSIVE":
				currentPlayer.setDefensive(Integer.parseInt(new String(ch, start, length)));
				break;
				
			case "STAMINA":
				currentPlayer.setStamina(Integer.parseInt(new String(ch, start, length)));
				break;
				
			case "DATEOFBIRTH":
				Date date;
				try {
					date = new SimpleDateFormat("dd-MM-yyyy").parse(new String(ch, start, length));
				} catch (ParseException e) {
					e.printStackTrace();
					date = new Date();
				}
				
				currentPlayer.setDateOfBirth(date);
				break;
				
			default:
				//System.out.println("XML: Unkown element in GameState XML file. --> XMLConfigHandler.characters()");
				break;
			}

			
			// currentElement will be set by the next call of startElement().
			currentPlayerElement = null;
		}
		

	}
	
	
	public void endElement(String uri, String localName, String qName) { 
		//System.out.println("[endElement][uri: " + uri + "][localName: " + localName + "][qName: " + qName + "]");
		
		// Exit from players nodelist.
		if(bInsidePlayers && qName.equalsIgnoreCase("PLAYERS")) {
			bInsidePlayers = false;
			bInsideLeague = false;
			bInsideCorrectLeague = false;
			bInsideTeam = false;
			bInsidePlayer = false;
			return;
		}
		
		// Exit from league nodelist.
		if(bInsideLeague && qName.equalsIgnoreCase("LEAGUE")) {
			
			if(bInsideCorrectLeague)
				leagueObjects.add(currentLeague);
			
			currentLeague = null;
			currentTeam = null;
			currentPlayer = null;
			currentPlayerElement = null;
			
						
			bInsideLeague = false;
			bInsideCorrectLeague = false;
			bInsideTeam = false;
			bInsidePlayer = false;
			return;
		}
		
		// Exit from team nodelist and add current Team to the Teams array.
		if(bInsideTeam && qName.equalsIgnoreCase("TEAM")) {
			currentLeague.addTeam(currentTeam);
			
			currentTeam = null;
			currentPlayer = null;
			currentPlayerElement = null;
			
			bInsideTeam = false;
			bInsidePlayer = false;
			return;
		}
		
		// Exit from player nodelist.
		if(bInsidePlayer && qName.equalsIgnoreCase("PLAYER")) {
			
			currentPlayer.setClub(currentTeam.getTeam());
			currentTeam.addPlayer(currentPlayer);
			currentPlayer = null;
			
			bInsidePlayer = false;
			return;
		}
	}
	
	
	/**
	 * Fills the ArrayList of player elements, in UPPER case.
	 * @return
	 */
	private ArrayList<String> fillPlayerElementsList() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("FIRSTNAME");
		list.add("LASTNAME");
		list.add("POSITION");
		list.add("PACE");
		list.add("SHOOTING");
		list.add("PASSING");
		list.add("OFFENSIVE");
		list.add("DEFENSIVE");
		list.add("STAMINA");
		list.add("CLUB");
		list.add("DATEOFBIRTH");
		
		return list;
	}
	
	/**
	 * @param attributes
	 * @param qName
	 * @return
	 */
	private String getValueIgnoreCase(Attributes attributes, String qName){
		String qn = null;
	    for(int i = 0; i < attributes.getLength(); i++){
	        qn = attributes.getQName(i);
	        if(qn.equalsIgnoreCase(qName)){
	            return attributes.getValue(i);
	        }
	    }
	    return null;
	}
}
