import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * The maze view Interface.
 */
public interface ViewInterface {
  /**
   * Adding the key listener to the view.
   *
   * @param listener a key listener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Resets the focus back into the maze view everytime a button is clicked on the maze or on the
   * keyboard.
   */
  void resetFocus();

  /**
   * Displays the maze along the buttons.
   *
   * @param p            the model
   * @param newLocationA the new location of character A
   * @param newLocationB the new location of character B
   * @param turn         the turn of the character
   * @param moveData     the data associated with the character's move
   */
  void callMaze(PerfectMazeModel p, Point newLocationA, Point newLocationB, int turn,
                String moveData) throws IOException;

  /**
   * Closes the main frame.
   */
  void exitFrame();

  /**
   * Returns the type of maze the user wants to play in.
   *
   * @return type of maze
   */
  String mazeType();

  /**
   * Prompts the user to enter the direction of shoot and the distance of shoot.
   */
  void displayShootMenu();

  /**
   * Adding the button listener to the view.
   *
   * @param buttonListener a button listener
   */
  void addListener(ButtonListener buttonListener);

  /**
   * Returns the number of steps the user wants the arrow to travel.
   *
   * @return the number of steps
   */
  int getShootSteps();

  /**
   * Returns the shoot direction input by the user.
   *
   * @return the shoot direction
   */
  String getShootDirection();

  /**
   * Returns the number of rows input by the user.
   *
   * @return the number of rows
   */
  int getRows();

  /**
   * Returns the number of columns input by the user.
   *
   * @return the number of columns
   */
  int getCols();

  /**
   * Checks whether the current game is multiplayer or not.
   *
   * @return whether the game is multiplayer or not
   */
  boolean isMultiplayer();

  /**
   * Returns the percentage of bats input by the user.
   *
   * @return the percentage of bats
   */
  int getBats();

  /**
   * Returns the percentage of pits input by the user.
   *
   * @return the percentage of pits
   */
  int getPits();
}
