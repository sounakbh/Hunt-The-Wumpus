/**
 * The Maze interface represents the Maze upon which the game is played.
 */
public interface MazeModel {
  /**
   * Simulate shooting of the arrow based on the current location, the direction and the number of
   * rooms the arrow should travel.
   *
   * @param coordinates the room from where the shooting occurs
   * @param direction   the direction of the arrow shot
   * @param steps       the number of rooms the arrow should travel
   * @return whether the arrow shot was successful or not
   */
  String arrowShoot(Cell coordinates, String direction, int steps, int numChar);

  /**
   * Moves the Character in the direction specified, updates the new location of the character and
   * the total amount of gold possessed currently.
   *
   * @param move direction of move (left, right, top, down)
   * @return the data associated with the character move
   */
  String moveCharacter(String move, int numChar);
}
