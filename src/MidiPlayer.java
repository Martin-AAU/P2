import javax.sound.midi.*;
import java.io.*;

public class MidiPlayer {
    // CONSTRUCTOR
    MidiPlayer(){}

    public void playMidiFile(String fileName){
        File midiFile = new File(fileName);

        if (!midiFile.canRead()){
            System.out.println("UNABLE TO READ FILE!");
            return;
        }

        // Get the sequencer, set the song and play it
        try{
            Sequence sequence = MidiSystem.getSequence(midiFile);

            // Create a sequencer for the sequence
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);

            // Start playing
            sequencer.start();
        } catch(MidiUnavailableException mue) {
            System.out.println("Midi device unavailable!");
        } catch(InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        } catch(IOException ioe) {
            System.out.println("I/O Error!");
        }
    }
}
