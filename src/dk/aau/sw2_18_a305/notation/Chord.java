package dk.aau.sw2_18_a305.notation;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;

public class Chord {

    // Spoken definition
    public enum ChordType {
        Major, Minor, Diminshed, Suspended2, Suspended4, Augmented;
    }

    // Program definition
    Note note1;
    Note note2;
    Note note3;

    private int interval1;
    private int interval2;
    private int length;

    public Chord(PitchClass pitchClass, ChordType chordType, int length) {
        standardChord(pitchClass, chordType, length);
    }

    private void standardChord(PitchClass pitchClass, ChordType chordType, int length) {
        this.note1 = new Note(pitchClass, length);

        int i, j;

        switch (chordType) {
            case Major: i = 4; j = 3;
            case Minor: i = 3; j = 4;
            case Diminshed: i = 3; j = 3;
            case Augmented: i = 4; j = 4;
            case Suspended2: i = 5; j = 2;
            case Suspended4: i = 2; j = 4;
            case default: i = 0; j = 0;
        }

        interval1 = i;
        interval2 = j;
    }

    private PitchClass intervalToNote(int interval) {
        switch ((note1.pitchClass.number + interval) % 12) {
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