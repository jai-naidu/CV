import org.junit.Test;

import cs3500.animator.model.supplementary.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class for testing the Color class.
 */
public class ColorTest {
  @Test
  public void testColorEquality() {
    Color c1 = new Color(0, 0, 0);
    Color c2 = new Color(0, 0, 0);
    assertEquals(c1.hashCode(), c2.hashCode());
    assertTrue(c2.equals(c1));
  }

  @Test
  public void testGetColors() {
    Color c1 = new Color(1, 2, 3);
    assertTrue(c1.getRed() == 1);
    assertTrue(c1.getGreen() == 2);
    assertTrue(c1.getBlue() == 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRedColorConstruction_Over() {
    Color c = new Color(256, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRedColorConstruction_Under() {
    Color c = new Color(-1, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreenColorConstruction_Over() {
    Color c = new Color(255, 256, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreenColorConstruction_Under() {
    Color c = new Color(255, -1, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlueColorConstruction_Over() {
    Color c = new Color(255, 255, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlueColorConstruction_Under() {
    Color c = new Color(255, 255, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColorConstruction_Over() {
    Color c = new Color(256, 256, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColorConstruction_Under() {
    Color c = new Color(-1, -1, -1);
  }
}