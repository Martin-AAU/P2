package dk.aau.sw2_18_a305.notation;

import java.util.Objects;

/**
 * Represents A Note played on an instrument. Has a {@link PitchClass},
 * octave and a midiValue representing the note in midi context
 */
public class Note {
    // FIELDS
    /**
     * Represents the pitch class of the note
     */
    private PitchClass pitchClass;
    /**
     * Represents in which octave the note is in
     */
    private int octave;
    /**
     * Represents the notes value in a midi context
     */
    private int midiValue;

    // CONSTRUCTORS
    /**
     * Constructs a note by a midivalue. The octave and pitch class are assigned according to the midivalue
     * @param midiValue number representing a note in Midi context
     */
    public Note(int midiValue) {
        this.midiValue = midiValue;
        this.calPitch();
        this.calOctave();
    }

    /**
     * Constructs a note by pitch class. The octave is defaulted at 3 and the midivalue is calculated accordingly
     * @param pitchClass the {@link PitchClass} represents the pitch of a note
     */
    public Note(PitchClass pitchClass) {
        this.pitchClass = pitchClass;
        this.octave = 3;
        this.calMidiValue();
    }

    /**
     * Constructs a note by pitch class and octave. The midivalue is calculated accordingly
     * @param pitchClass the {@link PitchClass} represents the pitch of a note
     * @param octave represents which octave the note is in
     */
    public Note(PitchClass pitchClass, int octave) {
        this.octave = octave;
        this.pitchClass = pitchClass;
        this.calMidiValue();
    }

    // GETTERS
    /**
     * Retrieves the pitch class
     * @return the pitch class
     */
    public PitchClass getPitchClass() {
        return pitchClass;
    }

    /**
     * Retrieves the octave
     * @return the octave
     */
    public int getOctave() {
        return octave;
    }

    /**
     * Retrieves the midi value
     * @return the midi value
     */
    public int getMidiValue() {
        return midiValue;
    }

    // METHODS
    /**
     * Calculates and sets the pitch class from the assigned midi value
     */
    private void calPitch() {
        int index = midiValue % 12;
        this.pitchClass = PitchClass.readPitchClass(index);
    }

    /**
     * Calculates and set the octave from the assigned midi value
     */
    private void calOctave() {
        this.octave = midiValue / 12 - 1;
    }

    /**
     * Calculates and sets the midi value from an assigned pitch class and octave
     */
    private void calMidiValue() {
        midiValue = pitchClass.number + (octave+1) * 12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return octave == note.octave &&
                midiValue == note.midiValue &&
                pitchClass == note.pitchClass;
    }

    @Override
    public int hashCode() {

        return Objects.hash(pitchClass, octave, midiValue);
    }
}
