package dk.aau.sw2_18_a305.nightsky;

import java.util.ArrayList;

/**
 * This class represents a night sky with stars and constellations
 */
public class Nightsky {
    // FIELDS
    /**
     * {@link ArrayList} of all {@link Star}s in the night sky
     */
    private ArrayList<Star> stars = new ArrayList<>();
    /**
     * {@link ArrayList} of all {@link Constellation}s in the night sky
     */
    private ArrayList<Constellation> constellations = new ArrayList<>();

    //CONSTRUCTORS
    /**
     * Constructs an empty night sky
     */
    public Nightsky() { }

    /**
     * Constructs a night sky with stars
     * @param stars An {@link ArrayList} of {@link Star}s in the night sky
     */
    public Nightsky(ArrayList<Star> stars) {
        this.stars = stars;
    }

    /**
     * Constructs a night sky with stars and constellations
     * @param stars An {@link ArrayList} of {@link Star}s in the night sky
     * @param constellations An {@link ArrayList} of {@link Constellation}s in the night sky
     */
    public Nightsky(ArrayList<Star> stars, ArrayList<Constellation> constellations) {
        this.stars = stars;
        this.constellations = constellations;
    }

    //GETTERS
    /**
     * Retrieves the {@link Star}s in the night sky
     * @return An {@link ArrayList} of all {@link Star}s in the nightsky
     */
    public ArrayList<Star> getStars() {
        return new ArrayList<>(stars);
    }

    /**
     * Retrieves all the constellations in the night sky
     * @return An {@link ArrayList} of {@link Constellation}s in the night sky
     */
    public ArrayList<Constellation> getConstellations() {
        return new ArrayList<>(constellations);
    }

    //METHODS
    /**
     * Adds a new {@link Star} to the night sky
     * @param star A {@link Star} Added to the night sky
     */
    public void addStar(Star star) {
        this.stars.add(star);
    }

    /**
     * Adds a new {@link Constellation} to the night sky
     * @param constellation A {@link Constellation} Added to the night sky
     */
    public void addConstellation(Constellation constellation) {
        this.constellations.add(constellation);
    }
}
