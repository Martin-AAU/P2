package dk.aau.sw2_18_a305.nightsky;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Constellation extends Group{
    private String name;
    private ArrayList<Star> stars = new ArrayList<>();

    //Constructors
    public Constellation(String name) {
        this.name = name;
    }
    public Constellation(String name, ArrayList<Star> stars) {
        this.name = name;
        this.stars = stars;
    }

    //Getter
    public String getName() {
        return name;
    }
    public ArrayList<Star> getStars() {
        return stars;
    }

    //Add a star to the constellation
    public void addStar(Star s) {
        stars.add(s);
    }
}
