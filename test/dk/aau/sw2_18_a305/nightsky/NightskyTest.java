package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NightskyTest {

    @Test
    void getStars01() {
        Nightsky n = new Nightsky();
        assertEquals(0, n.getStars().size());
    }
    @Test
    void getStars02() {
        Nightsky n = new Nightsky();
        Star s = new Star(50, 20, "Blue");

        assertEquals(0, n.getStars().size());
    }

    @Test
    void getConstellations() {
    }

    @Test
    void setStars() {
    }

    @Test
    void setConstellations() {
    }
}