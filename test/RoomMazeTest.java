import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests the RoomMaze class.
 */
public class RoomMazeTest {
  RoomMazeModel p;

  @Before
  public void setup() {
    p = new RoomMazeModel(4, 4, 0, 0, 20, 20);

    p.maze[0][0].setLeft(true);
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
    p.maze[0][3].setBottom(false);
    p.maze[0][3].setRight(true);
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

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartingPoint() {
    new RoomMazeModel(10, 20, 11, -1, 20, 20);
  }

  @Test
  public void testMovementRight() {
    p.moveCharacter("right", 0);
    assertEquals(p.character[0].getLocation().getX(), 3);
    assertEquals(p.character[0].getLocation().getY(), 1);
  }

  @Test
  public void testMovementLeft() {
    p.moveCharacter("left", 0);
    assertEquals(p.character[0].getLocation().getX(), 0);
    assertEquals(p.character[0].getLocation().getY(), 0);
  }

  @Test
  public void testMovementTop() {
    System.out.println(p);
    assertEquals(p.character[0].getLocation().getX(), 0);
    assertEquals(p.character[0].getLocation().getY(), 0);
  }

  @Test
  public void testMovementDown() {
    p.moveCharacter("down", 0);
    assertEquals(p.character[0].getLocation().getX(), 0);
    assertEquals(p.character[0].getLocation().getY(), 0);
  }

  @Test
  public void testPitFall() {
    p.maze[3][1].assignPit(true);
    String move = p.moveCharacter("right", 0);
    assertTrue(move.contains("over"));
  }

  @Test
  public void testDraft() {
    p.maze[3][2].assignPit(true);
    String move = p.moveCharacter("right", 0);
    assertTrue(move.contains("feel a draft"));
  }

  @Test
  public void testStench() {
    p.wumpus.setLocation(new Point(3, 2));
    String move = p.moveCharacter("right", 0);
    assertTrue(move.contains("feel a stench"));
  }

  @Test
  public void testArrowsOver() {
    p.arrowShoot(new Cell(0, 0), "left", 1, 0);
    p.arrowShoot(new Cell(0, 0), "left", 1, 0);
    String move = p.arrowShoot(new Cell(0, 0), "left", 1, 0);
    assertTrue(move.contains("ran out of arrows"));
  }

  @Test
  public void testAvailableDirections() {
    String move = p.moveCharacter("right", 0);
    assertTrue(move.contains("Available Directions are: [ N  E  W ]"));
  }

  @Test
  public void testInvalidMoveWithWall() {
    String move = p.moveCharacter("left", 0);
    assertTrue(move.contains("Wall Present"));
    assertEquals(p.character[0].getLocation().getX(), 0);
    assertEquals(p.character[0].getLocation().getY(), 0);
  }

  @Test
  public void testStartingPoint() {
    assertEquals(p.character[0].getLocation().getX(), 0);
    assertEquals(p.character[0].getLocation().getY(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMaze() {
    new RoomMazeModel(-1, 30, -1, 0, 20, 20);
  }

  @Test
  public void testWumpusKilled() {
    p.wumpus.setLocation(new Point(3, 2));
    String move = p.arrowShoot(new Cell(0, 0), "right", 2, 0);
    assertTrue(move.contains("You Won!"));
  }

  @Test
  public void testBatEncounter() {
    p.maze[3][1].assignBat(true);
    Point currentLocation = p.character[0].getLocation();
    while (true) {
      String move = p.moveCharacter("right", 0);
      if (move.contains("You encountered some Super Bats!")) {
        assertFalse(currentLocation.isEqual(new Point(3, 1)));
        break;
      } else {
        p.moveCharacter("left", 0);
      }
    }
  }

  @Test
  public void testArrowsRemaining() {
    p.arrowShoot(new Cell(0, 0), "left", 1, 0);
    assertEquals(p.character[0].getArrowsRemaining(), 2);
  }

  @Test
  public void testKilledByWumpus() {
    p.wumpus.setLocation(new Point(3, 1));
    String move = p.moveCharacter("right", 0);
    assertTrue(move.contains("You have been killed by the Wumpus! Game over."));
  }

  @Test
  public void testInitialArrows() {
    assertEquals(p.character[0].getArrowsRemaining(), 3);
  }

  @Test
  public void testMultiplayerMove() {
    p.moveCharacter("right", 0);
    p.moveCharacter("right", 1);
    assertEquals(p.character[0].getLocation().getX(), p.character[1].getLocation().getX());
    assertEquals(p.character[0].getLocation().getY(), p.character[1].getLocation().getY());
  }

  @Test
  public void shootArrowMultiplayer() {
    p.arrowShoot(new Cell(0, 0), "left", 1, 0);
    p.arrowShoot(new Cell(0, 0), "left", 1, 1);
    p.arrowShoot(new Cell(0, 0), "left", 1, 1);
    assertEquals(p.character[0].getArrowsRemaining(), 2);
    assertEquals(p.character[1].getArrowsRemaining(), 1);
  }

}