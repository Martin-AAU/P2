package dk.aau.sw2_18_a305.piano;

public class Chord {
    // Talesprogsdefinition
    private enum rootNote {
        C, D, E, F, G, A, H
    }
    private enum chordType {
        Major, Minor, Diminshed, Major_Seventh, Minor_Seventh,
        Dominant_Seventh, Suspended, Augmented, Extended;
    }

    // Programdefinition
    int interval;
    int klang;
    int reversal;

    public Chord(int interval, int klang, int reversal) {
        this.interval = interval;
        this.klang = klang;
        this.reversal = reversal;
    }
}
