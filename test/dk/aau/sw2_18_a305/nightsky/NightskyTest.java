package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class  NightskyTest {

    Nightsky nightsky = null;

    @BeforeEach
    void setup() {
        ArrayList<Star> stars = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            stars.add(new Star(50, 20));
        }

        nightsky = new Nightsky(stars);
    }

    @Test
    void getStars01() {
        assertEquals(20, nightsky.getStars().get(1).getyCoordinate());
    }

    @Test
    void getStars02() {
        assertEquals(10, nightsky.getStars().size());
    }

    @Test
    void getConstellations01() {
        assertEquals(0, nightsky.getConstellations().size());
    }

    @Test
    void addStar01() {
        Star star = new Star(100, 30);
        nightsky.addStar(star);

        assertEquals(100, nightsky.getStars().get(10).getxCoordinate());
    }

    @Test
    void addConstellationTest01() {
        Constellation constellation = new Constellation("Test");
        Star s = new Star(500, 200);
        constellation.addStar(s);

        nightsky.addConstellation(constellation);

        assertEquals(200, nightsky.getConstellations().get(0).getStars().get(0).getyCoordinate());
    }

    @Test
    void addConstellation02() {
        Constellation constellation = new Constellation("Test");
        Star s = new Star(500, 200);
        constellation.addStar(s);

        nightsky.addConstellation(constellation);

        assertEquals(500, nightsky.getStars().get(10).getxCoordinate());
    }
}