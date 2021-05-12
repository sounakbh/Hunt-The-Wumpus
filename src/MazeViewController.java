import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;


/**
 * The controller which deals with the GUI of the game.
 */
public class MazeViewController implements MazeController {
  private ViewImpl theView;
  private PerfectMazeModel theModel;
  private String moveData;
  private int turn;

  /**
   * Instantiating the View Controller.
   *
   * @param theView the view
   * @param theModel the model
   */
  public MazeViewController(ViewImpl theView, PerfectMazeModel theModel) {
    this.theModel = theModel;
    this.theView = theView;
    this.configureButtonListener();
    this.configureKeyBoardListener();
    this.turn = 0;
  }


  @Override
  public void start() throws IOException {
    System.out.println("From the controller: " + theView.getRows() + ", " + theView.getCols());

    if (theView.mazeType().equals("W")) {
      theModel = new WrappingMazeModel(theView.getRows(), theView.getCols(), 0, 0,
              theView.getPits(), theView.getBats());
    } else {
      theModel = new RoomMazeModel(theView.getRows(), theView.getCols(), 0, 0,
              theView.getPits(), theView.getBats());
    }
    Point newLocationA = theModel.character[0].getLocation();
    Point newLocationB = null;
    if (theView.isMultiplayer()) {
      newLocationB = theModel.character[1].getLocation();
    }
    System.out.println("Char location: " + newLocationA);
    theView.callMaze(theModel, newLocationA, newLocationB, turn, "");
  }

  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    keyPresses.put(KeyEvent.VK_DOWN, () -> {
      System.out.println("Down Pressed!");
      this.moveData = this.theModel.moveCharacter("down", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(this.theModel.character[0].getLocation());
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });

    keyPresses.put(KeyEvent.VK_UP, () -> {
      System.out.println("Up Pressed!");
      this.moveData = this.theModel.moveCharacter("top", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });

    keyPresses.put(KeyEvent.VK_RIGHT, () -> {

      System.out.println("Right Pressed!");
      this.moveData = this.theModel.moveCharacter("right", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });

    keyPresses.put(KeyEvent.VK_LEFT, () -> {

      System.out.println("Left Pressed!");
      this.moveData = this.theModel.moveCharacter("left", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });

    KeyBoardListener kbd = new KeyBoardListener();
    kbd.setKeyPressedMap(keyPresses);
    theView.addKeyListener(kbd);

  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Down", () -> {
      this.moveData = this.theModel.moveCharacter("down", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(this.theModel.character[0].getLocation());
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });
    buttonClickedMap.put("Up", () -> {
      this.moveData = this.theModel.moveCharacter("top", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });
    buttonClickedMap.put("Left", () -> {
      this.moveData = this.theModel.moveCharacter("left", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });
    buttonClickedMap.put("Right", () -> {
      this.moveData = this.theModel.moveCharacter("right", turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else {
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });

    buttonClickedMap.put("Exit", () -> theView.exitFrame());

    buttonClickedMap.put("New", () -> {
      theView.exitFrame();
      this.theView = new ViewImpl();
      MazeViewController m = new MazeViewController(theView, theModel);
      try {
        m.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    buttonClickedMap.put("Restart", () -> {
      theView.exitFrame();
      this.turn = 0;
      if (theView.mazeType().equals("W")) {
        theModel = new WrappingMazeModel(theView.getRows(), theView.getCols(), 0, 0,
                theView.getPits(), theView.getBats());
      } else {
        theModel = new RoomMazeModel(theView.getRows(), theView.getCols(), 0, 0,
                theView.getPits(), theView.getBats());
      }
      Point newLocationA = theModel.character[0].getLocation();
      Point newLocationB = null;
      if (theView.isMultiplayer()) {
        newLocationB = theModel.character[1].getLocation();
      }
      System.out.println("Char location: " + newLocationA);
      try {
        theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
      } catch (IOException e) {
        e.printStackTrace();
      }
      theView.resetFocus();

    });

    // arrow shoot functionality
    buttonClickedMap.put("Shoot", () -> {
      theView.displayShootMenu();
      Point p = theModel.character[turn].getLocation();
      Cell charLocation = new Cell(p.getX(), p.getY());
      String direction = theView.getShootDirection();
      if (direction.equals("N")) {
        direction = "up";
      } else if (direction.equals("S")) {
        direction = "down";
      } else if (direction.equals("E")) {
        direction = "right";
      } else {
        direction = "left";
      }

      this.moveData = this.theModel.arrowShoot(charLocation, direction,
              theView.getShootSteps(), turn);
      if (theView.isMultiplayer()) {
        turn = (turn == 0) ? 1 : 0;
      }
      System.out.println(moveData);
      if (moveData.contains("Won")) {
        JOptionPane.showMessageDialog(null, "You killed the Wumpus. You won!");
        theView.exitFrame();
      } else if (moveData.contains("Over")) {
        JOptionPane.showMessageDialog(null, moveData);
        theView.exitFrame();
      } else if (moveData.contains("Missed")) {
        JOptionPane.showMessageDialog(null, moveData);
        try {
          Point newLocationA = theModel.character[0].getLocation();
          Point newLocationB = theModel.character[1].getLocation();
          theView.exitFrame();
          theView.callMaze(theModel, newLocationA, newLocationB, turn, moveData);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      theView.resetFocus();
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.theView.addListener(buttonListener);
  }

}
