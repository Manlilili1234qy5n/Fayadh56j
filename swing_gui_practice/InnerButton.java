// changes text on a button whenever pressed

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class InnerButton {
	JFrame frame;
	JButton b;

	public static void main(String[] args) {
		InnerButton gui = new InnerButton();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b = new JButton("Saleh, ");
		b.addActionListener(new BListener());

		frame.getContentPane().add(BorderLayout.CENTER, b);
		frame.setSize(200, 100);
		frame.setVisible(true);
	}

	class BListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (b.getText().equals("Saleh, ")) {
				b.setText("Call me Master!");
			} else {
				b.setText("Saleh, ");
			}
		}
	}
}