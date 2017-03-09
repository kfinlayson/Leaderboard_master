package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Database {
	private Map<String, Student> studentMap;
	private Map<String, Course> courseMap;	
	
	public Database(String studentFile, String courseFile) {
		studentMap = new TreeMap<String, Student>();
		courseMap = new TreeMap<String, Course>();
		setStudentMaps(readData(studentFile));
		setCourseMaps(readData(courseFile));
		
	}	
	
	public Database() {
		studentMap = new TreeMap<String, Student>();
		courseMap = new TreeMap<String, Course>();
	}
	
	public Database(String basicURL) {
		JSONWebSource website = new JSONWebSource(basicURL);
		setStudentMaps(website.getJSONStudent());
		setCourseMaps(website.getJSONCourse());
		//website.getJSONGrades();
	}
	
	private List<String[]> readData(String fileName) {
		List<String[]> mapData = new ArrayList<String[]>();
		try{
			CSVReader reader = new CSVReader(new FileReader(fileName));
			try{
				mapData =  reader.readAll();
				
			}
			catch(IOException e) {}
		}
		catch(FileNotFoundException e) {}
		return mapData;
	}
	
	@SuppressWarnings("unchecked")
	private void setStudentMaps(List<String[]> studentData) {
		String key = "";
		for(String[] token : studentData) {
			Student student = new Student();
			if(token != studentData.get(0)) {
				for(String element : token) {
					if(element.equals(token[0])) {
						key = element;
						student.setID(element);
					}
					else if(element.equals(token[1])) {
						student.setFirstName(element);
					}
					else if(element.equals(token[2])) {
						student.setLastName(element);
					}
					else if(element.equals(token[3])) {
						student.setStudentEmail(element);
					}
					studentMap.put(key, student);
				}
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void setCourseMaps(List<String[]> courseData) {
		String key = "";
		for(String[] token : courseData) {
			Course course = new Course();
			if(token != courseData.get(0)) {
				for(String element : token) {
					if(element.equals(token[0])) {
						key = element;
						course.setCourseID(element);
					}
					else if(element.equals(token[1])) {
						course.setCourseTerm(element);
					}
					else if(element.equals(token[2])) {
						course.setCourseYear(element);
					}
					else if(element.equals(token[3])) {
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
	
	public String[] getAllCourseIDsArray(){
		List<String> array = new ArrayList<String>(courseMap.keySet());
		String[] s = new String[array.size()];
		for(int i = 0; i < array.size(); i++){
			s[i] = array.get(i);
		}
		return s;
	}
}