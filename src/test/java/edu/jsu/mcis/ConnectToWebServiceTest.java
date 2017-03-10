package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ConnectToWebServiceTest {
	private JSONWebSource website;
	
	@Before
	public void setUp() {
		website = new JSONWebSource("http://inspired.jsu.edu:7272/gamegogy/"); 
	}

	@Test
	public void testCreateConnectionToStudentList() {
		String output = JSONWebSource.createConnection("studentlist");
		assertEquals("111111", output.substring(2,8));
	}
	@Test
	public void testCreateConnectionToStudent() {
		String output = JSONWebSource.createConnection("student/111111");
		assertEquals("{\"id\":\"111111\",\"first\":\"Jerrod\",\"last\":\"Shields\",\"email\":\"jshields\"}", output);
	}
	@Test
	public void testCreateConnectionToCourseGrades() {
		String output = JSONWebSource.createConnection("course/99001");
		assertEquals("{\"id\":\"99001\",\"term\":\"Summer\",\"year\":\"2012\",\"size\":9,\"grades\":{\"colHeaders\":[\"Total\",\"Assignment 1\",\"Assignment 2\",\"Assignment 3\",\"Exam 1\",\"Exam 2\",\"Exam 3\"],\"rowHeaders\":[\"111291\",\"111208\",\"111148\",\"111236\",\"111326\",\"111293\",\"111143\",\"111157\",\"111254\"],\"data\":[[823.0,124.0,105.0,123.0,175.0,153.0,143.0],[557.0,77.0,68.0,79.0,134.0,112.0,87.0],[494.0,69.0,87.0,74.0,72.0,104.0,88.0],[669.0,98.0,89.0,88.0,111.0,135.0,148.0],[499.0,66.0,83.0,58.0,113.0,109.0,70.0],[548.0,64.0,72.0,83.0,122.0,81.0,126.0],[886.0,132.0,123.0,114.0,190.0,162.0,165.0],[593.0,82.0,71.0,92.0,94.0,135.0,119.0],[820.0,120.0,102.0,127.0,174.0,153.0,144.0]]}}", output);
	}
}
