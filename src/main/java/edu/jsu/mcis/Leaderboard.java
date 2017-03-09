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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.category.CategoryDataset;

public class Leaderboard extends JPanel{
	
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

	private Grades grades;
	private int studentPlacement;
	private ChartPanel chartPanel;
    
	private CategoryDataset createDataset() { 
        final double[][] data = new double[][] {
            {65.0,55.0,40.0,34.0,41.0,36.0,41.0,58.0,36.0,34.0,46.0}
        };
        return DatasetUtilities.createCategoryDataset("A", "S", data);
    }
	
    public Leaderboard(Grades grades) {
		this.grades = grades;
	

		studentPlacement = -1;
        
		
        Map<String,Integer> MockGrades = new TreeMap<String,Integer>();
		MockGrades.put("111318", 65);
		MockGrades.put("111383", 55);
		MockGrades.put("111190", 40);
		MockGrades.put("111406", 34);
		MockGrades.put("111115", 41);
		MockGrades.put("111211", 36);
		MockGrades.put("111208", 41);
		MockGrades.put("111310", 58);
		MockGrades.put("111335", 36);
		MockGrades.put("111141", 34);
		MockGrades.put("111262", 46);
		
		final CategoryDataset dataset = createDataset();
      
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
        domainAxis.setLowerMargin(0.10);
        domainAxis.setUpperMargin(0.10);
        domainAxis.setVisible(false);
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        rangeAxis.setVisible(false);
       
	

       
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 135));
		
		chartPanel.addChartMouseListener(new ChartMouseListener(){
			
			@Override
			public void chartMouseClicked(ChartMouseEvent chartMouseEvent) { 
				try{
					String s = chartMouseEvent.getEntity().getToolTipText();
					s = s.substring(6,s.indexOf(")"));
					studentPlacement = Integer.parseInt(s);

												System.out.println("hi");
												EntityCollection entities = chartPanel.getChartRenderingInfo().getEntityCollection();
												for(int i = 0; i < entities.getEntityCount(); i++) {
													System.out.println(entities.getEntity(i));
													String coords = entities.getEntity(i).getShapeCoords();
													java.util.Scanner scanner = new java.util.Scanner(coords);
													scanner.useDelimiter(",");
													int ulx = scanner.nextInt();
													int uly = scanner.nextInt();
													int lrx = scanner.nextInt();
													int lry = scanner.nextInt();
													System.out.println(ulx);
													System.out.println(uly);
													System.out.println(lrx);
													System.out.println(lry);
													int x = (ulx + lrx) / 2;
													int y = (uly + lry) / 2;
													System.out.println("(" + x + ", " + y + ")");
												}
					
					
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
	
	public Map<String,Integer> getSortedGrades(String assignment) {
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
	
	public int getStudentPlacement(){
		return studentPlacement;
	}
	
	public Shape[] getShapes() {
		EntityCollection entities = chartPanel.getChartRenderingInfo().getEntityCollection();
		java.util.List<Shape> shapes = new ArrayList<>();
		for(int i = 0; i < entities.getEntityCount(); i++) {
			ChartEntity e = entities.getEntity(i);
			if(e instanceof CategoryItemEntity) {
				System.out.println(e);
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