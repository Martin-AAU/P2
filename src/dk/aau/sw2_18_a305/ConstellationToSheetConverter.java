package dk.aau.sw2_18_a305;

import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;

import java.awt.*;

/**
 * Final class with only static methods. Used purely to convert a {@link Constellation} to a {@link Sheet}
 */
public final class ConstellationToSheetConverter {

    // METHODS
    /**
     * Takes a constellation and converts all the stars to chords and puts it into a sheet
     *
     * @param constellation The given constellation to be converted into a note sheet
     * @return A Sheet as a result from the constellation
     */
    public static Sheet convert(Constellation constellation) {
        Sheet sheet = new Sheet();
        Chord latestChord;

        // if the given constellation is empty,
        // return an empty sheet
        if(constellation.getStars().size() < 1) {
            return new Sheet();
        }

        latestChord = FirstStar(sheet, constellation);
        latestChord = mostStars(sheet, constellation, latestChord);
        lastStar(sheet, constellation, latestChord);

        return sheet;
    }

    /**
     * Finds the first star in a constellation and makes a chord out of it,
     * and adds it to the given sheet
     *
     * @param sheet The sheet that is being created
     * @param constellation The constellation that is being converted
     * @return The latest chord that was added
     */
    private static Chord FirstStar(Sheet sheet, Constellation constellation) {
        Star star = constellation.getStars().get(0);

        //Make the first star a Major chord, and add it to the sheet
        Note note = new Note(calPitchClass(star.getyCoordinate()));
        Chord major = new Chord(note, 4, 3);
        sheet.addChord(major, sheet.getTimeDivision()/2, 0);

        return major;
    }

    /**
     * Takes all stars between the first and last from a constellation
     * and then convert them into chords and add the chords to the given sheet
     *
     * @param sheet The sheet that is being created
     * @param constellation The constellation that is being converted
     * @param latestChord The latest chord to be added to the sheet
     * @return The latest chord that was added
     */
    private static Chord mostStars(Sheet sheet, Constellation constellation, Chord latestChord) {
        // Go through the second to the second last star, and add them as notes to the sheet
        int numberOfStars = constellation.getStars().size();

        for (int i = 1; i < numberOfStars - 1 ; i++) {
            Star star = constellation.getStars().get(i);
            Note note = new Note(calPitchClass(star.getyCoordinate()));

            //calculate the distance between the latest and current chord, and playtime of current chord
            int distance = calLength(constellation.getStars().get(i-1), star);
            int playtime = calPlayTime(constellation.getStars().get(i-1), star, constellation.getStars().get(i+1));

            // Get the next chord
            Chord nextChord = decideMajorMinor(latestChord, note);

            //Add the closest of the major or minor chords to sheet
            sheet.addChord(nextChord, playtime, distance + sheet.getTotalPlaytime());

            latestChord = nextChord;
        }

        return latestChord;
    }

    /**
     * Takes the last star in a constellation and adds it to a sheet as a chord
     *
     * @param sheet The sheet that is being created
     * @param constellation The constellation that is being converted
     * @param latestChord The latest chord to be added to the sheet
     */
    private static void lastStar(Sheet sheet, Constellation constellation, Chord latestChord) {
        Star star = constellation.getStars().get(constellation.getStars().size() - 1);
        Star lastStar = constellation.getStars().get(constellation.getStars().size() - 2);
        int space = calLength(lastStar, star);

        //Calculate the end note and chord (Last) and add it to sheet
        Note note = new Note(calPitchClass(constellation.getStars().get(constellation.getStars().size() - 1).getyCoordinate()));
        sheet.addChord(decideMajorMinor(latestChord, note), sheet.getTimeDivision()/2, space + sheet.getTotalPlaytime());
    }

    /**
     * Takes two stars, and calculates the length between them.
     * it uses the determineTime(double, double) method to return a time in 16th parts of a note
     *
     * @param a The star that the distance is calculated from
     * @param b The star that the distance is calculated to
     * @return The lenght between two points in 16 parts of a note
     */
    private static int calLength(Star a, Star b) {
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width/3.4);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/3.7);
        int x1 = a.getxCoordinate();
        int x2 = b.getxCoordinate();
        int y1 = a.getyCoordinate();
        int y2 = b.getyCoordinate();

        double result = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        double maxDistance = Math.sqrt((width)*(width) + (height)*(height));

        return determineTime(result, maxDistance);
    }

    /**
     * Calculates the playtime of a note by calculating the angle between three stars.
     * the angle that is calculated is between 0 and PI, and then converted into time with the determineTime(double, double) method
     *
     * @param a One of the stars the angle is calculated between
     * @param b One of the stars the angle is calculated between
     * @param c One of the stars the angle is calculated between
     * @return The playtime of a note by calculating an angle between three points
     */
    private static int calPlayTime(Star a, Star b, Star c) {
        int x1 = a.getxCoordinate();
        int x2 = b.getxCoordinate();
        int x3 = c.getxCoordinate();
        int y1 = a.getyCoordinate();
        int y2 = b.getyCoordinate();
        int y3 = c.getyCoordinate();

        double angle = Math.atan2(y2-y1,x2-x1) - Math.atan2(y2-y3,x2-x3);

        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        if(angle > Math.PI) {
            angle = (2 * Math.PI) - angle;
        }

        angle = Math.PI - angle;

        return determineTime(angle, Math.PI);
    }

    /**
     * Converts a number in a specific range into either 1, 2, 4, 8, or 16.
     * the number represents 16th parts of a note. The closer the number is to the range (max value)
     * the higher the returned value is.
     *
     * @param number The number of which is to be converted to a lenght in time, in a music piece
     * @param range The max value the number parameter can acheive. The range is used to measure
     * @return Either 1, 2, 4, 8, or 16 compared to how close number is to range
     */
    private static int determineTime(double number, double range) {
        if(number < range * 0.25) {
            return 1;
        } else if(number < range * 0.5) {
            return 2;
        } if(number < range * 0.75) {
            return 4;
        } else {
            return 8;
        }
    }

    /**
     * Takes a coordinate that it converts into a pitchclass depending on how large the given coordinate is
     * compared to the static height value in dk.aau.sw2_18_a305.DrawGUI
     *
     * @param y The y coordinate
     * @return A pitchclass depending on the coordinate and how large the window of dk.aau.sw2_18_a305.DrawGUI is
     */
    private static PitchClass calPitchClass(int y) {
        int index = y / ((int)((Toolkit.getDefaultToolkit().getScreenSize().height/1.25)/12));
        index = 11 - index;
        return PitchClass.readPitchClass(index);
    }

    /**
     * Checks whether the given Chord a or b, is closest to the ref Chord,
     * using the distanceStrategy that the ref chord has implemented
     *
     * @param ref The reference Chord
     * @param a A chord where the distance to ref is to be determined
     * @param b A chord where the distance to ref is to be determined
     * @return The chord a or b that is closest to ref, using the distance strategy that is assigned in the ref chord
     */
    private static Chord checkClosestChord(Chord ref, Chord a, Chord b) {
        if(ref.distanceTo(a) < ref.distanceTo(b)) {
            return a;
        } else return b;
    }

    /**
     * Calculates the next chord, depending on the last chord
     * @param lastChord The last chord that was added to the sheet
     * @param nextNote The next note that is to make a new chord
     * @return A minor or major chord, depending on which is closest to the last chord in the circle of fifths.
     * Also if the root note in the new chord and last chord are the same, then sets the chord type different to the last
     */
    private static Chord decideMajorMinor(Chord lastChord, Note nextNote) {
        //Make the minor and major chords
        Chord major = new Chord(nextNote, 4, 3);
        Chord minor = new Chord(nextNote, 3, 4);
        Chord nextChord = checkClosestChord(lastChord, minor, major);

        // Make sure the new chord is different from the latest
        if (lastChord.equals(major)) {
            nextChord = minor;
        } else if (lastChord.equals(minor)) {
            nextChord = major;
        }

        return nextChord;
    }
}
