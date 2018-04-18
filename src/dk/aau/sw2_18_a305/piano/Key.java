package dk.aau.sw2_18_a305.piano;

public class Key {
    private enum Note {
        C, D, E, F, G, A, H
    }
    Note note;
    int octave;


    public Key(int octave, Note note) {
        this.octave = octave;
        this.note = note;
    }
}
