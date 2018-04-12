package dk.aau.sw2_18_a305.nightsky.exceptions;

public class IllegalWidthException extends RuntimeException{
    private int width;

    public IllegalWidthException(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}