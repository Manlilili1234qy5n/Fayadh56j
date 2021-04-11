

import java.awt.*;
import javax.swing.*;

class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(20, 50,100, 100);
	}
}

public class SimpleGui1C {

	public static void main(String[] args) {
		SimpleGui1C gui = new SimpleGui1C();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		MyDrawPanel x = new MyDrawPanel();

		frame.getContentPane().add(x);
		frame.setDefaultClostOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
	