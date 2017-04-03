package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Database {
	private Map<String, Student> studentMap;
	private Map<String, Course> courseMap;
	private JSONWebSource webSource;
	
	public Database(String source) {
		studentMap = new TreeMap<String, Student>();
		courseMap = new TreeMap<String, Course>();
		if(source.equals("FileSource")) {
			setStudentMaps(readData("src/main/resources/students.csv"));
			setCourseMaps(readData("src/main/resources/courses.csv"));
			
		}
		else {
			webSource = new JSONWebSource(source);
			setStudentMaps(webSource.getJSONStudent());
			setCourseMaps(webSource.getJSONCourse());
		}
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
					else {
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
						course.setGrades(readGradesData(key));
					}
					else if(element.equals(token[1])) {
						course.setCourseTerm(element);
					}
					else if(element.equals(token[2])) {
						course.setCourseYear(element);
					}
					else {
						course.setCourseSize(element);
					}
					courseMap.put(key, course);
				}
			}	
		}
	}
	
	private Grades readGradesData(String courseID) {
		if(webSource != null) {
			List<String[]> gradesData = webSource.getJSONGrades(courseID);
			int rowSize = gradesData.size();
			int colSize = gradesData.get(0).length;
			String[][] gradesArray = new String[rowSize][colSize];
			for(int i = 0; i < rowSize; i++) {
				for(int j = 0; j < colSize; j++) {
					gradesArray[i][j] = gradesData.get(i)[j];
				}
			}
			Grades grades = new Grades(gradesArray);
			return grades;
		}
		
		else {
			try{
				CSVReader gradesReader = new CSVReader(new FileReader("src/main/resources/courses/" + courseID + ".csv"));
				try{
					List<String[]> gradesData =  gradesReader.readAll();
					int rowSize = gradesData.size();
					int colSize = gradesData.get(0).length;
					String[][] gradesArray = new String[rowSize][colSize];
					for(int i = 0; i < rowSize; i++) {
						for(int j = 0; j < colSize; j++) {
							gradesArray[i][j] = gradesData.get(i)[j];
						}
					}
					Grades grades = new Grades(gradesArray);
					return grades;
				}
				catch(IOException e) {}
				
			}
			catch(FileNotFoundException e) {}
		}
		return null;
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