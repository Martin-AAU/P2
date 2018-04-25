import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class DrawGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private int amount = 50;
    public int width = 1024;
    private int height = 768;

    public void start(Stage primaryStage) {

        ArrayList<Circle> circles = new ArrayList<>();
        Random random = new Random();

        // Generate nightsky
        Nightsky nightsky = new Nightsky();
        ArrayList<Star> allStars = nightsky.getStars();

        // Add a constellation to the Nightsky
        nightsky.addConstellation(new Constellation("Music Constellation"));
        ArrayList<Star> conStars = nightsky.getConstellations().get(0).getStars();

        // Make group
        Group nightskyScene = new Group();

        // Generate stars in arraylist
        for(int i = 0; i < amount; i++) {
            nightsky.addStar(new Star(random.nextInt(width), random.nextInt(height), "BLUE"));
        }
        System.out.println(allStars.size());

        // Generates circles and lines from the stars x and y positions
        for(int i = 0; i < amount; i++) {
            Circle c = new Circle(allStars.get(i).getxCoordinate(), allStars.get(i).getyCoordinate(), 7+ random.nextInt(8), Color.BLUE);
            int finalI = i;

            c.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                nightsky.getConstellations().get(0).addStar(nightsky.getStars().get(finalI));
                System.out.println(conStars.size());

                if(conStars.size() > 1) {
                    int size = nightsky.getConstellations().get(0).getStars().size();
                    //ArrayList<Star> stars = nightsky.getConstellations().get(0).getStars();

                    Line l = new Line(conStars.get(size-2).getxCoordinate(), conStars.get(size-2).getyCoordinate(), conStars.get(size-1).getxCoordinate(), conStars.get(size-1).getyCoordinate());
                    nightskyScene.getChildren().add(l);
                }
            } );
            circles.add(c);
        }
        System.out.println(allStars.size());

        // add circles to group
        nightskyScene.getChildren().addAll(circles);

        // create scene
        primaryStage.setTitle("NightSky");
        primaryStage.setScene(new Scene(nightskyScene, width, height));

        // show scene
        primaryStage.show();
    }
}
