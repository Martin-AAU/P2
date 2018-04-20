package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;


public class Chord{

    private DistanceMethod distanceMethod = new CircleOfFifths();
    private ArrayList<Note> notes = new ArrayList<>();
    private int length;

    //CONSTRUCTORS
    public Chord(PitchClass rootNote, ChordType chordType, int length) {
        //This constructor generates a chord by a rootnote (PitchClass) and a chordtype
        standardChord(rootNote, chordType, length);
    }
    public Chord(PitchClass root, PitchClass second, PitchClass third, int length) {
        notes.add(new Note(root, length));
        notes.add(new Note(second, length));
        notes.add(new Note(third, length));
        this.length = length;
    }
    public Chord(PitchClass root, PitchClass second, PitchClass third, PitchClass fifth, int length) {
        notes.add(new Note(root, length));
        notes.add(new Note(second, length));
        notes.add(new Note(third, length));
        notes.add(new Note(fifth, length));
        this.length = length;
    }
    public Chord(PitchClass root, PitchClass second, PitchClass third, PitchClass fifth, PitchClass sixth, int length) {
        notes.add(new Note(root, length));
        notes.add(new Note(second, length));
        notes.add(new Note(third, length));
        notes.add(new Note(fifth, length));
        notes.add(new Note(sixth, length));
        this.length = length;
    }
    public Chord(PitchClass root, PitchClass second, PitchClass third, PitchClass fifth, PitchClass sixth, PitchClass seventh, int length) {
        notes.add(new Note(root, length));
        notes.add(new Note(second, length));
        notes.add(new Note(third, length));
        notes.add(new Note(fifth, length));
        notes.add(new Note(sixth, length));
        notes.add(new Note(seventh, length));
        this.length = length;
    }

    //GETTERS
    public ArrayList<Note> getNotes() {
        return notes;
    }
    public int getLength() {
        return length;
    }

    //SETTERS
    public void setDistanceMethod(DistanceMethod distanceMethod) {
        this.distanceMethod = distanceMethod;
    }

    //Methods
    //Generates 3 notes of a standard chord, and adds them to the notes list.
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
}