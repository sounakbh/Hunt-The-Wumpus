import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * The Abstract Maze class implements the Maze interface, and contains all the main functionalities
 * like creation of the maze, removing the walls of the maze, assigning gold and thieves to the
 * maze, etc.
 */
public class PerfectMazeModel implements MazeModel {
  protected int rows;
  protected int cols;
  protected Cell[][] maze;
  protected Character[] character;
  private int wallsRemoved;
  private List<Cell> visited = new ArrayList<>();
  private Queue<Cell> q = new LinkedList<>();

  Random randNum;


  /**
   * Initializing the PerfectMaze with a number of rows and cols, and setting the starting and
   * ending point of the Character.
   *
   * @param rows   the number of rows of the maze
   * @param cols   the number of columns of the maze
   * @param startX starting position X of character
   * @param startY starting position Y of character
   */
  public PerfectMazeModel(int rows, int cols, int startX, int startY) {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Rows and/or Columns cannot be negative or 0!");
    }
    if (startX < 0 || startX >= rows || startY < 0 || startY >= cols) {
      throw new IllegalArgumentException("Illegal Starting point!");
    }
    this.rows = rows;
    this.cols = cols;
    this.wallsRemoved = 0;
    randNum = new Random();
    randNum.setSeed(123456);
    // the total number of walls in the maze (outer and inner)
    int totalWalls = rows * (cols * 2 + 1) + cols;
    // the maze with all the walls intact
    this.maze = new Cell[rows][cols];
    this.character = new Character[2];
    this.character[0] = new Character(new Point(startX, startY));
    this.character[1] = new Character(new Point(startX, startY));
    this.buildMaze();
    this.createVisitedNodeArray();
    this.dfs();
  }

  /**
   * Used in the driver when the type of maze to be constructed is not yet known.
   */
  public PerfectMazeModel() {
    // used for initialising in the driver
  }

  /**
   * Builds a m * n matrix of Cells, which have all four walls initially, and assigns thieves and
   * gold to random cells.
   */
  private void buildMaze() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        maze[i][j] = new Cell(i, j);
      }
    }
  }

  /**
   * Creating an array of all the nodes which are yet to be visited.
   */
  private void createVisitedNodeArray() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        visited.add(maze[i][j]);
      }
    }
  }

  /**
   * Performing Depth First Search to remove walls from the maze.
   */
  private void dfs() {
    int randIndex = getRandom(0, visited.size() - 1);
    // choosing a random starting cell
    Cell currentCellA = visited.get(randIndex);
    // while all the nodes are not visited keep removing walls
    while (visited.size() > 0) {
      visited.remove(currentCellA);
      q.add(currentCellA);
      List<Cell> neigh = getNeighbors(currentCellA);
      List<Cell> neighbors = new ArrayList<>();
      // getting all the neighbours of currentCellA
      for (Cell c : neigh) {
        if (visited.contains(c)) {
          neighbors.add(c);
        }
      }
      // if no neighbours are unvisited keep popping from the queue until non visited neighbor is
      // found
      if (neighbors.size() == 0) {
        currentCellA = q.peek();
        q.remove();
        continue;
      }
      randIndex = getRandom(0, neighbors.size() - 1);
      // choosing a neighbor at random
      Cell currentCellB = neighbors.get(randIndex);
      // removing the wall
      removeWall(currentCellA, currentCellB);
      this.wallsRemoved++;
      // assigning the neighbor to currentCellA and repeating the process
      currentCellA = currentCellB;
    }
  }


  /**
   * Removing a wall between Cell A and Cell B.
   *
   * @param a first Cell
   * @param b second Cell
   */
  private void removeWall(Cell a, Cell b) {
    int a_x = a.x;
    int a_y = a.y;

    int b_x = b.x;
    int b_y = b.y;

    // getting the placement of Cell B wrt Cell A
    String direction = "";
    if (a_x == b_x && a_y < b_y) {
      direction = "right";
    } else if (a_x == b_x && a_y > b_y) {
      direction = "left";
    } else if (a_y == b_y && a_x < b_x) {
      direction = "bottom";
    } else if (a_y == b_y && a_x > b_x) {
      direction = "top";
    }

    // removing the walls between both cells in the specified direction
    if (direction.equals("right")) {
      maze[a_x][a_y].setRight(false);
      maze[b_x][b_y].setLeft(false);
    } else if (direction.equals("left")) {
      maze[a_x][a_y].setLeft(false);
      maze[b_x][b_y].setRight(false);
    } else if (direction.equals("top")) {
      maze[a_x][a_y].setTop(false);
      maze[b_x][b_y].setBottom(false);
    } else if (direction.equals("bottom")) {
      maze[a_x][a_y].setBottom(false);
      maze[b_x][b_y].setTop(false);
    }
  }

  /**
   * Returns a list of Cells which are neighbors of a Cell C.
   *
   * @param c a Cell
   * @return a List of Cells which are neighbors of c.
   */
  private List<Cell> getNeighbors(Cell c) {
    List<Cell> neighbors = new ArrayList<>();
    int c_x = c.x;
    int c_y = c.y;
    if (c_y > 0) {
      neighbors.add(maze[c_x][c_y - 1]);
    }
    if (c_y < cols - 1) {
      neighbors.add(maze[c_x][c_y + 1]);
    }
    if (c_x > 0) {
      neighbors.add(maze[c_x - 1][c_y]);
    }
    if (c_x < rows - 1) {
      neighbors.add(maze[c_x + 1][c_y]);
    }
    return neighbors;
  }

  @Override
  public String arrowShoot(Cell coordinates, String direction, int steps, int numChar) {
    return null;
  }

  /**
   * Get a random number between the limits provided.
   *
   * @param min lower limit
   * @param max upper limit
   * @return a number between and including the limits
   */
  protected int getRandom(int min, int max) {
    return randNum.nextInt(max + 1);
  }

  @Override
  public String moveCharacter(String move, int numChar) {
    return null;
  }

  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        res += maze[i][j].toString();
      }
      res += '\n';
    }
    return res;
  }
}
