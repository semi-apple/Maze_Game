package PlayGame;

import Exceptions.MazeNotSolvable;
import Maze.GameState;
import Maze.StateNode;
import Maze.MazeEnv;
import java.util.*;

/**
 * Represent an agent to solve the maze using A*.
 *
 * Structure:
 * - mazeEnv {@link MazeEnv} is the environment of the game.
 * - gameStates is the all possible states that the agent can reach.
 * - state {@link GameState} is the current state that the agent arrives at.
 */
public class Solver {
    MazeEnv mazeEnv;
    ArrayList<GameState> gameStates = new ArrayList<>();
    char[][] mazeData;
    int[] exit;
    public GameState state;

    /**
     * One class used for get the min-heap by comparison.
     */
    class HeuristicComparator implements Comparator<StateNode> {
        @Override
        public int compare(StateNode n1, StateNode n2) {
            double h1 = n1.getPathCost() + computeHeuristic(n1.getState());
            double h2 = n2.getPathCost() + computeHeuristic(n2.getState());
            return Double.compare(h1, h2);
        }
    }

    /**
     * Heuristic function used for A* search.
     * Use Manhattan distance to make a judgement.
     * @param state Current state that the agent arrives at.
     * @return A number represents the distance from the current state to the exit, which is used for A*.
     */
    private double computeHeuristic(GameState state) {
        int[] position = state.getPosition();
        return Math.abs(position[0] - this.exit[0]) + Math.abs(position[1] - this.exit[1]);
    }

    public Solver(MazeEnv mazeEnv) {
        this.mazeEnv = mazeEnv;
        this.mazeData = mazeEnv.getMazeData();

        // Get all possible state.
        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j< mazeData[i].length; j++) {
                if (mazeData[i][j] != '#') {
                    gameStates.add(new GameState(i, j));
                }
            }
        }
        this.exit = mazeEnv.getExit();
        int[] initial_position = mazeEnv.getInitialState();
        state = new GameState(initial_position[0], initial_position[1]);
    }

    /**
     * A* search, find the solution of the game.
     * @return A series of actions from initial state to exit.
     */
    public List<Character> a_star() throws MazeNotSolvable {
        // min-heap, always return the min node.
        PriorityQueue<StateNode> container = new PriorityQueue<>(new HeuristicComparator());
        container.add(new StateNode(mazeEnv, state, null, null, 0));
        // Explored state.
        Map<GameState, Double> visited = new HashMap<>();
        visited.put(state, 0.0);

        while (!container.isEmpty()) {
            StateNode node = container.poll();

            // If game solved.
            if (mazeEnv.isSolved(node.getState())) {
                return node.getPath();
            }

            // perform action until reach exit.
            ArrayList<StateNode> successors = node.getSuccessors();
            for (StateNode s: successors) {
                // Find the best action to reach the state.
                if (!visited.containsKey(s.getState()) || s.getPathCost() < visited.get(s.getState())) {
                    visited.put(s.getState(), s.getPathCost());
                    container.add(s);
                }
            }
        }
        throw new MazeNotSolvable();
//        return null;
    }
}
