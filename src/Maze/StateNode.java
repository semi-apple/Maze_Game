package Maze;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represent each state in maze.
 * Used for find the solution in A*.
 *
 * Structure:
 * - state {@link GameState} represents current state.
 * - parentNode {@link StateNode} node represent its parent.
 * - actionFromParent is the action parent node used to get to current node.
 * - pathCost is the cost of action (default by 1).
 * - mazeEnv {@link MazeEnv} is the environment of the game.
 */
public class StateNode {
    GameState state;
    StateNode parentNode;
    Character actionFromParent;
    double pathCost;
    MazeEnv mazeEnv;
    public StateNode(MazeEnv mazeEnv, GameState state, StateNode parentNode, Character actionFromParent, double pathCost) {
        this.state = state;
        this.parentNode = parentNode;
        this.actionFromParent = actionFromParent;
        this.pathCost = pathCost;
        this.mazeEnv = mazeEnv;
    }

    /**
     * Get the series of actions from the initial state.
     * @return A series of actions.
     */
    public ArrayList<Character> getPath() {
        StateNode cur = this;
        ArrayList<Character> path = new ArrayList<>();
        while (cur.parentNode != null) {
            path.add(cur.actionFromParent);
            cur = cur.parentNode;
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Get a series of StateNode if the action is valid.
     * @return a series of StateNode used for A*.
     */
    public ArrayList<StateNode> getSuccessors() {
        ArrayList<StateNode> successors = new ArrayList<>();
        for (char a: MazeEnv.actionList) {
            ActionResults ar = mazeEnv.performAction(this.state, a);
            if (ar.isSuccess()) {
                successors.add(new StateNode(this.mazeEnv, ar.getState(), this, a, this.pathCost + 1));
            }
        }
        return successors;
    }

    // Get the current cost of actions.
    public double getPathCost() {
        return this.pathCost;
    }

    // Get the current state.
    public GameState getState() {
        return this.state;
    }
}
