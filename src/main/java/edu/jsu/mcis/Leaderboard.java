package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Leaderboard {
	
	private Grades grades;
	
	public Leaderboard(Grades grades) {
		this.grades = grades;
	}
	
	public List<Integer> getSortedGrades(String assignment) {
		Map<String,Integer> map = grades.getAssignmentGrades(assignment);
		List<Integer> sorted = new ArrayList<Integer>();
		
		Set<Entry<String, Integer>> set = map.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2 )
            {
                return (map2.getValue()).compareTo( map1.getValue() );
            }
        } );
		for(Map.Entry<String, Integer> entry:list){
            sorted.add(entry.getValue());
        }
		return sorted;
	}
	
}