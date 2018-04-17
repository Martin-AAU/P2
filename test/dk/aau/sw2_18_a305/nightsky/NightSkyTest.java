package dk.aau.sw2_18_a305.nightsky;

import dk.aau.sw2_18_a305.nightsky.exceptions.IllegalHeightException;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NightSkyTest {

    private NightSky0 nightSky0;

    @BeforeEach
    void setUp() {
        nightSky0 = new NightSky0(100, 100);
    }

    @Test
    void getHeight() {
        assertEquals(100, nightSky0.getHeight());
    }

    @Test
    void getWidth() {
        assertEquals(100, nightSky0.getWidth());
    }

    @Test
    void getStars() {
    }

    @Test
    void getConstellations() {
    }

    @Test
    void getActiveConstellation() {
    }

    @Test
    void setHeight01() {
        assertThrows(IllegalHeightException.class, ()-> nightSky0.setHeight(0));
    }

    @Test
    void setWidth01() {
        assertThrows(IllegalHeightException.class, ()-> nightSky0.setWidth(0));
    }

    @Test
    void setActiveConstellation() {
    }

    @Test
    void setStars() {
        ArrayList<Circle> stars = new ArrayList<>();
        stars.add(new Circle(10, 10, 5));
        nightSky0.setStars(stars);

        assertEquals(10, nightSky0.getStars().get(0).getCenterX());
        assertEquals(10, nightSky0.getStars().get(0).getCenterY());
        assertEquals(5, nightSky0.getStars().get(0).getRadius());
    }

    @Test
    void setConstellations() {
    }
}