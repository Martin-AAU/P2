import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;
import javafx.scene.shape.Circle;

import java.util.Vector;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;

public final class ConstToSheetConv {

    public static Sheet convert(Constellation constellation) {

        Sheet sheet = new Sheet();
        Star star = constellation.getStars().get(0);

        //Initialize the Note and chord in the first end of the sheet (Beginning) and add it to the sheet as a major chord
        Note note = new Note(calPitchClass(star.getyCoordinate()));
        Chord major = new Chord(note, 4, 3);
        Chord minor;
        Chord latestChord = major;
        sheet.addChord(major, 16, 0);

        int numberOfStars = constellation.getStars().size();

        // Go through the second to the second last star, and add them as notes to the sheet
        for (int i = 1; i < numberOfStars - 1 ; i++) {
            star = constellation.getStars().get(i);
            note = new Note(calPitchClass(star.getyCoordinate()));

            //calculate the distance between the latest and current chord, and playtime of current chord
            int distance = calcLength(constellation.getStars().get(i-1).getxCoordinate(), constellation.getStars().get(i-1).getyCoordinate(), star.getxCoordinate(), star.getyCoordinate());
            int playtime = calcPlayTime(constellation.getStars().get(i-1).getxCoordinate(), constellation.getStars().get(i-1).getyCoordinate(),
                                        star.getxCoordinate(), star.getyCoordinate(), constellation.getStars().get(i+1).getxCoordinate(),
                                        constellation.getStars().get(i+1).getyCoordinate());

            //Make the mol and dur chords
            major = new Chord(note, 4, 3);
            minor = new Chord(note, 3, 4);

            //Add the closest of the dur or mol chords to sheet
            sheet.addChord(checkClosestChord(latestChord, minor, major), playtime, distance + sheet.getTotalPlaytime());

        }

        star = constellation.getStars().get(constellation.getStars().size() - 1);
        Star lastStar = constellation.getStars().get(constellation.getStars().size() - 2);
        int space = calcLength(lastStar.getxCoordinate(), lastStar.getyCoordinate(), star.getxCoordinate(), star.getyCoordinate());

        //Calculate the end note and chord (Last) and add it to sheet
        note = new Note(calPitchClass(constellation.getStars().get(constellation.getStars().size() - 1).getyCoordinate()));
        Chord dur = new Chord(note, 4, 3);
        Chord mol = new Chord(note, 3, 4);
        sheet.addChord(checkClosestChord(latestChord, mol, dur), 16, space + sheet.getTotalPlaytime());

        return sheet;
    }


    /**
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coodrinate of second point
     * @param y2 y coordinate of second point
     * @return returns the lenght between two points in 16 parts of a note
     */
    private static int calcLength(int x1, int y1, int x2, int y2) {
        double result = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        double maxDistance = Math.sqrt((DrawGUI.width)*(DrawGUI.width) + (DrawGUI.height)*(DrawGUI.height));

        return determineTime(result, maxDistance);
    }

    /**
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coodrinate of second point
     * @param y2 y coordinate of second point
     * @param x3 x coordinate of third point
     * @param y3 y coordinate of third point
     * @return returns the playtime of a note by calculating an angle between three points
     */
    private static int calcPlayTime(int x1, int y1, int x2, int y2, int x3, int y3) {
        Vector<Integer> v1 = new Vector<>(Math.abs(x1-x2), Math.abs(y1-y2));
        Vector<Integer> v2 = new Vector<>(Math.abs(x2-x3), Math.abs(y2-y3));

        double angle = Math.atan2(v2.get(1), v2.get(0)) - Math.atan2(v1.get(1), v1.get(0));
        if (angle < 0) angle += 2 * Math.PI;

        if(angle > Math.PI) {
            angle = (2 * Math.PI) - angle;
        }
        return determineTime(angle, Math.PI);
    }

    /**
     * @param number The number of which is to be converted to a lenght in time, in a music piece
     * @param range The max value the number parameter can acheive. The range is used to measure
     * @return
     */
    private static int determineTime(double number, double range) {
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

    private static PitchClass calPitchClass(int y) {
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

    private static Chord checkClosestChord(Chord ref, Chord a, Chord b) {
        if(ref.distanceTo(a) < ref.distanceTo(b))
            return a;
        return b;
    }
}
