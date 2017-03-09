package edu.jsu.mcis;

import java.io.*;
import au.com.bytecode.opencsv.*;
import java.util.*;

public class Course {
	private String courseID;
	private String term;
	private String year;
	private String classSize;
	private Grades grades;

	public Course() {
		courseID = " ";
		term = " ";
		year = " ";
		classSize = " ";
		
	}
	private int rowSize;
	private int colSize;
	
	private void readData(String fileName) {
		try{
			CSVReader gradesReader = new CSVReader(new FileReader("src/main/resources/courses/" + fileName + ".csv"));
			try{
				List<String[]> gradesData =  gradesReader.readAll();
				rowSize = gradesData.size();
				colSize = gradesData.get(0).length;
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
	
	public int getCol(){
		return colSize;
	}
	public int getRow(){
		return rowSize;
	}
	
	public Grades getGrades() {
		readData(courseID);
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