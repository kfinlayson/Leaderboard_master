package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.util.*;

public class LeaderboardTest {
	
	private Leaderboard leaderboard;
	
	@Before
	public void setUp() {
		Database data = new Database();
		Course course = data.getCourse("99001");
		Grades grades = course.getGrades();
		leaderboard = new Leaderboard(grades);
	}
	
	@Test
	public void testGetSortedGrades() {
		int[] sorted = leaderboard.getSortedGrades("Total");
		int[] expected = { 886, 823, 820, 669, 593, 557, 548, 499, 494 };
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], sorted[i]);
		}
	}
}