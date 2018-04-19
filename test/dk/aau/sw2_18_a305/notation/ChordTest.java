package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChordTest {

    @Test
    void getNotes01() {
        Chord c = new Chord(PitchClass.C, ChordType.Major, 2);
        Note note = c.getNotes().get(1);

        assertEquals(PitchClass.E, note.getPitchClass());
    }

    @Test
    void getLength01() {
        Chord c = new Chord(PitchClass.C, ChordType.Major, 2);
        assertEquals(2, c.getLength());
    }
}