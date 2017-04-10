package edu.jsu.mcis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 

public class Main {

	private static Database data;
	private static JMenuBar menuBar;
	private static JMenu menu;
	private static JRadioButtonMenuItem rbMenuItem;
	private static Gamegogy gamegogy;
	
	public static void main(String[] args){
		if(args.length < 3 && args.length != 0) {
			
			if(args[0] == "student") {
				data = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
				data.getStudent(args[1]).toString();
			}
			if(args[0] == "course") {
				data = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
				data.getCourse(args[1]).toString();
			}
			if(args[0] == "studentids") {
				data = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
				data.getAllStudentIDs();
			}
			if(args[0] == "courseids") {
				data = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
				data.getAllCourseIDs();
			}
			
			if(args[0].equals("http://inspired.jsu.edu:7272/gamegogy/") || args[0].equals("http://inspired.jsu.edu:7272/gamegogy")) {
				data = new Database(new WebSource(args[0]));
				JFrame win = new JFrame("Gamegogy");
				win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gamegogy = new Gamegogy(data);
				win.add(new Gamegogy(data));
				win.setSize(600, 700);
				win.setVisible(true);
			}
		}
		
		
		else {
			data = new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv"));
			JFrame win = new JFrame("Gamegogy");
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gamegogy = new Gamegogy(data);
			win.add(gamegogy);
			win.setSize(600, 700);
			win.setVisible(true);
			
			menuBar = new JMenuBar();
			menu = new JMenu("Source");
			menu.setMnemonic(KeyEvent.VK_A);
			menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
			menuBar.add(menu);
			
			ButtonGroup group = new ButtonGroup();
			rbMenuItem = new JRadioButtonMenuItem("Resource File");
			rbMenuItem.setSelected(true);
			rbMenuItem.setMnemonic(KeyEvent.VK_R);
			rbMenuItem.setName("Resource File");
			rbMenuItem.addActionListener(new ActionListener()
				{
				  public void actionPerformed(ActionEvent e)
				  {
					gamegogy.setDatabase(new Database(new FileSource("src/main/resources/students.csv","src/main/resources/courses.csv")));
					gamegogy.setIndex(0);
				  }
				});
			group.add(rbMenuItem);
			menu.add(rbMenuItem);
			
			rbMenuItem = new JRadioButtonMenuItem("Web Service");
			rbMenuItem.setMnemonic(KeyEvent.VK_O);
			rbMenuItem.setName("Web Service");
			rbMenuItem.addActionListener(new ActionListener()
				{
				 public void actionPerformed(ActionEvent e)
				  {
					gamegogy.setDatabase(new Database(new WebSource("http://inspired.jsu.edu:7272/gamegogy/")));
					gamegogy.setIndex(0);
				  }
				});
			group.add(rbMenuItem);
			menu.add(rbMenuItem);
			
			
			win.setJMenuBar(menuBar);
		}
	}
	
}