package Maze;

import java.util.Objects;

/**
 * This class represents each position of the maze.
 */
public class GameState {
    protected int row, col;

    public GameState(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // return the current position.
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = this.row;
        position[1] = this.col;
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameState other = (GameState) obj;
        return (this.row == other.row && this.col == other.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
