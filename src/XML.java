import java.io.File;

/**
 * @author Boris Schrijver <boris@radialcontext.nl>
 */
public class XML {
	private File file;
	
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
