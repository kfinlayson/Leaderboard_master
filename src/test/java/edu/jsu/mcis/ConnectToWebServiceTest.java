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
}
