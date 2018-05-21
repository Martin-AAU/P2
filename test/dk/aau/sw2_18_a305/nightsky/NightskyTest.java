package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NightskyTest {

    @Test
    void getStars01() {
        Nightsky n = new Nightsky();
        assertEquals(0, n.getStars().size());
    }

    @Test
    void getStars02() {
        ArrayList<Star> stars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stars.add(new Star(50, 20, "green"));
        }
        Nightsky n = new Nightsky(stars);
        assertEquals(10, n.getStars().size());
    }

    @Test
    void getConstellations01() {
        Nightsky n = new Nightsky();
        assertEquals(0, n.getConstellations().size());
    }

    @Test
    void addStar01() {
        Nightsky n = new Nightsky();
        Star star = new Star(50, 20, "Red");
        n.addStar(star);
        assertEquals(50, n.getStars().get(0).getxCoordinate());
    }

    @Test
    void addConstellation() {
        Nightsky n = new Nightsky();
        Constellation c = new Constellation("Test");
        Star s = new Star(50, 20, "red");
        c.addStar(s);
        n.addConstellation(c);

        assertEquals(20, n.getConstellations().get(0).getStars().get(0).getyCoordinate());
    }
}