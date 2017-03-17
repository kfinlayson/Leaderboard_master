package edu.jsu.mcis;

import javax.swing.*;

public class Main {
	
	private static Database data;
	
	public static void main(String[] args){
		if(args.length < 3 && args.length != 0) {
			
			if(args[0] == "student") {
				data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
				data.getStudent(args[1]).toString();
			}
			if(args[0] == "course") {
				data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
				data.getCourse(args[1]).toString();
			}
			if(args[0] == "studentids") {
				data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
				data.getAllStudentIDs();
			}
			if(args[0] == "courseids") {
				data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
				data.getAllCourseIDs();
			}
			
			if(args[0].equals("http://inspired.jsu.edu:7272/gamegogy/") || args[0].equals("http://inspired.jsu.edu:7272/gamegogy")) {
				System.out.println(args[0]);
				data = new Database(args[0]);
				JFrame win = new JFrame("Gamegogy");
				
				win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				win.add(new Gamegogy(data));
				win.setSize(600, 700);
				win.setVisible(true);
			}
		}
		
		
		else {
			data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
			JFrame win = new JFrame("Gamegogy");
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			win.add(new Gamegogy(data));
			win.setSize(600, 700);
			win.setVisible(true);
		}
	}
	
}