/**
 * Represents each cell in a maze. Every cell has 4 walls namely left, right, top and bottom in the
 * beginning. A cell can have a random amount of gold between 1 and 10, and thieves are present at
 * 10% of the total number of cells.
 */
public class Cell {
  private boolean left;
  private boolean top;
  private boolean right;
  private boolean bottom;
  private boolean isPit;
  private boolean isBat;
  private boolean isWumpus;
  private boolean isVisited;
  int x;
  int y;

  /**
   * Initialising the cell with all 4 walls, a X and Y coordinate, 0 amount of gold, and no thief.
   *
   * @param x X coordinate
   * @param y Y coordinate
   */
  public Cell(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Coordinates cannot be negative!");
    }
    left = true;
    top = true;
    right = true;
    bottom = true;
    this.isVisited = false;
    this.x = x;
    this.y = y;
  }

  public void assignPit(boolean isPit) {
    this.isPit = isPit;
  }

  public boolean getPit() {
    return this.isPit;
  }

  /**
   * Assigns a thief to the cell.
   *
   * @param isBatPresent boolean which states whether a thief is there in the cell or not
   */
  public void assignBat(boolean isBatPresent) {
    this.isBat = isBatPresent;
  }

  public boolean getBat() {
    return this.isBat;
  }


  /**
   * Sets or removes the top wall.
   *
   * @param b add or remove the wall
   */
  public void setTop(boolean b) {
    top = b;
  }

  /**
   * Sets or removes the bottom wall.
   *
   * @param b add or remove the wall
   */
  public void setBottom(boolean b) {
    bottom = b;
  }

  /**
   * Sets or removes the left wall.
   *
   * @param b add or remove the wall
   */
  public void setLeft(boolean b) {
    left = b;
  }

  /**
   * Sets or removes the right wall.
   *
   * @param b add or remove the wall
   */
  public void setRight(boolean b) {
    right = b;
  }

  public void setWumpus(boolean b) {
    isWumpus = b;
  }

  public void setVisited(boolean b) {
    this.isVisited = b;
  }

  /**
   * Checks whether the top wall is present or not.
   *
   * @return whether the top wall is present or not
   */
  public boolean getTop() {
    return top;
  }

  /**
   * Checks whether the bottom wall is present or not.
   *
   * @return whether the bottom wall is present or not
   */
  public boolean getBottom() {
    return bottom;
  }

  /**
   * Checks whether the right wall is present or not.
   *
   * @return whether the right wall is present or not
   */
  public boolean getRight() {
    return right;
  }

  /**
   * Checks whether the left wall is present or not.
   *
   * @return whether the left wall is present or not
   */
  public boolean getLeft() {
    return left;
  }

  public boolean isBat() {
    return this.isBat;
  }

  public boolean isPit() {
    return this.isPit;
  }

  public boolean isWumpus() {
    return this.isWumpus;
  }

  public boolean isVisited() {
    return this.isVisited;
  }

  @Override
  public String toString() {
    String x = "";
    if (left) {
      x += "L";
    }
    if (right) {
      x += "R";
    }
    if (top) {
      x += "T";
    }
    if (bottom) {
      x += "B";
    }
    if (isPit) {
      x += " Pit";
    }
    if (isBat) {
      x += " Bat";
    }
    return String.format("(" + this.x + ", " + this.y + ") " + x + " ");
  }
}
