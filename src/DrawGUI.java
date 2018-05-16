import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class DrawGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width/1.5);
    public static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/1.5);

    public void start(Stage primaryStage) {
        Random random = new Random();

        // Generate nightsky
        Nightsky nightsky = new Nightsky();
        nightsky.addConstellation(new Constellation("Music Constellation"));

        for(int i = 0; i < 50; i++) {
            nightsky.addStar(new Star(random.nextInt(width), random.nextInt(height)));
        }

        // Creating buttons
        Button button1 = new Button("Generate MIDI file");
        Button button2 = new Button("Exit");

        VBox UI = new VBox();
        HBox buttons = new HBox();
        buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(10);
        buttons.setStyle("-fx-background-color: #336699;");
        buttons.getChildren().add(button1);
        buttons.getChildren().add(button2);

        // Make group
        Group nightskyScene = new Group();

        // Background image
        ImageView iv = new ImageView();
        Image image = new Image("NightskyBG.png");
        iv.setImage(image);
        iv.setFitHeight(height);
        iv.setFitWidth(width);
        nightskyScene.getChildren().add(iv);

        // Generate circles
        ArrayList<Circle> circles = generateCircles(nightsky, nightskyScene);

        // Add circles to group
        nightskyScene.getChildren().addAll(circles);

        // Add nightsky and buttons to the UI
        UI.getChildren().add(nightskyScene);
        UI.getChildren().add(buttons);

        // Setup the Scene
        primaryStage.setTitle("NightSky");
        primaryStage.setScene(new Scene(UI));
        primaryStage.setResizable(false);

        // show scene
        primaryStage.show();

    }

    private static ArrayList<Circle> generateCircles(Nightsky nightsky, Group nightskyScene) {
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Star> stars = nightsky.getStars();
        Random random = new Random();

        // Generates circles and lines from the stars x and y positions
        for(int i = 0; i < stars.size(); i++) {
            Circle c = new Circle(stars.get(i).getxCoordinate(), stars.get(i).getyCoordinate(), 7+ random.nextInt(8), Color.BLUE);
            int finalI = i;

            c.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                nightsky.getConstellations().get(0).addStar(nightsky.getStars().get(finalI));

                ArrayList<Star> conStars = nightsky.getConstellations().get(0).getStars();

                if(conStars.size() > 1) {
                    int size = nightsky.getConstellations().get(0).getStars().size();
                    //ArrayList<Star> stars = nightsky.getConstellations().get(0).getStars();

                    Line l = new Line(conStars.get(size-2).getxCoordinate(), conStars.get(size-2).getyCoordinate(), conStars.get(size-1).getxCoordinate(), conStars.get(size-1).getyCoordinate());
                    nightskyScene.getChildren().add(l);
                }
            } );
            circles.add(c);
        }

        return circles;
    }
}
