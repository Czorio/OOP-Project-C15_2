import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

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
	
	public boolean WriteToFile() {
		
		return true;
	}
	
	/**
	 * @return GameState read from the document.
	 */
	public GameState ReadFromFile(){
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		try {
			// Setup the SAX XML Parser and assign the handler
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLConfigHandler handler = new XMLConfigHandler();
			
			// Parse the document
			saxParser.parse(file, handler);
			
			// Return the GameState object extracted from the XML
			return handler.getGameState();
			
			
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		// Return empty GameState if non found.
		return new GameState(null, 0, null, null);
	}
}
