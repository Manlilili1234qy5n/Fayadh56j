//BeatBox!

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox {

	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList; //checkboxes stored in array list
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;

	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", 
		"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo",
		"Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", 
		"Low-Mid Tom", "High Agogo", "Open Hi Conga"};
	// names of the instruments, as a string array for GUI labels

	int [] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	// different drum keys 

	public static void main(String[] args) {
		new BeatBox().buildGUI();
	}

	public void buildGUI() {
		theFrame = new JFrame("Fayadh's Epic BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// this gives us a margin between edges of panel and where the components are placed. 

		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		JButton upTempo = new JButton("Up Tempo");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		JButton downTempo = new JButton("Down Tempo");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		/*JButton saveFile = new JButton("Save");
		saveFile.addActionListener(new MySendListener());
		buttonBox.add(saveFile);*/

		/*JButton loadFile = new JButton("Load");
		loadFile.addActionListener(new MyReadInListener());
		buttonBox.add(loadFile);*/

		// loading file stuff
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load Beat");
		loadMenuItem.addActionListener(new OpenMenuListener());
		fileMenu.add(loadMenuItem);
		

		//saving file stuff
		JMenuItem saveMenuItem = new JMenuItem("Save Beat");
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		theFrame.setJMenuBar(menuBar);

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);

		theFrame.getContentPane().add(background);

		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
			// make the checkboxes, set to false, add them to arraylist, and gui panel
		}

		setUpMidi();

		theFrame.setBounds(50, 59, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	}

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {e.printStackTrace();}
	}

	public void buildTrackAndStart() {
		int [] trackList = null;
		// 16 element array to hold values of one instrument, if instrument
		// is supposed to play on that beat, value of element will be the key
		// else itll be zero. 

		sequence.deleteTrack(track);
		track = sequence.createTrack();
		// get rid of old track, make new one

		for (int i = 0; i < 16; i++) {
			trackList = new int[16];

			int key = instruments[i];
			// set key, which instrument it is

			for (int j = 0; j < 16; j++) {
				// do for each of the beats of this row
				JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
				if (jc.isSelected()) {
					trackList[j] = key;	//is the checkbox selected?
				} else {
					trackList[j] = 0;
				}
			}

			makeTracks(trackList);
			track.add(makeEvent(176, 1, 127, 0, 16));
			// for this instrument, and for all 16 beats, make events and add to track
		}

		track.add(makeEvent(192, 9, 1, 0, 15));
		// always make sure there is an event at beat 16
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			//specify number of loop iterations
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {e.printStackTrace();}
	} // close method

	// Several Inner Classes Follow now, for the Button Listeners

	public class MyStartListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			buildTrackAndStart();
		}
	}

	public class MyStopListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	}

	public class MyUpTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * .97));
		}
	}

	// tempo factor scales the sequencers tempo by the factor provided, default 
	// is 1.0, we're adjusting it by +/- 3% per click

	public class MyDownTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * .97));
		}
	}

	// below method makes events for one instrument at a time, for all 
	// 16 beats, might get an int[] for Bass drum, each index in the array
	// will hold either key for instrument or zero. If its zero doesnt play
	// anything, else make an event and add it to the track

	public void makeTracks (int[] list) {
		for (int i = 0; i < 16; i++) {
			int key = list[i];
			if (key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i+1));
				// make note ON and note OFF events add to track
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {e.printStackTrace();}
		return event;
	}

	// Chapter 14 - More stuff added below here, mainly a way to save, and load files (serialization used)

	// serializing here
	private void saveFile (File file) {
			boolean[] checkboxState = new boolean[256]; // make a boolean array to hold the state 

			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if (check.isSelected()) {
					checkboxState[i] = true;
				}
			}
			try {
				FileOutputStream fileStream = new FileOutputStream(new File("save.ser"));
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(checkboxState);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}

	// deserializing here
	private void loadFile(File file) {
			boolean[] checkboxState = null;
			try {
				FileInputStream fileIn = new FileInputStream(new File("save.ser"));
				ObjectInputStream is = new ObjectInputStream(fileIn);
				checkboxState = (boolean[]) is.readObject();
				// read the single object, in the file and cast it back to a boolean array
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if (checkboxState[i]) {
					check.setSelected(true);
				} else {
					check.setSelected(false);
				}
			}
			sequencer.stop();
			buildTrackAndStart();
			// stop whatever is currently playing, and rebuild the sequence using the new state of the checkboxes
			// in the ArrayList
	}

	// here is the listener for loading - relays back to the loadFile function
	public class OpenMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(theFrame);
			loadFile(fileOpen.getSelectedFile());
		}
	}

	// listener for saving, relays back to saveFile Fuction above
	public class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(theFrame);
			saveFile(fileSave.getSelectedFile());
		}
	}
} // close class!
