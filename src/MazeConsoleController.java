import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The console controller class which takes input from the user and builds the appropriate type of
 * maze.
 */
public class MazeConsoleController implements MazeController {
  private PerfectMazeModel model;
  private final Readable rd;
  private final Appendable ap;

  /**
   * Initialising the Console controller with the model and the reading and writing streams.
   *
   * @param m  the model
   * @param rd the readable stream
   * @param ap the appendable stream
   */
  public MazeConsoleController(PerfectMazeModel m, Readable rd, Appendable ap) {
    if (m == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Model, Readable, or Appendable cannot be null");
    }
    this.model = m;
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void start() throws IOException {
    this.ap.append("Choose your type of maze (1/2): \n");
    this.ap.append("1) Non-Wrapping\n");
    this.ap.append("2) Wrapping\n");
    Scanner sc = new Scanner(this.rd);
    try {
      int mazeType = sc.nextInt();
      this.ap.append("Multiplayer? Press 1 for 'Yes', and 0 for 'No': ");
      int isMultiplayer = sc.nextInt();
      this.ap.append("Enter number of rows: ");
      int rows = sc.nextInt();
      this.ap.append("Enter number of cols: ");
      int cols = sc.nextInt();
      this.ap.append("Starting X Position: ");
      int startX = sc.nextInt();
      this.ap.append("Starting Y Position: ");
      int startY = sc.nextInt();
      this.ap.append("Percentage of Pits (0 - 30): ");
      int pitsPercentage = sc.nextInt();
      this.ap.append("Percentage of Super Bats (0 - 50): ");
      int batsPercentage = sc.nextInt();
      // Room Maze
      if (mazeType == 1) {
        this.model = new RoomMazeModel(rows, cols, startX, startY, pitsPercentage, batsPercentage);
      }
      // Wrapping maze
      else if (mazeType == 2) {
        this.model = new WrappingMazeModel(rows, cols, startX, startY, pitsPercentage,
                batsPercentage);
      }
      sc.nextLine();
      int turn = 0;
      while (true) {
        System.out.println("Player's turn: " + (turn + 1));
        this.ap.append("\nTo move press n (North), s (South), e (East) or w (West)\n");
        this.ap.append("To shoot an arrow, press y(Yes)\n");
        this.ap.append("Press q to quit\n");
        String input = sc.nextLine();
        String moveData = "";
        // if the player wishes to move
        if (input.equalsIgnoreCase("n")) {
          moveData = this.model.moveCharacter("top", turn) + '\n';
          this.ap.append(moveData + '\n');
          if (moveData.contains("over") || moveData.contains("won")) {
            break;
          }
        } else if (input.equalsIgnoreCase("e")) {
          moveData = this.model.moveCharacter("right", turn) + '\n';
          this.ap.append(moveData + '\n');
          if (moveData.contains("over") || moveData.contains("won")) {
            break;
          }
        } else if (input.equalsIgnoreCase("s")) {
          moveData = this.model.moveCharacter("down", turn) + '\n';
          this.ap.append(moveData + '\n');
          if (moveData.contains("over") || moveData.contains("won")) {
            break;
          }
        } else if (input.equalsIgnoreCase("w")) {
          moveData = this.model.moveCharacter("left", turn) + '\n';
          this.ap.append(moveData + '\n');
          if (moveData.contains("over") || moveData.contains("won")) {
            break;
          }
        }
        // if the player wants to quit
        else if (input.equalsIgnoreCase("q")) {
          this.ap.append(this.model.toString());
          break;
        }
        // if the player wants to shoot an arrow
        else if (input.equalsIgnoreCase("y")) {
          // get direction of shoot
          this.ap.append("Enter the direction you want to shoot it in [n, s, e, w]: ");
          String arrowDirection = sc.nextLine();
          // get distance
          this.ap.append("Enter the number of rooms it should travel: ");
          int steps = sc.nextInt();
          if (arrowDirection.equalsIgnoreCase("n")) {
            arrowDirection = "up";
          } else if (arrowDirection.equalsIgnoreCase("s")) {
            arrowDirection = "down";
          } else if (arrowDirection.equalsIgnoreCase("e")) {
            arrowDirection = "right";
          } else if (arrowDirection.equalsIgnoreCase("w")) {
            arrowDirection = "left";
          }
          // if illegal input
          else {
            this.ap.append("Wrong input. Try again.");
          }
          Point characterLocation = this.model.character[turn].getLocation();
          // call shoot arrow method
          String arrowResult = this.model.arrowShoot(new Cell(characterLocation.getX(),
                          characterLocation.getY()),
                  arrowDirection, steps, turn);
          this.ap.append(arrowResult);
          // if arrow hits wumpus, then break
          if (arrowResult.contains("Won") || arrowResult.contains("Over")) {
            break;
          }
          sc.nextLine();
        } else {
          this.ap.append("Wrong input. Try again!\n");
        }
        if (isMultiplayer == 1) {
          turn = (turn == 0) ? 1 : 0;
        }
      }
    } catch (InputMismatchException e) {
      this.ap.append("You provided an illegal input. Please try again.");
    } catch (IllegalArgumentException e) {
      this.ap.append(e.getMessage());
    }

  }
}
