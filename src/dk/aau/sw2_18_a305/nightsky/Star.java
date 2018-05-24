package dk.aau.sw2_18_a305.nightsky;

import java.util.Objects;

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
    private int apparentMagnitude;
    /**
     * The apparent magnitude of the star if it was 10 parsecs away (scale from -1 to 10)
     */
    private int absoluteMagnitude;
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
    /**
     * The distance to the star
     */
    private double distance;

    // CONSTRUCTORS
    /**
     * Constructs a Star with an x coordinate and a y coordinate
     * @param xCoordinate The x coordinate of the star
     * @param yCoordinate The y coordinate of the star
     */
    public Star(int xCoordinate, int yCoordinate) {
        if (isGreaterThanZero(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (isGreaterThanZero(yCoordinate))
            this.yCoordinate = yCoordinate;
    }
    /**
     * Constructs a star with an x coordinate, y coordinate and an apparent colour
     * @param xCoordinate The x coordinate of the star
     * @param yCoordinate The y coordinate of the star
     * @param distance The distance to the star
     */
    public Star(int xCoordinate, int yCoordinate, double distance) {
        if (isGreaterThanZero(xCoordinate))
            this.xCoordinate = xCoordinate;
        if (isGreaterThanZero(yCoordinate))
            this.yCoordinate = yCoordinate;
        this.apparentColour = apparentColour;
    }

    //GETTERS
    /**
     * Retreives the x coordinate
     * @return The x coordinate of the star
     */
    public int getxCoordinate() {
        return xCoordinate;
    }
    /**
     * Retreives the y coordinate
     * @return The y coordinate of the star
     */
    public int getyCoordinate() {
        return yCoordinate;
    }
    /**
     * Retreives the star class
     * @return A string containing the classification of the star
     */
    public String getStarClass() {
        return starClass;
    }
    /**
     * Retrieves the apparent colour of the star
     * @return A string containing the apparent colour of the star
     */
    public String getApparentColour() {
        return apparentColour;
    }
    /**
     * Retreives the apparent magnitude of the star
     * @return The apparent magnitude (apparent brightness)
     */
    public int getApparentMagnitude() {
        return apparentMagnitude;
    }
    /**
     * Retreives the absolute magnitude of the star
     * @return The absolute magnitude of the star (brightness at 10 parsec)
     */
    public int getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }
    /**
     * Retreives the radius of the star
     * @return The radius of the star
     */
    public double getradius() {
        return radius;
    }
    /**
     * Retreives the mass of the star
     * @return The mass of the star
     */
    public double getMass() {
        return mass;
    }
    /**
     * Retreives the temperature of the star
     * @return The temperature of the star
     */
    public double getTemperature() {
        return temperature;
    }
    /**
     * Retreives the distance to the star
     * @return The distance to the star
     */
    public double getDistance() {
        return distance;
    }

    // SETTERS
    /**
     * Sets a new x coordinate for the star, cannot be negative
     * @param xCoordinate The new x coordinate
     */
    public void setxCoordinate(int xCoordinate) {
        if (isGreaterThanZero(xCoordinate)) {
            this.xCoordinate = xCoordinate;
        }
    }
    /**
     * Sets a new y coordinate for the star, cannot be negative
     * @param yCoordinate The new y coordinate
     */
    public void setyCoordinate(int yCoordinate) {
        if (isGreaterThanZero(yCoordinate)) {
            this.yCoordinate = yCoordinate;
        }
    }
    /**
     * Sets a new classification for the star
     * @param starClass The new classification of the star
     */
    public void setStarClass(String starClass) {
        this.starClass = starClass;
    }
    /**
     * Sets the apparent colour of the star
     * @param apparentColour The new apparent colour
     */
    public void setApparentColour(String apparentColour) {
        this.apparentColour = apparentColour;
    }
    /**
     * Sets the apparent magnitude of the star, can only be between -1 and 10
     * @param apparentMagnitude The magnitude of the star
     */
    public void setApparentMagnitude(int apparentMagnitude) {
        if (apparentMagnitude > -1 && apparentMagnitude < 10){
            this.apparentMagnitude = apparentMagnitude;
        }
    }
    /**
     * Sets the absolute magnitude of the star
     * @param absoluteMagnitude The absolute magnitude of the star
     */
    public void setAbsoluteMagnitude(int absoluteMagnitude) {
        if (isGreaterThanZero(absoluteMagnitude)){
            this.absoluteMagnitude = absoluteMagnitude;
        }
    }
    /**
     * Sets the radius of the star, cannot be negative
     * @param radius The radius of the star
     */
    public void setradius(double radius) {
        if (isGreaterThanZero(radius)) {
            this.radius = radius;
        }
    }
    /**
     * Sets the mass of the star, cannot be negative
     * @param mass The mass of the star
     */
    public void setMass(double mass) {
        if (isGreaterThanZero(mass)) {
            this.mass = mass;
        }
    }
    /**
     * Sets the temperature of the star, cannot be negative (using kelvin)
     * @param temperature The temperature of the star
     */
    public void setTemperature(double temperature) {
        if (isGreaterThanZero(temperature)){
            this.temperature = temperature;
        }
    }
    /**
     * Sets the distance to the star, cannot be negative
     * @param distance The distance to the star
     */
    public void setDistance(double distance) {
        if (isGreaterThanZero(distance)){
            this.distance = distance;
        }
    }

    // Check methods
    /**
     * Checks if a double is positive
     * @param test The value that is to be tested
     * @return True if the test value is greater than or equal to zero. Returns false if test value is negative
     */
    private boolean isGreaterThanZero(double test) {
        return test >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return xCoordinate == star.xCoordinate &&
                yCoordinate == star.yCoordinate &&
                Double.compare(star.distance, distance) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(xCoordinate, yCoordinate, distance);
    }
}
