package edu.jsu.mcis;

import java.util.*;

public class Database {
	
	private Student student;
	private Course course;
	private List<String[]> studentData;
	private HashMap studentMap;
	private DataReader reader;
	private List<String[]> courseData;
	private HashMap courseMap;
	
	public Database() {
		student = new Student();
		course = new Course();
		reader = new DataReader();
		studentData = reader.getStudentData();
		studentMap = new HashMap<String, Student>();
		courseData = reader.getCourseData();
		courseMap = new HashMap<String, Course>();
	}
	
	@SuppressWarnings("unchecked")
	private void setStudentMaps() {
		String key = "";
		for(String[] token : studentData) {
			if(token != studentData.get(0)) {
				for(String element : token) {
					if(element == token[0]) {
						key = element;
						student.setID(element);
					}
					else if(element == token[1]) {
						student.setFirstName(element);
					}
					else if(element == token[2]) {
						student.setLastName(element);
					}
					else if(element == token[3]) {
						student.setStudentEmail(element);
					}
					studentMap.put(key, student.toString());
				}
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void setCourseMaps() {
		String key = "";
		for(String[] token : courseData) {
			if(token != courseData.get(0)) {
				for(String element : token) {
					if(element == token[0]) {
						key = element;
						course.setCourseID(element);
					}
					else if(element == token[1]) {
						course.setCourseTerm(element);
					}
					else if(element == token[2]) {
						course.setCourseYear(element);
					}
					else if(element == token[3]) {
						course.setCourseSize(element);
					}
					courseMap.put(key, course.toString());
				}
			}
		}
		
	}
	
	public boolean hasCourseID(String courseID) {
		if(courseMap.containsKey(courseID)) {
			return true;
		}
		else return false;
	}
	
	public boolean hasStudentID(String studentID) {
		if(studentMap.containsKey(studentID)) {
			return true;
		}
		else return false;
	}
	
	public Student getStudent(String studentID) throws InvalidIDException {
		if(!hasStudentID(studentID)) {
			String s = "The ID " + studentID + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		else return (Student)studentMap.get(studentID);
	}
	
	public Course getCourse(String courseID) throws InvalidIDException {
		if(!hasCourseID(courseID)) {
			String s = "The ID " + courseID + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		else return (Course)courseMap.get(courseID);
	}
}