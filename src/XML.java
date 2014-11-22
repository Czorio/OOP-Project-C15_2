import java.io.File;


/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public abstract class XML {
	protected File file;		// File location and filename to read from or write to.
	
	XML(File file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Parse the XML file to get the DOM structure.
	 */
	protected void parseXMLFile() {

	}


//	/**
//	 * Takes an XML line, extracts the data enclosed between the tagname.
//	 * @param el
//	 * @param tagName
//	 * @return
//	 */
//	protected String getTextValue(Element el, String tagName) {
//		String textVal = null;
//		NodeList nl = el.getElementsByTagName(tagName);
//		if(nl != null && nl.getLength() > 0) {
//			Element tmp = (Element)nl.item(0);
//			textVal = tmp.getFirstChild().getNodeValue();
//		}
//
//		return textVal;
//	}
//	
//	/**
//	 * Calls getTextValue and returns a int value
//	 * @param el
//	 * @param tagName
//	 * @return
//	 */
//	protected int getIntValue(Element el, String tagName) {
//		return Integer.parseInt(getTextValue(el,tagName));
//	}
}
