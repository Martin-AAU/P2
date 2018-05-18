import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.*;

public class MidiPlayer {

    // CONSTRUCTOR
    MidiPlayer(){}


    public void playMidiFile(String fileName){
        File midiFile = new File("Resources/test.mid");

        if (!midiFile.canRead()){
            System.out.println("UNABLE TO READ FILE!");
            return;
        }

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();



            InputStream is = new BufferedInputStream(new FileInputStream(new File("Resources/Star01.png")));
            sequencer.setSequence(is);
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
