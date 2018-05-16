/*
    Thomas Møller Grosen
    tgrose17@student.aau.dk
*/

import dk.aau.sw2_18_a305.nightsky.Constellation;
import dk.aau.sw2_18_a305.nightsky.Nightsky;
import dk.aau.sw2_18_a305.nightsky.Star;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StarSound {

    public static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width/1.5);
    public static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/1.5);

    public static void main(String[] args) {
        Nightsky nightsky = new Nightsky();
        nightsky.addConstellation(new Constellation("Music Constellation"));

        generateStars(nightsky);
    }

    private static void generateStars(Nightsky nightsky) {
        Random random = new Random();

        for(int i = 0; i < 50; i++)
            nightsky.addStar(new Star(random.nextInt(width), random.nextInt(height)));
    }
}
