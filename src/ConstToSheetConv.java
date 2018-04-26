import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;
import javafx.scene.shape.Circle;

import java.util.Vector;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;

public class ConstToSheetConv {

    public Sheet convert(Constellation c) {

        Sheet sheet = new Sheet();

        //Initialize the Note and chord in the first end of the sheet (Beginning) and add it to the sheet as a dur chord
        Note endNote = new Note(calPitchClass(c.getStars().get(0).getyCoordinate()));
        Chord endChord = new Chord(endNote, 4, 3);
        Chord latestChord = endChord;
        sheet.addChord(endChord, 16, 0);

        for (int i = 1; i < c.getStars().size() -1 ; i++) {
            Star star = c.getStars().get(i);
            Note n = new Note(calPitchClass(star.getyCoordinate()));

            //calculate the space between the latest and current chord, and playtime of current chord
            int space = calcLength(c.getStars().get(i-1).getxCoordinate(), c.getStars().get(i-1).getyCoordinate(), star.getxCoordinate(), star.getyCoordinate());
            int playtime = calcPlayTime(c.getStars().get(i-1).getxCoordinate(), c.getStars().get(i-1).getyCoordinate(),
                                        star.getxCoordinate(), star.getyCoordinate(), c.getStars().get(i+1).getxCoordinate(),
                                        c.getStars().get(i+1).getyCoordinate());

            //Make the mol and dur chords
            Chord dur = new Chord(n, 4, 3);
            Chord mol = new Chord(n, 3, 4);

            //Add the closest of the dur or mol chords to sheet
            sheet.addChord(checkClosestChord(latestChord, mol, dur), playtime, space + sheet.getTotalPlaytime());

        }
        Star endStar = c.getStars().get(c.getStars().size() - 1);
        Star lastStar = c.getStars().get(c.getStars().size() - 2);
        int space = calcLength(lastStar.getxCoordinate(), lastStar.getyCoordinate(), endStar.getxCoordinate(), endStar.getyCoordinate());

        //Calculate the end note and chord (Last) and add it to sheet
        endNote = new Note(calPitchClass(c.getStars().get(c.getStars().size() - 1).getyCoordinate()));
        Chord dur = new Chord(endNote, 4, 3);
        Chord mol = new Chord(endNote, 3, 4);
        sheet.addChord(checkClosestChord(latestChord, mol, dur), 16, space + sheet.getTotalPlaytime());

        return sheet;
    }

    private int calcLength(int x1, int y1, int x2, int y2) {
        double result = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        double maxDistance = Math.sqrt((DrawGUI.width-0)*(DrawGUI.width-0) + (DrawGUI.height-0)*(DrawGUI.height-0));

        return determineTime(result, maxDistance);
    }

    private int calcPlayTime(int x1, int y1, int x2, int y2, int x3, int y3) {
        Vector<Integer> v1 = new Vector<>(Math.abs(x1-x2), Math.abs(y1-y2));
        Vector<Integer> v2 = new Vector<>(Math.abs(x2-x3), Math.abs(y2-y3));

        double angle = Math.atan2(v2.get(1), v2.get(0)) - Math.atan2(v1.get(1), v1.get(0));
        if (angle < 0) angle += 2 * Math.PI;

        if(angle > Math.PI) {
            angle = (2 * Math.PI) - angle;
        }
        return determineTime(angle, Math.PI);
    }

    private int determineTime(double number, double range) {
        if(number < range * 0.2) {
            return 1;
        } else if(number < range * 0.4) {
            return 2;
        } if(number < range * 0.6) {
            return 4;
        } if(number < range * 0.8) {
            return 8;
        } else {
            return 16;
        }
    }

    private PitchClass calPitchClass(int y) {
        int index = y / (DrawGUI.height/12) + 1;

        switch (index % 12) {
            case 1: return C;
            case 2: return Cs;
            case 3: return D;
            case 4: return Ds;
            case 5: return E;
            case 6: return F;
            case 7: return Fs;
            case 8: return G;
            case 9: return Gs;
            case 10: return A;
            case 11: return As;
            case 0: return B;
            default: return C;
        }
    }

    private Chord checkClosestChord(Chord ref, Chord a, Chord b) {
        if(ref.distanceTo(a) < ref.distanceTo(b))
            return a;
        return b;
    }
}
