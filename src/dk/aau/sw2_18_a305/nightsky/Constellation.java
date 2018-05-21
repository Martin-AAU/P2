package dk.aau.sw2_18_a305.nightsky;

import java.util.ArrayList;

/**
 * This class represents a constellation of stars
 */
public class Constellation{
    // FIELDS
    /**
     * The name of the Constellation
     */
    private String name;
    /**
     * The stars in the constellation
     */
    private ArrayList<Star> stars = new ArrayList<>();

    // CONSTRUCTOR
    /**
     * Constructs a constellation with a name
     * @param name The name of the constellation
     */
    public Constellation(String name) {
        this.name = name;
    }

    /**
     * Constructs a constellation with a name and an arraylist of stars
     * @param name The name of the constellation
     * @param stars An array list of stars, all the stars in the constellation
     */
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

    // Remove the last star from the constellation
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
}
