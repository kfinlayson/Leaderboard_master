package edu.jsu.mcis;

import java.io.*;
import org.json.*;
import au.com.bytecode.opencsv.*;
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
	
	public Course(JSONWebSource webSource) {
		courseID = " ";
		term = " ";
		year = " ";
		classSize = " ";
		this.webSource = webSource;
	}
	
	private void readData() {
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
			grades = new Grades(gradesArray);			
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
					grades = new Grades(gradesArray);
				}
				catch(IOException e) {}
				
			}
			catch(FileNotFoundException e) {}
		}
	}
	
	public Grades getGrades() {
		readData();
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