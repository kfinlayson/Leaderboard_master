package edu.jsu.mcis;

import java.util.*;

public interface DataSource{
	
	public Map<String, Student> getStudentMap();
	public Map<String, Course> getCourseMap();

}