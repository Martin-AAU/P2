package dk.aau.sw2_18_a305;

import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ConstToSheetConvTest {

    private Constellation constellation = null;
    private Sheet sheet = null;

    @BeforeEach
    void setup() {
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width/1.10);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/1.25);
        constellation = new Constellation("Karlsvognen");
        constellation.addStar(new Star(0,height/3))
                     .addStar(new Star(0,0))
                     .addStar(new Star(width/3,0))
                     .addStar(new Star(width/3,height/3))
                     .addStar(new Star(width/2,height/2))
                     .addStar(new Star(width,height/3))
                     .addStar(new Star(width, height));

        sheet = ConstellationToSheetConverter.convert(constellation);
    }

    @Disabled
    @Test// This test is purely to print out the notes that was added to the sheet
    void printTest() {
        int i = 1;
        for (TimedNote timedNote : sheet.getNotes()) {
            System.out.println( "Note "+i+": "+timedNote.getPitchClass() + " Length: "
                                + timedNote.getLength() + " Timestamp: "
                                + timedNote.getTimestamp() + " Midi: "
                                + timedNote.getMidiValue());
        }
    }

    @Test
    void test01() {
        assertEquals(4, sheet.getNotes().get(0).getLength());
    }

    @Test
    void test02() {
        assertEquals(4, sheet.getNotes().get(3).getLength());
    }

    @Test
    void test03() {
        assertEquals(32, sheet.getTotalPlaytime());
    }

    @Test
    void test04() {
        assertEquals(2, sheet.getNotes().get(10).getLength());
    }
}