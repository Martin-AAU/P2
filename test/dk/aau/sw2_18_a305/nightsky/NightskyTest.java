package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NightskyTest {

    Nightsky nightsky = null;

    @BeforeEach
    void setup() {
        nightsky = new Nightsky();
    }

    @Test
    void getStars01() {
        assertEquals(0, nightsky.getStars().size());
    }

    @Test
    void getStars02() {
        ArrayList<Star> stars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stars.add(new Star(50, 20));
        }
        nightsky = new Nightsky(stars);

        assertEquals(10, nightsky.getStars().size());
    }

    @Test
    void getConstellations01() {
        assertEquals(0, nightsky.getConstellations().size());
    }

    @Test
    void addStar01() {
        Star star = new Star(50, 20);
        nightsky.addStar(star);

        assertEquals(50, nightsky.getStars().get(0).getxCoordinate());
    }

    @Test
    void addConstellation() {
        Constellation constellation = new Constellation("Test");
        Star s = new Star(50, 20);
        constellation.addStar(s);
        nightsky.addConstellation(constellation);

        assertEquals(20, nightsky.getConstellations().get(0).getStars().get(0).getyCoordinate());
    }
}