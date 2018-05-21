package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

/**
 * Implements the {@link DistanceStrategy}. Used by {@link Chord} to determine the distance between to chords on the circle of fifths
 */
public class CircleOfFifths implements DistanceStrategy {
    // FIELDS
    /**
     * Represent the Outer row of {@link PitchClass}es in the Circle of fifths
     */
    private ArrayList<PitchClass> OuterRow = new ArrayList<>();
    /**
     * Represents the Inner row of {@link PitchClass}es in the circle of fifths
     */
    private ArrayList<PitchClass> InnerRow = new ArrayList<>();

    // CONSTRUCTOR
    /**
     * Constructs a circle of fifths
     */
    public CircleOfFifths(){fillArrayLists();}

    // GETTERS
    /**
     * Retrieves the outer row of the circle of fifths
     * @return An ArrayList of {@link PitchClass}es representing the outer row of the circle of fifths
     */
    public ArrayList<PitchClass> getOuterRow() {return new ArrayList<>(OuterRow);}

    /**
     * Retrieves the inner row of the circle of fifths
     * @return An ArrayList of {@link PitchClass}es representing the inner row of the circle of fifths
     */
    public ArrayList<PitchClass> getInnerRow() {return new ArrayList<>(InnerRow);}

    // METHODS
    /**
     * Used in the constructor to fill the inner and outer row with correct {@link PitchClass}es to represent the circle of fifths
     */
    private void fillArrayLists(){
        // Clear list in case this will run multiple times
        OuterRow.clear();
        InnerRow.clear();

        // Fill out both arrays based on the circle of fiths
        // Outer row:
        OuterRow.add(PitchClass.C);      OuterRow.add(PitchClass.G);
        OuterRow.add(PitchClass.D);      OuterRow.add(PitchClass.A);
        OuterRow.add(PitchClass.E);      OuterRow.add(PitchClass.B);
        OuterRow.add(PitchClass.Fs);     OuterRow.add(PitchClass.Cs);
        OuterRow.add(PitchClass.Gs);     OuterRow.add(PitchClass.Ds);
        OuterRow.add(PitchClass.As);     OuterRow.add(PitchClass.F);
        // Inner row:
        InnerRow.add(PitchClass.A);     InnerRow.add(PitchClass.E);
        InnerRow.add(PitchClass.B);     InnerRow.add(PitchClass.Fs);
        InnerRow.add(PitchClass.Cs);    InnerRow.add(PitchClass.Gs);
        InnerRow.add(PitchClass.Ds);    InnerRow.add(PitchClass.As);
        InnerRow.add(PitchClass.F);     InnerRow.add(PitchClass.C);
        InnerRow.add(PitchClass.Gs);    InnerRow.add(PitchClass.D);
    }

    /**
     * Calculates the distance between two chords in the circle of fifths. Only works with major and minor chords
     * @param from The chord the distance is calculated from
     * @param to The chord the distance is calculated to
     * @return The number of steps between two chord in the circle of fifths. Returns -1 if the chords are invalid
     */
    @Override
    public int distanceTo(Chord from, Chord to) {
        int fromPosition, toPosition, distance;

        // Get current positions
        fromPosition = getPosition(from);
        toPosition = getPosition(to);

        // Check for validity
        if (fromPosition == -1 || toPosition == -1){
            return -1;
        }

        // Calculate distance
        distance = fromPosition - toPosition;

        // If negative then turn to positive
        if (distance < 0){
            distance = distance * -1;
        }

        return distance;
    }

    /**
     * Finds the position of a {@link Chord} on the circle of fifths. Only works with major and minor chords
     * @param chord The chord of which the position is to be found
     * @return An integer representing the position of a {@link Chord} on the circle of fifths. Returns -1 if the chord is invalid
     */
    public int getPosition(Chord chord){
        if (chord.getChordType() == ChordType.Major && OuterRow.contains(chord.getMainPitchClass())){
            return OuterRow.indexOf(chord.getMainPitchClass());
        } else if (chord.getChordType() == ChordType.Minor && InnerRow.contains(chord.getMainPitchClass())){
            return InnerRow.indexOf(chord.getMainPitchClass());
        } else return -1;
    }
}