package dk.aau.sw2_18_a305.notation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleOfFifthsTest {

    @Test
    void getPosition01() {
        CircleOfFifths tester = new CircleOfFifths();

        // Test Outer ring
        assertEquals(tester.getPosition("C"), 0);
        assertEquals(tester.getPosition("Eb"), 9);

        // Test inner ring
        assertEquals(tester.getPosition("Am"), 0);
        assertEquals(tester.getPosition("Cm"), 9);

        // Test invalid
        assertEquals(tester.getPosition("INVALID"), -1);
    }
}