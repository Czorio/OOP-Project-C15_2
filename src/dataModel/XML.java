package dataModel;
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
}
