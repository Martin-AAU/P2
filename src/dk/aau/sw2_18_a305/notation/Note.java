package dk.aau.sw2_18_a305.notation;

public class Note {

    private PitchClass pitchClass;
    private int octave;

    // CONSTRUCTOR
    public Note(PitchClass pitchClass) {
        this.pitchClass = pitchClass;
        this.octave = 3;
    }
    public Note(PitchClass pitchClass, int octave) {
        this.octave = octave;
        this.pitchClass = pitchClass;
    }

    // GETTER
    public PitchClass getPitchClass() {
        return pitchClass;
    }
    public int getOctave() {
        return octave;
    }
}
