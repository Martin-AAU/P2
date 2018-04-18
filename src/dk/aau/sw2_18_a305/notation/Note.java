package dk.aau.sw2_18_a305.notation;

public class Note {

    PitchClass pitchClass;
    int length;

    //Constructors
    public Note(PitchClass pitchClass) {
        this.pitchClass = pitchClass;
    }
    public Note(PitchClass pitchClass, int length) {
        this.pitchClass = pitchClass;
        this.length = length;
    }
}
