import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The starting point of the program.
 */
public class Driver {
  /**
   * The main function of the program.
   */
  public static void main(final String[] args) {
    try {
      final String mode = "gui";
      if (mode.equalsIgnoreCase("--gui")) {
        PerfectMazeModel model = new RoomMazeModel(5, 5, 0, 0, 10, 10);
        ViewImpl view = new ViewImpl();
        MazeViewController m = new MazeViewController(view, model);
        m.start();
      } else if (mode.equalsIgnoreCase("--text")) {
        PerfectMazeModel model = new PerfectMazeModel();
        MazeController ctrl = new MazeConsoleController(model, new InputStreamReader(System.in),
                System.out);
        try {
          ctrl.start();
        } catch (IOException e) {
          System.out.println("Something went wrong. Please check your input.");
        }
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
