import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.Sheet;
import org.junit.jupiter.api.BeforeEach;
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

        sheet = ConstToSheetConv.convert(constellation);
    }

    @Test
    void Test01() {
    }
}