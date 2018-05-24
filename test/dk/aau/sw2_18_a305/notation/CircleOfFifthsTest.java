package dk.aau.sw2_18_a305.notation;

import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleOfFifthsTest {

    CircleOfFifths circleOfFifths = null;

    @BeforeEach
    void setup() {
        circleOfFifths = new CircleOfFifths();
    }

    @Test
    void getPositionTest01() {
        Chord cMajor = new Chord(new Note(PitchClass.C), 4,3);

        assertEquals(circleOfFifths.getPosition(cMajor), 0);
    }

    @Test
    void getPositionTest02() {
        Chord dsMajor = new Chord(new Note(PitchClass.Ds), 4,3);

        assertEquals(circleOfFifths.getPosition(dsMajor), 9);
    }

    @Test
    void getPositionTest03() {
        Chord aMinor = new Chord(new Note(PitchClass.A), 3,4);

        assertEquals(circleOfFifths.getPosition(aMinor), 0);
    }

    @Test
    void getPositionTest04() {
        Chord cMinor = new Chord(new Note(PitchClass.C), 3,4);

        assertEquals(circleOfFifths.getPosition(cMinor), 9);
    }

    @Test
    void getPositionTest05() {
        Chord chord_InvalidType = new Chord(new Note(PitchClass.A), 333993, 32283);

        assertEquals(circleOfFifths.getPosition(chord_InvalidType), -1);
    }

    @Test
    void distanceToTest01() {
        Chord cMajor = new Chord(new Note(PitchClass.C), 4,3);
        Chord cMinor = new Chord(new Note(PitchClass.C), 3,4);

        assertEquals(9, circleOfFifths.distanceTo(cMajor, cMinor));
    }

    @Test
    void distanceToTest02() {
        Chord cMajor = new Chord(new Note(PitchClass.C), 4,3);
        Chord chord_InvalidType = new Chord(new Note(PitchClass.A), 333993, 32283);

        assertEquals(-1, circleOfFifths.distanceTo(cMajor, chord_InvalidType));
    }
}