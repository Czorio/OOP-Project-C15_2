/**
 * 
 */
package nl.tudelft.footballmanager.model;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 *
 */
public class SchemableMatch extends Match {
	private boolean schemable;

	/**
	 * @param schemable
	 */
	public SchemableMatch(Team home, Team away, boolean schemable) {
		super(home, away);
		this.setSchemable(schemable);
	}

	/**
	 * @return the schemable
	 */
	public boolean isSchemable() {
		return schemable;
	}

	/**
	 * @param schemable the schemable to set
	 */
	public void setSchemable(boolean schemable) {
		this.schemable = schemable;
	}
	
	
}

