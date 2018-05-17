import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Global variables for half width and height of screen
    public static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width/1.15);
    public static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/1.25);

    public void start(Stage primaryStage) {
        Random random = new Random();
        Nightsky nightsky = new Nightsky();
        // Box'es for bottom row
        VBox UI = new VBox();
        HBox buttons = new HBox();
        // The scene for the night-sky (where stars are placed)
        Group nightskyScene = new Group();

        // Add stars to the nightsky
        addStarsToNightsky(nightsky, random);

        // Create the buttons on the bottom of the GUI
        generateButtons(buttons);

        // Add a picture as the background of the nightskyScene
        addSkyBackgroundImage(nightskyScene);

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
        // circles is to be returned, stars is used as a shortcut
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Star> stars = nightsky.getStars();
        Random random = new Random();


        // Generates circles and lines from the stars x and y positions
        for(int i = 0; i < stars.size(); i++) {

            // Create a new circle on the same coordinates of a star, and a random radius
            Circle c = new Circle(stars.get(i).getxCoordinate(), stars.get(i).getyCoordinate(), 7 + random.nextInt(8), Color.LIGHTBLUE);
            int finalI = i;

            // Add an eventhandler to the circle, that triggers when you click on the circle
            c.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {

                // Add the star represented as a circle to the constellation
                nightsky.getConstellations().get(0).addStar(stars.get(finalI));
                ArrayList<Star> conStars = nightsky.getConstellations().get(0).getStars();

                if(conStars.size() > 1) {
                    int size = nightsky.getConstellations().get(0).getStars().size();

                    // Draw a line between current and previous star
                    Line l = new Line(conStars.get(size-2).getxCoordinate(), conStars.get(size-2).getyCoordinate(), conStars.get(size-1).getxCoordinate(), conStars.get(size-1).getyCoordinate());
                    l.setStroke(Color.YELLOW);
                    nightskyScene.getChildren().add(l);
                }
            } );

            circles.add(c);
        }

        return circles;
    }

    private void addStarsToNightsky(Nightsky nightsky, Random random){
        // Generate nightsky and add stars to it
        nightsky.addConstellation(new Constellation("Music Constellation"));
        for(int i = 0; i < 50; i++) {
            nightsky.addStar(new Star(random.nextInt(width-30)+15, random.nextInt(height-30)+15));
        }
    }

    private void generateButtons(HBox buttons){
        // Creating buttons
        Button buttonGen = new Button("Generate .MIDI file");
        Button buttonPlay = new Button("Play");
        Button buttonUndo = new Button("Undo");
        Button buttonExit = new Button("Exit");
        ArrayList<Button> buttonArray = new ArrayList<>();

        // Fill arrayList
        buttonArray.add(buttonGen);
        buttonArray.add(buttonPlay);
        buttonArray.add(buttonUndo);
        buttonArray.add(buttonExit);

        // Setup buttons GUI
        buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(10);
        buttons.setStyle("-fx-background-color: linear-gradient(#0d1a26, #070d13);");
        buttons.getChildren().add(buttonGen);
        buttons.getChildren().add(buttonPlay);
        buttons.getChildren().add(buttonUndo);
        buttons.getChildren().add(buttonExit);
        styleButtons(buttonArray);

        // Exit functionality
        buttonExit.setOnMouseClicked(e -> Platform.exit());
    }

    // Function that loops through every button and sets their style
    private void styleButtons(ArrayList<Button> bA){
        Button b;

        // Loop through every button and set style
        for (int i = 0; i < bA.size(); i++){
            b = bA.get(i);

            b.setPrefSize((width / bA.size()) - 15, height / 13);
            b.setStyle("-fx-background-color: #21415f, " +
                       "linear-gradient(#0d1a26, #1a344c); " +
                       "-fx-text-fill: linear-gradient(#ffffff, #c6d9eb); " +
                       "-fx-font-size: 20px; " +
                       "-fx-background-insets: 0,1.5,5,5,5;" +
                       "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                       "-fx-font-family: "+ "Tahoma"+";");
        }
    }

    private void addSkyBackgroundImage(Group nightskyScene){
        // Background image sky
        ImageView ivSky = new ImageView();
        Image imageS = new Image("NightskyBG.jpg");
        ivSky.setImage(imageS);
        ivSky.setFitHeight(height);
        ivSky.setFitWidth(width);
        nightskyScene.getChildren().add(ivSky);
    }
}
