import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests the WrappingMaze class.
 */
public class WrappingMazeTest {
  WrappingMazeModel p;

  @Before
  public void setup() {
    p = new WrappingMazeModel(4, 4, 0, 0, 20, 20);
    p.maze[0][0].setLeft(false);
    p.maze[0][0].setTop(true);
    p.maze[0][0].setBottom(true);
    p.maze[0][0].setRight(false);
    p.maze[0][0].assignBat(false);
    p.maze[0][0].assignPit(false);


    p.maze[0][1].setLeft(false);
    p.maze[0][1].setTop(true);
    p.maze[0][1].setBottom(true);
    p.maze[0][1].setRight(false);
    p.maze[0][1].assignBat(false);
    p.maze[0][1].assignPit(false);

    p.maze[0][2].setLeft(false);
    p.maze[0][2].setTop(true);
    p.maze[0][2].setBottom(false);
    p.maze[0][2].setRight(true);
    p.maze[0][2].assignBat(false);
    p.maze[0][2].assignPit(false);

    p.maze[0][3].setLeft(true);
    p.maze[0][3].setTop(true);
    p.maze[0][3].setBottom(true);
    p.maze[0][3].setRight(false);
    p.maze[0][3].assignBat(false);
    p.maze[0][3].assignPit(false);

    p.maze[1][0].setLeft(true);
    p.maze[1][0].setTop(true);
    p.maze[1][0].setBottom(false);
    p.maze[1][0].setRight(false);
    p.maze[1][0].assignBat(false);
    p.maze[1][0].assignPit(false);

    p.maze[1][1].setLeft(false);
    p.maze[1][1].setTop(true);
    p.maze[1][1].setBottom(true);
    p.maze[1][1].setRight(false);
    p.maze[1][1].assignBat(false);
    p.maze[1][1].assignPit(true);

    p.maze[1][2].setLeft(false);
    p.maze[1][2].setTop(false);
    p.maze[1][2].setBottom(true);
    p.maze[1][2].setRight(true);
    p.maze[1][2].assignBat(false);
    p.maze[1][2].assignPit(false);

    p.maze[1][3].setLeft(true);
    p.maze[1][3].setTop(false);
    p.maze[1][3].setBottom(false);
    p.maze[1][3].setRight(true);
    p.maze[1][3].assignPit(true);
    p.maze[1][3].assignBat(true);


    p.maze[2][0].setLeft(true);
    p.maze[2][0].setTop(false);
    p.maze[2][0].setBottom(false);
    p.maze[2][0].setRight(true);
    p.maze[2][0].assignBat(false);
    p.maze[2][0].assignPit(false);

    p.maze[2][1].setLeft(true);
    p.maze[2][1].setTop(true);
    p.maze[2][1].setBottom(false);
    p.maze[2][1].setRight(true);
    p.maze[2][1].assignBat(false);
    p.maze[2][1].assignPit(false);

    p.maze[2][2].setLeft(true);
    p.maze[2][2].setTop(true);
    p.maze[2][2].setBottom(false);
    p.maze[2][2].setRight(false);
    p.maze[2][2].assignBat(false);
    p.maze[2][2].assignPit(false);

    p.maze[2][3].setLeft(false);
    p.maze[2][3].setTop(false);
    p.maze[2][3].setBottom(false);
    p.maze[2][3].setRight(true);
    p.maze[2][3].assignBat(false);
    p.maze[2][3].assignPit(false);


    p.maze[3][0].setLeft(true);
    p.maze[3][0].setTop(false);
    p.maze[3][0].setBottom(true);
    p.maze[3][0].setRight(false);
    p.maze[3][0].assignBat(false);
    p.maze[3][0].assignPit(false);

    p.maze[3][1].setLeft(false);
    p.maze[3][1].setTop(false);
    p.maze[3][1].setBottom(true);
    p.maze[3][1].setRight(false);
    p.maze[3][1].assignBat(false);
    p.maze[3][1].assignPit(false);

    p.maze[3][2].setLeft(false);
    p.maze[3][2].setTop(false);
    p.maze[3][2].setBottom(true);
    p.maze[3][2].setRight(false);
    p.maze[3][2].assignBat(false);
    p.maze[3][2].assignPit(false);

    p.maze[3][3].setLeft(false);
    p.maze[3][3].setTop(false);
    p.maze[3][3].setBottom(true);
    p.maze[3][3].setRight(true);
    p.maze[3][3].assignBat(false);
    p.maze[3][3].assignPit(false);

    p.wumpus.setLocation(new Point(0, 3));
  }

  @Test
  public void testWrapping() {
    p.moveCharacter("left", 1);
    assertTrue(p.character[1].getLocation().isEqual(new Point(0, 3)));
  }

  @Test
  public void testWrappingArrow() {
    p.wumpus.setLocation(new Point(0, 3));
    String move = p.arrowShoot(new Cell(0, 0), "left", 1, 1);
    assertTrue(move.contains("You Won! You have successfully killed the Wumpus"));
  }
}