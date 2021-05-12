import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests whether the cell in the maze is being formed correctly.
 */
public class CellTest {
  private Cell cell;

  @Before
  public void setup() {
    cell = new Cell(0, 0);
  }

  @Test
  public void testIfWallsArePresent() {
    assertEquals(cell.getTop(), true);
    assertEquals(cell.getBottom(), true);
    assertEquals(cell.getRight(), true);
    assertEquals(cell.getLeft(), true);
  }

  @Test
  public void testRemoveTopWall() {
    cell.setTop(false);
    assertEquals(cell.getTop(), false);
  }

  @Test
  public void testRemoveBottomWall() {
    cell.setBottom(false);
    assertEquals(cell.getBottom(), false);
  }

  @Test
  public void testRemoveLeftWall() {
    cell.setLeft(false);
    assertEquals(cell.getLeft(), false);
  }

  @Test
  public void testRemoveRightWall() {
    cell.setRight(false);
    assertEquals(cell.getRight(), false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCoordinates() {
    assertEquals(new Cell(-1, -1), false);
  }

  @Test
  public void testSetWalls() {
    cell.setTop(false);
    cell.setBottom(false);
    cell.setLeft(false);
    cell.setRight(false);
    assertEquals(cell.getTop(), false);
    assertEquals(cell.getBottom(), false);
    assertEquals(cell.getLeft(), false);
    assertEquals(cell.getRight(), false);
  }
}