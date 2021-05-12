/**
 * In a RoomMaze there exists multiple paths between one point and another. The number of remaining
 * walls is up to the user and can be between 0 and the remaining number of walls in case of a
 * perfect maze.
 */
public class RoomMazeModel extends PerfectMazeModel {
  private int requiredRooms;
  private int batsPercentage;
  private int pitsPercentage;
  private String enterDirection;
  Wumpus wumpus;

  /**
   * Initializing the RoomMaze with a number of rows and cols, setting the starting and ending point
   * of the Character, and the number of remaining walls.
   *
   * @param rows           the number of rows of the maze
   * @param cols           the number of columns of the maze
   * @param startX         starting position X of character
   * @param startY         starting position Y of character
   * @param pitsPercentage the percentage of rooms containing pits
   * @param batsPercentage the percentage of rooms containing bats
   */
  public RoomMazeModel(int rows, int cols, int startX, int startY, int pitsPercentage,
                       int batsPercentage) {
    super(rows, cols, startX, startY);
    if (pitsPercentage < 0 || pitsPercentage >= 30) {
      throw new IllegalArgumentException("Percentage of pits cannot be negative or greater "
              + "than or equal to 30");
    }
    if (batsPercentage < 0 || batsPercentage >= 50) {
      throw new IllegalArgumentException("Percentage of bats cannot be negative or greater "
              + "than to 50");
    }
    this.enterDirection = "";
    this.pitsPercentage = pitsPercentage;
    this.batsPercentage = batsPercentage;
    double temp = 0.65 * cols * rows;
    this.requiredRooms = (int) Math.round(temp);
    this.removeAdditionalWalls();
    this.placeWumpus();
    this.placePits();
    this.placeBats();
  }

  /**
   * Placing the wumpus in a room which does not have the character in the beginning.
   */
  private void placeWumpus() {
    int randX = getRandom(0, rows - 1);
    int randY = getRandom(0, cols - 1);
    Cell current = maze[randX][randY];
    // run the loop till we find a room and the room is not the location of the character to place
    // the wumpus
    while (isHallway(current)) {
      randX = getRandom(0, rows - 1);
      randY = getRandom(0, cols - 1);
      current = maze[randX][randY];
    }
    wumpus = new Wumpus(randX, randY);
    maze[randX][randY].setWumpus(true);
  }

  /**
   * Placing bats in rooms till the required percentage of bats is met.
   */
  private void placeBats() {
    double batsToBePlaced = 0.01 * batsPercentage * this.countRooms();
    int numberOfRoomsToBePlaced = (int) Math.round(batsToBePlaced);
    int totalBats = 0;
    while (totalBats < numberOfRoomsToBePlaced) {
      int randX = getRandom(0, rows - 1);
      int randY = getRandom(0, cols - 1);
      if (!isHallway(maze[randX][randY]) && !maze[randX][randY].getBat()) {
        maze[randX][randY].assignBat(true);
        totalBats++;
      }
    }
  }

  /**
   * Placing pits in rooms till the required percentage of pits is met.
   */
  private void placePits() {
    double pitsToBePlaced = 0.01 * pitsPercentage * this.countRooms();
    int numberOfRoomsToBePlaced = (int) Math.round(pitsToBePlaced);
    int totalPits = 0;
    while (totalPits < numberOfRoomsToBePlaced) {
      int randX = getRandom(0, rows - 1);
      int randY = getRandom(0, cols - 1);
      if (!isHallway(maze[randX][randY]) && !maze[randX][randY].getPit()) {
        maze[randX][randY].assignPit(true);
        totalPits++;
      }
    }
  }

  /**
   * Counting the total rooms in the maze.
   *
   * @return the number of rooms in the maze
   */
  private int countRooms() {
    int roomCount = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!isHallway(maze[i][j])) {
          roomCount++;
        }
      }
    }
    return roomCount;
  }

  /**
   * Checking whether a cell is a hallway(a cell with exactly two walls) or not.
   *
   * @param a the cell to be checked
   * @return whether the cell is a hallway or not
   */
  private boolean isHallway(Cell a) {
    int count = 0;
    int currX = a.x;
    int currY = a.y;
    if (maze[currX][currY].getRight()) {
      count++;
    }
    if (maze[currX][currY].getLeft()) {
      count++;
    }
    if (maze[currX][currY].getBottom()) {
      count++;
    }
    if (maze[currX][currY].getTop()) {
      count++;
    }
    return count == 2;
  }

  /**
   * Transporting the character to a new room when he/she encounters a bat.
   *
   * @return the cell where the character is transported
   */
  private Cell batEncounter() {
    int randX = getRandom(0, rows - 1);
    int randY = getRandom(0, cols - 1);
    Cell current = maze[randX][randY];
    while (isHallway(current)) {
      randX = getRandom(0, rows - 1);
      randY = getRandom(0, cols - 1);
      current = maze[randX][randY];
    }
    maze[randX][randY].setVisited(true);
    return current;
  }

  @Override
  public String moveCharacter(String move, int numChar) {
    Point p = character[numChar].getLocation();
    int curr_x = p.getX();
    int curr_y = p.getY();
    // moving the character based on the direction provided
    String moveString = ""; // contains all the data related to the character's move
    Cell temp = null;
    if (move.equals("right")) {
      if (!maze[curr_x][curr_y].getRight()) {
        temp = this.findNearestRoom(maze[curr_x][curr_y], "right");
        curr_x = temp.x;
        curr_y = temp.y;
      } else {
        moveString += "Wall Present\n";
      }
    } else if (move.equals("left")) {
      if (!maze[curr_x][curr_y].getLeft()) {
        temp = this.findNearestRoom(maze[curr_x][curr_y], "left");
        curr_x = temp.x;
        curr_y = temp.y;
      } else {
        moveString += "Wall Present\n";
      }
    } else if (move.equals("top")) {
      if (!maze[curr_x][curr_y].getTop()) {
        temp = this.findNearestRoom(maze[curr_x][curr_y], "up");
        curr_x = temp.x;
        curr_y = temp.y;
      } else {
        moveString += "Wall Present\n";
      }
    } else if (move.equals("down")) {
      if (!maze[curr_x][curr_y].getBottom()) {
        temp = this.findNearestRoom(maze[curr_x][curr_y], "down");
        curr_x = temp.x;
        curr_y = temp.y;
      } else {
        moveString += "Wall Present\n";
      }
    }
    // bat encounter
    if (maze[curr_x][curr_y].getBat()) {
      // 50% chance of encountering a bat
      int encounter = getRandom(0, 1);
      if (encounter == 1) {
        moveString += "You encountered some Super Bats!\n";
        Cell transportedCell = this.batEncounter();
        curr_x = transportedCell.x;
        curr_y = transportedCell.y;
      } else {
        // does not encounter the bats
        moveString += "Phew! You dodged some Super Bats!\n";
      }
    }
    character[numChar].setCurrentLocation(new Point(curr_x, curr_y));
    moveString += "Your location: " + curr_x + ", " + curr_y + '\n';
    // pit encounter -> game over
    if (maze[curr_x][curr_y].getPit()) {
      moveString += "You fell into a Bottomless Pit! Game over." + '\n';
      return moveString;
    }
    // wumpus encounter -> game over
    if (maze[curr_x][curr_y].x == wumpus.getLocationX()
            && maze[curr_x][curr_y].y == wumpus.getLocationY()) {
      moveString += "You have been killed by the Wumpus! Game over." + '\n';
      return moveString;
    }

    //smelling the wumpus
    Cell nearestRoomLeft = this.checkNearestRoom(maze[curr_x][curr_y], "left");
    Cell nearestRoomRight = this.checkNearestRoom(maze[curr_x][curr_y], "right");
    Cell nearestRoomTop = this.checkNearestRoom(maze[curr_x][curr_y], "up");
    Cell nearestRoomDown = this.checkNearestRoom(maze[curr_x][curr_y], "down");
    // if the nearest room contains the wumpus
    if (nearestRoomLeft.x == wumpus.getLocationX() && nearestRoomLeft.y == wumpus.getLocationY()
            || nearestRoomRight.x == wumpus.getLocationX()
            && nearestRoomRight.y == wumpus.getLocationY()
            || nearestRoomTop.x == wumpus.getLocationX()
            && nearestRoomTop.y == wumpus.getLocationY()
            || nearestRoomDown.x == wumpus.getLocationX()
            && nearestRoomDown.y == wumpus.getLocationY()) {
      moveString += "You feel a stench!" + '\n';
    }

    //getting draft from pit
    if (nearestRoomLeft.getPit() || nearestRoomRight.getPit() || nearestRoomTop.getPit()
            || nearestRoomDown.getPit()) {
      moveString += "You feel a draft!" + '\n';
    }

    // telling the player in which directions he/she can move it
    String availableDirections = "[";
    if (!maze[curr_x][curr_y].getTop()) {
      availableDirections += " N ";
    }
    if (!maze[curr_x][curr_y].getRight()) {
      availableDirections += " E ";
    }
    if (!maze[curr_x][curr_y].getBottom()) {
      availableDirections += " S ";
    }
    if (!maze[curr_x][curr_y].getLeft()) {
      availableDirections += " W ";
    }
    availableDirections += "]";
    moveString += "Available Directions are: " + availableDirections;
    return moveString;
  }

  /**
   * Find the nearest room from the current cell in the direction the player wants to move in.
   *
   * @param a         the current room
   * @param direction the direction in which the player wants to travel
   * @return the nearest room
   */
  private Cell findNearestRoom(Cell a, String direction) {
    int currX = a.x;
    int currY = a.y;
    enterDirection = "";
    maze[currX][currY].setVisited(true);
    if (direction.equals("left") && !a.getLeft()) {
      currY--;
      enterDirection = "right";
    } else if (direction.equals("right") && !a.getRight()) {
      currY++;
      enterDirection = "left";
    } else if (direction.equals("up") && !a.getTop()) {
      currX--;
      enterDirection = "down";
    } else if (direction.equals("down") && !a.getBottom()) {
      currX++;
      enterDirection = "up";
    }
    maze[currX][currY].setVisited(true);
    while (isHallway(maze[currX][currY])) {
      if (!maze[currX][currY].getBottom() && !enterDirection.equals("down")) {
        currX++;
        enterDirection = "up";
        maze[currX][currY].setVisited(true);
      } else if (!maze[currX][currY].getTop() && !enterDirection.equals("up")) {
        currX--;
        enterDirection = "down";
        maze[currX][currY].setVisited(true);
      } else if (!maze[currX][currY].getLeft() && !enterDirection.equals("left")) {
        currY--;
        enterDirection = "right";
        maze[currX][currY].setVisited(true);
      } else if (!maze[currX][currY].getRight() && !enterDirection.equals("right")) {
        currY++;
        enterDirection = "left";
        maze[currX][currY].setVisited(true);
      }
      maze[currX][currY].setVisited(true);
    }
    return maze[currX][currY];
  }

  /**
   * Checks whether the nearest room from the player contains the Wumpus or a Pit.
   *
   * @param a         the current room
   * @param direction the direction in which the player wants to travel
   * @return the nearest room
   */
  private Cell checkNearestRoom(Cell a, String direction) {
    int currX = a.x;
    int currY = a.y;
    enterDirection = "";
    if (direction.equals("left") && !a.getLeft()) {
      currY--;
      enterDirection = "right";
    } else if (direction.equals("right") && !a.getRight()) {
      currY++;
      enterDirection = "left";
    } else if (direction.equals("up") && !a.getTop()) {
      currX--;
      enterDirection = "down";
    } else if (direction.equals("down") && !a.getBottom()) {
      currX++;
      enterDirection = "up";
    }
    while (isHallway(maze[currX][currY])) {
      if (!maze[currX][currY].getBottom() && !enterDirection.equals("down")) {
        currX++;
        enterDirection = "up";
      } else if (!maze[currX][currY].getTop() && !enterDirection.equals("up")) {
        currX--;
        enterDirection = "down";
      } else if (!maze[currX][currY].getLeft() && !enterDirection.equals("left")) {
        currY--;
        enterDirection = "right";
      } else if (!maze[currX][currY].getRight() && !enterDirection.equals("right")) {
        currY++;
        enterDirection = "left";
      }
    }

    return maze[currX][currY];
  }

  @Override
  public String arrowShoot(Cell coordinates, String direction, int steps, int numChar) {
    Cell location = maze[coordinates.x][coordinates.y];
    while (steps > 0) {
      location = checkNearestRoom(location, direction);
      steps--;
      if (enterDirection.equals("left") && location.getRight()) {
        break;
      } else if (enterDirection.equals("right") && location.getLeft()) {
        break;
      } else if (enterDirection.equals("up") && location.getBottom()) {
        break;
      } else if (enterDirection.equals("down") && location.getTop()) {
        break;
      }
    }
    String arrowResult = "";
    if (steps == 0 && location.x == wumpus.getLocationX() && location.y == wumpus.getLocationY()) {
      arrowResult += "You Won! You have successfully killed the Wumpus\n";
    } else {
      int arrowsRemain = character[numChar].getArrowsRemaining();
      int temp = arrowsRemain - 1;
      character[numChar].setArrowsRemaining(temp);
      arrowResult += "You Missed! ";
      if (character[numChar].getArrowsRemaining() > 0) {
        arrowResult += "Try again\n";
        arrowResult += "You have " + character[numChar].getArrowsRemaining()
                + " arrows remaining!\n";
      }
    }
    if (character[numChar].getArrowsRemaining() == 0) {
      arrowResult += "Game Over! You ran out of arrows.";
    }
    return arrowResult;
  }

  /**
   * Removing additional walls to meet the required rooms percentage.
   */
  private void removeAdditionalWalls() {
    // running the loop until the total rooms match the required number of rooms
    while (true) {
      int randX = getRandom(0, rows - 1);
      int randY = getRandom(0, cols - 1);
      Cell current = maze[randX][randY];
      // do not remove a wall if it forms the boundary of the maze
      if (randX == 0 && randY == 0 && !current.getBottom() && !current.getRight()) {
        continue;
      } else if (randX == rows - 1 && randY == 0 && !current.getTop() && !current.getRight()) {
        continue;
      } else if (randX == 0 && randY == cols - 1 && !current.getLeft() && !current.getBottom()) {
        continue;
      } else if (randX == rows && randY == cols - 1 && !current.getLeft() && !current.getTop()) {
        continue;
      }
      // removing the walls and updating the total number of walls removed
      if (isHallway(current)) {
        if (current.getTop() && randX != 0) {
          maze[randX][randY].setTop(false);
          maze[randX - 1][randY].setBottom(false);
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        } else if (current.getBottom() && randX != rows - 1) {
          maze[randX][randY].setBottom(false);
          maze[randX + 1][randY].setTop(false);
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        } else if (current.getLeft() && randY != 0) {
          maze[randX][randY].setLeft(false);
          maze[randX][randY - 1].setRight(false);
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        } else if (current.getRight() && randY != cols - 1) {
          maze[randX][randY].setRight(false);
          maze[randX][randY + 1].setLeft(false);
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        }
      }
    }
  }
}
