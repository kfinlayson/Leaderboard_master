package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.*;

public class Gamegogy extends JPanel implements ActionListener{
	
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
		columnComboBox = new JComboBox();
		courseComboBox.setName("courseComboBox");
		courseComboBox.addActionListener(this);
		
		columnComboBox.setName("columnComboBox");
		//columnComboBox.setSelectedIndex(0);
		columnComboBox.addActionListener(this);

		
		
		courseComboBox.setPreferredSize(new Dimension(100,65));
		columnComboBox.setPreferredSize(new Dimension(100,65));
		
		JPanel dropDownGrid = new JPanel();
		dropDownGrid.setLayout(new GridLayout(1,5));
		dropDownGrid.add(new JLabel("Course"));
		dropDownGrid.add(courseComboBox);
		dropDownGrid.add(new JLabel(""));
		dropDownGrid.add(new JLabel("Column"));
		dropDownGrid.add(columnComboBox);
		
		
		JPanel labelGridMain = new JPanel();
		labelGridMain.setLayout(new GridLayout(1,3));
		JPanel labelGrid1 = new JPanel();
		labelGrid1.setLayout(new GridLayout(1,2));
		JPanel labelGrid2 = new JPanel();
		labelGrid2.setLayout(new GridLayout(1,2));
		
		courseTerm = new JLabel();
		courseTerm.setName("courseTerm");
		labelGrid1.add(new JLabel("Term: "));
		labelGrid1.add(courseTerm);
		
		courseEnrollment = new JLabel(); 
		courseEnrollment.setName("courseEnrollment");
		labelGrid2.add(new JLabel("Enrollment: "));
		labelGrid2.add(courseEnrollment);
		
		labelGridMain.add(labelGrid1);
		labelGridMain.add(new JLabel(""));
		labelGridMain.add(labelGrid2);
		
		
		JPanel studentInfoBoxMain = new JPanel();
		studentInfoBoxMain.setLayout(new GridLayout(1,2));
		
		JPanel studentInfoBox1 = new JPanel();
		studentInfoBox1.setLayout(new GridLayout(4,1));
		studentInfoBox1.add(new JLabel("ID: "));
		studentInfoBox1.add(new JLabel("Name: "));
		studentInfoBox1.add(new JLabel("Email: "));
		studentInfoBox1.add(new JLabel("Score: "));
		
		JPanel studentInfoBox2 = new JPanel();
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
		
		courseComboBox.setSelectedIndex(0);
	
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == courseComboBox){ 
			String courseID = (String)courseComboBox.getSelectedItem();
			System.out.println("changing course combo -- " + courseID);
			Course course = database.getCourse(courseID);
			Grades temp = course.getGrades();
			DefaultComboBoxModel model = new DefaultComboBoxModel(temp.getAssignmentList());
			columnComboBox.setModel(model);
			
			temp.findMaxScore(temp.getAssignmentList()[0]);
			Student student = database.getStudent(temp.getMaxScoreID());
			
			String assignmentString = (String)columnComboBox.getSelectedItem();
			courseTerm.setText(course.getCourseTerm() + " " + course.getCourseYear());
			
			courseEnrollment.setText(course.getCourseSize());
			
			studentId.setText(student.getID());
			studentName.setText(student.getFirstName() + " " + student.getLastName()) ;
			studentEmail.setText(student.getStudentEmail() + "@jsu.edu");
			studentScore.setText(temp.getMaxScore());
			
		}
		else{
			String courseID = (String)courseComboBox.getSelectedItem();
			Course course = database.getCourse(courseID);
			Grades temp = course.getGrades();
				
			String assignmentString = (String)columnComboBox.getSelectedItem();
			courseTerm.setText(database.getCourse(courseID).getCourseTerm() + " " + database.getCourse(courseID).getCourseYear());
			courseEnrollment.setText(database.getCourse(courseID).getCourseSize());
				
			temp.findMaxScore(assignmentString);
			Student student = database.getStudent(temp.getMaxScoreID());	
			studentId.setText(student.getID());
			studentName.setText(student.getFirstName() + " " + student.getLastName()) ;
			studentEmail.setText(student.getStudentEmail() + "@jsu.edu");
			studentScore.setText(temp.getMaxScore());
		}
	}
/*	
	private void updateColumnComboBox(){
			
			//columnComboBox.setSelectedIndex(0);
	}
	private void updateLabels(){
			
		
	}
*/	
}