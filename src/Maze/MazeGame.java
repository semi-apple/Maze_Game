package Maze;

import Exceptions.MazeMalformedException;
import Exceptions.MazeNotSolvable;
import Exceptions.MazeSizeMissmatchException;
import PlayGame.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A JFrame represents the game frame.
 * <p>
 *     The structure is as follows:
 *     |-- MazeGame
 *     |    |-- JMenuBar File
 *     |    |   |-- Open
 *     |    |   |-- Exit
 *     |    |-- Maze
 *     |    |-- JButton "Show explored"
 *     |    |-- JButton "Goooooo!"
 * </p>
 * Menu item:
 * - Click "Open" {@link JMenuItem} to open a file and creating MazeEnv and Maze based on it
 * - Click "Exit" {@link JMenuItem} to exit the game.
 *
 * Button:
 * - Click "Goooooo!" {@link JButton} to find the solution to the exit based on A*.
 * - Click "Show explored" {@link JButton} shows the agent explored states in A*.
 */
public class MazeGame {
    public Maze maze;
    public JFrame frame;
    public MazeEnv mazeEnv;

    // The MazeEnv stores solution.
    public MazeEnv pathEnv;

    // The MazeEnv stores explored states.
    public MazeEnv exploredEnv;

    /**
     * Using while run the game by default.
     */
    public MazeGame() {
        frame = new JFrame("The Holy King's Trial");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = getjMenuBar(frame);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    /**
     * Overload MazeGame.
     * Using while the file name is texted in the terminal.
     * @param filename The file name given in the terminal.
     * @throws MazeSizeMissmatchException if the maze data doesn't match the specified dimensions.
     * @throws IOException if there is a IO exception.
     * @throws MazeMalformedException if the maze data doesn't match the given format.
     */
    public MazeGame(String filename) throws MazeSizeMissmatchException, IOException, MazeMalformedException {
        frame = new JFrame("The Holy King's Trial");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = getjMenuBar(frame);
        JButton playButton = getjButton();
        frame.add(playButton, BorderLayout.SOUTH);
        frame.setJMenuBar(menuBar);
        mazeEnv = new MazeEnv(filename);
        maze = new Maze(mazeEnv);

        frame.add(maze);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Show explored stats in Maze.
     * @param maze The image.
     * @param exploredEnv The MazeEnv stores explored states.
     */
    private void showExploredState(Maze maze, MazeEnv exploredEnv) {
        maze.mazeData = exploredEnv.getMazeData();
        maze.repaint();
    }

    /**
     * Initialize the Menu bar.
     * @param frame The game frame.
     * @return A JMenuBar added in frame.
     */
    private JMenuBar getjMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFile = new JMenuItem("Open");
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnValue = chooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getAbsolutePath();

                    try {
                        if (maze != null) {
                            Container contentPane = frame.getContentPane();
                            contentPane.removeAll();
                            pathEnv = null;
                            exploredEnv = null;
                        }
                        mazeEnv = new MazeEnv(filename);
                        maze = new Maze(mazeEnv);
                        frame.add(maze);
                        JButton playButton = getjButton();
                        frame.add(playButton, BorderLayout.SOUTH);
                        frame.revalidate();
                        frame.repaint();
                        frame.pack();
                    } catch (IOException | MazeSizeMissmatchException | MazeMalformedException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            // Initialize two buttons.

        });

        // Exit menu item.
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(openFile);
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        return menuBar;
    }

    /**
     * Create "Goooooo!" button and replace to "Show explored" if the "Goooooo!" button is clicked.
     * @return a JButton added in frame.
     */
    private JButton getjButton() {
        JButton playButton = new JButton("Goooooo!");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maze != null) {
                    playGame(maze, mazeEnv);
                    frame.remove(playButton);
                    JButton showExplored = new JButton("Show explored");
                    showExplored.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            showExploredState(maze, exploredEnv);
                        }
                    });
                    frame.add(showExplored, BorderLayout.SOUTH);
                    frame.setVisible(true);
                }
            }
        });
        return playButton;
    }

    /**
     * Use A* in Solver to find the solution path and paint out.
     * @param maze The maze image used for repaint.
     * @param mazeEnv The environment the game runs.
     */
    public void playGame(Maze maze, MazeEnv mazeEnv) {
        MazeEnv pathEnv = mazeEnv.clone();
        Maze pathMaze = new Maze(pathEnv);
        Solver solver = new Solver(mazeEnv);
        List<Character> actions = new ArrayList<>();
        try {
            actions = solver.a_star();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // store the explored environment.
        this.exploredEnv = mazeEnv;

        GameState cur = solver.state;
        GameState newState;
        ArrayList<GameState> solution = new ArrayList<>();
        for (char a: actions) {
            solution.add(cur);
            ActionResults ar = mazeEnv.performAction(cur, a);
            newState = ar.getState();
            cur = newState;
        }

        // clear the mazeData to paint the solution path.
        maze.mazeData = pathMaze.mazeData;
        maze.printPath(solution);
        // store the path environment.
        this.pathEnv = maze.mazeEnv;
    }
}