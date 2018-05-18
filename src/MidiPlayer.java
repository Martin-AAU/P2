import javax.sound.midi.*;
import java.io.*;

public class MidiPlayer {

    // CONSTRUCTOR
    MidiPlayer(){}


    public void playMidiFile(String fileName){
        String path = "AwesomeSong.mid";
        File midiFile = new File(path);

        if (!midiFile.canRead()){
            System.out.println("UNABLE TO READ FILE!");
            return;
        }

        try{
            Sequence sequence = MidiSystem.getSequence(new File(path));

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
