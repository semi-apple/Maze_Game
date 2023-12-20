package Maze;

import java.util.Objects;

/**
 * A set stored a boolean and a GameState.
 *
 * This is used for receiving the results of performAction() function in MazeEnv class.
 */
public class ActionResults {
    private GameState state;
    private boolean success;

    public ActionResults(boolean success, GameState state) {
        this.state = state;
        this.success = success;
    }

    public GameState getState() { return state; }

    public boolean isSuccess() { return success; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ActionResults other = (ActionResults) obj;
        return (this.state == other.state && this.success == other.success);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, success);
    }
}
