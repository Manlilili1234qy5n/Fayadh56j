// Basic chat server
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// imports for the stream, socket and gui stuff

public class SimpleChatClientA {
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;

	public void go() {
		// building of the gui
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel mainPanel = new JPanel();
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send!");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		setUpNetowrking();
		frame.setSize(400, 500);
		frame.setVisible(true);
	}

	// setupnetoworking, using localhost right now, but I want to try it on the internet!
	private void setUpNetowrking() {
		try {
			sock = new Socket ("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Network Connection Established Successfully");
			//this is where we make the socket and the printwriter its called right before displaying the gui 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(outgoing.getText());
				writer.flush(); //flushed directly
				// writing is done, the writer is chained to the inputstream from socket, so when we do println it
				// goes directly to the server. 
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus(); //what does this do again?
		}
	}

	public static void main(String[] args) {
		new SimpleChatClientA().go();
	}
}