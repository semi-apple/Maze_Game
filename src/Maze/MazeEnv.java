package Maze;

import Exceptions.MazeMalformedException;
import Exceptions.MazeSizeMissmatchException;
import IO.FileLoader;

import java.io.IOException;

/**
 * This class is used for creating the environment of the maze.
 * Stored maze data for Maze.
 */
public class MazeEnv implements Cloneable{

    // Final object used in lambda for movement in maze.
    public static final char UP = 'u';
    public static final char DOWN = 'd';
    public static final char LEFT = 'l';
    public static final char RIGHT = 'r';
    public static char[] actionList = new char[4];
    static {
        actionList[0] = UP;
        actionList[1] = DOWN;
        actionList[2] = LEFT;
        actionList[3] = RIGHT;
    }

    // lambda function
    interface Move {
        GameState move(GameState state);
    }
    Move up = newState -> new GameState(newState.row - 1, newState.col);
    Move down = newState -> new GameState(newState.row + 1, newState.col);
    Move left = newState -> new GameState(newState.row, newState.col - 1);
    Move right = newState -> new GameState(newState.row, newState.col + 1);

    // mazeData stored the data of the maze.
    private char[][] mazeData;
    // total row anc col of the game.
    public int row, col;
    // initialState represented the beginning state.
    private int[] initialState;
    // exit represented the exit of the game.
    private int[] exit;

    // Initialize the maze environment.
    public MazeEnv(String filename) throws MazeSizeMissmatchException, IOException, MazeMalformedException {
        FileLoader fileLoader = new FileLoader(filename);
        this.mazeData = fileLoader.getMazeData();
//        this.initialMazeData = fileLoader.getMazeData();
        int[] mazeSize = fileLoader.getMazeSize();
        this.row = mazeSize[0];
        this.col = mazeSize[1];
        this.initialState = fileLoader.getInitialState();
        this.exit = fileLoader.getExit();
    }

    /**
     * A method used for deep copy. It will be used in MazeGame class, used for finding the explored state.
     * @return a deep copy of the class MazeEnv.
     */
    public MazeEnv clone() {
        MazeEnv copy = null;
        try {
            copy = (MazeEnv) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        copy.mazeData = new char[this.row][this.col];

        // Deep copy the mazeData.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copy.mazeData[i][j] = this.mazeData[i][j];
            }
        }

        copy.exit = this.exit.clone();
        copy.col = this.col;
        copy.row = this.row;
        copy.initialState = this.initialState.clone();
        return copy;
    }

    // return the initial state of the goose.
    public int[] getInitialState() { return initialState; }

    // return the exit of the maze.
    public int[] getExit() { return exit; }

    // return the maze data.
    public char[][] getMazeData() { return mazeData; }

    // return true if the goose reach the exit. Else false.
    public boolean isSolved(GameState state) { return (state.row == exit[0] && state.col == exit[1]); }

    /**
     * Perform the given action and return a valid state, else return the current state.
     *
     * If the action is valid, the state where the agent (designed in Solver class) explored will be marked by '.'.
     * @param state The current goose's state in maze.
     * @param action The action the goose performed in current state.
     * @return ActionResults, which the first element represents if the action is successful and the second represents
     *         the returned state.
     */
    public ActionResults performAction(GameState state, char action) {
        int row = state.row;
        int col = state.col;
        GameState newState;
        boolean success = false;
        switch (action) {
            case 'u':
                if (mazeData[row-1][col] == '#'){
                    newState = state;
                } else {
                    success = true;
                    mazeData[row][col] = '.';
                    newState = up.move(state);
                }
                break;

            case 'd':
                if (mazeData[row+1][col] == '#') {
                    newState = state;
                } else {
                    success = true;
                    mazeData[row][col] = '.';
                    newState = down.move(state);
                }
                break;

            case 'l':
                if (mazeData[row][col-1] == '#') {
                    newState = state;
                }else {
                    success = true;
                    mazeData[row][col] = '.';
                    newState = left.move(state);
                }
                break;

            case 'r':
                if (mazeData[row][col+1] == '#') {
                    newState = state;
                } else {
                    success = true;
                    mazeData[row][col] = '.';
                    newState = right.move(state);
                }
                break;

            default:
                newState = state;
                break;
        }

        // In case of printing image wrong.
        mazeData[initialState[0]][initialState[1]] = 'S';
        mazeData[exit[0]][exit[1]] = 'E';
        return new ActionResults(success, newState);
    }
}
