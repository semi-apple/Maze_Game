package IO;

import Exceptions.MazeMalformedException;
import Exceptions.MazeSizeMissmatchException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.datatransfer.FlavorEvent;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileLoaderTest {
    private FileLoader fileLoader;

    @Before
    public void setUp() throws Exception {
        fileLoader = new FileLoader("testcases/maze002.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() throws MazeSizeMissmatchException, IOException, MazeMalformedException {
        String maze =
                "#########################################" +
                "#S#         #   #       #     #   #     #" +
                "# ####### # # # # # ### # ### # ### # # #" +
                "# #     # #   #   #   #   # # #     # # #" +
                "# # ### # ####### ### ##### # ####### # #" +
                "#   # # # #     # #   # #   # #       # #" +
                "##### # ### ### # # ### # # # # ####### #" +
                "#     #     #   # # #     #   # #       #" +
                "# ### ####### ##### # ######### #########" +
                "#   #       #     # # #       #         #" +
                "######### ####### # # ##### # # ####### #" +
                "#   #   #       #   # #   # # #   #     #" +
                "# # # # ####### ##### # # # # ##### #####" +
                "# #   #   #     #   # # #   #     #     #" +
                "# # ##### # # ### # # # ######### ##### #" +
                "# # #   #   # #   #   # #       #       #" +
                "# ### # ##### # ####### # ############# #" +
                "#     # #     #         #           #   #" +
                "# ##### ################# ####### ### ###" +
                "#     #                         #      E#" +
                "#########################################";

        String test = "";
        char[][] testChar = fileLoader.load("testcases/maze002.txt");
        for (int i = 0; i < testChar.length; i++) {
            for (int j = 0; j < testChar[i].length; j++) {
                test += testChar[i][j];
            }
        }
        Assert.assertEquals(test, maze);
    }

    @Test
    public void getInitialState() {
        int[] position = fileLoader.getInitialState();
        Assert.assertEquals(position[0], 1);
        Assert.assertEquals(position[1], 1);
    }

    @Test
    public void getExit() {
        Assert.assertEquals(19, fileLoader.getExit()[0]);
        Assert.assertEquals(39, fileLoader.getExit()[1]);
    }

    @Test
    public void getMazeSize() {
        Assert.assertEquals(21, fileLoader.getMazeSize()[0]);
        Assert.assertEquals(41, fileLoader.getMazeSize()[1]);
    }

    @Test
    public void getMazeData() {
        String maze = "#########################################" +
                "#S#         #   #       #     #   #     #" +
                "# ####### # # # # # ### # ### # ### # # #" +
                "# #     # #   #   #   #   # # #     # # #" +
                "# # ### # ####### ### ##### # ####### # #" +
                "#   # # # #     # #   # #   # #       # #" +
                "##### # ### ### # # ### # # # # ####### #" +
                "#     #     #   # # #     #   # #       #" +
                "# ### ####### ##### # ######### #########" +
                "#   #       #     # # #       #         #" +
                "######### ####### # # ##### # # ####### #" +
                "#   #   #       #   # #   # # #   #     #" +
                "# # # # ####### ##### # # # # ##### #####" +
                "# #   #   #     #   # # #   #     #     #" +
                "# # ##### # # ### # # # ######### ##### #" +
                "# # #   #   # #   #   # #       #       #" +
                "# ### # ##### # ####### # ############# #" +
                "#     # #     #         #           #   #" +
                "# ##### ################# ####### ### ###" +
                "#     #                         #      E#" +
                "#########################################";
        String test = "";
        for (int i = 0; i < fileLoader.getMazeData().length; i++) {
            for (int j = 0; j < fileLoader.getMazeData()[i].length; j++) {
                test += fileLoader.getMazeData()[i][j];
            }
        }
        Assert.assertEquals(test, maze);
    }
}