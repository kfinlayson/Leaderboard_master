package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.util.*;

public class AssignmentTest {
	
	Assignment assignment;
	
	@Before
	public void setUp() {
		assignment = new Assignment("99000");
	}
	
	@Test
	public void testAssignmentGetMaxScore(){
		assignment.findMaxScore("Assignment 1");
		assertEquals("65",assignment.getMaxScore());
	}
	
	@Test
	public void testAssignmentGetMaxScoreID(){
		assignment.findMaxScore("Assignment 1");
		assertEquals("111318",assignment.getMaxScoreID());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetAssignmentList() {
		String[] expected = {"Total", "Assignment 1", "Assignment 2", "Assignment 3", "Assignment 4", "Assignment 5", "Assignment 6", "Assignment 7", "Assignment 8", "Assignment 9", "Exam 1" };
		assertEquals(expected, assignment.getAssignmentList());
	}
}