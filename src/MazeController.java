import java.io.IOException;

/**
 * The maze controller interface which takes control from the driver after it has started.
 */
public interface MazeController {
  /**
   * Starts the controller.
   *
   * @throws IOException if there is a mismatch in input.
   */
  void start() throws IOException;
}
