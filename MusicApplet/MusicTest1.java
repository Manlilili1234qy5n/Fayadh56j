// Chapter 11

import javax.sound.midi.*;
// OHOO this code doesn't compile?! Unreported Exception! That must be caught or declared. 
public class MusicTest1 {

	public void play() {

		// writing an exception here to solve the problem. I'll leave the oldcode for future references. 
		/*Sequencer sequencer = MidiSystem.getSequencer(); //ask Midi system to give us a sequencer. 
		System.out.println("We got a sequencer!");*/

		// write a try catch for the exception. 
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			System.out.println("Successfully got a Sequencer!");	// put the risky thing in the try block. 
		} catch (MidiUnavailableException ex) {
			System.out.println("Bummer, it blew up.");
			} // make a catch block, for what to do if the exceptional stich happens, in other words: MiidUnE is thrown by the getSequencer call. 
	} //close play									   
		

	public static void main(String[] args) {
		MusicTest1 mt = new MusicTest1();
		mt.play();
	} //endmain
}//endclass