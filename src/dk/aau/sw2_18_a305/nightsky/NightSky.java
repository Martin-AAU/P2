package dk.aau.sw2_18_a305.nightsky;

import dk.aau.sw2_18_a305.nightsky.exceptions.IllegalHeightException;
import dk.aau.sw2_18_a305.nightsky.exceptions.IllegalWidthException;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class NightSky extends Group{
    private int height, width, activeConstellation = 0;
    private ArrayList<Circle> stars = new ArrayList<>();
    private ArrayList<Constellation> constellations = new ArrayList<>();

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
        checkWidth(width);
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

    public void generateStars(int ammount) {
        constellations.add(new Constellation());
        Random random = new Random();
        stars.add(new Circle(random.nextInt(width), random.nextInt(height), 3+random.nextInt(4), Color.BLUE));

        for(int i = 0; i < ammount-1; i++) {

            Circle c = new Circle(random.nextInt(width), random.nextInt(height), 3+ random.nextInt(4), Color.BLUE);
            c.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                this.constellations.get(activeConstellation).addStar(c);
                updateGroup();
            } );

            stars.add(c);
            /*for (int k = 0; k < stars.size()-1; k++){
                System.out.println("Distance between Circle " + i + " and star " + k + "mis: " + dist(c.getCenterX(), c.getCenterY(), stars.get(k).getCenterX(), stars.get(k).getCenterY()));
                if (dist(c.getCenterX(), c.getCenterY(), stars.get(k).getCenterX(), stars.get(k).getCenterY()) > c.getRadius() + stars.get(k).getRadius() + 2) {
                        stars.add(c);
                }
            }*/
        }
        updateGroup();
    }

    private void updateGroup() {
        this.getChildren().clear();

        for(int i = 0; i < constellations.size(); i++) {
            getChildren().addAll(constellations.get(i).getLines());
        }

        getChildren().addAll(stars);
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}
