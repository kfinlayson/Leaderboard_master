package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class Course {
	private String courseID;
	private String term;
	private String year;
	private String classSize;
	private Grades grades;
	private JSONWebSource webSource;

	public Course() {
		courseID = " ";
		term = " ";
		year = " ";
		classSize = " ";
	}
	
	public void setGrades(Grades grades) {
		this.grades = grades;
	}
	
	public Grades getGrades() {
		return grades;
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