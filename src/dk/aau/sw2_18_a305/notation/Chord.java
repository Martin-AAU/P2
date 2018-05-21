package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

/**
 * Represents a Chord that has a number of {@link Note}s
 */
public class Chord{
    // FIELDS
    /**
     * Used in the <code>distanceTo(Chord)</code> method. Per standard the assigned distance strategy is {@link CircleOfFifths}
     */
    private DistanceStrategy distanceStrategy = new CircleOfFifths();
    /**
     * A list of all notes in the chord
     */
    private ArrayList<Note> notes = new ArrayList<>();
    /**
     * A list of intervals, representing the distance between the notes in the chord
     */
    private ArrayList<Integer> intervals = new ArrayList<>();

    // CONSTRUCTORS
    /**
     * Constructs a chord with only one note. Notes can be added with the <code>addNoteToChord(Note)</code> method
     * @param rootNote The first note in the chord. Is considered the rootnote
     */
    public Chord(Note rootNote){
        this.notes.add(rootNote);
    }

    /**
     * Constructs a chord with 2 intervals. Each interval is used to assign notes to the chord
     * @param rootNote The first note in the chord. Is considered the rootnote
     * @param interval1 First interval of the chord
     * @param interval2 Second interval of the chord
     */
    public Chord(Note rootNote, int interval1, int interval2) {
        this.notes.add(rootNote);
        this.intervals.add(interval1);
        this.intervals.add(interval2);
        this.finishNotes();
    }

    /**
     * Constructs a chord with 3 intervals. Each interval is used to assign notes to the chord
     * @param rootNote The first note in the chord. Is considered the rootnote
     * @param interval1 First interval of the chord
     * @param interval2 Second interval of the chord
     * @param interval3 Third interval of the chord
     */
    public Chord(Note rootNote, int interval1, int interval2, int interval3) {
        this.notes.add(rootNote);
        this.intervals.add(interval1);
        this.intervals.add(interval2);
        this.intervals.add(interval3);
        this.finishNotes();
    }

    /**
     * Constructs a chord with 4 intervals. Each interval is used to assign notes to the chord
     * @param rootNote The first note in the chord. Is considered the rootnote
     * @param interval1 First interval of the chord
     * @param interval2 Second interval of the chord
     * @param interval3 Third interval of the chord
     * @param interval4 Fourth interval of the chord
     */
    public Chord(Note rootNote, int interval1, int interval2, int interval3, int interval4) {
        this.notes.add(rootNote);
        this.intervals.add(interval1);
        this.intervals.add(interval2);
        this.intervals.add(interval3);
        this.intervals.add(interval4);
        this.finishNotes();
    }

    /**
     * Constructs a chord with 5 intervals. Each interval is used to assign notes to the chord
     * @param rootNote The first note in the chord. Is considered the rootnote
     * @param interval1 First interval of the chord
     * @param interval2 Second interval of the chord
     * @param interval3 Third interval of the chord
     * @param interval4 Fourth interval of the chord
     * @param interval5 Fifth interval of the chord
     */
    public Chord(Note rootNote, int interval1, int interval2, int interval3, int interval4, int interval5) {
        this.notes.add(rootNote);
        this.intervals.add(interval1);
        this.intervals.add(interval2);
        this.intervals.add(interval3);
        this.intervals.add(interval4);
        this.intervals.add(interval5);
        this.finishNotes();
    }

    // GETTERS
    /**
     * Retreives the list of notes in the chord
     * @return A list of all notes in the chord
     */
    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    /**
     * Retrieves the pitch class of the first note in the chord
     * @return The pitch class of the first note in the chord
     */
    public PitchClass getMainPitchClass() {return notes.get(0).getPitchClass();}

    /**
     * Retreives the chord type by looking at all the intervals, and matching them up with the intervals of the chord types
     * in the enumeration {@link ChordType}
     * @return returns a chord type based on the intervals of the chord
     */
    // Source of intervals: https://www.edmprod.com/different-chord-types/
    public ChordType getChordType() {
        if (intervals.size() == 2 && intervals.get(0) == 4 && intervals.get(1) == 3){
            return ChordType.Major;
        } else if (intervals.size() == 2 && intervals.get(0) == 3 && intervals.get(1) == 4){
            return ChordType.Minor;
        } else if (intervals.size() == 2 && intervals.get(0) == 3 && intervals.get(1) == 3){
            return ChordType.Diminished;
        } else if (intervals.size() == 3 && intervals.get(0) == 4 && intervals.get(1) == 3 && intervals.get(2) == 7){
            return ChordType.Major7;
        } else if (intervals.size() == 3 && intervals.get(0) == 3 && intervals.get(1) == 4 && intervals.get(2) == 3){
            return ChordType.Minor7;
        } else if (intervals.size() == 3 && intervals.get(0) == 4 && intervals.get(1) == 3 && intervals.get(2) == 3){
            return ChordType.Dominant7;
        } else if (intervals.size() == 2 && intervals.get(0) == 2 && intervals.get(1) == 5){
            return ChordType.Suspended2;
        } else if (intervals.size() == 2 && intervals.get(0) == 5 && intervals.get(1) == 2){
            return ChordType.Suspended4;
        } else if (intervals.size() == 2 && intervals.get(0) == 4 && intervals.get(1) == 4){
            return ChordType.Augmented;
        } else if (intervals.size() == 4 && intervals.get(0) == 4 && intervals.get(1) == 3 && intervals.get(2) == 3 && intervals.get(3) == 4){
            return ChordType.Dominant9;
        } else if (intervals.size() == 5 && intervals.get(0) == 4 && intervals.get(1) == 3 && intervals.get(2) == 4 && intervals.get(3) == 3 && intervals.get(4) == 3){
            return ChordType.Major11;
        } else return ChordType.UNKNOWN;
    }

    // SETTERS
    public void setDistanceStrategy(DistanceStrategy distanceStrategy) {
        this.distanceStrategy = distanceStrategy;
    }

    // Adds a note to the chord
    public Chord addNoteToChord(Note n){
        this.notes.add(n);
        return this;
    }

    // METHODS
    private void finishNotes() {
        for (Integer interval : intervals)
            notes.add(setNote(interval));
    }

    private Note setNote(int interval) {
        PitchClass p;
        int index = notes.get(notes.size()-1).getPitchClass().number + interval;
        int octave = notes.get(0).getOctave() + (index/12);

        p = PitchClass.readPitchClass(index % 12);

        return new Note(p, octave);
    }

    public int distanceTo(Chord c ) {
        return distanceStrategy.distanceTo(this, c);
    }
}