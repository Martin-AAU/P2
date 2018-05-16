package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleOfFifthsTest {

    @Test
    void getPosition01() {
        CircleOfFifths tester = new CircleOfFifths();
        Chord chordC = new Chord(new Note(PitchClass.C), 4,3);
        Chord chordDs = new Chord(new Note(PitchClass.Ds), 4,3);

        Chord chordAm = new Chord(new Note(PitchClass.A), 3,4);
        Chord chordCm = new Chord(new Note(PitchClass.C), 3,4);

        Chord chord_InvalidType = new Chord(new Note(PitchClass.A), 333993, 32283);

        // Test Outer ring
        assertEquals(tester.getPosition(chordC), 0);
        assertEquals(tester.getPosition(chordDs), 9);

        // Test inner ring
        assertEquals(tester.getPosition(chordAm), 0);
        assertEquals(tester.getPosition(chordCm), 9);

        // Test invalid
        assertEquals(tester.getPosition(chord_InvalidType), -1);
    }

    @Test
    void testDistanceTo(){
        CircleOfFifths tester = new CircleOfFifths();
        Chord chordC = new Chord(new Note(PitchClass.C), 4,3);
        Chord chordCm = new Chord(new Note(PitchClass.C), 3,4);
        Chord chord_InvalidType = new Chord(new Note(PitchClass.A), 333993, 32283);

        assertEquals(tester.distanceTo(chordC, chordCm),9);
        assertEquals(tester.distanceTo(chordC, chord_InvalidType),-1);
    }
}