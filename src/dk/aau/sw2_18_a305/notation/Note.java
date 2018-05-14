package dk.aau.sw2_18_a305.notation;

public class Note {

    private PitchClass pitchClass;
    private int octave;
    private int midiValue;

    // CONSTRUCTOR
    public Note(int midiValue) {
        this.midiValue = midiValue;
        calPitch();
        calOctave();
    }
    public Note(PitchClass pitchClass) {
        this.pitchClass = pitchClass;
        this.octave = 3;
        calMidiValue();
    }
    public Note(PitchClass pitchClass, int octave) {
        this.octave = octave;
        this.pitchClass = pitchClass;
        calMidiValue();
    }

    // GETTER
    public PitchClass getPitchClass() {
        return pitchClass;
    }
    public int getOctave() {
        return octave;
    }

    // Methods
    private void calPitch() {
        int index = midiValue % 12;
        this.pitchClass = PitchClass.readPitchClass(index);
    }
    private void calOctave() {
        this.octave = midiValue / 12 - 1;
    }
    private void calMidiValue() {
        midiValue = pitchClass.number + octave+1 * 12;
    }
}
