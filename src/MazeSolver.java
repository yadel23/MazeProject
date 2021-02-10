
/**
 * This is where the main method is located and where the maze is going to be solved. 
 * 
 * @author Yadel Negash
 * @date(03/30/2020)
 */
import java.util.Scanner;

public class MazeSolver {

	public static void main(String[] args) {
		System.out.println("what is the name of your maze file?");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();

		// checks if the maze file exists and and returns a maze if not returns null
		// user will not pass until passing this prompt.
		while (Maze.loadMaze(userInput) == null) {
			System.out.println("please input a vaild file name?");
			userInput = scanner.nextLine();
		}

		// a maze object is created and printed out
		Maze maze = Maze.loadMaze(userInput);
		System.out.println(maze.toString());

		// user is asked to input where in the maze they want to start.
		System.out.println("Please input the start coordinates to use to solve the maze?");
		int userInput1 = scanner.nextInt();
		int userInput2 = scanner.nextInt();

		// the user's coordinates is used in the point class to get a point.
		// then it is checked if the point in the maze is in bounds or if
		// it's not empty if it is the user is asked for another set of coordinates
		Point p = new Point(userInput1, userInput2);
		while (!maze.inBounds(p) || maze.get(p) != TextMaze.EMPTY) {
			System.out.println("please input two valid new coordinates?:)");
			userInput1 = scanner.nextInt();
			userInput2 = scanner.nextInt();
			p = new Point(userInput1, userInput2);
		}

		// user is asked to input where in the maze they want to end.
		System.out.println("Please input the end coordinates to use to solve the maze?");
		int userInput3 = scanner.nextInt();
		int userInput4 = scanner.nextInt();

		// the user's coordinates is used in the point class to get a point.
		// then it is checked if the point in the maze is in bounds or if
		// it's not empty if it is the user is asked for another set of coordinates
		Point p2 = new Point(userInput3, userInput4);
		while (!maze.inBounds(p2) || maze.get(p2) != TextMaze.EMPTY) {
			System.out.println("please input two valid new coordinates?");
			userInput3 = scanner.nextInt();
			userInput4 = scanner.nextInt();
			p2 = new Point(userInput1, userInput2);
		}

		// this is where the maze is going to be solved by using the solveMaze and solveMazeHelper
		// when the maze is solved it saves the maze as a new maze with .solved in its name
		// if the maze was not solved it will give a prompt that says so
		if (solveMaze(maze, p, p2)) {
			System.out.println(maze.toString());
			Maze.saveMaze(userInput + ".solved", maze);
			System.out.println("the maze was solved:)");
		} else
			System.out.println("the maze was not solved:(");
			System.out.println(maze.toString());
	}

	// this method helps the solveMaze method by recursively searching the points
	// adjacentPoints and marking the path
	// for each adjacentPoint it calls itself to see if it has reached the goal mark
	public static boolean solveMazeHelper(Maze maze, Point currentPoint) {
		if (maze.get(currentPoint) == TextMaze.GOAL) {
			return true;
		} else if (maze.get(currentPoint) != TextMaze.EMPTY) {
			return false;
		}
		maze.set(currentPoint, TextMaze.PATH);
		Point[] adjPoints = currentPoint.getAdjacentPoints();
		for (int i = 0; i < adjPoints.length; i++) {
			if (maze.inBounds(adjPoints[i])) {
				boolean isMazeSolved = solveMazeHelper(maze, adjPoints[i]);

				if (isMazeSolved) {
					return true;
				}
			}
		}

		maze.set(currentPoint, TextMaze.VISITED);
		return false;

	}

	// solveMaze solves the maze by setting the goal point and then calling the
	// solveMazehelper to recursively solve the maze by finding the goal mark
	// after the isSolved variable is set true or false based on the solveMazeHelper
	// it will determine if SolveMaze will be true or false
	public static boolean solveMaze(Maze maze, Point startP, Point endP) {
		maze.set(endP, TextMaze.GOAL);
		boolean isSolved = solveMazeHelper(maze, startP);
		maze.set(startP, TextMaze.START);

		if (isSolved) {
			return true;
		} else
			return false;

	}

}
