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
	private int price;
	
	public Player(String name, int off, int def, int sta, int price) {
		setName(name);
		setOffensive(off);
		setDefensive(def);
		setStamina(sta);
		setPrice(price);
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the offensive
	 */
	public int getOffensive() {
		return offensive;
	}

	/**
	 * @param offensive the offensive to set
	 */
	public void setOffensive(int offensive) {
		this.offensive = offensive;
	}

	/**
	 * @return the defensive
	 */
	public int getDefensive() {
		return defensive;
	}

	/**
	 * @param defensive the defensive to set
	 */
	public void setDefensive(int defensive) {
		this.defensive = defensive;
	}

	/**
	 * @return the stamina
	 */
	public int getStamina() {
		return stamina;
	}

	/**
	 * @param stamina the stamina to set
	 */
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
}
