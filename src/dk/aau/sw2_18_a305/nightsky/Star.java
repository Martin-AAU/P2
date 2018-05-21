package dk.aau.sw2_18_a305.nightsky;

/**
 * This class represents a star
 */
public class Star {
    // FIELDS
    /**
     * The x coordinate of the Star
     */
    private int xCoordinate;
    /**
     * The y coordinate of the star
     */
    private int yCoordinate;
    /**
     * The classification of the star
     */
    private String starClass;
    /**
     * The apparent color if the star. The color of the star as it appears to be in the sky
     */
    private String apparentColour;
    /**
     * How bright the star appears to be (scale from -1 to 10)
     */
    private double apparentMagnitude;
    /**
     * The apparent magnitude of the star if it was 10 parsecs away (scale from -1 to 10)
     */
    private double absoluteMagnitude;
    /**
     * The radius of the star
     */
    private double radius;
    /**
     * The mass of the star
     */
    private double mass;
    /**
     * The temperature of the star
     */
    private double temperature;

    // CONSTRUCTORS
    /**
     * Constructs a Star with an x coordinate and a y coordinate
     * @param xCoordinate The x coordinate of the star
     * @param yCoordinate The y coordinate of the star
     */
    public Star(int xCoordinate, int yCoordinate) {
        if (isVaild(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (isVaild(yCoordinate))
            this.yCoordinate = yCoordinate;
    }

    /**
     * Constructs a star with an x coordinate, y coordinate and an apparent colour
     * @param xCoordinate The x coordinate of the star
     * @param yCoordinate The y coordinate of the star
     * @param apparentColour The apparent colour of the star
     */
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
        return absoluteMagnitude;
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
            this.absoluteMagnitude = magnitude;
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
