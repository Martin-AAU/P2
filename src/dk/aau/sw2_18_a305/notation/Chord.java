package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;


public class Chord{

    private DistanceStrategy distanceStrategy = new CircleOfFifths();
    private ArrayList<Note> notes = new ArrayList<>();
    private int length;
    private ArrayList<Integer> intervals = new ArrayList<>();

    // CONSTRUCTORS
    public Chord(Note rootNote, int interval1, int interval2) {
        notes.add(rootNote);
        intervals.add(interval1);
        intervals.add(interval2);
        finishNotes();
    }
    public Chord(Note rootNote, int interval1, int interval2, int interval3) {
        notes.add(rootNote);
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);
        finishNotes();
    }
    public Chord(Note rootNote, int interval1, int interval2, int interval3, int interval4) {
        notes.add(rootNote);
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);
        intervals.add(interval4);
        finishNotes();
    }
    public Chord(Note rootNote, int interval1, int interval2, int interval3, int interval4, int interval5) {
        notes.add(rootNote);
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);
        intervals.add(interval4);
        intervals.add(interval5);
        finishNotes();
    }

    // GETTERS
    public ArrayList<Note> getNotes() {
        return notes;
    }
    public int getLength() {
        return length;
    }

    // SETTERS
    public void setDistanceStrategy(DistanceStrategy distanceStrategy) {
        this.distanceStrategy = distanceStrategy;
    }

    // METHODS
    private void finishNotes() {
        for (Integer interval : intervals) {
            notes.add(setNote(interval));
        }
    }

    private Note setNote(int interval) {

        PitchClass p = null;
        int index = notes.get(notes.size()-1).getPitchClass().number + interval;
        int octave = notes.get(0).getOctave() + (index/12);

        switch ((index % 12) + 1) {
            case 1: p = C; break;
            case 2: p = Cs; break;
            case 3: p = D;  break;
            case 4: p = Ds; break;
            case 5: p = E;  break;
            case 6: p = F;  break;
            case 7: p = Fs; break;
            case 8: p = G;  break;
            case 9: p = Gs; break;
            case 10: p = A; break;
            case 11: p = As;break;
            case 12: p = B; break;
            default: p = C;
        }

        return new Note(p, octave);
    }

    public int distanceTo(Chord c ) {
        return distanceStrategy.distanceto(this, c);

    }
}

/*
* //Generates 3 notes of a standard chord, and adds them to the notes list.
    private void standardChord(PitchClass pitchClass, ChordType chordType, int length) {
        notes.add(new Note(pitchClass, length));

        int i, j;

        //Determines the two intervals of the given chord type
        switch (chordType) {
            case Major: i = 4; j = 3;
            case Minor: i = 3; j = 4;
            case Diminshed: i = 3; j = 3;
            case Augmented: i = 4; j = 4;
            case Suspended2: i = 5; j = 2;
            case Suspended4: i = 2; j = 4;
            default: i = 0; j = 0;
        }

        notes.add(new Note(intervalToNote(i), length));
        notes.add(new Note(intervalToNote(j), length));
    }

    //Returns a pitchClass depending on what
    private PitchClass intervalToNote(int interval) {
        switch ((notes.get(0).getPitchClass().number + interval) % 12) {
            case 1: return C;
            case 2: return Cs;
            case 3: return D;
            case 4: return Ds;
            case 5: return E;
            case 6: return F;
            case 7: return Fs;
            case 8: return G;
            case 9: return Gs;
            case 10: return A;
            case 11: return As;
            case 12: return B;
        }
        return C;
    }
* */