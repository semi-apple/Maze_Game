package Exceptions;

/**
 * Throw exception if the maze is not solvable.
 */
public class MazeNotSolvable extends Exception {
    public MazeNotSolvable() {
        super("The maze is not solvable");
    }
}
