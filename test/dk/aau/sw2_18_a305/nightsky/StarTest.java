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