/**
 * Player class.
 * 
 * Represents a football player.
 * 
 * @author Toine Hartman <tjbhartman@gmail.com>
 */
public class Player {
	// Properties of a player
	private String name;
	private int offensive;
	private int defensive;
	private int stamina;
	
	public Player(String name, int off, int def, int sta) {
		setName(name);
		setOffensive(off);
		setDefensive(def);
		setStamina(sta);
	}
	
	/**
	 * Checks if this is equal to other.
	 */
	public boolean equals(Object other) {
		if (other instanceof Player) {
			Player that = (Player)other;
			
			return this.name.equals(that.name) &&
					this.offensive == that.offensive &&
					this.defensive == that.defensive &&
					this.stamina == that.stamina;
		}
		
		return false;
	}
	
	// TODO add javadoc
	public String toString() {
		String s = this.name;
		s += String.format(": (%d, %d, %d)", getOffensive(), getDefensive(), getStamina());
		
		return s;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOffensive() {
		return this.offensive;
	}
	
	public void setOffensive(int off) {
		this.offensive = off;
	}
	
	public int getDefensive() {
		return this.defensive;
	}
	
	public void setDefensive(int def) {
		this.defensive = def;
	}
	
	public int getStamina() {
		return this.stamina;
	}
	
	public void setStamina(int sta) {
		this.stamina = sta;
	}
}
