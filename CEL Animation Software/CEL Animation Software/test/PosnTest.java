import org.junit.Test;

import cs3500.animator.model.supplementary.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for a Posn class.
 */
public class PosnTest {

  @Test
  public void testPosnEquality() {
    Posn one = new Posn(0, 0);
    Posn two = new Posn(0, 0);
    assertTrue(one.equals(two));
    assertEquals(one.hashCode(), two.hashCode());
  }

  @Test
  public void testGetXTests() {
    Posn one = new Posn(1, 3);
    assertTrue(one.getX() == 1);
    Posn two = new Posn(-1, 2);
    assertTrue(two.getX() == -1);
  }

  @Test
  public void testGetYTests() {
    Posn one = new Posn(2, 1);
    assertTrue(one.getY() == 1);
    Posn two = new Posn(2, -1);
    assertTrue(two.getY() == -1);
  }
}