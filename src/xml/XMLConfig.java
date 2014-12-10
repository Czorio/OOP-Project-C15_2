package xml;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import dataModel.GameState;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class XMLConfig extends XML {

	/**
	 * @param file
	 */
	public XMLConfig(File file) {
		super(file);
	}
	
	/**
	 * @param gameState The GameState to write to XML.
	 * @return true or false based on success.
	 */
	public boolean writeToFile(GameState gameState) {
		Document dom;
	    Element el = null;	    
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    
	    try {
	    	DocumentBuilder db = dbf.newDocumentBuilder();
	    	dom = db.newDocument();
	    	
	    	// Create the root element
	        Element rootEle = dom.createElement("GameState");
	    	
	        // Create data elements and place them under root
	        el = dom.createElement("coachName");
	        el.appendChild(dom.createTextNode(gameState.getCoachName()));
	        rootEle.appendChild(el);

	        el = dom.createElement("round");
	        el.appendChild(dom.createTextNode(Integer.toString(gameState.getRound())));
	        rootEle.appendChild(el);

	        el = dom.createElement("league");
	        el.appendChild(dom.createTextNode(gameState.getLeague()));
	        rootEle.appendChild(el);

	        el = dom.createElement("team");
	        el.appendChild(dom.createTextNode(gameState.getTeam()));
	        rootEle.appendChild(el);

	        dom.appendChild(rootEle);
	        
	        try {
	        	Transformer tr = TransformerFactory.newInstance().newTransformer();
	        	tr.setOutputProperty(OutputKeys.INDENT, "yes");
	        	tr.setOutputProperty(OutputKeys.METHOD, "xml");
	        	tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	            
	            // Write to file
	            tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(file)));
	        } catch(TransformerException | IOException e) {
	        	e.printStackTrace();
	        }
	    	
	    } catch(ParserConfigurationException e) {
	    	e.printStackTrace();
	    }
		
		return true;
	}
	
	/**
	 * @return GameState read from the document.
	 */
	public GameState readFromFile(){
		
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
	
	/**
	 * equals method.
	 */
	public boolean equals(Object other) {
		if(other instanceof XMLConfig) {
			XMLConfig that = (XMLConfig)other;
			
			return this.file.equals(that.file);
		}
		
		return false;
	}
}
