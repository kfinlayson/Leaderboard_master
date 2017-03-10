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
		Database data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
		Course course = data.getCourse("99001");
		Grades grades = course.getGrades();
		leaderboard = new Leaderboard(grades,"Total");
	}
	
	@Test
	public void testGetSortedGrades() {
		Map<String,Integer> sorted = leaderboard.getSortedGrades("Total");
		Map<String,Integer> expected = new TreeMap<String,Integer>();
		expected.put("111143", 886);
		expected.put("111291", 823);
		expected.put("111254", 820);
		expected.put("111236", 669);
		expected.put("111157", 593);
		expected.put("111208", 557);
		expected.put("111293", 548);
		expected.put("111326", 499);
		expected.put("111148", 494);
		assertEquals(expected, sorted);
	}
}