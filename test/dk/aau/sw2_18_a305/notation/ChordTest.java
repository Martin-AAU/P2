package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;
import static dk.aau.sw2_18_a305.notation.PitchClass.C;
import static org.junit.jupiter.api.Assertions.*;

class ChordTest {

    Chord chord = null;

    @BeforeEach
    void setup() {
        Note c = new Note(C, 4);
        chord = new Chord(c, 4, 3);
    }

    @Test
    void getNotes01() {
        Note c = new Note(C, 4);

        assertEquals(c, chord.getNotes().get(0));
    }

    @Test
    void getNotes02() {
        Note e = new Note(E, 4);

        assertEquals(e, chord.getNotes().get(1));
    }

    @Test
    void getNotes03() {
        Note g = new Note(G, 4);

        assertEquals(g, chord.getNotes().get(2));
    }
}