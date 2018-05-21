package dk.aau.sw2_18_a305.nightsky;

/**
 * This class represents a star
 */

public class Star {
    // FIELDS
    private int xCoordinate, yCoordinate;
    private String starClass, apparentColour;
    private double apparentMagnitude, magnitude, radius, mass, temperature;

    // CONSTRUCTORS
    public Star(int xCoordinate, int yCoordinate) {
        if (checkCoordinate(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (checkCoordinate(yCoordinate))
            this.yCoordinate = yCoordinate;
    }
    public Star(int xCoordinate, int yCoordinate, String apparentColour) {
        if (checkCoordinate(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (checkCoordinate(yCoordinate))
            this.yCoordinate = yCoordinate;
        this.apparentColour = apparentColour;
    }

    //GETTERS
    public int getxCoordinate() {
        return xCoordinate;
    }
    public int getyCoordinate() {
        return yCoordinate;
    }
    public String getStarClass() {
        return starClass;
    }
    public String getApparentColour() {
        return apparentColour;
    }
    public double getApparentMagnitude() {
        return apparentMagnitude;
    }
    public double getMagnitude() {
        return magnitude;
    }
    public double getradius() {
        return radius;
    }
    public double getMass() {
        return mass;
    }
    public double getTemperature() {
        return temperature;
    }

    // SETTERS
    public void setxCoordinate(int xCoordinate) {
        if (checkCoordinate(xCoordinate))
            this.xCoordinate = xCoordinate;
    }
    public void setyCoordinate(int yCoordinate) {
        if (checkCoordinate(yCoordinate))
            this.yCoordinate = yCoordinate;
    }
    public void setStarClass(String starClass) {
        //Make rules for starClass  ex. "B5V"
        this.starClass = starClass;
    }
    public void setApparentColour(String apparentColour) {
        //Make rules for input
        this.apparentColour = apparentColour;
    }
    public void setApparentMagnitude(double apparentMagnitude) {
        //Make Rules for input
        this.apparentMagnitude = apparentMagnitude;
    }
    public void setMagnitude(double magnitude) {
        //Make rules for input
        this.magnitude = magnitude;
    }
    public void setradius(double radius) {
        //Make rules for input
        this.radius = radius;
    }
    public void setMass(double mass) {
        //Make rules for input
        this.mass = mass;
    }
    public void setTemperature(double temperature) {
        //Make rules for input
        this.temperature = temperature;
    }

    //Check methods
    private boolean checkCoordinate(double coordinate) {
        return coordinate >= 0;
    }
}
