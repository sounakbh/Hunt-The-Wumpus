/**
 * Represents the Wumpus in the maze.
 */
public class Wumpus {
  private int locationX;
  private int locationY;

  /**
   * Initialising the wumpus.
   *
   * @param x the x coordinate of the wumpus
   * @param y the y coordinate of the wumpus
   */
  public Wumpus(int x, int y) {
    this.locationX = x;
    this.locationY = y;
  }

  /**
   * Return the X coordinate of the Wumpus.
   *
   * @return x coordinate of the Wumpus
   */
  public int getLocationX() {
    return this.locationX;
  }

  /**
   * Return the Y coordinate of the Wumpus.
   *
   * @return y coordinate of the Wumpus
   */
  public int getLocationY() {
    return this.locationY;
  }

  /**
   * Sets the location of the Wumpus in the maze.
   */
  public void setLocation(Point p) {
    this.locationX = p.getX();
    this.locationY = p.getY();
  }
}
