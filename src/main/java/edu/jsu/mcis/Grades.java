package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Grades {
	
	private String maxScore;
	private String maxScoreID;
	private String[][] gradesArray;
	
	public Grades(String[][] gradesArray) {
		this.gradesArray = gradesArray;
	}
	
	public void findMaxScore(String assignment) {
		int max = 0;
		int temp = 0;
		for(int i = 0; i < gradesArray.length; i++) {
			if(gradesArray[0][i].equals(assignment)){
				for(int j = 1; j < gradesArray[0].length; j++){
					if(Integer.parseInt(gradesArray[j][i]) > max){
						max = Integer.parseInt(gradesArray[j][i]);
						temp = j;
					}
				}
			}
		}			
		
		maxScoreID = gradesArray[temp][0];
		maxScore = "" + max;
	
	}
	
	public int getStudentAssignmentGrade(String studentID, String assignment) throws InvalidIDException {
		if(!hasStudentID(studentID)) {
			String s = "The ID " + studentID + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		else if(!hasAssignment(assignment)) {
			String s = "The assignment " + assignment + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		else {
			int grade = 0;
			for(int i = 0; i < gradesArray.length; i++) {
				if(gradesArray[0][i].equals(assignment)) {
					for(int j = 0; j < gradesArray[0].length; j++) {
						if(gradesArray[j][0].equals(studentID)) {
							grade = Integer.parseInt(gradesArray[j][i]);
						}
					}
				}
			}
			return grade;
		}
	}
	
	public Map<String,Integer> getStudentGrades(String studentID) throws InvalidIDException {
		if(!hasStudentID(studentID)) {
			String s = "The ID " + studentID + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		else {
			Map<String,Integer> studentGrades = new TreeMap<String,Integer>();
			for(int i = 0; i < gradesArray[0].length; i++) {
				if(gradesArray[i][0].equals(studentID)) {
					for(int j = 1; j < gradesArray.length; j++) {
						int grade = Integer.parseInt(gradesArray[i][j]);
						studentGrades.put(gradesArray[0][j], grade);
					}
				}
			}
			return studentGrades;
		}
		
	}
	
	public Map<String,Integer> getAssignmentGrades(String assignment) throws InvalidIDException {
		if(!hasAssignment(assignment)) {
			String s = "The assignment " + assignment + " is invalid or does not exist";
			throw new InvalidIDException(s);
		}
		else {
			Map<String,Integer> assignmentGrades = new TreeMap<String,Integer>();
			for(int i = 0; i < gradesArray.length; i++) {
				if(gradesArray[0][i].equals(assignment)) {
					for(int j = 1; j < gradesArray[0].length; j++) {
						int grade = Integer.parseInt(gradesArray[j][i]);
						assignmentGrades.put(gradesArray[j][0], grade);
					}
				}
			}
			return assignmentGrades;
		}
		
	}
	
	private boolean hasStudentID(String studentID) {
		for(int i = 1; i < gradesArray[0].length; i++) {
			if(gradesArray[i][0].equals(studentID)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasAssignment(String assignment) {
		for(int i = 1; i < gradesArray[0].length; i++) {
			if(gradesArray[0][i].equals(assignment)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String[] getAssignmentList() {
		String[] assignmentList = new String[gradesArray.length-1];
		for(int i = 1; i < gradesArray.length; i++) {
			assignmentList[i-1] = gradesArray[0][i];
		}
		return assignmentList;
	}
	
	public String getMaxScore(){
		return maxScore;
	}
	
	public String getMaxScoreID() {
		return maxScoreID;
	}
	
}