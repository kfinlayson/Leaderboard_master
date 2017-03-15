package edu.jsu.mcis;


import org.junit.*;
import java.io.*;
import java.util.Map.Entry;
import java.util.*;
import javax.swing.event.EventListenerList;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.RectangleInsets;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.category.CategoryDataset;

public class Leaderboard extends JPanel{

	private Grades grades;
	private int studentPlacement;
	private Map<Integer,String> map;
	private ChartPanel chartPanel;
	private double[][] data;
    
	private CategoryDataset createDataset(Map<Integer,String> map) { 
		data = new double[1][map.size()]; //note sizing may be wrong
		int i = 0;
		for(Map.Entry<Integer,String> entry:map.entrySet()){
			data[0][i] = entry.getKey();
			i++;	
		}
        return DatasetUtilities.createCategoryDataset("A", "S", data);
    }
	
	public Leaderboard() {}
	
    public Leaderboard(Grades grades, String assignment) {
		this.grades = grades;
	

		studentPlacement = -1;
       
		
		map = getSortedGrades(assignment);
		final CategoryDataset dataset = createDataset(map);
      
        final JFreeChart chart = ChartFactory.createBarChart(
            null,  // chart title
            "Category",             // domain axis label
            "Score (%)",            // range axis label
            dataset,                // data
            PlotOrientation.HORIZONTAL,
            false,                  // include legend
            true,
            false
        );

        
        final CategoryPlot plot = chart.getCategoryPlot();  
        plot.setRangeGridlinesVisible(false);
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0);
        domainAxis.setUpperMargin(0);
        domainAxis.setVisible(false);
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		Map.Entry<Integer,String> entry = map.entrySet().iterator().next();
		Integer key = entry.getKey();
        rangeAxis.setRange(0.0, (key * 0.2) + key);
        rangeAxis.setVisible(false);
       
	

       
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 135));
		
		chartPanel.addChartMouseListener(new ChartMouseListener(){
			
			@Override
			public void chartMouseClicked(ChartMouseEvent chartMouseEvent) { 
				try{
					System.out.println("I've been clicked " + chartMouseEvent);
					String s = chartMouseEvent.getEntity().getToolTipText();
					System.out.println(s);
					s = s.substring(s.lastIndexOf("=") + 1);
					studentPlacement = Integer.parseInt(s.trim());
					fireMyEvent(new BarGraphEvent(new Object()));
				}
				catch(NullPointerException e){
					
				}
			} 
			
			@Override
			public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {
				
			}
		});
		
        add(chartPanel);		
		
		setVisible(true);
		

	}
	
	public Map<Integer,String> getSortedGrades(String assignment) {
		Map<String,Integer> map = grades.getAssignmentGrades(assignment);
		java.util.List<Integer> sorted = new ArrayList<Integer>();
		
		Set<Entry<String, Integer>> set = map.entrySet();
        java.util.List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
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
		
		Map<Integer,String> sortedMap = new TreeMap<Integer,String>(Collections.reverseOrder());
		String key = "";
		for(int i = 0; i < sorted.size(); i++) {
			for(Map.Entry<String,Integer> entry:map.entrySet()) {
				if(entry.getValue() == sorted.get(i)) {
					sortedMap.put(sorted.get(i),entry.getKey());
					key = entry.getKey();
				}
			}
			map.remove(key);
		}
		return sortedMap;
	}
	
	public String getStudentID(){
		return map.get(studentPlacement);
	}
	
	public int getStudentScore() {
		return studentPlacement;
	}
	
	protected EventListenerList listenerList = new EventListenerList();

	public void addBarGraphEventListener(BarGraphEventListener listener) {
		listenerList.add(BarGraphEventListener.class, listener);
	}
	public void removeBarGraphEventListener(BarGraphEventListener listener) {
		listenerList.remove(BarGraphEventListener.class, listener);
	}
	void fireMyEvent(BarGraphEvent event) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i+2) {
		  if (listeners[i] == BarGraphEventListener.class) {
			((BarGraphEventListener) listeners[i+1]).barPressed(event);
			}	
		}
	}
	
	public Shape[] getShapes() {
		EntityCollection entities = chartPanel.getChartRenderingInfo().getEntityCollection();
		java.util.List<Shape> shapes = new ArrayList<>();
		for(int i = 0; i < entities.getEntityCount(); i++) {
			ChartEntity e = entities.getEntity(i);
			if(e instanceof CategoryItemEntity) {
				String coords = e.getShapeCoords();
				java.util.Scanner scanner = new java.util.Scanner(coords);
				scanner.useDelimiter(",");
				int ulx = scanner.nextInt();
				int uly = scanner.nextInt();
				int lrx = scanner.nextInt();
				int lry = scanner.nextInt();
				shapes.add(new Rectangle(ulx, uly, lrx - ulx, lry - uly));
			}
		}
		
		return shapes.toArray(new Shape[1]);
	}
}