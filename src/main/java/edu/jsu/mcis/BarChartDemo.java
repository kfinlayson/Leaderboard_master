package edu.jsu.mcis;

import java.awt.Color;
import java.awt.Insets;

import org.junit.*;
import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.*;

import org.jfree.chart.*;
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

/**
 * Another horizontal bar chart demo.  This time all the extras (titles, legend and axes) are
 * removed, to display just a single bar.
 *
 */
public class BarChartDemo extends JPanel{
		
	private int studentPlacement;
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
	private CategoryDataset createDataset() { 
        final double[][] data = new double[][] {
            {65.0,55.0,40.0,34.0,41.0,36.0,41.0,58.0,36.0,34.0,46.0}
        };
        return DatasetUtilities.createCategoryDataset("A", "S", data);
    }
	
    public BarChartDemo() {

        //super(title);
		studentPlacement = -1;
        // create a dataset...
		
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
        // create the chart...
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

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        //chart.setBackgroundPaint(Color.yellow);  // not seen
        final CategoryPlot plot = chart.getCategoryPlot();
//        plot.setInsets(new Insets(0, 0, 0, 0));
        plot.setRangeGridlinesVisible(false);
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.10);
        domainAxis.setUpperMargin(0.10);
        domainAxis.setVisible(false);
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        rangeAxis.setVisible(false);
        // OPTIONAL CUSTOMISATION COMPLETED.
	

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 135));
		chartPanel.addChartMouseListener(new ChartMouseListener(){
			
			@Override
			public void chartMouseClicked(ChartMouseEvent chartMouseEvent) { 
				try{
					String s = chartMouseEvent.getEntity().getToolTipText();
					s = s.substring(6,s.indexOf(")"));
					studentPlacement = Integer.parseInt(s);
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
	
	public int getStudentPlacement(){
		return studentPlacement;
	}
	
	
}