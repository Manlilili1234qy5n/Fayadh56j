// Version two. Registering and getting controller events

import javax.sound.midi.*;

public class MiniMusicPlayer2 implements ControllerEventListener { //to listen for controller events

	public static void main(String[] args) {
		MiniMusicPlayer2 mini = new MiniMusicPlayer2();
		mini.go();
	}

	public void go() {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			// make and open a sequencer

			int[] eventsIWant = {127};
			sequencer.addControllerEventListener(this, eventsIWant);
			// register for events with sequencer, takes listener and an int array reperesing the list you want. 
			// we only want event #127

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			// make a sequence and add a track

			for (int i = 10; i < 90; i+= 2) {
				// make a bunch of events to make notes keep going higher
				track.add(makeEvent(144, 2, i, 100, i)); // Note ON
				track.add(makeEvent(176, 1, 127, 100, i)); // how we pick up the beat
				track.add(makeEvent(128, 2, i, 100, i + 2)); // Note OFF
				// call new makeEvent() method to make message and event, the add the result to track
			} //end loop

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
			// this starts it
		} catch (Exception ex) {ex.printStackTrace();}
	} //close go

	public void controlChange(ShortMessage event) {
		System.out.println("la");
	}
	// event handler method, each time we get an event we print la

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
			//make the message and the event, using method parameters 
		} catch (Exception e) {}
		return event; //return the event
	}
}