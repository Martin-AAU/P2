import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static dk.aau.sw2_18_a305.notation.PitchClass.*;
import static org.junit.jupiter.api.Assertions.*;

class ConstToSheetConvTest {

    Constellation constellation = null;
    Sheet sheet = null;

    @BeforeEach
    void setup() {
        constellation = new Constellation("Carlsvognen");
        constellation.addStar(new Star(0,4))
                     .addStar(new Star(0,0))
                     .addStar(new Star(4,0))
                     .addStar(new Star(4,4))
                     .addStar(new Star(6,5))
                     .addStar(new Star(10,6));

        sheet = ConstToSheetConv.convert(constellation);
    }

    @Test
    void test01() {
        Note note = new Note(readPitchClass(constellation.getStars().get(0).getyCoordinate() / (DrawGUI.height/12)));
        Chord c = new Chord(new Note(C), 4, 3);
        System.out.println(sheet.getTotalPlaytime());

        int i = 1;
        for (TimedNote timedNote : sheet.getNotes()) {
            System.out.println("Note "+i+": "+timedNote.getPitchClass() + " Length: " + timedNote.getLength() + " Timestamp: " + timedNote.getTimeStamp());
        }
    }
}