package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.Test;

import static dk.aau.sw2_18_a305.notation.PitchClass.C;
import static org.junit.jupiter.api.Assertions.*;

class ChordTest {

    @Test
    void getNotes01() {
        Note c = new Note(C, 4);
        Chord cChord = new Chord(c, 4, 3);
        Note presumedC = cChord.getNotes().get(0);

        assertEquals(PitchClass.C, presumedC.getPitchClass());
    }

    @Test
    void getNotes02() {
        Note c = new Note(C, 4);
        Chord cChord = new Chord(c, 4, 3);
        Note presumedE = cChord.getNotes().get(1);

        assertEquals(PitchClass.E, presumedE.getPitchClass());
    }

    @Test
    void getNotes03() {
        Note c = new Note(C, 4);
        Chord cChord = new Chord(c, 4, 3);
        Note presumedG = cChord.getNotes().get(2);

        assertEquals(PitchClass.G, presumedG.getPitchClass());
    }

    @Test
    void getLength01() {
        Note c = new Note(C, 2);
        Chord cChord2 = new Chord(c, 4, 3);
        assertEquals(2, c.getLength());
    }
}