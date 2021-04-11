// Gui3 - Circle Changes color each time you click a button!
// Fayadh Ahmed, fayadh.xyz

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui1D implements ActionListener {

	JFrame frame;

	public static void main(String[] args) {
		SimpleGui1D gui = new SimpleGui1D();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton button = new JButton("Change Color!");
		button.addActionListener(this); // add listener to (this) button

		MyDrawPanel drawPanel = new MyDrawPanel();

		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel); // add two widgets, to two regions of the frame
		frame.setSize(300, 300);
		frame.setVisible(true);
	}	

	public void actionPerformed(ActionEvent event) {
		frame.repaint();
	}
}

class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color startColor = new Color(red, green, blue);

		red = (int) (Math.random() * 255);
		green = (int) (Math.random() * 255);
		blue = (int) (Math.random() * 255);
		Color endColor = new Color(red, green, blue);

		GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100);
	}
}