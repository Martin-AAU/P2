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

import java.util.ArrayList;
import java.util.Random;

public class DrawGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    ArrayList<Star> stars = new ArrayList<>();
    ArrayList<Star> chosenStars = new ArrayList<>();
    ArrayList<Circle> circles = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    Random random = new Random();
    int amount = 50;
    int width = 1024;
    int height = 768;

        // generate stars in arraylist
        for(int i = 0; i < amount; i++) {
            stars.add(new Star(random.nextInt(width), random.nextInt(height), "BLUE"));
        }
        System.out.println(stars.size());

        // generate nightsky with said arraylist of stars
        Nightsky nightsky = new Nightsky(stars);

        // generate constellation with empty arraylist of stars
        Constellation constellation = new Constellation("constellation", chosenStars);

        // generates circles from the stars x and y positions
        for(int i = 0; i < amount; i++) {
            Circle c = new Circle(stars.get(i).getxCoordinate(), stars.get(i).getyCoordinate(), 7+ random.nextInt(8), Color.BLUE);
            int finalI = i;
            c.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                constellation.addStar(stars.get(finalI));
                System.out.println(constellation.getStars().size());
                if(finalI > 2) {
                    Line l = new Line(constellation.getStars().get(finalI-1).getxCoordinate(), constellation.getStars().get(finalI-1).getyCoordinate(), constellation.getStars().get(finalI).getxCoordinate(), constellation.getStars().get(finalI).getyCoordinate());
                    lines.add(l);
                }
            } );
            circles.add(c);
        }
        System.out.println(stars.size());

        // make group
        Group nightskyScene = new Group();
        nightskyScene.getChildren().addAll(circles);
        nightskyScene.getChildren().addAll(lines);

        // create scene
        primaryStage.setTitle("NightSky");
        primaryStage.setScene(new Scene(nightskyScene, width, height));

        // show scene
        primaryStage.show();
    }
}
