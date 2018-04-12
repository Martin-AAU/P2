package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NightSkyTest {

    private NightSky nightSky;

    @BeforeEach
    void setUp() {
        NightSky nightSky = new NightSky(100, 100);
    }

    @Test
    void getHeight() {
        assertEquals(100, nightSky.getHeight());
    }

    @Test
    void getWidth() {
        assertEquals(100, nightSky.getWidth());
    }

    @Test
    void getStars() {
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

    @Test
    void getHeight1() {
    }

    @Test
    void getWidth1() {
    }

    @Test
    void getStars1() {
    }

    @Test
    void getConstellations1() {
    }

    @Test
    void getActiveConstellation() {
    }

    @Test
    void setHeight() {
        nightSky.setHeight(-5);
        assertEquals(100, nightSky.getHeight());
    }

    @Test
    void setWidth() {
        nightSky.setWidth(-5);
        assertEquals(100, nightSky.getWidth());
    }

    @Test
    void setStars1() {
    }

    @Test
    void setConstellations1() {
    }

    @Test
    void setActiveConstellation() {
    }
}