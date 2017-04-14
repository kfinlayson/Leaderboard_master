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
		Database data = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
		Course course = data.getCourse("99001");
		Grades grades = course.getGrades();
		leaderboard = new Leaderboard(grades, "Total");
	}
	
	@Test
	public void testGetSortedGrades() {
		Map<Integer,String> sorted = leaderboard.getSortedGrades("Total");
		Map<Integer,String> expected = new TreeMap<Integer,String>();
		expected.put(886, "111143");
		expected.put(823, "111291");
		expected.put(820, "111254");
		expected.put(669, "111236");
		expected.put(593, "111157");
		expected.put(557, "111208");
		expected.put(548, "111293");
		expected.put(499, "111326");
		expected.put(494, "111148");
		assertEquals(expected, sorted);
	}
}