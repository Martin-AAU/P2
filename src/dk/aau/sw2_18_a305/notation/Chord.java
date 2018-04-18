package dk.aau.sw2_18_a305.notation;

public class Chord {

    // Spoken definition
    public enum ChordType {
        Major, Minor, Diminshed, Major_Seventh, Minor_Seventh,
        Dominant_Seventh, Suspended, Augmented, Extended;
    }

    // Program definition
    Note.rootNote note1;
    Note.rootNote note2;
    Note.rootNote note3;

    int interval;
    int reversal;

    public Chord(Note key, ChordType type) {
        this.interval = interval;
        this.reversal = reversal;
    }
}