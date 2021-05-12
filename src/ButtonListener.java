import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Used to configure the Buttons in the Maze.
 */
public class ButtonListener implements ActionListener {

  private Map<String, Runnable> buttonClickedActions;

  public ButtonListener() {
    buttonClickedActions = null;
  }

  /**
   * Sets the buttonClickedActions map to contain keys along with their corresponding action.
   *
   * @param map contains the keys and their corresponding commands
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
