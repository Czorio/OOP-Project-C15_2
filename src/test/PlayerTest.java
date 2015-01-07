package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import dataModel.Player;
import dataModel.Team;

public class PlayerTest {

	Player p1 = new Player(1,"Davy","Klaasen","Ajax","Dutch", null, new Date(1/1/1990),"CB",40,50,60,60,80,70);
	Player p2 = new Player(2,"Celso","Ortiz","AZ","Dutch",null, new Date(1/1/1800),"RW",50,50,60,40,20,70);
	Player p3 = new Player(1,"Davy","Klaasen","Ajax","Dutch", null, new Date(1/1/1990),"CB",40,50,60,60,80,70);
	Player p4 = new Player(3,"Mitchell","Shet","Ado Den Haag","Dutch",null, new Date(2/2/2000),"ST",50,60,60,70,40,60);
	Player p5 = new Player(5,"Jasper","Cillesen","Ajax","Dutch", null, new Date(5/5/1950),"GK",0,0,0,0,70,70);
	
	/**Constructor Test
	 * Tests the constructor using few get Methods and comparing them to actual value.
	 */
	@Test
	public void testPlayerIntegerStringStringStringStringDateStringIntIntIntIntIntInt() {
		assertEquals(p1.getClub(),"Ajax");
		assertEquals(p1.getFirstName(),"Davy");
		assertEquals(p2.getLastName(),"Ortiz");
		assertEquals(p1.getNationality(),"Dutch");
		assertEquals(p1.getDateOfBirth(), new Date(1/1/1990));
		assertEquals(p1.getPosition(),"Defender");
		assertEquals(p1.getPace(),40);
		assertEquals(p1.getShooting(),50);
		assertEquals(p1.getPassing(),60);
		assertEquals(p1.getOffensive(),60);
		assertEquals(p1.getDefensive(),80);
		assertEquals(p1.getStamina(),70);
		assertNotEquals(p1.getFirstName(),"Celso");
	}

	/**Player id test
	 * Create players with only an id. Use getId to test.
	 */
	@Test
	public void testPlayerInteger() {
		Player pl1 = new Player(1);
		Player pl2 = new Player(3);
		assertEquals(pl1.getId(),1);
		assertNotEquals(pl2.getId(),5);
	}

	/**Equals Test
	 * Compares 2 different player objects and checks if it is the or not(true or false).
	 */
	@Test
	public void testEqualsObject() {
		assertEquals(true,p1.equals(p3));
		assertEquals(false,p1.equals(p4));
		assertNotEquals(true,p1.equals(p2));
	}

	/**toString test
	 * Uses the toString method to test if 2 players are the same. 
	 */
	@Test
	public void testToString() {
		assertEquals(p1.toString(),p3.toString());
		assertEquals(p1.toString(),("[1 Davy Klaasen: Ajax Dutch " + new Date(1/1/1990) +  " CB " +null + " 40 50 60 60 80 70 0]"));
		assertNotEquals(p1.toString(),p4.toString());
		
	}
	
	/**getPosition test
	 * Create new Players with all the positions. And test them using getPosition method. 
	 */
	@Test
	public void testGetPosition(){
		Player CF = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"CF",40,40,40,40,40,40);
		Player RB = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"RB",40,40,40,40,40,40);
		Player LB = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"LB",40,40,40,40,40,40);
		Player CM = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"CM",40,40,40,40,40,40);
		Player CDM  = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"CDM",40,40,40,40,40,40);
		Player CAM  = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"CAM",40,40,40,40,40,40);
		Player LW = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"LW",40,40,40,40,40,40);
		Player RM = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"RM",40,40,40,40,40,40);
		Player LM = new Player(1,"Test","Test","Ajax","Dutch", null, new Date(1/1/2015),"LM",40,40,40,40,40,40);
		assertEquals(CF.getPosition(),"Attacker");
		assertEquals(p4.getPosition(),"Attacker");
		assertEquals(p2.getPosition(),"Midfielder");
		assertEquals(CM.getPosition(),"Midfielder");
		assertEquals(CDM.getPosition(),"Midfielder");
		assertEquals(CAM.getPosition(),"Midfielder");
		assertEquals(LW.getPosition(),"Midfielder");
		assertEquals(RM.getPosition(),"Midfielder");
		assertEquals(LM.getPosition(),"Midfielder");
		assertEquals(p5.getPosition(),"Goalkeeper");
		assertEquals(RB.getPosition(),"Defender");
		assertEquals(LB.getPosition(),"Defender");
		assertEquals(p1.getPosition(),"Defender");
		
	}

	/**setId Test
	 * set a new id for a player and check it with getID method.
	 */
	@Test
	public void testSetID(){
		p1.setId(3);
		p2.setId(4);
		assertEquals(p1.getId(),3);
		assertNotEquals(p2.getId(),10);
	}

	/**setFirstName Test
	 * Use setFirstName method to rename player. Use getFirstName method to test.
	 */
	@Test
	public void testSetFirstName(){
		p1.setFirstName("Jan");
		p2.setFirstName("Klaas");
		assertEquals(p1.getFirstName(),"Jan");
		assertNotEquals(p2.getFirstName(),"Piet");
	}

	/**setLastName Test
	 * Use setLastName to set a new last name for player. Use getLastName and compare.
	 */
	@Test
	public void testSetLastName(){
		p1.setLastName("Jantje");
		p2.setLastName("Klaasje");
		assertEquals(p1.getLastName(),"Jantje");
		assertNotEquals(p2.getLastName(),"Pietje");
	}

	/**setClub Tes
	 * Use setClub method to change playes club. Use getClub to test.
	 */
	@Test
	public void testSetClub(){
		p1.setClub("Feyenoord");
		p2.setClub("Cambuur");
		assertEquals(p1.getClub(),"Feyenoord");
		assertNotEquals(p2.getClub(),"AZ");
	}

	/**setNationality Test
	 * setNationality of players. Use getNationality to compare.
	 */
	@Test
	public void testSetNationality(){
		p1.setNationality("Surinamese");
		p2.setNationality("Surinamese");
		assertEquals(p1.getNationality(),"Surinamese");
		assertNotEquals(p2.getNationality(),"Dutch");
	}

	/**setDateOfBirth Test
	 * use setDateOfBirth to set new date of birth. 
	 */
	@Test
	public void testSetDateOfBirth(){
		p1.setDateOfBirth(new Date(1/1/2010));
		p2.setDateOfBirth(new Date(1/1/2020));
		assertEquals(p1.getDateOfBirth(),(new Date(1/1/2010)));
	}

	/**setPosition Test
	 * setPosition to set new position for player. 
	 */
	@Test
	public void testSetPosition(){
		p1.setPosition("CM");
		p2.setPosition("RM");
		assertEquals(p1.getPosition(),"Midfielder");
		assertNotEquals(p2.getPosition(),"Defender");
	}

	/**setPace
	 * Use Setpace to change player pace. 
	 */
	@Test
	public void testSetPace(){
		p1.setPace(80);
		p2.setPace(90);
		assertEquals(p1.getPace(),80);
		assertNotEquals(p2.getPace(),70);	
	}

	/**setShooting Test
	 * Setshooting of player. Compare with getShooting
	 */
	@Test
	public void testSetShooting(){
		p1.setShooting(80);
		p2.setShooting(60);
		assertEquals(p1.getShooting(),80);
		assertNotEquals(p2.getShooting(),100);
	}

	/**setPassing Test
	 * Setpassing to change passing of player. Compare with getPassing
	 */
	@Test
	public void testSetPassing(){
		p1.setPassing(80);
		p2.setPassing(70);
		assertEquals(p1.getPassing(),80);
		assertNotEquals(p2.getPassing(),0);
	}

	/**setOffensive Test
	 * use setoffensive to set offensive of player. Compare with setOffensive
	 */
	@Test
	public void testSetOffensive(){
		p1.setOffensive(80);
		p2.setOffensive(90);
		assertEquals(p1.getOffensive(),80);
		assertNotEquals(p2.getOffensive(),100);
	}
	
	/**setDefensive Test
	 * Use setDefensive to change defensive stat. Compare with getDefensive
	 */
	@Test
	public void testSetDefensive(){
		p1.setDefensive(80);
		p2.setDefensive(90);
		assertEquals(p1.getDefensive(),80);
		assertNotEquals(p2.getDefensive(),100);
	}
	
	/**setStamina Test
	 * setStamina for new player. Compare with getStamina
	 */
	@Test
	public void testSetStaming(){
		p1.setStamina(80);
		p2.setStamina(70);
		assertEquals(p1.getStamina(),80);
		assertNotEquals(p2.getStamina(),100);
		
	}

}



