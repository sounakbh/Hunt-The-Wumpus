import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Used to configure the Keys in the Keyboard.
 */
public class KeyBoardListener extends KeyAdapter implements KeyListener {
  private Map<Integer, Runnable> keyPressedMap;


  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  /**
   * Sets the keyPressedMap map to contain keyboard keys along with their corresponding action.
   *
   * @param keyPresses contains the keys and their corresponding commands
   */
  public void setKeyPressedMap(Map<Integer, Runnable> keyPresses) {
    this.keyPressedMap = keyPresses;
  }
}