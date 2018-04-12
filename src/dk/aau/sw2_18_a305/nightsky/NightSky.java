package dk.aau.sw2_18_a305.nightsky;

import dk.aau.sw2_18_a305.nightsky.exceptions.IllegalHeightException;
import dk.aau.sw2_18_a305.nightsky.exceptions.IllegalWidthException;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class NightSky extends Group{
    private int height, width, activeConstellation;
    private ArrayList<Circle> stars;
    private ArrayList<Constellation> constellations;

    //Constructors
    public NightSky(int height, int width) {
        checkHeight(height);
        checkWidth(width);

        this.height = height;
        this.width = width;
    }
    public NightSky(int height, int width, ArrayList<Circle> stars) {
        checkHeight(height);
        checkWidth(width);

        this.height = height;
        this.width = width;
        this.stars = stars;
        this.getChildren().addAll(stars);
    }
    public NightSky(int height, int width, ArrayList<Circle> stars, ArrayList<Constellation> constellations) {
        checkHeight(height);
        checkWidth(width);

        this.height = height;
        this.width = width;
        this.stars = stars;
        this.getChildren().addAll(stars);
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
    public int getActiveConstellation() {
        return activeConstellation;
    }

    //Setters
    public void setHeight(int height) {
        checkHeight(height);
        this.height = height;
    }
    public void setWidth(int width) {
        checkHeight(height);
        this.width = width;
    }
    public void setStars(ArrayList<Circle> stars) {
        this.stars = stars;
    }
    public void setConstellations(ArrayList<Constellation> constellations) {
        this.constellations = constellations;
    }
    public void setActiveConstellation(int activeConstellation) {
        this.activeConstellation = activeConstellation;
    }

    private void checkHeight(int height) {
        if(!isHeightValid(height))
            throw new IllegalHeightException(height);
    }

    private void checkWidth(int width) {
        if(!isWidthValid(width))
            throw new IllegalWidthException(width);
    }

    private boolean isHeightValid(int height) {
        return height > 0;
    }

    private boolean isWidthValid(int width) {
        return width > 0;
    }
}
