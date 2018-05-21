package dk.aau.sw2_18_a305.notation;

import javax.sound.midi.*;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Sheet {
    // FIELDS
    private static int QUARTER_NOTE = 4;
    private static int EIGHTH_NOTE = 8;
    private static int SIXTEENTH_NOTE = 16;

    private LinkedList<TimedNote> notes = new LinkedList<>();

    private int timeDivision = QUARTER_NOTE;
    private int totalPlaytime = 0;

    // CONTRUCTORS
    public Sheet() {}
    public Sheet(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // GETTERS
    public LinkedList<TimedNote> getNotes() {
        return new LinkedList<>(notes);
    }
    public int getTimeDivision() {
        return timeDivision;
    }
    public int getTotalPlaytime() {
        return totalPlaytime;
    }

    // SETTERS
    public void setTimeDivision(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // METHODS
    public void addTimedNote(TimedNote n) {
        int time = n.getTimeStamp();
        int latestStamp = 0;

        if (notes.size() != 0) {
            latestStamp = notes.get(notes.size() - 1).getTimeStamp();
        }

        if(time >= latestStamp) {
            notes.add(n);
        }else {
            IntStream.range(0, notes.size()).filter(i -> time >= notes.get(i).getTimeStamp()).forEach(i -> {
                notes.add(i, n);
            });
        }

        this.totalPlaytime = this.totalPlaytime < time ? time: this.totalPlaytime;
    }

    public void addNote(Note n, int length, int timeStamp) {
        TimedNote timedNote = new TimedNote(n, length, timeStamp);
        addTimedNote(timedNote);
    }

    public void addChord(Chord c, int length, int timeStamp) {
        for(int i = 0; i < c.getNotes().size(); i++) {
            TimedNote n = new TimedNote(c.getNotes().get(i), length, timeStamp);
            addTimedNote(n);
        }
    }

    public Sequence convertToMidiTrack() {
        try {
            Sequence sequence = new Sequence(Sequence.PPQ, this.timeDivision/2);
            Track track = sequence.createTrack();

            for (TimedNote note : notes) {
                System.out.println( "Note added: "+note.getPitchClass() +
                                    " Length: " + note.getLength() + " Timestamp: "
                                    + note.getTimeStamp() + " Midi: " + note.getMidiValue()
                                    + " octave: "+note.getOctave());

                // Create Midi messages
                ShortMessage messageOn = new ShortMessage();
                ShortMessage messageOff = new ShortMessage();
                messageOn.setMessage(ShortMessage.NOTE_ON, 0, note.getMidiValue(), 100);
                messageOff.setMessage(ShortMessage.NOTE_OFF, 0, note.getMidiValue(), 100);

                // Create 4 MidiEvents (class) and add the messages to them
                MidiEvent noteOn = new MidiEvent(messageOn, note.getTimeStamp());
                MidiEvent noteOff = new MidiEvent(messageOff, note.getLength() + note.getTimeStamp());

                // Add the notes to the track
                track.add(noteOn);
                track.add(noteOff);
            }

            return sequence;
        } catch (InvalidMidiDataException e) {
            System.out.println("ERROR: Could not Make a sequence for some reason :c");
        }
        return null;
    }
}
