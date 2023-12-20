package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ArrayList;

/**
 * This is the JPenal in game frame, using GUI to draw the image.
 * Using MazeEnv to initialize this class.
 * <p>
 *     The structure is as follows:
 *     |-- Maze
 *     |    |-- BufferedImage
 *     |    |   |-- Graphics2D
 * </p>
 */

public class Maze extends JComponent {
    int row, col;
    char[][] mazeData;

    // Set the grid size by default.
    int gridHeight = 5;
    int gridWidth = 8;
    int[] initialState = new int[2];
    int[] exit;
    MazeEnv mazeEnv;
    private BufferedImage buffer;

    // These two are used for printing the image in printPath() method.
    private int currentIndex = 0;
    private Timer timer;

    public Maze(MazeEnv mazeEnv) {
        this.mazeEnv = mazeEnv;
        this.mazeData = mazeEnv.getMazeData();
        this.row = mazeEnv.row;
        this.col = mazeEnv.col;
        this.exit = mazeEnv.getExit();
        int width = gridWidth * col;
        int height = gridHeight * row;

        // BufferedImage to cache the image.
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();
        drawMaze(g2);
        g2.dispose();
    }

    /**
     * Print image based on mazeData.
     * <p>
     *     The color follow the rules:
     *     - '#': Dark gray.
     *     - ' ': Light gray.
     *     - 'S': Red.
     *     - 'E': Green.
     *     - '.': Explored state colored in yellow.
     *     - '*': Solution colored in blue.
     * </p>
     * @param g2 The Graphics2D argument represents each grid in maze.
     */
    private void drawMaze(Graphics2D g2) {
        g2.clearRect(0, 0, gridWidth * col, gridHeight * row);
        for (int i = 0; i< row; i++) {
            for (int j = 0; j< col; j++) {
                switch (mazeData[i][j]) {
                    case '#':
                        g2.setColor(Color.DARK_GRAY);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                        break;

                    case ' ':
                        g2.setColor(Color.LIGHT_GRAY);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                        break;

                    case 'S':
                        g2.setColor(Color.RED);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                        if (initialState[0] == 0 && initialState[1] == 0) {
                            initialState[0] = i;
                            initialState[1] = j;
                        }
                        break;

                    case 'E':
                        g2.setColor(Color.GREEN);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                        if (exit[0] == 0 && exit[1] == 0) {
                            exit[0] = i;
                            exit[1] = j;
                        }
                        break;

                    case '.':
                        g2.setColor(Color.YELLOW);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                        break;

                    case '*':
                        g2.setColor(Color.BLUE);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                        break;

                    default:
                        g2.setColor(Color.WHITE);
                        g2.fillRect(gridWidth * j, i * gridHeight, gridWidth, gridHeight);
                }
            }
        }
    }

    // Repaint automatically.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawMaze(g2);
    }

    // Adaptive window size.
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gridWidth * col, gridHeight * row);
    }

    /**
     * Use Time to print image.
     * @param states A ArrayList stored the solution represented by a series of states.
     */
    public void printPath(ArrayList<GameState> states) {
        timer = new Timer(17, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < states.size() - 1) {
                    GameState s = states.get(currentIndex);
                    int[] position = s.getPosition();

                    // Color current state in blue and next state in red as the goose's next state.
                    mazeData[position[0]][position[1]] = '*';
                    mazeData[states.get(currentIndex+1).row][states.get(currentIndex+1).col] = 'S';
                    repaint();

                    currentIndex++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}
