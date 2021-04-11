// my first gui - button on a frame
import javax.swing.*; //import swing 

public class SimpleGui {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame(); // make a frame
		JButton button = new JButton("Matthew is a Nigger!"); // add a button

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes program quit when you hit close
	
		frame.getContentPane().add(button); // add button to frame content pane

		frame.setSize(300, 300); // give the frame a size in pixels

		frame.setVisible(true); //	make it visible!
	}
}