package dk.aau.sw2_18_a305.notation;

public class Note {

    private PitchClass pitchClass;
    private int length;
    private int octave;

    //Constructors
    public Note(PitchClass pitchClass) {
        this.pitchClass = pitchClass;
    }
    public Note(PitchClass pitchClass, int length) {
        this.pitchClass = pitchClass;
        this.length = length;
    }
    public Note(int octave, PitchClass pitchClass) {
        this.octave = octave;
        this.pitchClass = pitchClass;
    }
    public Note(int octave, PitchClass pitchClass, int length) {
        this.octave = octave;
        this.pitchClass = pitchClass;
        this.length = length;

    }

    //Getters
    public PitchClass getPitchClass() {
        return pitchClass;
    }
    public int getLength() {
        return length;
    }
    public int getOctave() {
        return octave;
    }
}
