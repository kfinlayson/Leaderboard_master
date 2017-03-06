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
		List<Integer> sorted = leaderboard.getSortedGrades("Total");
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(886);
		expected.add(823);
		expected.add(820);
		expected.add(669);
		expected.add(593);
		expected.add(557);
		expected.add(548);
		expected.add(499);
		expected.add(494);
		for(int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i), sorted.get(i));
		}
	}
}