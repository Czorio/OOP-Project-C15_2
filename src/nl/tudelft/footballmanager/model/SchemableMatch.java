/**
 * 
 */
package nl.tudelft.footballmanager.model;

/**
 * @author Toine Hartman <tjbhartman@gmail.com>
 * 
 * A match, which specifies whether it is to be put in the match scheme or not
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
	 * @return if the match is schemable
	 */
	public boolean isSchemable() {
		return schemable;
	}

	/**
	 * @param schemable Whether the match is schemable or not
	 */
	public void setSchemable(boolean schemable) {
		this.schemable = schemable;
	}
	
	
}

