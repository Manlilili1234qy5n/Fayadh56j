// Version 1 - Making and using the makeEvent() Method

import javax.sound.midi.*;

public class MiniMusicPlayer1 {

	public static void main(String[] args) {
		
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			// make and open a sequencer

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			// make a sequence and add a track

			for (int i = 5; i < 81; i+= 3) {
				// make a bunch of events to make notes keep going higher
				track.add(makeEvent(144, 1, i, 100, i)); // Note ON
				track.add(makeEvent(128, 1, i, 100, i + 2)); // Note OFF
				// call new makeEvent() method to make message and event, the add the result to track
			} //end loop

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
			// this starts it
		} catch (Exception ex) {ex.printStackTrace();}
	}//close main

	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
			//make the message and the event, using method parameters 
		} catch (Exception e) {}
		return event; //return the event
	}
}//close class