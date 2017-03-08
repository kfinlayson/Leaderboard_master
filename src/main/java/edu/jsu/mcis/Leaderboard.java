package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import java.awt.Color;
import java.awt.Insets;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Leaderboard {
	
	private Grades grades;
	
	public Leaderboard(Grades grades) {
		this.grades = grades;
	}
	
	public Map<String,Integer> getSortedGrades(String assignment) {
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
		
		Map<String,Integer> sortedMap = new TreeMap<String,Integer>();
		String key = "";
		for(int i = 0; i < sorted.size(); i++) {
			for(Map.Entry<String,Integer> entry:map.entrySet()) {
				if(entry.getValue() == sorted.get(i)) {
					sortedMap.put(entry.getKey(),sorted.get(i));
					key = entry.getKey();
				}
			}
			map.remove(key);
		}
		return sortedMap;
	}
	
}