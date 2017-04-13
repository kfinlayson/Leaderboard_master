package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class FileSource implements DataSource{
	
	private Map<String, Student> studentMap;
	private Map<String, Course> courseMap;
	
	public FileSource(String filename1, String filename2){
		studentMap = new TreeMap<String, Student>();
		courseMap = new TreeMap<String, Course>();
		setStudentMaps(readData("src/main/resources/students.csv"));
		setCourseMaps(readData("src/main/resources/courses.csv"));
	}
	

	private void setStudentMaps(List<String[]> studentData){
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
	
	
	private void setCourseMaps(List<String[]> courseData){
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
	
	private Grades readGradesData(String courseID){
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
		return null;
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
	
	public Map<String, Student> getStudentMap(){
		return studentMap;
	}
	
	public Map<String, Course> getCourseMap(){
		return courseMap;
	}
}