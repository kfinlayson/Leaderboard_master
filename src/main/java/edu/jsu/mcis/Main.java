package edu.jsu.mcis;

import javax.swing.*;

public class Main {
	public static void main(String[] args){
		if(args.length < 3 && args.length != 0) {
			Database data = new Database("src/main/resources/students.csv","src/main/resources/courses.csv");
			if(args[0] == "student") {
				data.getStudent(args[1]).toString();
			}
			if(args[0] == "course") {
				data.getCourse(args[1]).toString();
			}
			if(args[0] == "studentids") {
				data.getAllStudentIDs();
			}
			if(args[0] == "courseids") {
				data.getAllCourseIDs();
			}
		}
		
		if(args.length == 1) {
			Database data = new Database(args[0]);
		}
		else {
			JFrame win = new JFrame("Gamegogy");
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			win.add(new Gamegogy());
			win.setSize(500, 500);
			win.setVisible(true);
		}
	}
}