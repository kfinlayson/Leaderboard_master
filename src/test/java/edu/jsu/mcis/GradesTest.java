package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.util.*;

public class GradesTest {
	
	private Grades grades;
	
	@Before
	public void setUp() {
		Database data = new Database();
		Course course = data.getCourse("99000");
		grades = course.getGrades();
		
	}
	
	@Test
	public void testAssignmentGetMaxScore(){
		grades.findMaxScore("Assignment 1");
		assertEquals("65",grades.getMaxScore());
	}
	
	@Test
	public void testAssignmentGetMaxScoreID(){
		grades.findMaxScore("Assignment 1");
		assertEquals("111318",grades.getMaxScoreID());
	}
	
	@Test
	public void testGetStudentAssignmentGrade(){
		int grade = grades.getStudentAssignmentGrade("111318", "Assignment 1");
		assertEquals(65, grade);
	}
	
	@Test
	public void testGetStudentGrades() {
		Map<String,Integer> expected = new TreeMap<String,Integer>();
		expected.put("Total", 925);
		expected.put("Assignment 1", 65);
		expected.put("Assignment 2", 58);
		expected.put("Assignment 3", 52);
		expected.put("Assignment 4", 60);
		expected.put("Assignment 5", 66);
		expected.put("Assignment 6", 66);
		expected.put("Assignment 7", 59);
		expected.put("Assignment 8", 57);
		expected.put("Assignment 9", 58);
		expected.put("Exam 1", 384);
		Map<String,Integer> actual = grades.getStudentGrades("111318");
		assertEquals(expected, actual);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetAssignmentList() {
		String[] expected = {"Total", "Assignment 1", "Assignment 2", "Assignment 3", "Assignment 4", "Assignment 5", "Assignment 6", "Assignment 7", "Assignment 8", "Assignment 9", "Exam 1" };
		assertEquals(expected, grades.getAssignmentList());
	}
}