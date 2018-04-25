package dk.aau.sw2_18_a305.notation;

import java.util.ArrayList;

public class Sheet {

    public static int QUARTER_NOTE = 4;
    public static int EIGHTH_NOTE = 8;
    public static int SIXTEENTH_NOTE = 16;

    private ArrayList<Note> notes = new ArrayList<>();
    private ArrayList<Integer> timeStamps = new ArrayList<>();

    private int timeDivision = QUARTER_NOTE;
    private int totalPlaytime = 0;

    //Constructors
    public Sheet() {
    }
    public Sheet(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    //Getters
    public ArrayList<Note> getNotes() {
        return notes;
    }
    public ArrayList<Integer> getTimeStamps() {
        return timeStamps;
    }
    public int getTimeDivision() {
        return timeDivision;
    }
    public int getTotalPlaytime() {
        return totalPlaytime;
    }

    //Setters
    public void setTimeDivision(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    //Methods
    public void addNote(Note n, int time) {
        notes.add(n);
        this.timeStamps.add(time);
        totalPlaytime = totalPlaytime + time;
    }

    public void addChord(Chord c, int timeStamp) {
        for(int i = 0; i < c.getNotes().size(); i++) {
            addNote(c.getNotes().get(i), timeStamp);
        }
    }
}
