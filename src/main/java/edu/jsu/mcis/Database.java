package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Database {
	private Map<String, Student> studentMap;
	private Map<String, Course> courseMap;
	
	
	public Database(DataSource source) {
		studentMap = source.getStudentMap();
		courseMap = source.getCourseMap();
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
	
	public String[] getAllCourseIDsArray(){
		List<String> array = new ArrayList<String>(courseMap.keySet());
		String[] s = new String[array.size()];
		for(int i = 0; i < array.size(); i++){
			s[i] = array.get(i);
		}
		return s;
	}
	
	public String[] getAllStudentIDsArray(){
		List<String> array = new ArrayList<String>(studentMap.keySet());
		String[] s = new String[array.size()];
		for(int i = 0; i < array.size(); i++){
			s[i] = array.get(i);
		}
		return s;
	}
}