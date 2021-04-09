// sound player app. 
// Fayadh Ahmed. Creating a single note!! 
import javax.sound.midi.*; //import midi package. 

public class MiniMiniMusicApp {

	public static void main(String[] args) {
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		mini.play();
	} //endmain

	public void play() {

		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open(); // get sequencer and open it, so we can use it. Doesn't come already opened. 

			Sequence seq = new Sequence(Sequence.PPQ, 4); // readybake args for the sequence constructor. 

			Track track = seq.createTrack(); //ask the sequence for a track. MiDi data lives on the track. 

			/* a Midi event is instruction for part of a song, MidiEvent is a combination of the Message, 
			plus the moment in time when that message should fire. So we always need a message and midi event.
			the moment in time when that message should fire. So we always need a message and midi event,
			message says what to do, MiDi event says when to do it.*/

			ShortMessage a = new ShortMessage(); 
			ShortMessage c = new ShortMessage();
			ShortMessage e = new ShortMessage(); 
			ShortMessage first = new ShortMessage();// make a message
			first.setMessage(192, 1, 102, 0);
			a.setMessage(144, 1, 66, 110);
			c.setMessage(144, 1, 20, 110); 
			e.setMessage(144, 2, 100, 110);// this message says, start playing note 44. 
			MidiEvent noteOn = new MidiEvent(a, 1); 
			MidiEvent noteOn1 = new MidiEvent(c, 31);
			MidiEvent noteOn2 = new MidiEvent(e, 3);// adds moment in time, this says to trigger message
			// a after first beat. 
			track.add(noteOn);
			track.add(noteOn1);
			track.add(noteOn2); 
			track.add(noteOn); //track holds all the MidiEvent objects, start playing at NoteOn, stop at NoteOff. 

			/* More info on making a MiDi Message. To make one, make a ShortMessage instance, and invoke 
			setMessage(), passing in the four arguments. 
			What are the 4 arguments you ask? The first arg is the type; 144: means NoteOn, 126 means NoteOff. 
			Next channel, note to play, velocity. 
			Channel: Think of this as musician in a band, Channel 1 is muscican 1(keybaord), Channel 9 
			is the drummer etc... 
			Note to Play: A number from 0-127 going from low to high notes. 
			Velocity: How fast and Hard did you press the key. 0 is so soft you probably wont hear anything, 
			100 is a good default. */

			ShortMessage b = new ShortMessage();
			ShortMessage d = new ShortMessage();
			ShortMessage f = new ShortMessage();
			b.setMessage(128, 1, 90, 110);
			c.setMessage(128, 1, 20, 110);
			e.setMessage(128, 2, 100, 110);
			MidiEvent noteOff = new MidiEvent(b, 30);
			MidiEvent noteOff1 = new MidiEvent(d, 20);
			MidiEvent noteOff2 = new MidiEvent(f, 10);
			track.add(noteOff);
			track.add(noteOff1);
			track.add(noteOff2);
			track.add(noteOff);
			player.setSequence(seq); // give seq to the sequencer

			player.start(); // push play
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

