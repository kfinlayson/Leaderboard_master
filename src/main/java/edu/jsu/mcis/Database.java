package edu.jsu.mcis;

import java.util.*;

public class Database {
	
	private DataReader reader;
	private List<String[]> studentData;
	private List<String[]> courseData;
	private TreeMap<String, Student> studentMap;
	private TreeMap<String, Course> courseMap;
	
	public Database() {
		reader = new DataReader();
		studentData = reader.getStudentData();
		courseData = reader.getCourseData();
		studentMap = new TreeMap<String, Student>();
		courseMap = new TreeMap<String, Course>();
		setStudentMaps();
		setCourseMaps();
	}
	
	@SuppressWarnings("unchecked")
	private void setStudentMaps() {
		String key = "";
		for(String[] token : studentData) {
			Student student = new Student();
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
					studentMap.put(key, student);
				}
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void setCourseMaps() {
		String key = "";
		for(String[] token : courseData) {
			Course course = new Course();
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
					courseMap.put(key, course);
				}
			}
		}
		
	}
	
	private boolean hasCourseID(String courseID) {
		if(courseMap.containsKey(courseID)) {
			return true;
		}
		else return false;
	}
	
	private boolean hasStudentID(String studentID) {
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
	
	public String getAllStudentIDs() {
		String s = "";
		List<String> array = new ArrayList<String>(studentMap.keySet());
		for ( int i = 0; i < array.size(); i++ ) {
			if(array.get(i) != array.get(array.size() - 1)) {
				s += array.get(i) + "\n";
			}
			else {
				s += array.get(i);
			}
		}
		return s;
	}
	
	public String getAllCourseIDs() {
		String s = "";
		List<String> array = new ArrayList<String>(courseMap.keySet());
		for ( int i = 0; i < array.size(); i++ ) {
			if(array.get(i) != array.get(array.size() - 1)) {
				s += array.get(i) + "\n";
			}
			else {
				s += array.get(i);
			}
		}
		return s;
	}
}