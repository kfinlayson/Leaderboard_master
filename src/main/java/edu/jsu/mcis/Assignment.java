package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Assignment {
	
	private String maxScore;
	private String maxScoreID;
	private String[][] assignmentArray;
	private String[] assignmentList;
	
	public Assignment(String course){
		readData(course);
	}
	
	private void readData(String fileName) {
		try{
			CSVReader assignmentReader = new CSVReader(new FileReader("src/main/resources/courses/" + fileName + ".csv"));
			try{
				List<String[]> assignmentData =  assignmentReader.readAll();
				int rowSize = assignmentData.size();
				int colSize = assignmentData.get(0).length;
				assignmentList = new String[rowSize-1];
				assignmentArray = new String[rowSize][colSize];
				for(int i = 0; i < rowSize; i++) {
					for(int j = 0; j < colSize; j++) {
						assignmentArray[i][j] = assignmentData.get(i)[j];
					}
				}
			}
			catch(IOException e) {}
			
		}
		catch(FileNotFoundException e) {}
	}
	
	public void findMaxScore(String assignment) {
		int max = 0;
		int temp = 0;
		for(int i = 0; i < assignmentArray.length; i++) {
			if(assignmentArray[0][i].equals(assignment)){
				for(int j = 1; j < assignmentArray[0].length; j++){
					if(Integer.parseInt(assignmentArray[j][i]) > max){
						max = Integer.parseInt(assignmentArray[j][i]);
						temp = j;
					}
				}
			}
		}			
		
		maxScoreID = assignmentArray[temp][0];
		maxScore = "" + max;
	
	}
	
	public String[] getAssignmentList() {
		for(int i = 1; i < assignmentArray.length; i++) {
			assignmentList[i-1] = assignmentArray[0][i];
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