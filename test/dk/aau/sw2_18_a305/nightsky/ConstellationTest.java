package dk.aau.sw2_18_a305.nightsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConstellationTest {

    @Test
    void getName01() {
        Constellation c = new Constellation("c");
        assertEquals("c", c.getName());
    }

   @Test
   void getStars01() {
       Star s = new Star(50, 20, "red");
       ArrayList<Star> stars = new ArrayList<>();
       stars.add(s);
       Constellation c = new Constellation("c", stars);

       assertEquals(20, c.getStars().get(0).getyCoordinate());
   }

   @Test
    void addStar01() {
       Star s = new Star(50, 20, "red");
       Constellation c = new Constellation("c");
       c.addStar(s);

       assertEquals(20, c.getStars().get(0).getyCoordinate());
   }

   @Test
    void removeLastStar01(){
       Star s1 = new Star(50, 20, "red");
       Star s2 = new Star(45, 20, "red");
       Star s3 = new Star(10, 20, "red");
       Constellation c = new Constellation("c");
       c.addStar(s1);
       c.addStar(s2);
       c.addStar(s3);

       assertEquals(c.getStars().get(c.getStars().size() -1).getxCoordinate(), 10);

       c.removeLastStar();

       assertEquals(c.getStars().get(c.getStars().size() -1).getxCoordinate(), 45);

       c.removeLastStar();

       assertEquals(c.getStars().get(c.getStars().size() -1).getxCoordinate(), 50);

       c.removeLastStar();

       assertEquals(c.getStars().size(), 0);

       c.removeLastStar();

       assertEquals(c.getStars().size(), 0);

   }

}