package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		Cell thing = maze.getCell(randGen.nextInt(width), randGen.nextInt(height));
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(thing);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		ArrayList<Cell> yeye = getUnvisitedNeighbors(currentCell);
		System.out.println(yeye.size());
		//C. if has unvisited neighbors,
		if(yeye.size() > 0) {
			//C1. select one at random.
			Cell s = yeye.get(randGen.nextInt(yeye.size()));
			//C2. push it to the stack
			uncheckedCells.push(s);
			//C3. remove the wall between the two cells
			removeWalls(currentCell,s);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = s;
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if(yeye.size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
				// D1b. make that the current cell

				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX()) {
			if(c1.getX() > c2.getX()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			else {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
		if(c1.getY() == c2.getY()) {
			if(c1.getY() > c2.getY()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			else {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		System.out.println(c.getX() );
		System.out.println(c.getY());
		ArrayList<Cell> hey = new ArrayList<Cell>();
		if(c.getX() < width - 1 && maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited()) {
			hey.add(maze.getCell(c.getX() + 1, c.getY()));
			System.out.println(" he");
		}
		if(c.getY() < height - 1 && maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited()) {
			hey.add(maze.getCell(c.getX(), c.getY() + 1));
			System.out.println(" he");
		}
		if(c.getX() > 0 && maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited()) {
			hey.add(maze.getCell(c.getX() - 1, c.getY()));
			System.out.println(" he");
		}
		if(c.getY() > 0 && maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited()) {
			hey.add(maze.getCell(c.getX(), c.getY() - 1));
			System.out.println(" he");
		}
		return hey;
	}
}
