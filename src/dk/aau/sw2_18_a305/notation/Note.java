package dk.aau.sw2_18_a305.notation;

public class Note {

    // "s" for Sharp
    public enum rootNote {
        C, Cs, D, Ds, E, F, Fs, G, Gs, A, As, B
    }
    rootNote rootnote;
    int octave;

    public Note(int tempOctave, rootNote tempNote) {
        this.octave = octave;
        this.rootnote = tempNote;
    }
}
