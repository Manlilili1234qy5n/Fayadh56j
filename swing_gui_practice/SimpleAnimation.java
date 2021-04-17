// simple animation code - Fayadh A. 

import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {
	int x = 70;
	int y = 70; 
	// make two instance variables, for x and y co-ords of circle

	public static void main(String[] args) {
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyDrawPanel drawPanel = new MyDrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		// nothing new here, make widgets, and put them in frame

		for (int i = 0; i < 130; i++) {
			x++;
			y++;
			drawPanel.repaint(); //tell panel to repaint

			try {
				Thread.sleep(50); //slow it down - threads aren't covered yet.
			} catch(Exception ex) { }
		} 
	} // close go method

	class MyDrawPanel extends JPanel { // now an inner class
		public void paintComponent(Graphics g) {
			g.setColor(Color.white); //the fix!!! to the smearing problem mentioned below. 
			g.fillRect(0,0,this.getWidth(), this.getHeight()); // methods inherited from jpanel

			g.setColor(Color.red);
			g.fillOval(x,y,40,40); // use updated x,y from outerclass

		}
	} //close inner class
} //close outer class

// oh oh this didn't move, it smeared. What did we do wrong? We forgot to erase what was already there!
// so we got trails
// the fix: fill in the entire panel with the background color, each time the loop runs
