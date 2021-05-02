// LOL. This sublime text is beautiful. 
// Quiz card builder 
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardBuilder {

	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;

	public static void main(String[] args) {
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
	}

	public void go() {
		//build gui
		frame = new JFrame("Quiz Card Builder!");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sansserif", Font.BOLD, 24); //change font

		question = new JTextArea(6, 20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);

		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);

		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton nextButton = new JButton("Next Card");

		cardList = new ArrayList<QuizCard>();

		JLabel qLabel = new JLabel("Question");
		JLabel aLabel = new JLabel("Answer");

		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar = new JMenuBar(); //this is new
		JMenu fileMenu = new JMenu("File"); //also new stuff?
		JMenuItem newMenuItem = new JMenuItem("New"); //also new
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		// we make a new menu bar, make a file menu, then put new,and save items into the file menu
		// we add the menu to the menu bar, then tell the frame to use this menu bar, 
		// menu items can fire an actionEvent listener thingy
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
		//thats the end of all the gui stuff
	}

	public class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}

	public class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);

			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
			// Brings up a file dialong, and waits on this line until user chooses 'Save', all navigation etc is done for
			// you by the JFileChooser!! Really easy. 
		}
	}

	public class NewMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			cardList.clear();
			clearCard();
		}
	}

	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}

	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			// chain a buffered writer on to a new FileWriter, to make writing more efficient

			for(QuizCard card:cardList) {
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				// walk through the ArrayList of cards and write them out, one card per line, with the question
				// and answer seperated by a / , and then a new line character. 
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println("Couldn't write the cardlist out!");
			ex.printStackTrace();
		}
	}
}