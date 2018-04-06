import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class CreateMidi{

    public static void main(String[] args) throws InvalidMidiDataException, IOException {

        // Create a Sequence (class)
        Sequence sequence = new Sequence(Sequence.PPQ, 4);

        // Add a Track in the Sequence class
        Track track = sequence.createTrack();

        //Create 4 ShortMessages (Two notes)
        ShortMessage cNoteOn = new ShortMessage();
        ShortMessage cNoteOff = new ShortMessage();
        ShortMessage dNoteOn = new ShortMessage();
        ShortMessage dNoteOff = new ShortMessage();

        // Define the 4 shortmessages as noteOn and noteOff
        cNoteOn.setMessage(ShortMessage.NOTE_ON, 0, 60, 100);
        cNoteOff.setMessage(ShortMessage.NOTE_OFF, 0, 60, 100);

        dNoteOn.setMessage(ShortMessage.NOTE_ON, 0, 62, 100);
        dNoteOff.setMessage(ShortMessage.NOTE_OFF, 0, 62, 100);

        // Create 4 MidiEvents (class) and add the messages to them
        MidiEvent noteOnC = new MidiEvent(cNoteOn, 0);
        MidiEvent noteOffC = new MidiEvent(cNoteOff, 16);
        MidiEvent noteOnD = new MidiEvent(dNoteOn, 8);
        MidiEvent noteOffD = new MidiEvent(dNoteOff, 24);

        // Add MidiEvents to the Track
        track.add(noteOnC);
        track.add(noteOffC);
        track.add(noteOnD);
        track.add(noteOffD);

        //Create a new (or overwrite the) Midi File
        File midifil = new File("midifil.mid");

        // Get the midi file type, and write the sequence to the Midi file
        int[] type = MidiSystem.getMidiFileTypes(sequence);

        MidiSystem.write(sequence, type[0], midifil);

    }
}