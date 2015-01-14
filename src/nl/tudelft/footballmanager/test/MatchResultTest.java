package nl.tudelft.footballmanager.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import nl.tudelft.footballmanager.model.MatchResult;

import org.junit.Test;

public class MatchResultTest {

	MatchResult testResult = new MatchResult();
	MatchResult testResult2 = new MatchResult();
	ArrayList<Integer> homeScoreTimes = new ArrayList<Integer>(20);
	ArrayList<Integer> awayScoreTimes = new ArrayList<Integer>(30);
	
	
	@Test
	public void testAddHomeScoreTime() {
		testResult.addHomeScoreTime(20);
		testResult2.addHomeScoreTime(20);
		assertEquals(testResult.getHomeScoreTimes(),testResult2.getHomeScoreTimes());
	}

	@Test
	public void testAddAwayScoreTime() {
		testResult.addAwayScoreTime(20);
		testResult2.addAwayScoreTime(20);
		assertEquals(testResult.getAwayScoreTimes(),testResult2.getAwayScoreTimes());
	}

	@Test
	public void testSetHomeScore() {
		testResult.setHomeScore(3);
		assertEquals(testResult.getHomeScore(),3);
	}

	@Test
	public void testGetAwayScore() {
		testResult.setAwayScore(4);
		assertEquals(testResult.getAwayScore(),4);
	}

	@Test
	public void testSetHomeScoreTimes() {
		testResult.setHomeScoreTimes(homeScoreTimes);
		testResult2.setHomeScoreTimes(homeScoreTimes);
		assertEquals(testResult2.getHomeScoreTimes(),testResult.getHomeScoreTimes());
	}

	@Test
	public void testSetAwayScoreTimes() {
		testResult.setAwayScoreTimes(awayScoreTimes);
		testResult2.setAwayScoreTimes(awayScoreTimes);
		assertEquals(testResult.getAwayScoreTimes(),testResult2.getAwayScoreTimes());
	}

}
