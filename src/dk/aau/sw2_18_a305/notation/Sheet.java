package dk.aau.sw2_18_a305.notation;

import javax.sound.midi.*;
import java.util.LinkedList;

/**
 * Represents a note sheet. A timestamp sorted collection of {@link TimedNote}s in relation to each other.
 * Able to convert to a {@link Sequence}
 */
public class Sheet {
    // FIELDS
    /**
     * Constant used in the <code>timeDivision</code> to indicate 4th parts of note
     */
    public static int QUARTER_NOTE = 4;
    /**
     * Constant used in the <code>timeDivision</code> to indicate 8th parts of note
     */
    public static int EIGHTH_NOTE = 8;
    /**
     * Constant used in the <code>timeDivision</code> to indicate 4th parts of note
     */
    public static int SIXTEENTH_NOTE = 16;

    /**
     * Linked List of {@link TimedNote}s to represent all notes in the sheet
     */
    private LinkedList<TimedNote> notes = new LinkedList<>();

    /**
     * Represents how time in this sheet is interpreted. For example a time division of 16 means
     * time in the sheet is  interpreted as 16th parts of a note (a time of 16 would be a full note length)
     */
    private int timeDivision = EIGHTH_NOTE;
    /**
     * The time stamp of the last note in the sheet
     */
    private int totalPlaytime = 0;

    // CONTRUCTORS
    /**
     * Constructs a Sheet with standard time division
     */
    public Sheet() {}

    /**
     * Constructs a Sheet with a specific time division
     * @param timeDivision The time division of the sheet
     */
    public Sheet(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // GETTERS
    /**
     * Retrieves all the notes in the sheet
     * @return A linked list of {@link TimedNote}s
     */
    public LinkedList<TimedNote> getNotes() {
        return new LinkedList<>(notes);
    }
    /**
     * Retrieves the time division of the Sheet
     * @return An integer representing the time division of the sheet
     */
    public int getTimeDivision() {
        return timeDivision;
    }
    /**
     * Retrieves the total play time of the sheet (the timestamp of the last note)
     * @return An integer representing the timestamp of the last played note in the sheet
     */
    public int getTotalPlaytime() {
        return totalPlaytime;
    }

    // SETTERS
    /**
     * Sets the time division of the sheet
     * @param timeDivision The new time division of the sheet
     */
    public void setTimeDivision(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // METHODS
    /**
     * Adds a {@link TimedNote} to the Sheet
     * @param n The {@link TimedNote} to be added
     */
    public void addTimedNote(TimedNote n) {
        int time = n.getTimestamp();
        int latestStamp = 0;

        if (notes.size() != 0) {
            latestStamp = notes.get(notes.size() - 1).getTimestamp();
        }

        if(time >= latestStamp) {
            notes.add(n);
        }else {
            int i = 0;
            for (TimedNote note : notes) {
                if (time < note.getTimestamp()) {
                    notes.add(i, n);
                    return;
                }
                i++;
            }
        }

        this.totalPlaytime = this.totalPlaytime < time ? time: this.totalPlaytime;
    }

    /**
     * Adds a {@link Note} to the sheet as a {@link TimedNote}
     * @param n The {@link Note} to be added to the Sheet
     * @param length The length (playtime) of the note
     * @param timeStamp The time stamp of the note
     */
    public void addNote(Note n, int length, int timeStamp) {
        TimedNote timedNote = new TimedNote(n, length, timeStamp);
        addTimedNote(timedNote);
    }

    /**
     * Adds a {@link Chord} to the sheet as multiple {@link TimedNote}s
     * @param c The {@link Chord} to be added to the Sheet
     * @param length The length (playtime) of the chord
     * @param timeStamp The time stamp of the chord
     */
    public void addChord(Chord c, int length, int timeStamp) {
        for(int i = 0; i < c.getNotes().size(); i++) {
            TimedNote n = new TimedNote(c.getNotes().get(i), length, timeStamp);
            addTimedNote(n);
        }
    }

    /**
     * Converts the Sheet to a {@link Sequence}
     * @return A {@link Sequence} with a single track containing all notes in the sheet
     */
    public Sequence convertToMidiSequence() {
        try {
            Sequence sequence = new Sequence(Sequence.PPQ, timeDivision/4);
            Track track = sequence.createTrack();

            for (TimedNote note : notes) {
                // Create Midi messages
                ShortMessage messageOn = new ShortMessage();
                ShortMessage messageOff = new ShortMessage();
                messageOn.setMessage(ShortMessage.NOTE_ON, 0, note.getMidiValue(), 100);
                messageOff.setMessage(ShortMessage.NOTE_OFF, 0, note.getMidiValue(), 100);

                // Create 4 MidiEvents (class) and add the messages to them
                MidiEvent noteOn = new MidiEvent(messageOn, note.getTimestamp());
                MidiEvent noteOff = new MidiEvent(messageOff, note.getLength() + note.getTimestamp());

                track.add(noteOn);
                track.add(noteOff);
            }

            return sequence;
        } catch (InvalidMidiDataException e) {
            System.out.println("ERROR: Could not Make a sequence");
        }
        return null;
    }
}
