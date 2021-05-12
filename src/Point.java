/**
 * The Point class denotes a point with a X and Y coordinate.
 */
public class Point {
  private int x;
  private int y;

  /**
   * Initialises the constructor with a X and Y coordinate.
   *
   * @param x X coordinate
   * @param y Y coordinate
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the X coordinate of the Point.
   *
   * @return the X coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the Y coordinate of the Point.
   *
   * @return the Y coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns whether two points are equal or not.
   *
   * @param p another point
   * @return whether they are equal or not
   */
  public boolean isEqual(Point p) {
    return this.x == p.x && this.y == p.y;
  }

  @Override
  public String toString() {
    return String.format("(" + this.x + ", " + this.y + ") ");
  }

}
