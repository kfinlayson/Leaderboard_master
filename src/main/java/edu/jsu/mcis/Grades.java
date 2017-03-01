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
	
	public int getStudentAssignmentGrade(String id, String assignment) {
		return 0;
	}
	
	public TreeMap<String,Integer> getStudentGrades(String id) {
		return null;
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