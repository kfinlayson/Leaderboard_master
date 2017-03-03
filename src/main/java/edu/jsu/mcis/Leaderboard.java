package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class Leaderboard {
	
	private Grades grades;
	
	public Leaderboard(Grades grades) {
		this.grades = grades;
	}
	
	public int[] getSortedGrades(String assignment) {
		Map<String,Integer> map = grades.getAssignmentGrades(assignment);
		int[] sorted = new int[map.size()];
		for(int i = 0; i < sorted.length; i++) {
			
		}
		return sorted;
	}
}