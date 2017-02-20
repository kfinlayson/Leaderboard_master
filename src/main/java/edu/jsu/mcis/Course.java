package edu.jsu.mcis;

import java.util.*;

public class Course {
	private String courseID;
	private String term;
	private String year;
	private String classSize;
	private List<String[]> courseData;
	private HashMap courseMap;
	private DataReader reader;
	
	public Course (String courseID){
		this.courseID = courseID;
	}
	public Course (String courseID, String term, String year, String classSize){
		this.courseID = courseID;
		this.term = term;
		this.year = year;
		this.classSize = classSize;
	}
	public Course() {
		courseID = " ";
		term = " ";
		year = " ";
		classSize = " ";
		reader = new DataReader();
		courseData = reader.getCourseData();
		courseMap = new HashMap<String, String>();
		setCourseMaps();
	}
	
	@SuppressWarnings("unchecked")
	private void setCourseMaps() {
		String key = "";
		for(String[] token : courseData) {
			if(token != courseData.get(0)) {
				for(String element : token) {
					if(element == token[0]) {
						key = element;
						setCourseID(element);
					}
					else if(element == token[1]) {
						setCourseTerm(element);
					}
					else if(element == token[2]) {
						setCourseYear(element);
					}
					else if(element == token[3]) {
						setCourseSize(element);
					}
					courseMap.put(key, this.toString());
				}
			}
		}
		
	}
	
	public Object getCourseInfo(String courseID) {
		Object object = courseMap.get(courseID);
		return object;
	}
	
	public boolean hasID(String courseID) {
		if(courseMap.containsKey(courseID)) {
			return true;
		}
		else return false;
	}
	
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public String getCourseTerm() {
		return term;
	}
	public void setCourseTerm(String term){
		this.term = term;
	}
	
	public String getCourseYear() {
		return year;
	}
	public void setCourseYear(String year) {
		this.year = year;
	}
	
	public String getCourseSize() {
		return classSize;
	}
	public void setCourseSize(String classSize){
		this.classSize = classSize;
	}
	
	public String toString(){
		return "[" +courseID+ "]" + " " +term+ " " + year+ " (" +classSize+ " students)";
	}

}