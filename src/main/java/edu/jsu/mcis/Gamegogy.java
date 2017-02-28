package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.*;

public class Gamegogy extends JPanel{
	
	private Database database;
	
	private JComboBox courseComboBox;
	private JComboBox columnComboBox;

	private JLabel courseTerm;
	private JLabel courseEnrollment;
	private JLabel studentId;
	private JLabel studentName;
	private JLabel studentEmail;
	private JLabel studentScore;
	
	@SuppressWarnings("unchecked")
	public Gamegogy() {
		
		database = new Database();
		courseComboBox = new JComboBox(database.getAllCourseIDsArray());
		courseComboBox.setName("courseComboBox");
		
		courseComboBox.setSelectedIndex(0);
		Assignment assignment = new Assignment("99000");
		
		columnComboBox = new JComboBox(assignment.getAssignmentList());
		columnComboBox.setName("columnComboBox");
		
		columnComboBox.setSelectedIndex(0);
		
		courseComboBox.setPreferredSize(new Dimension(100,65));
		columnComboBox.setPreferredSize(new Dimension(100,65));
		JLabel courseLabel = new JLabel("Course");
		JLabel columnLabel = new JLabel("Column");
		
		JPanel dropDownGrid = new JPanel();
		dropDownGrid.setLayout(new GridLayout(1,5));
		dropDownGrid.add(courseLabel);
		dropDownGrid.add(courseComboBox);
		dropDownGrid.add(new JLabel(""));
		dropDownGrid.add(columnLabel);
		dropDownGrid.add(columnComboBox);
		
		JPanel labelGridMain = new JPanel();
		labelGridMain.setLayout(new GridLayout(1,3));
		JPanel labelGrid1 = new JPanel();
		labelGrid1.setLayout(new GridLayout(1,2));
		JPanel labelGrid2 = new JPanel();
		labelGrid2.setLayout(new GridLayout(1,2));
		
		JLabel courseTermLabel = new JLabel("Term: ");
		Course course = database.getCourse(courseComboBox.getSelectedItem().toString());
		courseTerm = new JLabel(course.getCourseTerm() + " " + course.getCourseYear());
		courseTerm.setName("courseTerm");
		labelGrid1.add(courseTermLabel);
		labelGrid1.add(courseTerm);
		
		JLabel courseEnrollmentLabel = new JLabel("Enrollment: ");
		courseEnrollment = new JLabel(course.getCourseSize()); 
		courseEnrollment.setName("courseEnrollment");
		labelGrid2.add(courseEnrollmentLabel);
		labelGrid2.add(courseEnrollment);
		
		labelGridMain.add(labelGrid1);
		labelGridMain.add(new JLabel(""));
		labelGridMain.add(labelGrid2);
		
		JPanel studentInfoBoxMain = new JPanel();
		studentInfoBoxMain.setLayout(new GridLayout(1,2));
		
		JPanel studentInfoBox1 = new JPanel();
		studentInfoBox1.setLayout(new GridLayout(4,1));
		
		JLabel studentIdLabel = new JLabel("ID: ");
		studentInfoBox1.add(studentIdLabel);
		
		JLabel studentNameLabel = new JLabel("Name: ");
		studentInfoBox1.add(studentNameLabel);
		
		JLabel studentEmailLabel = new JLabel("Email: ");
		studentInfoBox1.add(studentEmailLabel);
		
		JLabel studentScoreLabel = new JLabel("Score: ");
		studentInfoBox1.add(studentScoreLabel);
		
		JPanel studentInfoBox2 = new JPanel();
		studentInfoBox2.setLayout(new GridLayout(4,1));
		
		
		assignment.findMaxScore(columnComboBox.getSelectedItem().toString());
		Student student = database.getStudent(assignment.getMaxScoreID());
		studentId = new JLabel(student.getID());
		studentId.setName("studentId");
		studentInfoBox2.add(studentId);
		studentName = new JLabel(student.getFirstName() + " " + student.getLastName());
		studentName.setName("studentName");
		studentInfoBox2.add(studentName);
		studentEmail = new JLabel(student.getStudentEmail() + "@jsu.edu");
		studentEmail.setName("studentEmail");
		studentInfoBox2.add(studentEmail);
		studentScore = new JLabel(assignment.getMaxScore());
		studentScore.setName("studentScore");
		studentInfoBox2.add(studentScore);
		
		studentInfoBoxMain.add(studentInfoBox1);
		studentInfoBoxMain.add(studentInfoBox2);
		
		studentInfoBoxMain.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setLayout(new GridLayout(3,1));
		add(dropDownGrid);
		add(labelGridMain);
		add(studentInfoBoxMain);
		
		courseComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent item){
				String course = (String)courseComboBox.getSelectedItem();
				Assignment temp = new Assignment(course);
				DefaultComboBoxModel model = new DefaultComboBoxModel(temp.getAssignmentList());
				columnComboBox.setModel(model);
			}
		});
		columnComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent item){
				String course = (String)courseComboBox.getSelectedItem();
				Assignment temp = new Assignment(course);
				
				String assignmentString = (String)columnComboBox.getSelectedItem();
				courseTerm.setText(database.getCourse(course).getCourseTerm() + " " + database.getCourse(course).getCourseYear());
				courseEnrollment.setText(database.getCourse(course).getCourseSize());
				
				temp.findMaxScore(assignmentString);
				studentId.setText(temp.getMaxScoreID());
				studentName.setText(database.getStudent(temp.getMaxScore()).getFirstName() + " " + database.getStudent(temp.getMaxScore()).getLastName()) ;
				studentEmail.setText(database.getStudent(temp.getMaxScore()).getStudentEmail() + "@jsu.edu");
				studentScore.setText(temp.getMaxScore());
			}
		});
	}
	
	
	
}