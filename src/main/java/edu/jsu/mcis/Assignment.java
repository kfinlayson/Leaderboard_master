package edu.jsu.mcis;

import java.util.*;

public class Assignment {
	private List<String[]> data;
	private String maxScore;
	private String maxScoreID;
	private DataReader reader;
	private String[][] assignmentArray;
	private String[] assignmentList;
	
	public Assignment(String course){
		reader = new DataReader(course);
		data = reader.getAssignmentData();
		int rowSize = data.size();
		int colSize = data.get(0).length;
		assignmentList = new String[rowSize-1];
		assignmentArray = new String[rowSize][colSize];
		for(int i = 0; i < rowSize; i++) {
			for(int j = 0; j < colSize; j++) {
				assignmentArray[i][j] = data.get(i)[j];
			}
		}
	}
	
	public void findMaxScore(String assignment) {
		int correctCol = 0;
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
	@Override
	public String toString(){
		String s = "";
		for(int i = 0; i < assignmentArray.length; i++){
			for(int j = 0; j < assignmentArray[0].length;j++){
				s += " " + assignmentArray[i][j];
			}
			s += "\n";
		}
		return s;
	}
}