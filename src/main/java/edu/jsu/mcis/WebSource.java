package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class WebSource implements DataSource{
	
	private Map<String, Student> studentMap;
	private Map<String, Course> courseMap;
	private JSONWebSource webSource;
	
	public WebSource(String url){
		studentMap = new TreeMap<String, Student>();
		courseMap = new TreeMap<String, Course>();
		webSource = new JSONWebSource(url);
		setStudentMaps(webSource.getJSONStudent());
		setCourseMaps(webSource.getJSONCourse());
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
	
	public Map<String, Student> getStudentMap(){
		return studentMap;
	}
	
	public Map<String, Course> getCourseMap(){
		return courseMap;
	}

}