package IO;

import Exceptions.MazeMalformedException;
import Exceptions.MazeSizeMissmatchException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used for loading a map file to a series of data.
 *
 * @see FileInterface
 */
public class FileLoader implements FileInterface{
    // The first number stored in mazeSize is the row of the maze and the second is the col.
    private int[] mazeSize = new int[2];

    // The first number stored in initialState is the row of the initial state and the second is the col.
    private int[] initialState = new int[2];

    // The first number stored in exit is the row of the exit and the second is the col.
    private int[] exit = new int[2];
    private char[][] mazeData;

    public FileLoader(String filename) throws MazeSizeMissmatchException, IOException, MazeMalformedException {
        this.mazeData = load(filename);
    }

    /**
     * Load data from the given file
     * @param filename The path to the maze file to be loaded.
     * @see FileInterface
     * @return A two-dimensional array representing the map
     * @throws MazeMalformedException
     * @throws MazeSizeMissmatchException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Override
    public char[][] load(String filename) throws MazeMalformedException, MazeSizeMissmatchException, IllegalArgumentException, IOException {
        int row, col;
        int initialIndex = 0, exitIndex = 0;

        // check if the valid file
        FileReader fr = new FileReader(filename);
        if (fr == null)
            throw new FileNotFoundException();

        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        // check the first line
        try {
            String[] str = line.split(" ");
            row = Integer.valueOf(str[0]);
            col = Integer.valueOf(str[1]);
        } catch (Exception e) {
            throw new MazeMalformedException();
        }

        this.mazeSize[0] = row;
        this.mazeSize[1] = col;

        char[][] data = new char[row][col];

        // set the maze data to the data[i][j]
        for (int i = 0; i < row; i++) {
            line = br.readLine();
            // if not match the given line row and col
            if (line == null || line.length() != col)
                throw new MazeSizeMissmatchException();
            for (int j = 0; j < col; j ++) {
                if (line.charAt(j) != '#' && line.charAt(j) != 'S' && line.charAt(j) != 'E' && line.charAt(j) != ' ')
                    throw new IllegalArgumentException();
                data[i][j] = line.charAt(j);
                if (line.charAt(j) == 'S') {
                    initialIndex++;
                    if (initialIndex > 1) throw new MazeMalformedException();
                    this.initialState[0] = i;
                    this.initialState[1] = j;
                }
                if (line.charAt(j) == 'E') {
                    exitIndex++;
                    if (exitIndex > 1) throw new MazeMalformedException();
                    this.exit[0] = i;
                    this.exit[1] = j;
                }
            }
        }

        // if not match the given line number
        if (br.readLine() != null)
            throw new MazeSizeMissmatchException();

        return data;
    }

    // return the initial state of the goose.
    public int[] getInitialState() {
        return this.initialState;
    }

    // return the exit of the maze.
    public int[] getExit() {
        return this.exit;
    }

    // return the row/col of the maze. The first number.
    public int[] getMazeSize() {
        return this.mazeSize;
    }

    // return the maze data loaded from the load() method.
    public char[][] getMazeData() {
        return this.mazeData;
    }
}
