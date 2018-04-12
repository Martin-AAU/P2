import dk.aau.sw2_18_a305.nightsky.NightSky;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        NightSky nightSky = new NightSky(1024, 768);
        nightSky.generateStars(100);

        primaryStage.setTitle("NightSky");
        primaryStage.setScene(new Scene(nightSky, 1024, 768));

        primaryStage.show();
    }
}
