import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ShapeAnimation;

import org.junit.Test;

import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.model.transformation.Translate;

/**
 * Tests for ShapeAnimation that aren't accounted for in MovieModelImplTest.
 */
public class ShapeAnimationTest {

  //---------------------------- CONSTRUCTOR TESTS ----------------------------

  @Test(expected = IllegalArgumentException.class)
  public void InvalidShapeToShapeAnimation() {
    new ShapeAnimation(null);
  }

  //------------------------------- EXTRA TESTS -------------------------------

  @Test
  public void getShapeAtTickTest() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(0, 30,
            new Posn(20, 50),
            new Posn(40, 100)));

    assertEquals("20 50 40 40 0 0 0 0", sa.getShapeAtTick(0).shapeInfo());
    assertEquals("30 75 40 40 0 0 0 0", sa.getShapeAtTick(15).shapeInfo());
    assertEquals("40 100 40 40 0 0 0 0", sa.getShapeAtTick(30).shapeInfo());
    assertEquals("40 100 40 40 0 0 0 0", sa.getShapeAtTick(35).shapeInfo());
    sa.addTransformation(new Translate(30, 40,
            new Posn(40, 100),
            new Posn(20, 50)));

  }

  @Test(expected = IllegalArgumentException.class)
  public void getShapeAtTickTest2() {
    Shape r2 = new Rectangle("rect1", 20, 40, 10, 30, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r2);
    sa.addTransformation(new Translate(0, 10,
            new Posn(20, 40), new Posn(40, 80)));
    sa.addTransformation(new Translate(15, 30,
            new Posn(40, 80), new Posn(50, 70)));
  }
}
