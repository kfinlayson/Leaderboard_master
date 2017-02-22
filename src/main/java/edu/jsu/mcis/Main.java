package edu.jsu.mcis;

public class Main {
	
	public static void main(String[] args) {
		Database data = new Database();
		if(args.length == 2) {
			if(args[0] == "student") {
				data.getStudent(args[1]).toString();
			}
			if(args[0] == "course") {
				data.getCourse(args[1]).toString();
			}
		}
	}
}