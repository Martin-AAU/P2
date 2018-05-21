package dk.aau.sw2_18_a305.notation;

/**
 * A {@link Note} with length and a timestamp. Used in {@link Sheet} to represent notes in relation to time
 */
public class TimedNote extends Note {
    /**
     * Used to determine how long the note is played. The unit used depends on the {@link Sheet}s time division setting
     */
    private int length;
    /**
     * Represent when the note is played. The unit used depends on the {@link Sheet}s time division setting
     */
    private int timeStamp;

    /**
     * Constructs a TimedNote from an existing {@link Note} a length and a time stamp
     * @param note A note used to construct a TimedNote
     * @param length The length of the note (time played)
     * @param timeStamp When the note is played
     */
    public TimedNote(Note note, int length, int timeStamp) {
        super(note.getPitchClass(), note.getOctave());
        this.length = length;
        this.timeStamp = timeStamp;
    }

    /**
     * Constructs a TimedNote by giving the super class a pitch class, and setting a length and a time stamp
     * @param pitchClass Given to the {@link Note} class' constructor
     * @param length The length of the note (time played)
     * @param timeStamp When the note is played
     */
    public TimedNote(PitchClass pitchClass, int length, int timeStamp) {
        super(pitchClass);
        this.length = length;
        this.timeStamp = timeStamp;
    }

    /**
     * Constructs a TimedNote by giving the super class' constructor a pitch class and an octave, and
     * giving TimedNote a length and a time stamp
     * @param pitchClass Given to the {@link Note} class' constructor
     * @param octave Given to the {@link Note} class' constructor
     * @param length The length of the note (time played)
     * @param timeStamp When the note is played
     */
    public TimedNote(PitchClass pitchClass, int octave, int length, int timeStamp) {
        super(pitchClass, octave);
        this.length = length;
        this.timeStamp = timeStamp;
    }

    /**
     * Constructs a TimedNote by giving the super class' constructor a midi value, and giving
     * the TimedNote a length and a time stamp
     * @param midiValue Used to by the {@link Note}s constructor
     * @param length The length of the note (time played)
     * @param timeStamp When the note is played
     */
    public TimedNote(int midiValue, int length, int timeStamp) {
        super(midiValue);
        this.length = length;
        this.timeStamp = timeStamp;
    }

    /**
     * Retreives the length (playtime) of the TimedNote
     * @return the length of the TimedNote
     */
    public int getLength() {
        return length;
    }

    /**
     * Retreives the time stamp (when the note is played) of the TimedNote
     * @return The timeStamp of the TimedNote
     */
    public int getTimeStamp() {
        return timeStamp;
    }
}
