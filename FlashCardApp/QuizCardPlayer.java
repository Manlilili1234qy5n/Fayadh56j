import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer {
  private JTextArea display;
  private JTextArea answer;
  private ArrayList<QuizCard> cardList;
  private QuizCard currentCard;
  private int currentCardIndex;
  private JFrame frame;
  private JButton nextButton;
  private boolean isShowAnswer;

  public static void main(String[] args) {
    QuizCardPlayer reader = new QuizCardPlayer();
    reader.go();
  }

  public void go() {
    // build the gui

    frame = new JFrame("Quiz Card Reader");
    JPanel mainPanel = new JPanel();
    Font bigFont = new Font("sansserif", Font.BOLD, 24);

    display = new JTextArea(10, 20);
    display.setFont(bigFont);

    display.setLineWrap(true);
    display.setEditable(false);

    JScrollPane qScroller = new JScrollPane(display);
    qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    nextButton = new JButton("Show Question");
    mainPanel.add(qScroller);
    mainPanel.add(nextButton);
    nextButton.addActionListener(new NextCardListener());

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
    loadMenuItem.addActionListener(new OpenMenuListener());
    fileMenu.add(loadMenuItem);
    menuBar.add(fileMenu);
    frame.setJMenuBar(menuBar);
    frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    frame.setSize(640, 500);
    frame.setVisible(true);
  } // close go

  public class NextCardListener implements ActionListener {
    public void actionPerformed(ActionEvent ev) {
      if (isShowAnswer) {
        // show answeer because they've seen the question
        display.setText(currentCard.getAnswer());
        nextButton.setText("Next Card");
        isShowAnswer = false;
      } else {
        // show the next question
        if (currentCardIndex < cardList.size()) {
          showNextCard();
        } else {
          // there are no more cards!
          display.setText("That was the last card!");
          nextButton.setEnabled(false);
        }
      }
    }
  }

  // check the isAnswer booleanflag to see if they're currently viewing a question or an answer, and do 
  // the appropriate thing, accordingly. 

  public class OpenMenuListener implements ActionListener {
    public void actionPerformed(ActionEvent ev) {
      JFileChooser fileOpen = new JFileChooser();
      fileOpen.showOpenDialog(frame);
      loadFile(fileOpen.getSelectedFile());
      // bring up the file dialog, and let them navigate to and choose the file to open
    }
  }

  private void loadFile(File file) {
    cardList = new ArrayList<QuizCard>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = reader.readLine()) != null) {
        makeCard(line); 
        // make a buffered reader chained to a new file reader, giving the file reader the file object
        // the user choose from the open file dialog, read a line at a time passing the line to the make
        // card() method that parses it, and turns it into a real quiz card and adds it to ArrayList
      }
      reader.close();
    } catch (Exception ex) {
      System.out.println("Couldn't read the card file");
      ex.printStackTrace();
    }
    // now time to start by showing the first card
    showNextCard();
  }

  private void makeCard(String lineToParse) {
    String[] result = lineToParse.split("/");
    // each line of text corresponds, to a single flashcard, but we have to parse out the question 
    // and answer as seperate pieces, we use the String split() method to break the line into two
    // tokens,  one for q, and one for a, well look at the split method later. 
    QuizCard card = new QuizCard(result[0], result[1]);
    cardList.add(card);
    System.out.println("Make a Card");
  }

  private void showNextCard() {
    currentCard = cardList.get(currentCardIndex);
    currentCardIndex++;
    display.setText(currentCard.getQuestion());
    nextButton.setText("Show Answer");
    isShowAnswer = true;
  }
}