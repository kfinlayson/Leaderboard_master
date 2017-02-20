package edu.jsu.mcis;

import java.util.*;

public class Student {
	private String studentID;
	private String firstName;
	private String lastName;
	private String email;
	private List<String[]> studentData;
	private HashMap studentMap;
	private DataReader reader;
	
	public Student (String studentID){
		this.studentID = studentID;
	}
	public Student (String studentID, String firstName, String lastName, String email){
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public Student() {
		studentID = " ";
		firstName = " ";
		lastName = " ";
		email = " ";
		reader = new DataReader();
		studentData = reader.getStudentData();
		studentMap = new HashMap<String, String>();
		setStudentMaps();
	}
	
	@SuppressWarnings("unchecked")
	private void setStudentMaps() {
		String key = "";
		for(String[] token : studentData) {
			if(token != studentData.get(0)) {
				for(String element : token) {
					if(element == token[0]) {
						key = element;
						setID(element);
					}
					else if(element == token[1]) {
						setFirstName(element);
					}
					else if(element == token[2]) {
						setLastName(element);
					}
					else if(element == token[3]) {
						setStudentEmail(element);
					}
					studentMap.put(key, this.toString());
				}
			}
		}
		
	}
	
	public Object getStudentInfo(String studentID) {
		Object object = studentMap.get(studentID);
		return object;
	}

	public String getID() {
		return studentID;
	}
	public void setID(String studentID) {
		this.studentID = studentID;
	}
	
	public boolean hasID(String studentID) {
		if(studentMap.containsKey(studentID)) {
			return true;
		}
		else return false;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getStudentEmail() {
		return email;
	}
	public void setStudentEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return "[" +studentID+ "]" + " " +firstName+ " " +lastName+ " " +email+ "@jsu.edu";
	}
}