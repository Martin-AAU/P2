package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

public class CircleOfFifths implements DistanceStrategy {
    // FIELDS
    private ArrayList<String> OuterRow = new ArrayList<>();
    private ArrayList<String> InnerRow = new ArrayList<>();

    // CONSTRUCTORS
    CircleOfFifths(){
        fillArrayLists();
    }

    // GETTERS
    public ArrayList<String> getOuterRow() {return OuterRow;}
    public ArrayList<String> getInnerRow() {return InnerRow;}

    // METHODS
    private void fillArrayLists(){
        // Clear list in case this will run multiple times
        OuterRow.clear();
        InnerRow.clear();

        // Fill out both arrays based on the circle of fiths
        // Outer row:
        OuterRow.add("C");      OuterRow.add("G");
        OuterRow.add("D");      OuterRow.add("A");
        OuterRow.add("E");      OuterRow.add("H");
        OuterRow.add("F#");     OuterRow.add("Db");
        OuterRow.add("Ab");     OuterRow.add("Eb");
        OuterRow.add("Bb");     OuterRow.add("F");
        // Inner row:
        InnerRow.add("Am");     InnerRow.add("Em");
        InnerRow.add("Hm");     InnerRow.add("F#m");
        InnerRow.add("C#m");    InnerRow.add("G#m");
        InnerRow.add("Ebm");    InnerRow.add("Bbm");
        InnerRow.add("Fm");     InnerRow.add("Cm");
        InnerRow.add("G#m");    InnerRow.add("Dm");
    }

    // Public method for getting the distance between two chords
    //
    // (THIS IS THE ONE WHO SHALL BE INTEGRATED)
    //
    // Return of -1 is invalid
    @Override
    public int distanceTo(Chord from, Chord to) {
        int fromPosition, toPostion, distance;

        //
        // SOME METHOD TO TELL WHICH CHORD
        //

        // Get current positions
        fromPosition = getPosition("Cm");
        toPostion = getPosition("C");

        // Check for validity
        if (fromPosition == -1 || toPostion == -1){
            return -1;
        }

        // Calculate distance
        distance = fromPosition - toPostion;

        // If negative then turn to positive
        if (distance < 0){
            distance = distance * -1;
        }

        return distance;
    }

    // Returns the given position of a chord
    // -1 is treated as invalid input
    public int getPosition(String chord){
        if (OuterRow.contains(chord)){ return OuterRow.indexOf(chord);
        } else if (InnerRow.contains(chord)){ return InnerRow.indexOf(chord);
        } else return -1;
    }
}
