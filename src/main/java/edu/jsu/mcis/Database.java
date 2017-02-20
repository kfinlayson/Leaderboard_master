package edu.jsu.mcis;

import java.util.*;

public class Database {
	
	private Student student;
	private Course course;
	
	public Database() {
		student = new Student();
		course = new Course();
	}
	
	public String getStudent(String studentID) throws InvalidIDException {
		if(!student.hasID(studentID)) {
			String s = "The ID " + studentID + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		return student.getStudentInfo(studentID).toString();
	}
	
	public String getCourse(String courseID) throws InvalidIDException {
		if(!course.hasID(courseID)) {
			String s = "The ID " + courseID + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		return course.getCourseInfo(courseID).toString();
	}
}