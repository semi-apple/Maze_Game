package Maze;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeEnvTest {
    String filename = "testcases/maze002.txt";
    MazeEnv mazeEnv;

    @Before
    public void setUp() throws Exception {
        mazeEnv = new MazeEnv(filename);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void performAction() {
        ActionResults arT = new ActionResults(true, new GameState(2, 1));
        ActionResults arR = mazeEnv.performAction(new GameState(1, 1), 'd');
        Assert.assertEquals(arT.isSuccess(), arR.isSuccess());
        Assert.assertEquals(arT.getState(), arR.getState());
    }

    @Test
    public void testClone() {
        MazeEnv testEnv = mazeEnv.clone();
        mazeEnv.performAction(new GameState(1, 1), 'd');
        Assert.assertNotEquals(mazeEnv.getMazeData(), testEnv.getMazeData());
//        for (int i = 0; i < mazeEnv.row; i++) {
//            for (int j = 0; j < mazeEnv.col; j++) {
//                Assert.assertEquals(testEnv.getMazeData()[i][j], mazeEnv.getMazeData()[i][j]);
//            }
//        }
    }

    @Test
    public void isSolved() {
        int[] initPosition = mazeEnv.getInitialState();
        int[] exitPosition = mazeEnv.getExit();
        Assert.assertFalse(mazeEnv.isSolved(new GameState(initPosition[0], initPosition[1])));
        Assert.assertTrue(mazeEnv.isSolved(new GameState(exitPosition[0], exitPosition[1])));
    }
}