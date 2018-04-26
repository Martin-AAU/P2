package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    Note note;

    @BeforeEach
    void setUp() {
        note = new Note(PitchClass.C, 4);
    }

    @Test
    void getPitchClass() {
        assertEquals(PitchClass.C, note.getPitchClass());
    }

    @Test
    void getOctave() {
        assertEquals(4, note.getOctave());
    }
}