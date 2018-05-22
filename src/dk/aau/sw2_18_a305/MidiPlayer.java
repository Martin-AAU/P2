package dk.aau.sw2_18_a305;

import javax.sound.midi.*;
import java.io.*;

/**
 * Class that can live play a midi file, while program is running
 */
public final class MidiPlayer {

    /**
     * Plays a .mid file live when called. Prints error message if file was unreadable
     * @param fileName The path and filename of the midi file to be played.
     */
    public static void playMidiFile(String fileName){
        File midiFile = new File(fileName);

        if (!midiFile.canRead()){
            System.out.println("ERROR: UNABLE TO READ FILE");
            return;
        }

        // Get the sequencer, set the song and play it
        try{
            Sequence sequence = MidiSystem.getSequence(midiFile);

            // Create a sequencer, open it & set the song to play
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
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
