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
		Database data = new Database("FileSource");
		Course course = data.getCourse("99000");
		grades = course.getGrades();
		
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test(expected=InvalidIDException.class)
	public void testExceptionThrownWithInvalidStudentID() {
		grades.getStudentAssignmentGrade("", "Total");
	}
	
	@Test(expected=InvalidIDException.class)
	public void testExceptionThrownWithInvalidAssignment() {
		grades.getStudentAssignmentGrade("111318", "");
	}
	
	@Test(expected=InvalidIDException.class)
	public void testExceptionThrownWithGetStudentGrades() {
		grades.getStudentGrades("");
	}
	
	@Test(expected=InvalidIDException.class)
	public void testExceptionThrownWithGetAssignmentGrades() {
		grades.getAssignmentGrades("");
	}
	
	@Test
	public void testAssignmentGetMaxScore(){
		grades.findMaxScore("Assignment 1");
		assertEquals("65.0",grades.getMaxScore());
	}
	
	@Test
	public void testAssignmentGetMaxScoreID(){
		grades.findMaxScore("Assignment 1");
		assertEquals("111318",grades.getMaxScoreID());
	}
	
	@Test
	public void testGetStudentAssignmentGrade(){
		Database data = new Database("http://inspired.jsu.edu:7272/gamegogy/");
		Course course = data.getCourse("99000");
		grades = course.getGrades();
		int grade = grades.getStudentAssignmentGrade("111318", "Assignment 1");
		assertEquals(65, grade);
	}
	
	@Test
	public void testGetStudentGrades() {
		Database data = new Database("http://inspired.jsu.edu:7272/gamegogy/");
		Course course = data.getCourse("99000");
		grades = course.getGrades();
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
	
	@Test
	public void testGetAssignmentGrades() {
		Map<String,Integer> expected = new TreeMap<String,Integer>();
		expected.put("111318", 65);
		expected.put("111383", 55);
		expected.put("111190", 40);
		expected.put("111406", 34);
		expected.put("111115", 41);
		expected.put("111211", 36);
		expected.put("111208", 41);
		expected.put("111310", 58);
		expected.put("111335", 36);
		expected.put("111141", 34);
		expected.put("111262", 46);
		Map<String,Integer> actual = grades.getAssignmentGrades("Assignment 1");
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAssignmentList() {
		String[] expected = { "Total", "Assignment 1", "Assignment 2", "Assignment 3", "Assignment 4", "Assignment 5", "Assignment 6", "Assignment 7", "Assignment 8", "Assignment 9", "Exam 1" };
		String[] actual = grades.getAssignmentList();
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
		Database data = new Database("FileSource");
		Course course = data.getCourse("99001");
		grades = course.getGrades();
		String[] expected1 = { "Total", "Assignment 1", "Assignment 2", "Assignment 3", "Exam 1", "Exam 2", "Exam 3" };
		String[] actual1 = grades.getAssignmentList();
		for(int i = 0; i < expected1.length; i++) {
			assertEquals(expected1[i], actual1[i]);
		}
	}
	
	
}