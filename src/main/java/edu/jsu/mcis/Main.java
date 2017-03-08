package edu.jsu.mcis;

import javax.swing.*;

public class Main {
	public static void main(String[] args){
		JFrame win = new JFrame("Gamegogy");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.add(new Gamegogy());
		win.setSize(600, 600);
		win.setVisible(true);
	}
}