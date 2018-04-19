package dk.aau.sw2_18_a305.nightsky;

import java.util.ArrayList;

public class Nightsky {
    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Constellation> constellations = new ArrayList<>();

    //CONSTRUCTORS
    public Nightsky() { }
    public Nightsky(ArrayList<Star> stars) {
        this.stars = stars;
    }
    public Nightsky(ArrayList<Star> stars, ArrayList<Constellation> constellations) {
        this.stars = stars;
        this.constellations = constellations;
    }

    //GETTERS
    public ArrayList<Star> getStars() {
        return stars;
    }
    public ArrayList<Constellation> getConstellations() {
        return constellations;
    }

    //METHODS
    public void addStar(Star star) {
        this.stars.add(star);
    }

    public void addConstellation(Constellation constellation) {
        this.constellations.add(constellation);
    }

}
