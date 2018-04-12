package dk.aau.sw2_18_a305.nightsky;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Constellation extends Group{
    private ArrayList<Circle> stars;
    private ArrayList<Line> lines;

    //Constructors
    public Constellation() {
    }
    public Constellation(ArrayList<Circle> stars) {
        this.stars = stars;
    }

    void generateLines() {
        if(stars.size() > 1) {
            for (int i = 0; i < stars.size(); i++) {
                Line l = new Line(this.stars.get(i).getCenterX(), this.stars.get(i).getCenterY(), this.stars.get(i+1).getCenterX(), this.stars.get(i+1).getCenterY());
                lines.add(l);
            }
            getChildren().addAll(lines);
        }
    }
}
