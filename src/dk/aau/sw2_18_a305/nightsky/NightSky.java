package dk.aau.sw2_18_a305.nightsky;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class NightSky extends Group{
    private int height, width;
    private ArrayList<Circle> stars;
    private ArrayList<Constellation> constellations;

    //Constructors
    public NightSky(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public NightSky(int height, int width, ArrayList<Circle> stars) {
        this.height = height;
        this.width = width;
        this.stars = stars;
    }
    public NightSky(int height, int width, ArrayList<Circle> stars, ArrayList<Constellation> constellations) {
        this.height = height;
        this.width = width;
        this.stars = stars;
        this.constellations = constellations;
    }

    //Getters
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public ArrayList<Circle> getStars() {
        return stars;
    }
    public ArrayList<Constellation> getConstellations() {
        return constellations;
    }

    //Setters
    public void setStars(ArrayList<Circle> stars) {
        this.stars = stars;
    }

    public void setConstellations(ArrayList<Constellation> constellations) {
        this.constellations = constellations;
    }
}
