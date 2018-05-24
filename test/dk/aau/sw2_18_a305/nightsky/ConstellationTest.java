package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConstellationTest {

    Constellation constellation = null;
    Star s1 = null;
    Star s2 = null;
    Star s3 = null;

    @BeforeEach
    void setup() {
        constellation = new Constellation("Stjernebillede");
        s1 = new Star(50, 20);
        s2 = new Star(45, 20);
        s3 = new Star(10, 20);
    }

    @Test
    void getName01() {
        assertEquals("Stjernebillede", constellation.getName());
    }

   @Test
   void getStars01() {
       ArrayList<Star> stars = new ArrayList<>();
       stars.add(s1);
       constellation = new Constellation("Stjernebillede", stars);

       assertEquals(20, constellation.getStars().get(0).getyCoordinate());
   }

   @Test
    void addStar01() {
       constellation.addStar(s1);

       assertEquals(20, constellation.getStars().get(0).getyCoordinate());
   }

   @Test
    void removeLastStar01() {
       constellation.addStar(s1);
       constellation.addStar(s2);

       constellation.removeLastStar();
       assertEquals(s1, constellation.getStars().get(constellation.getStars().size()-1));
   }

    @Test
    void removeLastStar02() {
        constellation.addStar(s1);
        constellation.addStar(s2);
        constellation.addStar(s3);

        constellation.removeLastStar();
        assertEquals(s2, constellation.getStars().get(constellation.getStars().size()-1));
    }

    @Test
    void removeLastStar03() {
        constellation.addStar(s1);
        constellation.addStar(s2);
        constellation.addStar(s3);

        constellation.removeLastStar();
        assertEquals(2, constellation.getStars().size());
    }
}