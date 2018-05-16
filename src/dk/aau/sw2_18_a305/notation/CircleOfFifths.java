package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

public class CircleOfFifths implements DistanceStrategy {
    // FIELDS
    private ArrayList<PitchClass> OuterRow = new ArrayList<>();
    private ArrayList<PitchClass> InnerRow = new ArrayList<>();

    // CONSTRUCTORS
    CircleOfFifths(){
        fillArrayLists();
    }

    // GETTERS
    public ArrayList<PitchClass> getOuterRow() {return OuterRow;}
    public ArrayList<PitchClass> getInnerRow() {return InnerRow;}

    // METHODS
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

    // Public method for getting the distance between two chords
    // Return of -1 is invalid
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

    // Returns the given position of a chord
    // -1 is treated as invalid input
    public int getPosition(Chord chord){
        if (chord.getChordType() == ChordType.Major && OuterRow.contains(chord.getMainPitchClass())){
            return OuterRow.indexOf(chord.getMainPitchClass());
        } else if (chord.getChordType() == ChordType.Minor && InnerRow.contains(chord.getMainPitchClass())){
            return InnerRow.indexOf(chord.getMainPitchClass());
        } else return -1;
    }
}
