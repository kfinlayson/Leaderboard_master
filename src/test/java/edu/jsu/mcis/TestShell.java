package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.util.*;

public class TestShell {
	private Student student;
	private Course course;
	private Database data;
	private String studentID;
	private String courseID;
	private String allStudentIDs;
	private String allCourseIDs;

    @Before
    public void setUp(){
		data = new Database();
		studentID = "111111";
		courseID = "99018";
		allStudentIDs = "111111\n" +
						"111112\n" +
						"111113\n" +
						"111114";
		allCourseIDs = "99000\n" +
						"99001\n" +
						"99002\n" +
						"99003";
    }
	
	
        
    @Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testGetStudentInfoByID() {
		try {
			String info = data.getStudent(studentID);
			String expectedOutput = "[111111] Jerrod Shields jshields@jsu.edu"; 
			assertEquals(expectedOutput, info);
		} 
		catch(InvalidIDException e) {}
	}
	
	@Test
	public void testGetCourseInfoByID() {
		try {
			String info = data.getCourse(courseID);
			String expectedOutput = "[99018] Spring 2014 (16 students)"; 
			assertEquals(expectedOutput, info);
		}
		catch(InvalidIDException e) {}
	}
	
	@Test(expected=InvalidIDException.class)
	public void testExceptionThrownWithInvalidID() {
		data.getStudent("");
	}
	/*
	@Test
	public void testGetAllStudentIDs() {
		String info = data.getStudentListKeys();
		assertEquals(allStudentIDs, info);
	}
	
	@Test
	public void testGetAllCourseIDs() {
		String info = data.getCourseListKeys();
		assertEquals(allCourseIDs, info);
	}*/
	
}







