package dataModel;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PlayerTest {

	Player p1 = new Player(1,"Davy","Klaasen","Ajax","Dutch", new Date(1/1/1990),"CB",40,50,60,60,80,70);
	Player p2 = new Player(2,"Celso","Ortiz","AZ","Dutch",new Date(1/1/1800),"RW",50,50,60,40,20,70);
	Player p3 = new Player(1,"Davy","Klaasen","Ajax","Dutch", new Date(1/1/1990),"CB",40,50,60,60,80,70);
	Player p4 = new Player(3,"Mitchell","Shet","Ado Den Haag","Dutch",new Date(2/2/2000),"ST",50,60,60,70,40,60);
	Player p5 = new Player(5,"Jasper","Cillesen","Ajax","Dutch", new Date(5/5/1950),"GK",0,0,0,0,70,70);
	
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

	@Test
	public void testPlayerInteger() {
		fail("Not yet implemented");
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
		Player CF = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"CF",40,40,40,40,40,40);
		Player RB = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"RB",40,40,40,40,40,40);
		Player LB = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"LB",40,40,40,40,40,40);
		Player CM = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"CM",40,40,40,40,40,40);
		Player CDM  = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"CDM",40,40,40,40,40,40);
		Player CAM  = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"CAM",40,40,40,40,40,40);
		Player LW = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"LW",40,40,40,40,40,40);
		Player RM = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"RM",40,40,40,40,40,40);
		Player LM = new Player(1,"Test","Test","Ajax","Dutch", new Date(1/1/2015),"LM",40,40,40,40,40,40);
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

}
