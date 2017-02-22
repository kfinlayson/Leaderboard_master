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
	private boolean argumentGiven;
	
	public GamegogyKeywords() {
		data = new Database();
		rt = Runtime.getRuntime();
		arg1 = "";
		arg2 = "";
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
		}
		return "";
	}
	
	public void loadStudentData(String studentID) {
		studentData = data.getStudent(arg2);
	}
	
	public void loadCourseData(String courseID) {
		courseData = data.getCourse(arg2);
	}

}