package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class DataReader {
	private List<String []> studentData;
	private List<String []> courseData;
	private List<String []> assignmentData;
	
	public DataReader(){
		try{
			CSVReader studentReader = new CSVReader(new FileReader("src/main/resources/students.csv"));
			try{
				studentData =  studentReader.readAll();
				}
			catch(IOException e) {}
			
			CSVReader courseReader = new CSVReader(new FileReader("src/main/resources/courses.csv"));
			try{
				courseData =  courseReader.readAll();
				}
			catch(IOException e) {}
		}
		catch(FileNotFoundException e) {}
		
	}
	
	public DataReader(String fileName) {
		try{
			CSVReader assignmentReader = new CSVReader(new FileReader("src/main/resources/courses/" + fileName + ".csv"));
			try{
				assignmentData =  assignmentReader.readAll();
				}
			catch(IOException e) {}
			
		}
		catch(FileNotFoundException e) {}
	}
			
	public List<String[]> getStudentData(){
		return studentData;
	}
	public List<String[]> getCourseData() {
		return courseData;
	}
	
	public List<String[]> getAssignmentData(){
		return assignmentData;
	}
	
}