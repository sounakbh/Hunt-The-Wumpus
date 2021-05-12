/**
 * In a wrapping maze the the outer walls are removed, and when the character reaches a sidewall,
 * and there is no wall present the position is wrapped around to the opposite side.
 */
public class WrappingMazeModel extends PerfectMazeModel {
  private String enterDirection;
  private int requiredRooms;
  private int batsPercentage;
  private int pitsPercentage;
  Wumpus wumpus;

  /**
   * Initialising the wrapping maze.
   *
   * @param rows           the number of rows of the maze
   * @param cols           the number of columns of the maze
   * @param startX         starting position X of character
   * @param startY         starting position Y of character
   * @param pitsPercentage the percentage of rooms containing pits
   * @param batsPercentage the percentage of rooms containing bats
   */
  public WrappingMazeModel(int rows, int cols, int startX, int startY,
                           int pitsPercentage, int batsPercentage) {
    super(rows, cols, startX, startY);
    if (pitsPercentage < 0 || pitsPercentage >= 30) {
      throw new IllegalArgumentException("Percentage of pits cannot be negative or greater "
              + "than or equal to 30");
    }
    if (batsPercentage < 0 || batsPercentage >= 50) {
      throw new IllegalArgumentException("Percentage of bats cannot be negative or greater "
              + "than equal to 50");
    }
    this.enterDirection = "";
    this.pitsPercentage = pitsPercentage;
    this.batsPercentage = batsPercentage;
    // 65% of the cells in the maze should be rooms
    final double roomsPercentage = 0.65;
    double requiredRoomsDouble = roomsPercentage * cols * rows;
    this.requiredRooms = (int) Math.round(requiredRoomsDouble);
    this.removeAdditionalWalls();
    this.placeWumpus();
    this.placePits();
    this.placeBats();
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
      // removing the walls and updating the total number of walls removed
      if (isHallway(current)) {
        if (current.getTop()) {
          maze[randX][randY].setTop(false);
          // wrap around and remove the bottom wall from the cell in the last row
          if (randX == 0) {
            maze[rows - 1][randY].setBottom(false);
          } else {
            maze[randX - 1][randY].setBottom(false);
          }
          // break if the total number of rooms have been met
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        } else if (current.getBottom()) {
          maze[randX][randY].setBottom(false);
          if (randX == rows - 1) {
            maze[0][randY].setTop(false);
          } else {
            maze[randX + 1][randY].setTop(false);
          }
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        } else if (current.getLeft()) {
          maze[randX][randY].setLeft(false);
          if (randY == 0) {
            maze[randX][cols - 1].setRight(false);
          } else {
            maze[randX][randY - 1].setRight(false);
          }
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        } else if (current.getRight() && randY != cols - 1) {
          maze[randX][randY].setRight(false);
          if (randY == cols - 1) {
            maze[randX][0].setLeft(false);
          } else {
            maze[randX][randY + 1].setLeft(false);
          }
          if (this.countRooms() >= requiredRooms) {
            break;
          }
        }
      }
    }
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
    return current;
  }

  @Override
  public String moveCharacter(String move, int numChar) {
    Point p = character[numChar].getLocation();
    int curr_x = p.getX();
    int curr_y = p.getY();
    // moving the character based on the direction provided
    Cell temp = null;
    String moveString = ""; // contains all the data related to the character's move
    if (move.equals("right")) {
      if (!maze[curr_x][curr_y].getRight()) {
        // finding the nearest room in the direction provided
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
        moveString += "Phew! You dodged some Super Bats!\n";
      }
    }
    // updating the character's location
    character[numChar].setCurrentLocation(new Point(curr_x, curr_y));
    moveString += "Your location: " + curr_x + ", " + curr_y + '\n';
    // falls into pit -> game over
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

    // showing all the available directions for the player to move in
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
      character[numChar].setArrowsRemaining(arrowsRemain - 1);
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
   * Find the nearest room from the current cell in the direction the player wants to move in.
   *
   * @param a         the current room
   * @param direction the direction in which the player wants to travel
   * @return the nearest room
   */
  private Cell findNearestRoom(Cell a, String direction) {
    int currX = a.x;
    int currY = a.y;
    String enterDirection = "";
    maze[currX][currY].setVisited(true);
    if (direction.equals("left") && !a.getLeft()) {
      if (currY == 0) {
        currY = cols - 1;
      } else {
        currY--;
      }
      enterDirection = "right";
    } else if (direction.equals("right") && !a.getRight()) {
      if (currY == cols - 1) {
        currY = 0;
      } else {
        currY++;
      }
      enterDirection = "left";
    } else if (direction.equals("up") && !a.getTop()) {
      if (currX == 0) {
        currX = rows - 1;
      } else {
        currX--;
      }
      enterDirection = "down";
    } else if (direction.equals("down") && !a.getBottom()) {
      if (currX == rows - 1) {
        currX = 0;
      } else {
        currX++;
      }
      enterDirection = "up";
    }
    // simulate the traversal till we encounter a cell which is a room
    maze[currX][currY].setVisited(true);
    while (isHallway(maze[currX][currY])) {
      if (!maze[currX][currY].getBottom() && !enterDirection.equals("down")) {
        if (currX == rows - 1) {
          currX = 0;
        } else {
          currX++;
        }
        enterDirection = "up";
      } else if (!maze[currX][currY].getTop() && !enterDirection.equals("up")) {
        if (currX == 0) {
          currX = rows - 1;
        } else {
          currX--;
        }
        enterDirection = "down";
      } else if (!maze[currX][currY].getLeft() && !enterDirection.equals("left")) {
        if (currY == 0) {
          currY = cols - 1;
        } else {
          currY--;
        }
        enterDirection = "right";
      } else if (!maze[currX][currY].getRight() && !enterDirection.equals("right")) {
        if (currY == cols - 1) {
          currY = 0;
        } else {
          currY++;
        }
        enterDirection = "left";
      }
      maze[currX][currY].setVisited(true);
    }

    return maze[currX][currY];
  }

  private Cell checkNearestRoom(Cell a, String direction) {
    int currX = a.x;
    int currY = a.y;
    String enterDirection = "";
    if (direction.equals("left") && !a.getLeft()) {
      if (currY == 0) {
        currY = cols - 1;
      } else {
        currY--;
      }
      enterDirection = "right";
    } else if (direction.equals("right") && !a.getRight()) {
      if (currY == cols - 1) {
        currY = 0;
      } else {
        currY++;
      }
      enterDirection = "left";
    } else if (direction.equals("up") && !a.getTop()) {
      if (currX == 0) {
        currX = rows - 1;
      } else {
        currX--;
      }
      enterDirection = "down";
    } else if (direction.equals("down") && !a.getBottom()) {
      if (currX == rows - 1) {
        currX = 0;
      } else {
        currX++;
      }
      enterDirection = "up";
    }
    // simulate the traversal till we encounter a cell which is a room
    while (isHallway(maze[currX][currY])) {
      if (!maze[currX][currY].getBottom() && !enterDirection.equals("down")) {
        if (currX == rows - 1) {
          currX = 0;
        } else {
          currX++;
        }
        enterDirection = "up";
      } else if (!maze[currX][currY].getTop() && !enterDirection.equals("up")) {
        if (currX == 0) {
          currX = rows - 1;
        } else {
          currX--;
        }
        enterDirection = "down";
      } else if (!maze[currX][currY].getLeft() && !enterDirection.equals("left")) {
        if (currY == 0) {
          currY = cols - 1;
        } else {
          currY--;
        }
        enterDirection = "right";
      } else if (!maze[currX][currY].getRight() && !enterDirection.equals("right")) {
        if (currY == cols - 1) {
          currY = 0;
        } else {
          currY++;
        }
        enterDirection = "left";
      }
    }

    return maze[currX][currY];
  }


}