import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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

        sheet = ConstellationToSheetConverter.convert(constellation);
    }

    @Disabled
    @Test // This test is purely to print out the notes that was added to the sheet
    void printTest() {
        int i = 1;
        for (TimedNote timedNote : sheet.getNotes()) {
            System.out.println("Note "+i+": "+timedNote.getPitchClass() + " Length: " + timedNote.getLength() + " Timestamp: " + timedNote.getTimeStamp());
        }
    }

    @Test
    void test01() {
        assertEquals(16, sheet.getNotes().get(0).getLength());
    }

    @Test
    void test02() {
        assertEquals(4, sheet.getNotes().get(3).getLength());
    }

    @Test
    void test03() {
        assertEquals(5, sheet.getTotalPlaytime());
    }

    @Test
    void test04() {
        assertEquals(8, sheet.getNotes().get(10).getLength());
    }
}