import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import dk.aau.sw2_18_a305.notation.Sheet;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
        // All of the lines drawn between the stars
        ArrayList<Line> lineArray = new ArrayList<>();
        // Generate and set icon
        Image icon = new Image("icon.png");
        primaryStage.getIcons().add(icon);

        // Add stars to the nightsky
        addStarsToNightsky(nightsky, random);

        // Create the buttons on the bottom of the GUI
        generateButtons(buttons, lineArray, nightsky, nightskyScene);

        // Add a picture as the background of the nightskyScene
        addSkyBackgroundImage(nightskyScene);

        // Generate circles
        ArrayList<Circle> circles = generateCircles(nightsky, nightskyScene, lineArray);

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

    private static ArrayList<Circle> generateCircles(Nightsky nightsky, Group nightskyScene, ArrayList<Line> lineArray) {
        // Circles is to be returned, stars is used as a shortcut
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Star> stars = nightsky.getStars();
        Random random = new Random();
        // Setup star pictures
        int randomColorNumber;
        Image star01 = new Image("Star01.png");
        Image star02 = new Image("Star02.png");
        Image star03 = new Image("Star03.png");
        Image star04 = new Image("Star04.png");
        Image star05 = new Image("Star05.png");
        Image star06 = new Image("Star06.png");

        // Generates circles and lines from the stars x and y positions
        for(int i = 0; i < stars.size(); i++) {
            randomColorNumber = random.nextInt(25);

            // Create a new circle on the same coordinates of a star, and a random radius
            Circle c = new Circle(stars.get(i).getxCoordinate(), stars.get(i).getyCoordinate(), 10 + random.nextInt(10), Color.LIGHTBLUE);

            // Small chance not to use the white texture
            switch (randomColorNumber){
                case 1:
                    c.setFill(new ImagePattern(star01));
                    break;
                case 2:
                    c.setFill(new ImagePattern(star02));
                    break;
                case 3:
                    c.setFill(new ImagePattern(star03));
                    break;
                case 4:
                    c.setFill(new ImagePattern(star04));
                    break;
                case 5:
                    c.setFill(new ImagePattern(star05));
                    break;
                case 6:
                    c.setFill(new ImagePattern(star06));
                    break;
                default:
                    c.setFill(new ImagePattern(star01));
                    break;
            }

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

                    l.setStroke(Color.WHITESMOKE);

                    lineArray.add(l);
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

    private void generateButtons(HBox buttons, ArrayList<Line> lineArray, Nightsky nightsky, Group nightskyScene){
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

        // Setup functionality
        buttonExit.setOnMouseClicked(e -> Platform.exit());
        buttonUndo.setOnMouseClicked(e -> undoStarChoice(lineArray, nightsky, nightskyScene));
        buttonGen.setOnMouseClicked(e -> generateMidiFile(nightsky.getConstellations().get(0)));
    }

    private void undoStarChoice(ArrayList<Line> lineArray, Nightsky nightsky, Group nightskyScene){
        if (lineArray.size() > 1){
            nightskyScene.getChildren().remove(lineArray.get(lineArray.size() - 1));
            lineArray.remove(lineArray.size() - 1);
            nightsky.getConstellations().get(0).removeLastStar();
        } else if (lineArray.size() == 1){
            nightskyScene.getChildren().remove(lineArray.get(lineArray.size() - 1));
            lineArray.clear();
            nightsky.getConstellations().get(0).removeLastStar();
        }
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

    private void generateMidiFile(Constellation constellation) {
        // Create a sheet from the constellation and a sequence from the sheet
        Sheet sheet = ConstellationToSheetConverter.convert(constellation);
        Sequence sequence = sheet.convertToMidiTrack();

        File file = new File("StarSound.mid");
        // Get the midi file type, and write the sequence to the Midi file
        int[] type = MidiSystem.getMidiFileTypes(sequence);

        try {
            MidiSystem.write(sequence, type[0], file);
        } catch (IOException e) {
            System.out.println("Could not write sequence onto the file");
        }
    }
}
