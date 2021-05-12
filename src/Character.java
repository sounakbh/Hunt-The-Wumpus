/**
 * The Character class represents the player and given information about the character, such as his
 * location in the maze, and the amount of gold he posses.
 */
public class Character {
  private Point location;
  private int arrowsRemaining;

  /**
   * Initialising the character with a starting point and 0 gold.
   *
   * @param p starting point
   */
  public Character(Point p) {
    this.arrowsRemaining = 3;
    this.location = p;
  }

  /**
   * Used to get the location of the character.
   *
   * @return a Point which shows the location of the character
   */
  public Point getLocation() {
    return this.location;
  }

  /**
   * Sets the new location of the character.
   *
   * @param p the new location of the character
   */
  public void setCurrentLocation(Point p) {
    this.location = p;
  }

  /**
   * Get the arrows possessed by the character.
   *
   * @return the arrows possessed by the character
   */
  public int getArrowsRemaining() {
    return this.arrowsRemaining;
  }

  /**
   * Set the number of arrows possessed by the character.
   *
   * @param arrows the number of arrows possessed by the character
   */
  public void setArrowsRemaining(int arrows) {
    this.arrowsRemaining = arrows;
  }

  @Override
  public String toString() {
    return location.getX() + " " + location.getY();
  }
}
