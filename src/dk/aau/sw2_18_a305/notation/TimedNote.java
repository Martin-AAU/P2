package dk.aau.sw2_18_a305.notation;

public class TimedNote extends Note {
    private int length, timeStamp;

    // CONSTRUCTORS
    public TimedNote(Note note, int length, int timeStamp) {
        super(note.getPitchClass(), note.getOctave());
        this.length = length;
        this.timeStamp = timeStamp;
    }
    public TimedNote(PitchClass pitchClass, int length, int timeStamp) {
        super(pitchClass);
        this.length = length;
        this.timeStamp = timeStamp;
    }
    public TimedNote(PitchClass pitchClass, int octave, int length, int timeStamp) {
        super(pitchClass, octave);
        this.length = length;
        this.timeStamp = timeStamp;
    }

    // GETTERS
    public int getLength() {
        return length;
    }
    public int getTimeStamp() {
        return timeStamp;
    }
}
