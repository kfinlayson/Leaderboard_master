package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.*;

public class Gamegogy extends JPanel{
	
	private Database database;
	private Assignment assignment;
	
	private JComboBox courseComboBox;
	private JComboBox columnComboBox;
	
	private JPanel dropDownGrid;
	private JPanel labelGrid;
	private JPanel studentInfoBoxMain;
	private JPanel studentInfoBox1;
	private JPanel studentInfoBox2;
	
	private JLabel courseLabel;
	private JLabel columnLabel;
	
	private JLabel courseTerm;
	private JLabel courseEnrollment;
	private JLabel studentId;
	private JLabel studentName;
	private JLabel studentEmail;
	private JLabel studentScore;
	
	private JLabel studentIdLabel;
	private JLabel studentNameLabel;
	private JLabel studentEmailLabel;
	private JLabel studentScoreLabel;
	
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
		courseLabel = new JLabel("Course");
		columnLabel = new JLabel("Column");
		
		dropDownGrid = new JPanel();
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
		courseTerm = new JLabel();
		courseTerm.setName("courseTerm");
		labelGrid1.add(courseTermLabel);
		labelGrid1.add(courseTerm);
		
		JLabel courseEnrollmentLabel = new JLabel("Enrollment: ");
		courseEnrollment = new JLabel(); 
		courseEnrollment.setName("courseEnrollment");
		labelGrid2.add(courseEnrollmentLabel);
		labelGrid2.add(courseEnrollment);
		
		labelGridMain.add(labelGrid1);
		labelGridMain.add(new JLabel(""));
		labelGridMain.add(labelGrid2);
		
		studentInfoBoxMain = new JPanel();
		studentInfoBoxMain.setLayout(new GridLayout(1,2));
		
		studentInfoBox1 = new JPanel();
		studentInfoBox1.setLayout(new GridLayout(4,1));
		
		studentIdLabel = new JLabel("ID: ");
		studentInfoBox1.add(studentIdLabel);
		
		studentNameLabel = new JLabel("Name: ");
		studentInfoBox1.add(studentNameLabel);
		
		studentEmailLabel = new JLabel("Email: ");
		studentInfoBox1.add(studentEmailLabel);
		
		studentScoreLabel = new JLabel("Score: ");
		studentInfoBox1.add(studentScoreLabel);
		
		studentInfoBox2 = new JPanel();
		studentInfoBox2.setLayout(new GridLayout(4,1));
		studentId = new JLabel("");
		studentId.setName("studentId");
		studentInfoBox2.add(studentId);
		studentName = new JLabel("");
		studentName.setName("studentName");
		studentInfoBox2.add(studentName);
		studentEmail = new JLabel("");
		studentEmail.setName("studentEmail");
		studentInfoBox2.add(studentEmail);
		studentScore = new JLabel("");
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
				updateLabels(0);
			}
		});
		columnComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent item){
				updateLabels(1);
			}
		});
	}
	
	/*
	public void actionPerformed(ActionEvent event) {
		
		String course = (String)item.getItem();
		Assignment temp = new Assignment(course);
		DefaultComboBoxModel model = new DefaultComboBoxModel(temp.getAssignmentList());
		columnComboBox.setModel(model);
		String assignmentString = (String)item.getItem();
		
		courseTerm.setText( "Term: " + database.getCourse(course).getCourseTerm() + " " + database.getCourse(course).getCourseYear());
		courseEnrollment.setText("Enrollment: " + database.getCourse(course).getCourseSize());
		
		temp.findMaxScore(assignmentString);
		studentId.setText("ID: " + temp.getMaxScoreID());
		studentName.setText("Name: " + database.getStudent(temp.getMaxScore()).getFirstName() + " " + database.getStudent(temp.getMaxScore()).getLastName()) ;
		studentEmail.setText("Email: " + database.getStudent(temp.getMaxScore()).getStudentEmail() + "@jsu.edu");
		studentScore.setText("Score: " + temp.getMaxScore());
		
	}
	*/
	
	private void updateLabels(int type){
		if(type == 0){
			String course = (String)courseComboBox.getSelectedItem();
			Assignment temp = new Assignment(course);
			DefaultComboBoxModel model = new DefaultComboBoxModel(temp.getAssignmentList());
			columnComboBox.setModel(model);
			//courseComboBox.setSelectedIndex(0);
		}
		else if(type == 1){
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
			
			//columnComboBox.setSelectedIndex(0);
		}
	}
	
}