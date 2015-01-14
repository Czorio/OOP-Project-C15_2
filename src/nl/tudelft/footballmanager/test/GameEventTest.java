package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.util.Date;

import nl.tudelft.footballmanager.model.Player;
import nl.tudelft.footballmanager.model.logic.GameEvent;

import org.junit.Test;

public class GameEventTest {

	Player testPlayer = new Player(1,"Davy","Klaasen","Ajax","Eredivisie","Dutch", new Date(1/1/1990),"CB",40,50,60,60,80,70);
	Player testPlayer2 = new Player(5,"Jasper","Cillesen","Ajax","Eredivisie","Dutch", new Date(5/5/1950),"GK",0,0,0,0,70,70);
	GameEvent testEvent = new GameEvent(10,testPlayer,"Red","none");
	GameEvent testEvent1 = new GameEvent(10,testPlayer,"Red","none");
	GameEvent noEvent = new GameEvent(-1,null,null,null);
	GameEvent testEvent2 = new GameEvent(20,testPlayer,"none","none");
	
	@Test
	public void testGameEvent() {
		assertEquals(testEvent.getCard(),"Red");
		assertEquals(testEvent.getInjury(),"none");
		assertEquals(testEvent.getMinute(),10);
		assertEquals(testEvent.getPlayer(),testPlayer);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(testEvent,testEvent1);
		testEvent1.setPlayer(testPlayer2);
		assertNotEquals(testEvent,testEvent1);
		testEvent1.setMinute(-1);
		testEvent1.setPlayer(null);
		testEvent1.setCard(null);
		testEvent1.setInjury(null);
		testEvent.setInjury("Knee");
		assertEquals(testEvent1,noEvent);
		assertNotEquals(testEvent1,testEvent);
		assertFalse(testEvent.equals(testEvent1));
		assertNotEquals(testEvent,testEvent2);
	}

	@Test
	public void testSetMinute() {
		testEvent.setMinute(90);
		assertEquals(testEvent.getMinute(),90);
	}

	@Test
	public void testSetPlayer() {
		testEvent.setPlayer(testPlayer2);
		assertEquals(testEvent.getPlayer(),testPlayer2);
	}

	@Test
	public void testSetCard() {
		testEvent.setCard("Yellow");
		assertEquals(testEvent.getCard(),"Yellow");
	}

	@Test
	public void testSetInjury() {
		testEvent.setInjury("Knee");
		assertEquals(testEvent.getInjury(),"Knee");
	}

}
