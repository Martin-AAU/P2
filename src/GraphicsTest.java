import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
        Button button1 = new Button("Hephey");

        for (int i = 0; i < 50; i++) {
            int hest = i;
            circle[i] = new Circle(rng.nextInt(1024), rng.nextInt(768),15, Color.GREEN);
            circle[i].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                button1.setText("Circle number:" + hest);
            } );
        }

        primaryStage.setTitle("Demo");
        Group root = new Group();
        root.getChildren().addAll(circle);
        root.getChildren().add(button1);

        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }
}
