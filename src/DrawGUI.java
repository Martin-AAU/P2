import dk.aau.sw2_18_a305.nightsky.NightSky0;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        NightSky0 nightSky0 = new NightSky0(1024, 768);
        nightSky0.generateStars(100);

        primaryStage.setTitle("NightSky0");
        primaryStage.setScene(new Scene(nightSky0, 1024, 768));

        primaryStage.show();
    }
}
