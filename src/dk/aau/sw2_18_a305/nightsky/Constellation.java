package dk.aau.sw2_18_a305.nightsky;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
 * This class represents a constellation of stars
 */

public class Constellation{
    private String name;
    private ArrayList<Star> stars = new ArrayList<>();

    // CONSTRUCTOR
    public Constellation(String name) {
        this.name = name;
    }
    public Constellation(String name, ArrayList<Star> stars) {
        this.name = name;
        this.stars = stars;
    }

    // GETTERS
    public String getName() {
        return name;
    }
    public ArrayList<Star> getStars() {return new ArrayList<>(stars);}

    // Add a star to the constellation
    public Constellation addStar(Star s) {
        stars.add(s);
        return this;
    }

    public Constellation removeLastStar(){
        if (stars.size() > 1){
            stars.remove(stars.size() - 1);
            return this;
        } else if (stars.size() == 1){
            stars.clear();
            return this;
        }

        else return this;
    }

    //
}
