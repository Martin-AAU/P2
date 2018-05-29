package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StarTest {
    Star star;

    @BeforeEach
    void setUp() {
        star = new Star(100, 33);
    }

    @Test
    void getxCoordinate() {
        assertEquals(100, star.getxCoordinate());
    }

    @Test
    void getyCoordinate() {
        assertEquals(33, star.getyCoordinate());
    }

    @Test
    void getStarClass() {
        star.setStarClass("WR");
        assertEquals("WR", star.getStarClass());
    }

    @Test
    void getApparentColour() {
        star.setApparentColour("Lightblue");
        assertEquals("Lightblue", star.getApparentColour());
    }

    @Test
    void getApparentMagnitude() {
        star.setApparentMagnitude(4);
        assertEquals(4, star.getApparentMagnitude());
    }

    @Test
    void getAbsoluteMagnitude() {
        star.setAbsoluteMagnitude(15);
        assertEquals(15, star.getAbsoluteMagnitude());
    }

    @Test
    void getradius() {
        star.setradius(2.5);
        assertEquals(2.5, star.getradius());
    }

    @Test
    void getMass() {
        star.setMass(3);
        assertEquals(3, star.getMass());
    }

    @Test
    void getTemperature() {
        star.setTemperature(1000);
        assertEquals(1000, star.getTemperature());
    }

    @Test
    void getDistance() {
        star.setDistance(4.5);
        assertEquals(4.5, star.getDistance());
    }

    @Test
    void setxCoordinate() {
        star.setxCoordinate(500);
        assertEquals(500, star.getxCoordinate());
    }

    @Test
    void setyCoordinate() {
        star.setyCoordinate(500);
        assertEquals(500, star.getyCoordinate());
    }
}