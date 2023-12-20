package Exceptions;

/**
 * Throw exception if the maze data doesn't match the given format.
 */
public class MazeMalformedException extends Exception {
    public MazeMalformedException() {
        super("The maze data is not correctly formatted.");
    }

    public MazeMalformedException(String message) {
        super(message);
    }
}
