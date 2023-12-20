import Exceptions.MazeMalformedException;
import Exceptions.MazeSizeMissmatchException;
import Maze.MazeGame;

import java.io.IOException;

/**
 * Main function.
 * Run this function to play the game.
 *
 * You can choose select file in game or text the file name in terminal.
 *
 * Please obey the following command format:
 * - java -cp src Launcher gui <testcase>
 * or
 * - java -cp src Launcher
 * to run the game.
 *
 * For example, use:
 * - java -cp src Launcher gui testcases/maze002.txt
 * to run the game using the given file testcases/maze002.txt in gui.
 *
 * Can just run this file and don't need to input test file or any other string in terminal.
 */
public class Launcher {
    public static void main(String[] args) throws MazeSizeMissmatchException, IOException, MazeMalformedException {
        // Use terminal to run the game.
        if (args.length != 0) {
            int index = 0;
            for (String s : args) {
                if (s.toLowerCase().equals("gui")) {
                    if (++index >= args.length) {
                        System.out.println("Please input a valid file name or follow the structure of the command");
                        return;
                    }
                    System.out.println("Run the file given in terminal.");
                    String filename = args[index];
                    new MazeGame(filename);
                }
                index++;
            }
            System.out.println("Please input a valid file name or follow the structure of the command");
        } else {
            System.out.println("Choose a file in game.");
            new MazeGame();
        }
//        new MazeGame("testcases/maze002.txt");
    }
}