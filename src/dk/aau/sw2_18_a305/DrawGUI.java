package dk.aau.sw2_18_a305;

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
import javafx.stage.Stage;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The main class of the application. Visualises the GUI, runs the buttons and their opperations on the stars, constellations and midi file
 */
public class DrawGUI extends Application {

    /**
     * The width of the night sky window minus the buttons.
     * Is set to the width of the computer screen divided by 1.1
     */
    private static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width/1.10);
    /**
     * The height of the night sky window minus the buttons.
     * Is set to the height of the computer screen divided by 1.25
     */
    private static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/1.25);

    /**
     * Main method, runs launch to create the GUI with javafx
     * @param args Arguments passed by the caller
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method that includes almost all functionality in the <code>DrawGUI</code> class
     * @param primaryStage The stage that makes up the GUI
     */
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
        Image icon = new Image("Resources/icon.png");
        primaryStage.getIcons().add(icon);

        // Add stars to the nightsky
        addStarsToNightsky(nightsky, 50);

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

    /**
     * Generates all stars as {@link Circle}s with images. Also gives all circles {@link javafx.event.Event}s.
     * When you clicl on a circle, the star that made the circle, is added to the first constellation in the night sky.
     * Lines are drawn between all stars in a constellation
     * @param nightsky The night sky of which stars are to be visualised
     * @param nightskyScene The scene where the circles and lines are going to be added
     * @param lineArray An {@link ArrayList} of lines, where all the lines are added
     * @return An {@link ArrayList} of the circles generated
     */
    private ArrayList<Circle> generateCircles(Nightsky nightsky, Group nightskyScene, ArrayList<Line> lineArray) {
        // Circles is to be returned, stars is used as a shortcut
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Star> stars = nightsky.getStars();
        Random random = new Random();

        // Generates circles and lines from the stars x and y positions
        for(int i = 0; i < stars.size(); i++) {
            int finalI = i;

            // Create a new circle on the same coordinates of a star, and a random radius
            Circle c = new Circle(  stars.get(i).getxCoordinate(), stars.get(i).getyCoordinate(),
                                    10 + random.nextInt(10), Color.LIGHTBLUE);

            // Set a random image to the circle
            c.setFill(randomImage());

            // Add an eventhandler to the circle, that triggers when you click on the circle
            c.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {

                // Add the star represented as a circle to the constellation
                nightsky.getConstellations().get(0).addStar(stars.get(finalI));
                ArrayList<Star> conStars = nightsky.getConstellations().get(0).getStars();

                if(conStars.size() > 1) {
                    int size = nightsky.getConstellations().get(0).getStars().size();

                    // Draw a line between current and previous star
                    Line l = new Line(  conStars.get(size-2).getxCoordinate(),
                                        conStars.get(size-2).getyCoordinate(),
                                        conStars.get(size-1).getxCoordinate(),
                                        conStars.get(size-1).getyCoordinate());

                    l.setStroke(Color.WHITESMOKE);

                    lineArray.add(l);
                    nightskyScene.getChildren().add(l);
                }
            } );
            circles.add(c);
        }
        return circles;
    }

    /**
     * Generates a number of randomly placed stars in a night sky
     * @param nightsky The night sky in which the stars are added
     * @param amountOfStars The amount of stars to be generated
     */
    private void addStarsToNightsky(Nightsky nightsky, int amountOfStars){
        Random random = new Random();

        // Generate nightsky and add stars to it
        nightsky.addConstellation(new Constellation("Music Constellation"));
        for(int i = 0; i < amountOfStars; i++) {
            nightsky.addStar(new Star(random.nextInt(width-30)+15, random.nextInt(height-30)+15));
        }
    }

    /**
     * Generate the buttons and their set their actions. They are but in a {@link HBox}
     * @param buttons The {@link HBox} where the buttons are stored
     * @param lineArray The {@link ArrayList} of {@link Line}s
     * @param nightsky The {@link Nightsky} that makes up the visible stars
     * @param nightskyScene The scene that contains all stars and lines
     */
    private void generateButtons(HBox buttons, ArrayList<Line> lineArray, Nightsky nightsky, Group nightskyScene){
        // Create a midi player
        MidiPlayer midiPlayer = new MidiPlayer();

        // Creating buttons
        Button buttonPlay = new Button("Play");
        Button buttonGen = new Button("Save to MIDI");
        Button buttonUndo = new Button("Undo");
        Button buttonExit = new Button("Exit");
        ArrayList<Button> buttonArray = new ArrayList<>();

        // Fill arrayList
        buttonArray.add(buttonPlay);
        buttonArray.add(buttonGen);
        buttonArray.add(buttonUndo);
        buttonArray.add(buttonExit);

        // Setup buttons GUI
        buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(10);
        buttons.setStyle("-fx-background-color: linear-gradient(#0d1a26, #070d13);");
        buttons.getChildren().add(buttonPlay);
        buttons.getChildren().add(buttonGen);
        buttons.getChildren().add(buttonUndo);
        buttons.getChildren().add(buttonExit);
        styleButtons(buttonArray);

        // Setup functionality
        buttonExit.setOnMouseClicked(e -> exitFunctionality());
        buttonGen.setOnMouseClicked(e -> generateMidiFile(nightsky.getConstellations().get(0)));
        buttonUndo.setOnMouseClicked(e -> undoStarChoice(lineArray, nightsky, nightskyScene));
        buttonPlay.setOnMouseClicked(e -> playFunctionality(midiPlayer, nightsky));

        // Set style while mouse is pressed
        buttonExit.setOnMousePressed(e -> styleButtonPressed(buttonExit, 1));
        buttonGen.setOnMousePressed(e -> styleButtonPressed(buttonGen, 1));
        buttonUndo.setOnMousePressed(e -> styleButtonPressed(buttonUndo, 1));
        buttonPlay.setOnMousePressed(e -> styleButtonPressed(buttonPlay, 1));

        // Set style while mouse is released
        buttonExit.setOnMouseReleased(e -> styleButtonPressed(buttonExit, 0));
        buttonGen.setOnMouseReleased(e -> styleButtonPressed(buttonGen, 0));
        buttonUndo.setOnMouseReleased(e -> styleButtonPressed(buttonUndo, 0));
        buttonPlay.setOnMouseReleased(e -> styleButtonPressed(buttonPlay, 0));
    }

    /**
     * The method that is called when you press the exit button. Closes the application
     */
    private void exitFunctionality(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Is called when the Play button is pressed. Generates and plays the midi file, using the {@link MidiPlayer}
     * @param mPlayer The midi player that is used to play the file
     * @param nightsky The night sky that the midi file is generated from
     */
    private void playFunctionality(MidiPlayer mPlayer, Nightsky nightsky){
        // Generate and save .mid file
        generateMidiFile(nightsky.getConstellations().get(0));

        // Then play file
        mPlayer.playMidiFile("StarSound.mid");
    }

    /**
     * Called when the Undo button is pressed. Removes the last star from the constellation, and the last line from the {@link ArrayList}
     * @param lineArray An {@link ArrayList} of lines that should have the last line removed
     * @param nightsky The {@link Nightsky} that contains the constellation where the last star should be removed
     * @param nightskyScene The {@link Scene} that contains all stars
     */
    private void undoStarChoice(ArrayList<Line> lineArray, Nightsky nightsky, Group nightskyScene){
        if (lineArray.size() > 1){
            nightskyScene.getChildren().remove(lineArray.get(lineArray.size() - 1));
            lineArray.remove(lineArray.size() - 1);
            nightsky.getConstellations().get(0).removeLastStar();
        } else if (lineArray.size() == 1){
            nightskyScene.getChildren().remove(lineArray.get(0));
            lineArray.clear();
            nightsky.getConstellations().get(0).removeLastStar();
        }
    }

    /**
     * Sets the default button style, so the buttons look better
     * @param bA An {@link ArrayList} of all the buttons that are to be styled
     */
    private void styleButtons(ArrayList<Button> bA){
        Button b;

        // Loop through every button and set style
        for (int i = 0; i < bA.size(); i++){
            b = bA.get(i);

            b.setPrefSize((width / bA.size()) - 15, height / 13);
            b.setStyle("-fx-background-color: #21415f, " +
                       "linear-gradient(#0d1a26, #1a344c); " +
                       "-fx-text-fill: linear-gradient(#ffffff, #c6d9eb); " +
                       "-fx-font-size: 22px; " +
                       "-fx-background-insets: 0,1.5,5,5,5;" +
                       "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                       "-fx-font-family: "+ "Tahoma"+";");
        }
    }

    /**
     * Sets the button style when pressed
     * @param button An {@link Button} to be styled
     * @param condition A binary condition for whether the button should be styled as pressed (1) or as default (0)
     */
    private void styleButtonPressed(Button button, int condition) {
        // set "pressed button" style if condition is 1
        // set default style if condition is 0
        if(condition == 1) {
            button.setStyle("-fx-background-color: #00060c, " +
                    "linear-gradient(#0d1a26, #1a344c); " +
                    "-fx-text-fill: linear-gradient(#ffffff, #c6d9eb); " +
                    "-fx-font-size: 22px; " +
                    "-fx-background-insets: 0,1.5,5,5,5;" +
                    "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                    "-fx-font-family: "+ "Tahoma"+";");
        } else if(condition == 0) {
            button.setStyle("-fx-background-color: #21415f, " +
                    "linear-gradient(#0d1a26, #1a344c); " +
                    "-fx-text-fill: linear-gradient(#ffffff, #c6d9eb); " +
                    "-fx-font-size: 22px; " +
                    "-fx-background-insets: 0,1.5,5,5,5;" +
                    "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                    "-fx-font-family: "+ "Tahoma"+";");
        }
    }

    /**
     * Sets the background of the night sky scene to a beatiful picture
     * @param nightskyScene The {@link Scene} where the background is set
     */
    private void addSkyBackgroundImage(Group nightskyScene){
        // Background image sky
        ImageView ivSky = new ImageView();
        Image imageS = new Image("Resources/NightskyBG.jpg");
        ivSky.setImage(imageS);
        ivSky.setFitHeight(height);
        ivSky.setFitWidth(width);
        nightskyScene.getChildren().add(ivSky);
    }

    /**
     * Generates a midi file by a constellation. Uses {@link ConstellationToSheetConverter} to make a {@link Sheet}
     * with all notes. Then use the sheets own converter to midi.
     * @param constellation The constellation that is to be converted to a midi file
     */
    private void generateMidiFile(Constellation constellation) {
        // Create a sheet from the constellation and a sequence from the sheet
        Sheet sheet = ConstellationToSheetConverter.convert(constellation);
        Sequence sequence = sheet.convertToMidiSequence();

        File file = new File("StarSound.mid");
        // Get the midi file type, and write the sequence to the Midi file
        int[] type = MidiSystem.getMidiFileTypes(sequence);

        try {
            MidiSystem.write(sequence, type[0], file);
        } catch (IOException e) {
            System.out.println("Could not write sequence onto the file");
        }
    }

    /**
     * Chooses a random image used by the circles to visualise a star
     * @return A random image of a star
     */
    private ImagePattern randomImage() {
        Random random = new Random();

        Image star01 = new Image("Resources/Star01.png");
        Image star02 = new Image("Resources/Star02.png");
        Image star03 = new Image("Resources/Star03.png");
        Image star04 = new Image("Resources/Star04.png");
        Image star05 = new Image("Resources/Star05.png");
        Image star06 = new Image("Resources/Star06.png");

        // Small chance not to use the white texture
        switch (random.nextInt(25)){
            case 1:
                return new ImagePattern(star01);
            case 2:
                return new ImagePattern(star02);
            case 3:
                return new ImagePattern(star03);
            case 4:
                return new ImagePattern(star04);
            case 5:
                return new ImagePattern(star05);
            case 6:
                return new ImagePattern(star06);
            default:
                return new ImagePattern(star01);
        }
    }
}
