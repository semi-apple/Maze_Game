# Maze Game 

This is my Java Game Project showcasing my skills and projects in Java programming.

This is a maze game. The task is to use the A* algorithm to find the best path.

**Note:** This project is the assignment of course CSSE7023, with some of the files provided by the University of Queensland. 

## Project Structure

This project includes the following files and directories:

- `src/`: Contains source code of this game, including `Exceptions`, `IO`, `Maze`, `PlayGames` and `Launcher`. 
- `test/`: Contains test code of this game.
- `testcases/`: Contains some testcases that can be used in this game.
- `README.md`: Explain the structure of this project and how to run this game.

### src

- `Exceptions/`: Some exceptions that may be used while running the code.
- `IO/`: Read testcase and transfer it to data that can be used in `MazeEnv`. This file.
  - `FileLoader`: This class is used for loading a map file to a series of data.
  - `FileInterface`: This interface provides a contract for loading mazes from files and converting them into a 2D character array.
- `Maze/`: Create game application using the data loaded by `IO`.
  - `MazeEnv`: This class is used for creating the environment of the `Maze`.
  - `Maze`: This is the JPenal in game frame, using GUI to draw the image and `MazeEnv` to initialize this class.
  - `MazeGame`: A JFrame represents the game frame, including some functions to create the game application using `Maze`. The structure of this game are well described in the Javadoc of this file.
  - `ActionResults`: A set stored a boolean and a `GameState`. This is used for receiving the results of `performAction()` in `MazeEnv` class
  - `GameState`: This class represents each position of the maze.
  - `StateNode`: Represent each state in maze. Used for find the solution in A*.
- `PlayGame/`: A way to find a solution. In this game, the searching way is A*.
  - `Solver`: Represent an agent to solve the maze using A*.
- `Launcher`: Run this class to play the game.

### test

Some codes to test the game, including:

- `IO/`
  - `FileLoaderTest`
- `Maze/`
  - `GameStateTest`
  - `MazeEnvTest`
  - `StateNodeTest`
- `PlayGame/`
  - `SolverTest`
- `LauncherTest`

