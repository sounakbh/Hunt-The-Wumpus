import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Implements the View of the Maze, along with the control buttons.
 */
public class ViewImpl extends JFrame implements ViewInterface {
  private JFrame frame;

  private int viewRows;
  private int viewCols;
  private String mazeType;
  private boolean multiplayer;
  private String shootDirection;
  private int shootSteps;
  private int batsPercentage;
  private int pitsPercentage;

  private ButtonListener l;
  private KeyListener k;

  public ViewImpl() {
    this.callViewMenu();
  }


  /**
   * Returns a panel which contains the maze and the control buttons.
   *
   * @param rows         the rows of the maze
   * @param cols         the cols of the maze
   * @param maze         the maze
   * @param newLocationA the new location of Character A
   * @param newLocationB the new location of Character B
   * @param turn         the turn of the player
   * @param moveData     data associated with the player's move
   * @return a Panel with the maze and control buttons
   */
  private JPanel createMazePanel(int rows, int cols, Cell[][] maze, Point newLocationA,
                                 Point newLocationB, int turn, String moveData) throws IOException {
    GridLayout gridLayout = new GridLayout(rows, cols, 0, 0);
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    JButton arrowDown = new JButton("⬇ South");
    JButton arrowLeft = new JButton("⬅️ West");
    JButton arrowRight = new JButton("➡️ East");
    JButton arrowUp = new JButton("⬆️ North");
    JButton arrowShootButton = new JButton("Shoot");
    JButton restartButton = new JButton("Restart");
    JButton exitButton = new JButton("Exit");
    JButton newGameButton = new JButton("New Game");

    arrowDown.addActionListener(l);
    arrowLeft.addActionListener(l);
    arrowRight.addActionListener(l);
    arrowUp.addActionListener(l);
    arrowShootButton.addActionListener(l);
    restartButton.addActionListener(l);
    exitButton.addActionListener(l);
    newGameButton.addActionListener(l);

    arrowDown.setActionCommand("Down");
    arrowUp.setActionCommand("Up");
    arrowRight.setActionCommand("Right");
    arrowLeft.setActionCommand("Left");
    arrowShootButton.setActionCommand("Shoot");
    restartButton.setActionCommand("Restart");
    exitButton.setActionCommand("Exit");
    newGameButton.setActionCommand("New");

    JPanel firstLevel = new JPanel();
    firstLevel.add(arrowUp);
    firstLevel.add(arrowDown);

    JPanel secondLevel = new JPanel();
    secondLevel.add(arrowLeft);
    secondLevel.add(arrowRight);

    JPanel thirdLevel = new JPanel();
    thirdLevel.add(arrowShootButton);

    JPanel fourthLevel = new JPanel();
    fourthLevel.add(restartButton);
    fourthLevel.add(exitButton);
    fourthLevel.add(newGameButton);

    mainPanel.add(firstLevel);
    mainPanel.add(secondLevel);
    mainPanel.add(thirdLevel);
    mainPanel.add(fourthLevel);

    JLabel playerTurn = new JLabel("It is Player " + (turn + 1) + "'s turn");
    if (turn == 1) {
      playerTurn.setBackground(Color.CYAN);
    } else {
      playerTurn.setBackground(Color.GREEN);
    }
    playerTurn.setOpaque(true);
    playerTurn.setForeground(Color.black);
    mainPanel.add(playerTurn);
    JPanel gridPanel = new JPanel(gridLayout);
    for (int i = 0; i < this.viewRows; i++) {
      for (int j = 0; j < this.viewCols; j++) {
        JLabel button = new JLabel();
        BufferedImage img = null;
        if (maze[i][j].getTop() && maze[i][j].getLeft() && maze[i][j].getRight()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/S.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if ((maze[i][j].getTop() && maze[i][j].getBottom() && maze[i][j].getRight())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/W.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if ((maze[i][j].getTop() && maze[i][j].getBottom() && maze[i][j].getLeft())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/E.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if ((maze[i][j].getRight() && maze[i][j].getBottom() && maze[i][j].getLeft())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/N.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if (maze[i][j].getTop() && maze[i][j].getBottom()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/EW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if (maze[i][j].getBottom() && maze[i][j].getLeft()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/NE.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if ((maze[i][j].getRight() && maze[i][j].getLeft())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/NS.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if ((maze[i][j].getRight() && maze[i][j].getBottom())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/NW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if ((maze[i][j].getTop() && maze[i][j].getLeft())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/SE.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }

        } else if ((maze[i][j].getTop() && maze[i][j].getRight())) {
          try {
            img = ImageIO.read(getClass().getResource("resources/SW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if (maze[i][j].getLeft()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/NSE.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if (maze[i][j].getRight()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/NSW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if (maze[i][j].getTop()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/SEW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else if (maze[i][j].getBottom()) {
          try {
            img = ImageIO.read(getClass().getResource("resources/NEW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        } else {
          try {
            img = ImageIO.read(getClass().getResource("resources/NSEW.png"));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        }

        if (maze[i][j].isWumpus()) {
          BufferedImage batImage = ImageIO.read(getClass().getResource("resources/wumpus.png"));
          batImage = resize(batImage, 0.5);
          img = overlayImages(img, batImage, "wumpus");
        }

        if (maze[i][j].isBat()) {
          BufferedImage batImage = ImageIO.read(getClass().getResource("resources/bats.png"));
          batImage = resize(batImage, 0.5);
          img = overlayImages(img, batImage, "bat");
        }

        if (maze[i][j].isPit()) {
          BufferedImage batImage = ImageIO.read(getClass().getResource("resources/pit.png"));
          batImage = resize(batImage, 0.5);
          img = overlayImages(img, batImage, "pit");
        }

        if (i == newLocationA.getX() && j == newLocationA.getY()) {
          BufferedImage batImage = ImageIO.read(getClass().getResource("resources/player.png"));
          batImage = resize(batImage, 0.5);
          BufferedImage stenchImage = ImageIO.read(getClass().getResource("resources/stench.png"));
          stenchImage = resize(stenchImage, 0.5);
          BufferedImage breezeImage = ImageIO.read(getClass().getResource("resources/breeze.png"));
          breezeImage = resize(breezeImage, 0.5);
          img = overlayImages(img, batImage, "player");
          if (moveData.contains("stench") && (turn == 1 || !this.isMultiplayer())) {
            img = overlayImages(img, stenchImage, "stench");
          }
          if (moveData.contains("draft") && (turn == 1 || !this.isMultiplayer())) {
            img = overlayImages(img, breezeImage, "breeze");
          }
          maze[i][j].setVisited(true);
        }

        if (this.isMultiplayer() && i == newLocationB.getX() && j == newLocationB.getY()) {
          BufferedImage batImage = ImageIO.read(getClass().getResource("resources/thief.png"));
          batImage = resize(batImage, 0.5);
          BufferedImage stenchImage = ImageIO.read(getClass().getResource("resources/stench.png"));
          stenchImage = resize(stenchImage, 0.5);
          BufferedImage breezeImage = ImageIO.read(getClass().getResource("resources/breeze.png"));
          breezeImage = resize(breezeImage, 0.5);
          img = overlayImages(img, batImage, "player");
          if (moveData.contains("stench") && turn == 0) {
            img = overlayImages(img, stenchImage, "stench");
          }
          if (moveData.contains("draft") && turn == 0) {
            img = overlayImages(img, breezeImage, "breeze");
          }
          maze[i][j].setVisited(true);
        }
        button.setIcon(new ImageIcon(img));
        if (!maze[i][j].isVisited()) {
          button.setVisible(false);
        }
        gridPanel.add(button);
      }
    }
    JScrollPane pane = new JScrollPane(gridPanel);
    mainPanel.add(pane);
    return mainPanel;
  }


  /**
   * Resize the image in order to fit the image in a cell.
   *
   * @param inputImage the image to be resized
   * @param percent    the percentage it should be resized by
   * @return the resized image
   */
  private BufferedImage resize(BufferedImage inputImage, double percent) {
    int scaledWidth = (int) (inputImage.getWidth() * percent);
    int scaledHeight = (int) (inputImage.getHeight() * percent);
    // creates output image
    BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

    // scales the input image to the output image
    Graphics2D g2d = outputImage.createGraphics();
    g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
    g2d.dispose();

    // writes to output file
    return outputImage;
  }

  /**
   * Overlapping multiple images.
   *
   * @param bgImage the background image
   * @param fgImage the foreground image
   * @param images  the type of image
   * @return the overlapped image
   */
  private static BufferedImage overlayImages(BufferedImage bgImage, BufferedImage fgImage,
                                             String images) {
    Graphics2D g = bgImage.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawImage(bgImage, 0, 0, null);
    if (images.equals("bat")) {
      g.drawImage(fgImage, 10, 0, null);
    } else if (images.equals("wumpus")) {
      g.drawImage(fgImage, 15, 10, null);
    } else if (images.equals("pit")) {
      g.drawImage(fgImage, 15, 25, null);
    } else if (images.equals("player")) {
      g.drawImage(fgImage, 15, 20, null);
    } else if (images.equals("breeze")) {
      g.drawImage(fgImage, 15, 25, null);
    } else if (images.equals("stench")) {
      g.drawImage(fgImage, 16, 25, null);
    }
    g.dispose();
    return bgImage;
  }

  @Override
  public void callMaze(PerfectMazeModel p, Point newLocationA, Point newLocationB, int turn,
                       String moveData) throws IOException {
    Cell[][] maze = p.maze;
    JPanel mazeLayout = createMazePanel(viewRows, viewCols, maze, newLocationA, newLocationB,
            turn, moveData);
    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    mazeLayout.setBorder(padding);
    mazeLayout.setBackground(Color.white);
    mazeLayout.setBounds(10, 10, viewCols * 50, viewRows * 50);
    this.frame = new JFrame();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.add(mazeLayout);
    this.frame.addKeyListener(k);
    this.frame.requestFocus();
    this.frame.pack();
    this.frame.setVisible(true);
  }

  @Override
  public void exitFrame() {
    this.frame.dispose();
  }

  @Override
  public String mazeType() {
    return this.mazeType;
  }

  @Override
  public void displayShootMenu() {
    // direction
    String[] values = {"North", "South", "East", "West"};
    Object selected = JOptionPane.showInputDialog(null, "Shoot Direction", "Selection",
            JOptionPane.DEFAULT_OPTION, null, values, "Room Maze");
    if (selected != null) {
      if (selected.toString().equals("North")) {
        this.shootDirection = "N";
      } else if (selected.toString().equals("South")) {
        this.shootDirection = "S";
      } else if (selected.toString().equals("East")) {
        this.shootDirection = "E";
      } else {
        this.shootDirection = "W";
      }
    } else {
      System.out.println("User cancelled");
    }
    // steps
    String steps = JOptionPane.showInputDialog(null, "Steps: ");
    this.shootSteps = Integer.parseInt(steps);
  }

  /**
   * Calling the view menu, and setting the rows, columns, type of maze, number of players from the
   * user.
   */
  private void callViewMenu() {
    String rows = JOptionPane.showInputDialog(null, "Rows: ");
    String cols = JOptionPane.showInputDialog(null, "Cols: ");

    String bats = JOptionPane.showInputDialog(null, "Bats Percentage(0 - 30): ");
    String pits = JOptionPane.showInputDialog(null, "Pits Percentage(0 - 30): ");
    String[] values = {"Room Maze", "Wrapping Maze"};
    Object selected = JOptionPane.showInputDialog(null, "Type of Maze", "Selection",
            JOptionPane.DEFAULT_OPTION, null, values, "Room Maze");
    if (selected != null) {
      if (selected.toString().equals("Wrapping Maze")) {
        this.mazeType = "W";
      } else {
        this.mazeType = "N";
      }
    } else {
      System.out.println("User cancelled");
    }

    String[] multiplayerValue = {"Single Player", "Multiplayer"};
    Object multi = JOptionPane.showInputDialog(null, "Number of Players", "Selection",
            JOptionPane.DEFAULT_OPTION, null, multiplayerValue, "Single Player");
    if (multi != null) {
      if (multi.toString().equals("Single Player")) {
        this.multiplayer = false;
      } else {
        this.multiplayer = true;
      }
    } else {
      System.out.println("User cancelled");
    }

    this.viewRows = Integer.parseInt(rows);
    this.viewCols = Integer.parseInt(cols);
    this.batsPercentage = Integer.parseInt(bats);
    this.pitsPercentage = Integer.parseInt(pits);
  }

  @Override
  public void addListener(ButtonListener buttonListener) {
    l = buttonListener;
  }

  @Override
  public int getShootSteps() {
    return this.shootSteps;
  }

  @Override
  public String getShootDirection() {
    return this.shootDirection;
  }

  @Override
  public int getRows() {
    return viewRows;
  }

  @Override
  public int getCols() {
    return viewCols;
  }

  @Override
  public boolean isMultiplayer() {
    return this.multiplayer;
  }

  @Override
  public int getBats() {
    return this.batsPercentage;
  }

  @Override
  public int getPits() {
    return this.pitsPercentage;
  }

  @Override
  public void resetFocus() {
    frame.setFocusable(true);
    frame.requestFocus();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.k = listener;
  }
}