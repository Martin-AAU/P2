package dk.aau.sw2_18_a305.nightsky;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawGUI extends Application {
    NightSky nightSky = new NightSky(1024, 768);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("NightSky");
        primaryStage.setScene(new Scene(nightSky, 1024, 768));
    }
}
