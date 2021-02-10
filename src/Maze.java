
/**
 * This is class is used to set and get things on the maze, to save and load the maze, 
 * to check if the maze is in bounds, and to make the maze toString.
 * 
 * @author Yadel Negash
 * @date(03/30/2020)
 */

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class Maze implements TextMaze {

	private char[][] maze;
	private int _width, _height;

	// makes it so that the maze coordinates start from the bottom left corner.
	public Maze(int width, int height) {
		_width = width;
		_height = height;
		maze = new char[_width][_height];

		for (int i = 0; i < _height; i++) {
			for (int j = 0; j < _width; j++) {
				maze[j][i] = TextMaze.EMPTY;
			}
		}
	}

	// sets a chosen character at a given point if it is in bounds
	public void set(Point p, char c) {
		if (!inBounds(p)) {
			throw new PointOutOfBoundsException(p.toString());
		} else
			maze[p.x][p.y] = c;
	}

	// gets the point in the maze from the Point class
	public char get(Point p) {
		if (!inBounds(p)) {
			throw new PointOutOfBoundsException(p.toString());
		} else
			return maze[p.x][p.y];
	}

	public int width() {
		return _width;
	}

	public int height() {
		return _height;
	}

	// checks if the given point is in bounds.
	// the width and height has to be less then the wall boarder
	public boolean inBounds(Point p) {
		if (p.x < _width && p.y < _height && p.x >= 0 && p.y >= 0) {
			return true;
		} else
			return false;
	}

	// used to print the maze
	public String toString() {
		StringBuilder strB = new StringBuilder();

		for (int i = _height - 1; i >= 0; i--) {
			for (int j = 0; j < _width; j++) {
				strB.append(maze[j][i]);
			}
			if (i != 0) {
				strB.append("\n");
			}
		}
		return strB.toString();
	}

	// used to load the maze by extracting the width and height from the file
	public static Maze loadMaze(String fileName) {
		File mazeFile = new File(fileName);

		try {
			Scanner fileInput = new Scanner(mazeFile);
			int width = fileInput.nextInt();
			int height = fileInput.nextInt();
			fileInput.nextLine();
			Maze maze1 = new Maze(width, height);

			for (int i = height - 1; i >= 0; i--) {
				String name = fileInput.nextLine();
				for (int j = 0; j < name.length(); j++) {
					maze1.maze[j][i] = name.charAt(j);
				}
			}
			return maze1;

		} catch (FileNotFoundException e) {
			return null;
		}
	}

	// used to save the maze after the maze has been solved
	public static void saveMaze(String filename, Maze maze) {
		loadMaze(filename);
		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new File(filename));
			fileWriter.println(maze.width() + " " + maze.height());
			fileWriter.println(maze.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}

}
