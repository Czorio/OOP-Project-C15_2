import java.util.Date;

/**
 * Player class.
 * 
 * Represents a football player.
 * 
 * @author Toine Hartman <tjbhartman@gmail.com>
 */
public class Player {
	// Properties of a player
	private int id;
	private String firstName;
	private String lastName;
	private String club;
	private String nationality;
	private Date dateOfBirth;
	private String position;
	private int pace;
	private int shooting;
	private int passing;
	private int offensive;
	private int defensive;
	private int stamina;
	//private int price; // Will be based on his stats.
	
	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param club
	 * @param nationality
	 * @param dateOfBirth
	 * @param position
	 * @param pace
	 * @param shooting
	 * @param passing
	 * @param offensive
	 * @param defensive
	 * @param stamina
	 */
	public Player(Integer id, String firstname, String lastname, String club, String nationality, Date dateOfBirth, String position, int pace,
			int shooting, int passing, int offensive, int defensive, int stamina) {
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.club = club;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
		this.position = position;
		this.pace = pace;
		this.shooting = shooting;
		this.passing = passing;
		this.offensive = offensive;
		this.defensive = defensive;
		this.stamina = stamina;
	}
	
	/**
	 * Create en empty Player object.
	 * @param id
	 */
	public Player(Integer id) {
		this.id = id;
		this.firstName = null;
		this.lastName = null;		
		this.club = null;
		this.nationality = null;
		this.dateOfBirth = null;
		this.position = null;
		this.pace = 0;
		this.shooting = 0;
		this.passing = 0;
		this.offensive = 0;
		this.defensive = 0;
		this.stamina = 0;
	}
	
	/**
	 * Checks if this is equal to other.
	 */
	public boolean equals(Object other) {
		if (other instanceof Player) {
			Player that = (Player)other;
			
			return this.id == that.id &&
					this.firstName.equals(that.firstName) &&
					this.lastName.equals(that.lastName) &&
					this.club.equals(that.club) &&
					this.nationality.equals(that.nationality) &&
					this.dateOfBirth.equals(that.dateOfBirth) &&
					this.position.equals(that.position) &&
					this.pace == that.pace &&
					this.shooting == that.shooting &&
					this.passing == that.passing &&
					this.offensive == that.offensive &&
					this.defensive == that.defensive &&
					this.stamina == that.stamina;
		}
		
		return false;
	}

	
	/**
	 * @return This Player object as a String.
	 */
	public String toString() {
		return "[" + this.id + " " + this.firstName + " " + this.lastName + ": "
				+ this.club + " "
				//+ this.nationality + " "
				+ this.dateOfBirth + " "
				+ this.position + " "
				+ this.pace + " "
				+ this.shooting + " "
				+ this.passing + " "
				+ this.offensive + " "
				+ this.defensive + " "
				+ this.stamina + "]";
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstName;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastName;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	/**
	 * @return the club
	 */
	public String getClub() {
		return club;
	}

	/**
	 * @param club the club to set
	 */
	public void setClub(String club) {
		this.club = club;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the pace
	 */
	public int getPace() {
		return pace;
	}

	/**
	 * @param pace the pace to set
	 */
	public void setPace(int pace) {
		this.pace = pace;
	}

	/**
	 * @return the shooting
	 */
	public int getShooting() {
		return shooting;
	}

	/**
	 * @param shooting the shooting to set
	 */
	public void setShooting(int shooting) {
		this.shooting = shooting;
	}

	/**
	 * @return the passing
	 */
	public int getPassing() {
		return passing;
	}

	/**
	 * @param passing the passing to set
	 */
	public void setPassing(int passing) {
		this.passing = passing;
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

	
}
