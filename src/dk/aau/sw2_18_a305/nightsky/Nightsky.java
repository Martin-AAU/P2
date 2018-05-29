package dk.aau.sw2_18_a305.nightsky;

import java.util.ArrayList;

/**
 * This class represents a night sky witha collection of {@link Star}s and {@link Constellation}s
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
    public Nightsky() {}
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

        // Add all stars from the constellations to the night sky
        for (Constellation constellation : constellations) {
            for (Star star : constellation.getStars()) {
                if(!stars.contains(star)) {
                    stars.add(star);
                }
            }
        }
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
        addAllStars(constellation);
    }
    /**
     * Adds all non dublicate stars from a constellation to the nightsky
     * @param constellation The constellation of which the stars are added from
     */
    private void addAllStars(Constellation constellation) {
        for (Star star : constellation.getStars()) {
            if (!stars.contains(star)) {
                stars.add(star);
            }
        }
    }
    /**
     * Removes all stars that from the nightsky that are present in the given constellation
     * @param constellation The constellation of which stars are removed from the nightsky
     */
    private void removeAllStars(Constellation constellation) {
        for (Star star : constellation.getStars()) {
            if(stars.contains(star)) {
                stars.remove(star);
            }
        }
    }
}
