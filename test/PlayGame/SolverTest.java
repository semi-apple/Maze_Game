package PlayGame;

import Exceptions.MazeNotSolvable;
import Maze.MazeEnv;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class SolverTest {
    Solver solver;
    MazeEnv mazeEnv;

    @Before
    public void setUp() throws Exception {
        mazeEnv = new MazeEnv("testcases/maze001.txt");
        solver = new Solver(mazeEnv);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void a_star() throws MazeNotSolvable {
        ArrayList<Character> testActions = new ArrayList<>();
        List<Character> actions = solver.a_star();
        testActions.add('d');
        testActions.add('d');
        testActions.add('d');
        testActions.add('d');
        testActions.add('r');
        testActions.add('r');
        testActions.add('u');
        testActions.add('u');
        testActions.add('r');
        testActions.add('r');
        testActions.add('d');
        testActions.add('d');
        for (int i = 0; i < actions.size(); i++) {
            Iterator tIter = testActions.iterator();
            Iterator iter = actions.iterator();
            while (iter.hasNext()) {
                Assert.assertEquals(tIter.next(), iter.next());
            }
        }
    }
}