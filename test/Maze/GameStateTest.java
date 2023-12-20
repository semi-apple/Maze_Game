package Maze;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
    private GameState state;

    @Before
    public void setUp() throws Exception {
        state = new GameState(2, 3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(state.getPosition()[0], 2);
        Assert.assertEquals(state.getPosition()[1], 3);
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(state, new GameState(2, 3));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(state, new GameState(2, 3));
    }
}