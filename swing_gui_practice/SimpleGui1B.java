// Getting a buttons Action Event
// Fayadh Ahmed, Head First Java Practice. 
// 1) Implement the ActionListener Interface
// 2) Register with the button (tell it you want to listen for its events.)
// 3) Define the event handling method, implement the actionPerformed() from the ActionListener interface. 

import javax.swing.*;
import java.awt.event.*; // import for the ActionListener interface and actionPerformed method. 

public class SimpleGui1B implements ActionListener { //implement the interface
	JButton button;

	public static void main(String[] args) {
		SimpleGui1B gui = new SimpleGui1B();
		gui.go(); 
	}

	public void go() {
		JFrame frame = new JFrame(); //create a new Jframe
		button = new JButton("Click me!"); // assign button reference to a new JButton instance

		button.addActionListener(this); // tells button you're interested, argument you pass MUST be an object from class that implements ActionListener. 

		frame.getContentPane().add(button); // add button to pane
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits
		frame.setSize(300,300);	// set size
		frame.setVisible(true);	// make visible
	} //end go

	public void actionPerformed(ActionEvent event) { // ActionEvent is the actual ActionListener interfaces actionPerformed() method. 
		button.setText("Shumail's Fucked."); // the button calls this method to let you know an event happened, 
	}										   // it also sends you ActionEvent object, we dont need it here. Knowing event happened is enough. 
}