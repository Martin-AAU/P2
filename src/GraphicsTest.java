import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.*;

public class GraphicsTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Random rng = new Random();

        Circle[] circle = new Circle[50];

        for (int i = 0; i < 50; i++) {
            circle[i] = new Circle(rng.nextInt(1024), rng.nextInt(768),15, Color.GREEN);
        }

        primaryStage.setTitle("Demo");
        Group root = new Group();
        root.getChildren().addAll(circle);

        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }
}
