import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Character class.
 */
public class CharacterTest {
  private Character character;

  @Before
  public void setup() {
    character = new Character(new Point(0, 0));
  }

  @Test
  public void testGetLocation() {
    Point p = character.getLocation();
    assertEquals(p.getX(), 0);
    assertEquals(p.getY(), 0);
  }

  @Test
  public void testSetLocation() {
    Point newLocation = new Point(1, 1);
    character.setCurrentLocation(newLocation);
    assertEquals(newLocation.getX(), 1);
    assertEquals(newLocation.getY(), 1);
  }
}