package dk.aau.sw2_18_a305.nightsky;

import java.util.ArrayList;

public class Nightsky1 {
    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Constellation> constellations = new ArrayList<>();

    //CONSTRUCTORS
    public Nightsky1() { }
    public Nightsky1(ArrayList<Star> stars) {
        this.stars = stars;
    }
    public Nightsky1(ArrayList<Star> stars, ArrayList<Constellation> constellations) {
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

    //SETTERS
    public void setStars(ArrayList<Star> stars) {
        this.stars = stars;
    }
    public void setConstellations(ArrayList<Constellation> constellations) {
        this.constellations = constellations;
    }

}