package dk.aau.sw2_18_a305.nightsky.exceptions;

public class IllegalHeightException extends RuntimeException{
    private int height;

    public IllegalHeightException(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }
}
