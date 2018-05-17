import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;

public final class ConstToSheetConv {

    /**
     * This method takes a constellation and converts all the stars to chords and puts it into a sheet
     *
     * @param constellation The given constellation to be converted into a note sheet
     * @return Returns a Sheet as a result from the constellation
     */
    public static Sheet convert(Constellation constellation) {

        Sheet sheet = new Sheet();
        Chord latestChord;

        latestChord = FirstStar(sheet, constellation);

        latestChord = mostStars(sheet, constellation, latestChord);

        lastStar(sheet, constellation, latestChord);

        return sheet;
    }

    /**
     * This method is used to find the first star in a constellation and make a chord out of it,
     * and adds it to the given sheet
     *
     * @param sheet The sheet that is being created
     * @param constellation The constellation that is being converted
     * @return returns the latest chord that was added
     */
    private static Chord FirstStar(Sheet sheet, Constellation constellation) {
        Star star = constellation.getStars().get(0);

        //Make the first star a Major chord, and add it to the sheet
        Note note = new Note(calPitchClass(star.getyCoordinate()));
        Chord major = new Chord(note, 4, 3);
        sheet.addChord(major, 16, 0);

        return major;
    }

    /**
     * This method takes all stars between the first and last from a constellation
     * and then convert them into chords and add the chords to the given sheet
     *
     * @param sheet The sheet that is being created
     * @param constellation The constellation that is being converted
     * @param latestChord The latest chord to be added to the sheet
     * @return returns the latest chord that was added
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

            //Make the minor and major chords
            Chord major = new Chord(note, 4, 3);
            Chord minor = new Chord(note, 3, 4);
            Chord nextChord = checkClosestChord(latestChord, minor, major);

            //Add the closest of the major or minor chords to sheet
            sheet.addChord(nextChord, playtime, distance + sheet.getTotalPlaytime());

            latestChord = nextChord;
        }

        return latestChord;
    }

    /**
     * This method takes the last star in a constellation and adds it to a sheet as a chord
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
        Chord dur = new Chord(note, 4, 3);
        Chord mol = new Chord(note, 3, 4);
        sheet.addChord(checkClosestChord(latestChord, mol, dur), 16, space + sheet.getTotalPlaytime());
    }

    /**
     * This method takes two stars, and calculates the length between them.
     * it uses the determineTime(double, double) method to return a time in 16th parts of a note
     *
     * @param a The star that the distance is calculated from
     * @param b The star that the distance is calculated to
     * @return returns the lenght between two points in 16 parts of a note
     */
    private static int calLength(Star a, Star b) {
        int x1 = a.getxCoordinate();
        int x2 = b.getxCoordinate();
        int y1 = a.getyCoordinate();
        int y2 = b.getyCoordinate();

        double result = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        double maxDistance = Math.sqrt((DrawGUI.width)*(DrawGUI.width) + (DrawGUI.height)*(DrawGUI.height));

        return determineTime(result, maxDistance);
    }

    /**
     * This method returns the playtime of a note by calculating the angle between three stars.
     * the angle that is calculated is between 0 and PI, and then converted into time with the determineTime(double, double) method
     *
     * @param a One of the stars the angle is calculated between
     * @param b One of the stars the angle is calculated between
     * @param c One of the stars the angle is calculated between
     * @return returns the playtime of a note by calculating an angle between three points
     */
    private static int calPlayTime(Star a, Star b, Star c) {
        int x1 = a.getxCoordinate();
        int x2 = b.getxCoordinate();
        int x3 = c.getxCoordinate();
        int y1 = a.getyCoordinate();
        int y2 = b.getyCoordinate();
        int y3 = c.getyCoordinate();

        double angle = Math.atan2(y2-y1,x2-x1) - Math.atan2(y2-y3,x2-x3);

        if (angle < 0) angle += 2 * Math.PI;

        if(angle > Math.PI) {
            angle = (2 * Math.PI) - angle;
        }

        return determineTime(angle, Math.PI);
    }

    /**
     * This method converts a number in a specific range into either 1, 2, 4, 8, or 16.
     * the number represents 16th parts of a note. The closer the number is to the range (max value)
     * the higher the returned value is.
     *
     * @param number The number of which is to be converted to a lenght in time, in a music piece
     * @param range The max value the number parameter can acheive. The range is used to measure
     * @return Returns either 1, 2, 4, 8, or 16 compared to how close number is to range
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

    /**
     * This method takes a coordinate that it converts into a pitchclass depending on how large the given coordinate is
     * compared to the static height value in DrawGUI
     *
     * @param y The y coordinate
     * @return returns a pitchclass depending on the coordinate and how large the window of DrawGUI is
     */
    private static PitchClass calPitchClass(int y) {
        int index = y / (DrawGUI.height/12);

        return PitchClass.readPitchClass(index);
    }

    /**
     * This method checks whether the given Chord a or b, is closest to the ref Chord,
     * using the distanceStrategy that the ref chord has implemented
     *
     * @param ref The reference Chord
     * @param a A chord where the distance to ref is to be determined
     * @param b A chord where the distance to ref is to be determined
     * @return Returns the chord a or b that is closest to ref, using the distance strategy that is assigned in the ref chord
     */
    private static Chord checkClosestChord(Chord ref, Chord a, Chord b) {
        if(ref.distanceTo(a) < ref.distanceTo(b)) {
            return a;
        }
        return b;
    }
}
