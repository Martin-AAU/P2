package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

public class Sheet {

    public static int QUARTER_NOTE = 4;
    public static int EIGHTH_NOTE = 8;
    public static int SIXTEENTH_NOTE = 16;

    private ArrayList<Note> notes = new ArrayList<>();
    private ArrayList<Integer> timeStamps = new ArrayList();

    private int timeDivision = QUARTER_NOTE;

    //Constructors
    public Sheet() {
    }
    public Sheet(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    public void addNote(Note n, int time) {
        notes.add(n);
        this.timeStamps.add(time);
    }

    public void addChord(Chord c, int timeStamp) {
        addNote(c.note1, timeStamp);
        addNote(c.note2, timeStamp);
        addNote(c.note3, timeStamp);
    }
}
