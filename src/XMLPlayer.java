import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


/**
 * @author Boris Schrijver
 *
 */
public class XMLPlayer extends XML {

	/**
	 * @param file
	 */
	XMLPlayer(File file) {
		super(file);
	}
	
	/**
	 * write league to file.
	 * @param league
	 */
	public void writeToFile(League league) {
		/**
		 * @TODO writeToFile();
		 */
	}
	
	/**
	 * read specific league from file.
	 * @param league
	 * @return
	 */
	public League readFromFile(String league) {
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		ArrayList<String> leagueArray = new ArrayList<String>();
		leagueArray.add(league);
		
		try { 
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLPlayerHandler handler = new XMLPlayerHandler(leagueArray);
			
			// Parse the document
			saxParser.parse(file, handler);
			
			// Return the League object extracted from the XML
			return handler.getLeagueObjects().get(0);
			
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
				
		// Return empty League if non found.
		return new League(null);
	}
	
	/**
	 * read multiple specific leagues from file.
	 * @param league
	 * @return
	 */
	public ArrayList<League> readFromFile(ArrayList<String> leagues) {
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		try { 
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLPlayerHandler handler = new XMLPlayerHandler(leagues);
			
			// Parse the document
			saxParser.parse(file, handler);
			
			// Return the League object extracted from the XML
			return handler.getLeagueObjects();
			
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
				
		// Return empty ArrayList if non found.
		return new ArrayList<League>();
	}
	
	
	
	/**
	 * equals method.
	 */
	public boolean equals(Object other) {
		if(other instanceof XMLPlayer) {
			XMLPlayer that = (XMLPlayer)other;
			
			return this.file.equals(that.file);
		}
		
		return false;		
	}

}
