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
        if (isVaild(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (isVaild(yCoordinate))
            this.yCoordinate = yCoordinate;
    }
    public Star(int xCoordinate, int yCoordinate, String apparentColour) {
        if (isVaild(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (isVaild(yCoordinate))
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
        if (isVaild(xCoordinate)) {
            this.xCoordinate = xCoordinate;
        }
    }
    public void setyCoordinate(int yCoordinate) {
        if (isVaild(yCoordinate)) {
            this.yCoordinate = yCoordinate;
        }
    }
    public void setStarClass(String starClass) {
        this.starClass = starClass;
    }
    public void setApparentColour(String apparentColour) {
        this.apparentColour = apparentColour;
    }
    public void setApparentMagnitude(double apparentMagnitude) {
        if (isVaild(apparentMagnitude)){
            this.apparentMagnitude = apparentMagnitude;
        }
    }
    public void setMagnitude(double magnitude) {
        if (isVaild(magnitude)){
            this.magnitude = magnitude;
        }
    }
    public void setradius(double radius) {
        if (isVaild(radius)) {
            this.radius = radius;
        }
    }
    public void setMass(double mass) {
        if (isVaild(mass)) {
            this.mass = mass;
        }
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    // Check methods
    private boolean isVaild(double test) {
        return test >= 0;
    }
}
