import static org.junit.Assert.assertEquals;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.MovieModelImpl;
import cs3500.animator.model.ShapeAnimation;

import org.junit.Test;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.supplementary.Color;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.model.transformation.ColorChange;
import cs3500.animator.model.transformation.Resize;
import cs3500.animator.model.transformation.Rotate;
import cs3500.animator.model.transformation.Translate;

/**
 * Tests for the Transformation class.
 */
public class TransformationTest {

  //---------------------------- CONSTRUCTOR TESTS ----------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicks() {
    new Translate(-1, 2, new Posn(0, 0), new Posn(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicks2() {
    new Translate(1, -2, new Posn(0, 0), new Posn(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicks3() {
    new Translate(2, 1, new Posn(0, 0), new Posn(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicks4() {
    new Translate(2, 3, null, new Posn(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicks5() {
    new Translate(2, 3, new Posn(0, 0), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksColor1() {
    new ColorChange(-2, 3, new Color(0, 0, 0), new Color(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksColor2() {
    new ColorChange(2, -3, new Color(0, 0, 0), new Color(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksColor() {
    new ColorChange(3, 2, new Color(0, 0, 0), new Color(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksColor3() {
    new ColorChange(2, 3, null, new Color(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksColor4() {
    new ColorChange(2, 3, new Color(0, 0, 0), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksSize1() {
    new Resize(2, 1, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksSize2() {
    new Resize(-1, 2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksSize3() {
    new Resize(1, -2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksSize4() {
    new Resize(1, 2, 0, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicksSize5() {
    new Resize(1, 2, 2, -2, 2, 2);
  }

  //-------------------- INVALID ADDING OF TRANSFORMATIONS --------------------

  @Test(expected = IllegalArgumentException.class)
  public void testAddEndTickInMiddleOfExisting_ValidPosns() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(10, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(5, 15,
            new Posn(40, 100),
            new Posn(20, 50)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStartTickInMiddleOfExisting_ValidPosns() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(10, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(15, 35,
            new Posn(40, 100),
            new Posn(40, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStartEqualEndAfter() {
    Shape r = new Ellipse("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(10, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(10, 35,
            new Posn(40, 100),
            new Posn(40, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStartBeforeEndAfter() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(10, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(5, 35,
            new Posn(40, 100),
            new Posn(40, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStartAfterEndBefore() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(10, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(12, 28,
            new Posn(40, 100),
            new Posn(40, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStartRightBeforeEnd() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(10, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(29, 40,
            new Posn(40, 100),
            new Posn(40, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMisalignedPosn() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Translate(0, 30,
            new Posn(20, 50),
            new Posn(40, 100)));
    sa.addTransformation(new Translate(30, 35,
            new Posn(40, 101),
            new Posn(40, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidTicksMisalignedResize() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Resize(10, 30, 40, 40, 20, 20));
    sa.addTransformation(new Resize(30, 35, 20, 15, 30, 30));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidTicksMisalignedColorChange() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new ColorChange(0, 10,
            new Color(0, 255, 0),
            new Color(255, 255, 0)));
    sa.addTransformation(new ColorChange(10, 20,
            new Color(254, 255, 0),
            new Color(255, 255, 255)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidTicksMisalignedColorChangeWithGap() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new ColorChange(0, 10,
            new Color(0, 255, 0),
            new Color(255, 255, 0)));
    sa.addTransformation(new ColorChange(15, 20,
            new Color(254, 255, 0),
            new Color(255, 255, 255)));
  }

  @Test
  public void testAddRightTransformsSameType() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Resize(20, 30, 40, 40, 20, 20));
    sa.addTransformation(new Resize(30, 50, 20, 20, 30, 30));
    assertEquals("20 50 30 30 0 0 0 0", sa.getShapeAtTick(50).shapeInfo());
    sa.addTransformation(new Resize(50, 55, 30, 30, 40, 40));
    assertEquals("20 50 40 40 0 0 0 0", sa.getShapeAtTick(55).shapeInfo());
  }

  @Test
  public void CompleteOverlappingTransformationsTest() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    movie1.addTranslateToShape("e", 10, 30, 10, 10, 30, 30);
    movie1.addResizeToShape("e", 10, 30, 20, 40, 40, 45);
    movie1.addColorChangeToShape("e", 10, 30, 20, 40, 60,
            40, 50, 70);
    movie1.fitMovieToMax();
    assertEquals(30, movie1.getRuntime());

    MovieModel movie2 = new MovieModelImpl();
    movie2.addEllipseShape("e", 0, 10, 20, 40, 20, 40, 60, 0);
    movie2.addTranslateToShape("e", 0, 30, 0, 10, 30, 30);
    movie2.addResizeToShape("e", 0, 30, 20, 40, 40, 45);
    movie2.addColorChangeToShape("e", 25, 30, 20, 40, 60,
            40, 50, 70);
    movie1.fitMovieToMax();
    assertEquals(30, movie1.getRuntime());
  }

  //---------------------------- TESTING ROTATES ----------------------------

  @Test
  public void testAddRotates() {
    Shape r = new Rectangle("r", 20, 50, 40, 40, 0, 0, 0, 0);
    ShapeAnimation sa = new ShapeAnimation(r);
    sa.addTransformation(new Rotate(20, 30, 40, 50));
    sa.addTransformation(new Rotate(30, 50, 50, -40));
    assertEquals("20 50 40 40 0 0 0 5", sa.getShapeAtTick(40).shapeInfo());
    assertEquals("20 50 40 40 0 0 0 -40", sa.getShapeAtTick(50).shapeInfo());
    sa.addTransformation(new Rotate(50, 55, -40, 360));
    assertEquals("20 50 40 40 0 0 0 360", sa.getShapeAtTick(55).shapeInfo());
  }
}