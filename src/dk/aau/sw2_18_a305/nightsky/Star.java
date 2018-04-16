package dk.aau.sw2_18_a305.nightsky;

public class Star {
    private int xCoordinate, yCoordinate;
    private String starClass, apparantColour;
    private double apparantMagnitude, magnitude, radius, mass, temperature;

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
    public String getApparantColour() {
        return apparantColour;
    }
    public double getApparantMagnitude() {
        return apparantMagnitude;
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

    //SETTERS
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
    public void setApparantColour(String apparantColour) {
        //Make rules for input
        this.apparantColour = apparantColour;
    }
    public void setApparantMagnitude(double apparantMagnitude) {
        //Make Rules for input
        this.apparantMagnitude = apparantMagnitude;
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

    //Constructors
    public Star(int xCoordinate, int yCoordinate) {
        if (checkCoordinate(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (checkCoordinate(yCoordinate))
            this.yCoordinate = yCoordinate;
    }
    public Star(int xCoordinate, int yCoordinate, String apparantColour) {
        if (checkCoordinate(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (checkCoordinate(yCoordinate))
            this.yCoordinate = yCoordinate;
        this.apparantColour = apparantColour;
    }

    //Check methods
    private boolean checkCoordinate(double coordinate) {
        return coordinate >= 0;
    }
}
