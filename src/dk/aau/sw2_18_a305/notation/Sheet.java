package dk.aau.sw2_18_a305.notation;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class Sheet {

    public static int QUARTER_NOTE = 4;
    public static int EIGHTH_NOTE = 8;
    public static int SIXTEENTH_NOTE = 16;

    private LinkedList<TimedNote> notes = new LinkedList<>();

    private int timeDivision = QUARTER_NOTE;
    private int totalPlaytime = 0;

    // CONTRUCTORS
    public Sheet() {
    }
    public Sheet(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // GETTERS
    public LinkedList<TimedNote> getNotes() {
        return notes;
    }
    public int getTimeDivision() {
        return timeDivision;
    }
    public int getTotalPlaytime() {
        return totalPlaytime;
    }

    // SETTTERS
    public void setTimeDivision(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // METHODS
    public void addNote(TimedNote n) {
        int time = n.getTimeStamp();

        if(time >= totalPlaytime) {
            notes.add(n);
        }
        else {
            IntStream.range(0, notes.size()).filter(i -> time >= notes.get(i).getTimeStamp()).forEach(i -> notes.add(i, n));
        }

        totalPlaytime = totalPlaytime < time ? time : totalPlaytime;
    }

    public void addChord(Chord c, int length, int timeStamp) {
        for(int i = 0; i < c.getNotes().size(); i++) {
            TimedNote n = new TimedNote(c.getNotes().get(i), length, timeStamp);
            addNote(n);
        }
    }
}
