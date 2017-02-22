package edu.jsu.mcis;

import java.util.*;

public class Assignment {
	private List<String[]> data;
	private String maxScore;
	private String maxScoreID;
	private DataReader reader;
	private String[][] assignmentArray;
	
	public Assignment(String course){
		reader = new DataReader(course);
		data = reader.getAssignmentData();
		int rowSize = data.size();
		int colSize = data.get(0).length;
		assignmentArray = new String[rowSize][colSize];
		for(int i = 0; i < rowSize; i++) {
			for(int j = 0; j < colSize; j++) {
				assignmentArray[i][j] = data.get(i)[j];
			}
		}	
	}
	
	public String getMaxScore(String assignment) {
		int correctCol = 0;
		int max = 0;
		for(int i = 0; i < assignmentArray.length; i++) {
			if(assignmentArray[i].equals(assignment)) correctCol = i;
		}
		for(int i = 1; i < assignmentArray[correctCol].length; i++){
			if(Integer.parseInt(assignmentArray[correctCol][i]) > max){
				max = Integer.parseInt(assignmentArray[correctCol][i]);
			}
		}
		maxScore = "" + max;
		return maxScore;
	}
	
	public String getMaxScoreID() {
		return maxScoreID;
	}
}