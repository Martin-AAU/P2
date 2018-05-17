package dk.aau.sw2_18_a305.notation;

import javax.sound.midi.Track;
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

    // SETTERS
    public void setTimeDivision(int timeDivision) {
        this.timeDivision = timeDivision;
    }

    // METHODS
    public void addTimedNote(TimedNote n) {
        int time = n.getTimeStamp();
        int latestStamp = 0;

        if (notes.size() != 0) {
            latestStamp = notes.get(notes.size() - 1).getTimeStamp();
        }

        if(time >= latestStamp) {
            notes.add(n);
        }else {
            IntStream.range(0, notes.size()).filter(i -> time >= notes.get(i).getTimeStamp()).forEach(i -> {
                notes.add(i, n);
            });
        }

        this.totalPlaytime = this.totalPlaytime < time ? time: this.totalPlaytime;
    }

    public void addNote(Note n, int length, int timeStamp) {
        TimedNote timedNote = new TimedNote(n, length, timeStamp);
        addTimedNote(timedNote);
    }

    public void addChord(Chord c, int length, int timeStamp) {
        for(int i = 0; i < c.getNotes().size(); i++) {
            TimedNote n = new TimedNote(c.getNotes().get(i), length, timeStamp);
            addTimedNote(n);
        }
    }

    public void convertToMidiTrack() {
        //Track track = new Track();
    }
}
