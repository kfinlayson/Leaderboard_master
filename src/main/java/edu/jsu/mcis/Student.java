package edu.jsu.mcis;

public class Student {
	private String studentID;
	private String firstName;
	private String lastName;
	private String email;
	
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
	}

	public String getID() {
		return studentID;
	}
	public void setID(String studentID) {
		this.studentID = studentID;
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