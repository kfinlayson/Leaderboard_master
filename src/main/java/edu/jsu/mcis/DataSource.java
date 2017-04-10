package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public interface DataSource{
	
	void setStudentMaps(List<String[]> studentData);
	void setCourseMaps(List<String[]> courseData);
	Grades readGradesData(String courseID);
	public Map<String, Student> getStudentMap();
	public Map<String, Course> getCourseMap();

}