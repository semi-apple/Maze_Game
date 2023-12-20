package Exceptions;

/**
 * Throw exception if the maze data doesn't match the specified dimensions.
 */
public class MazeSizeMissmatchException extends Exception {
    public MazeSizeMissmatchException() {
        super("The maze dimensions do not match the provided size.");
    }

    public MazeSizeMissmatchException(String message) {
        super(message);
    }
}
