package keywords;

import edu.jsu.mcis.*;
import java.io.*;

public class GamegogyKeywords {
    
	private Database data;
	private Runtime rt;
	private String arg1;
	private String arg2;
	private Student studentData;
	private Course courseData;
	private String allStudentIDs;
	private String allCourseIDs;
	private boolean argumentGiven;
	
	public GamegogyKeywords() {
		data = new Database("FileSource");
		rt = Runtime.getRuntime();
		arg1 = "";
		arg2 = "";
		allStudentIDs = "";
		allCourseIDs = "";
		studentData = new Student();
		courseData = new Course();
		argumentGiven = false;
	}
	
	public void startGamegogyCLIWithArguments() {
		try {
			rt.exec("java Gamegogy ");
		}
		catch(IOException e) {};
	}
	
	public void startGamegogyCLIWithArguments(String arg1) {
		this.arg1 = arg1;
		try {
			rt.exec("java Gamegogy " + arg1);
			if(arg1.equals("studentids")) {
				allStudentIDs = data.getAllStudentIDs();
			}
			if(arg1.equals("courseids")) {
				allCourseIDs = data.getAllCourseIDs();
			}
			argumentGiven = true;
		}
		catch(IOException e) {};
	}
	
	public void startGamegogyCLIWithArguments(String arg1, String arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		try {
			rt.exec("java Gamegogy " + arg1 + " " + arg2);
			if(arg1.equals("student")) {
				studentData = data.getStudent(arg2);
			}
			if(arg1.equals("course")) {
				courseData = data.getCourse(arg2);
			}
			argumentGiven = true;
		}
		catch(IOException e) {};
	}
	
	public String getCommandLineOutput() {
		if(argumentGiven) {
			if(arg1.equals("student")) {
				return studentData.toString();
			}
			if(arg1.equals("course")) {
				return courseData.toString();
			}
			if(arg1.equals("studentids")) {
				return allStudentIDs;
			}
			if(arg1.equals("courseids")) {
				return allCourseIDs;
			}
		}
		return "";
	}
	
	public void loadStudentData() {
		studentData = data.getStudent(arg2);
	}
	
	public void loadCourseData() {
		courseData = data.getCourse(arg2);
	}
	
	public void loadStudentList() {
		allStudentIDs = data.getAllStudentIDs();
	} 
	
	public void loadCourseList() {
		allCourseIDs = data.getAllCourseIDs();
	}

}