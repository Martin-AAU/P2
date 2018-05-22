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
    /**
     * Retrieves the name of the constellation
     * @return The name of the constellation
     */
    public String getName() {
        return name;
    }
    /**
     * Retreives all the stars in the constellation
     * @return An {@link ArrayList} of {@link Star}s in the constellation
     */
    public ArrayList<Star> getStars() {return new ArrayList<>(stars);}
    /**
     * Adds a star to the constellation
     * @param s The star that is to be added to the constellation
     * @return The constellation itself, so the method can be called consecutively
     */
    public Constellation addStar(Star s) {
        stars.add(s);
        return this;
    }
    /**
     * Removes the last star added to the constellation
     * @return The constellation itself so the method can be called consecutively
     */
    public Constellation removeLastStar(){
        if (stars.size() > 1){
            stars.remove(stars.size() - 1);
        } else if (stars.size() == 1){
            stars.clear();
        }

        return this;
    }
}
