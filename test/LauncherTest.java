import Exceptions.MazeMalformedException;
import Exceptions.MazeSizeMissmatchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class LauncherTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void main() throws MazeSizeMissmatchException, IOException, MazeMalformedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"gui", "testcases/maze002.txt"};
        Launcher.main(args);
        String expectedOutput = "Run the file given in terminal.";
        assertTrue(outContent.toString().contains(expectedOutput));
    }
}