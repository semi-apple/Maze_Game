package Maze;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class StateNodeTest {
    private StateNode node;

    @Before
    public void setUp() throws Exception {
        MazeEnv mazeEnv = new MazeEnv("testcases/maze002.txt");
        StateNode temNode = new StateNode(mazeEnv, new GameState(3, 4), null, null, 30);
        node = new StateNode(mazeEnv, new GameState(2, 4), temNode, 'u', 31);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPath() {
        ArrayList<Character> actionList = new ArrayList<>();
        actionList.add('u');
        Assert.assertEquals(actionList, node.getPath());
    }

    @Test
    public void getSuccessors() {
    }

    @Test
    public void getPathCost() {
        Assert.assertEquals(31.0, node.getPathCost(), 0.00001);
    }

    @Test
    public void getState() {
        Assert.assertEquals(new GameState(2, 4), node.getState());
    }
}