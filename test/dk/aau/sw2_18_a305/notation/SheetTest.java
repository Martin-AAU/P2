package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Sequence;

import static org.junit.jupiter.api.Assertions.*;

class SheetTest {

    Sheet sheet = null;
    Sequence sequence = null;

    @BeforeEach
    void setUp() {
        sheet = new Sheet(Sheet.SIXTEENTH_NOTE);
        sheet.addTimedNote(new TimedNote(PitchClass.C, 8, 0));
        sheet.addTimedNote(new TimedNote(PitchClass.Cs, 8, 8));
        sheet.addTimedNote(new TimedNote(PitchClass.A, 8, 16));
        sheet.addTimedNote(new TimedNote(PitchClass.B, 8, 24));

        sequence = sheet.convertToMidiSequence();
    }

    @Test
    void getNotesTest01() {
        assertEquals(PitchClass.Cs, sheet.getNotes().get(1).getPitchClass());
    }

    @Test
    void getNotesTest02() {
        assertEquals(PitchClass.A, sheet.getNotes().get(2).getPitchClass());
    }

    @Test
    void getTimeDivisionTest() {
        assertEquals(16, sheet.getTimeDivision());
    }

    @Test
    void getTotalPlaytimeTest() {
        assertEquals(24, sheet.getTotalPlaytime());
    }

    @Test
    void setTimeDivisionTest() {
        sheet.setTimeDivision(Sheet.EIGHTH_NOTE);
        assertEquals(8, sheet.getTimeDivision());
    }

    @Test
    void addTimedNoteTest() {
        sheet.addTimedNote(new TimedNote(PitchClass.D, 16, 4));

        assertEquals(PitchClass.D, sheet.getNotes().get(1).getPitchClass());
    }

    @Test
    void addNoteTest() {
        sheet.addNote(new Note(PitchClass.Ds),4,0);

        assertEquals(PitchClass.Ds, sheet.getNotes().get(1).getPitchClass());
    }

    @Test
    void addChordTest() {
        Chord chord = new Chord(new Note(PitchClass.E),3,4);
        System.out.println(sheet.getNotes().size());
        sheet.addChord(chord, 16, 20);

        assertEquals(7, sheet.getNotes().size());
    }

    @Test
    void convertToMidiSequenceTest01() {
        assertEquals(32, sequence.getTickLength());
    }

    @Test
    void convertToMidiSequenceTest02() {
        assertEquals(16/4, sequence.getResolution());
    }
}