// made by Fayadh Ahmed, Swing GUI practice. 

import java.awt.*;
import javax.swing.*;

// uncomment this to paint a red box. 
/*class MyDrawPanel extends JPanel { // make a subclass of JPanel, a widget, except its your own customized widget. 
	public void paintComponent(Graphics g) { // <- this is a big important graphics method, you dont call it yourself. 
		g.setColor(Color.red); //set color!
		g.fillRect(50, 70,200,200); // corods, size
	}
} */

// Uncomment this to run jpeg code. Basically paints an image, on to the JFrame. Fun Stuff. 
/*class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		Image image = new ImageIcon("1.jpg").getImage();
		g.drawImage(image, 3, 4, this);
	}
}*/

// Uncomment this to Paint a randomly coloured circle on a black background!! More fun!
/*class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		g.fillRect(0,0,this.getWidth(), this.getHeight()); //fill the center panel black
		//first two args define x,y (upper left corner) relative to panel. The other two args
		// say :Make the width of this rectangle as wide as the panel, and make the height as high. 

		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);

		Color randomColor = new Color(red, green, blue); //make a color by passing rgp values here. 
		g.setColor(randomColor);
		g.fillOval(70, 70, 100, 100); // start 70 pixels, left and top, make 100 px wide, 100px tall.

	}
}
*/

// the code here, paints an oval on the panel, with a gradient color!
/*class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // cast it so it refers to a graphics2d object

		GradientPaint gradient = new GradientPaint(70, 70, Color.blue, 150, 150, Color.orange);
											// starting point, start color, ending point, ending color. 
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100); //really means: fill oval with whatever is on the paintbrush (gradient)
	}
}*/

// this is the same as above, except it provides random colors for the start and stop!!!
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

public class SimpleGui1C {

	public static void main(String[] args) {
		SimpleGui1C gui = new SimpleGui1C(); // create a gui object
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame(); // new Jframe
		MyDrawPanel x = new MyDrawPanel(); // new drawpanel object

		frame.getContentPane().add(x); // add x to the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(1910, 1000); // size of frame! 
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
	