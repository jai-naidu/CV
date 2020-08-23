import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.MovieModelImpl;
import cs3500.animator.model.ShapeAnimation;

import org.junit.Test;

/**
 * A class to test all of MovieModelImpl's methods.
 */
public class MovieModelImplTest {

  //-------------------------- SETMOVIELENGTH() tests --------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testSetMovieLengthNegative() {
    MovieModelImpl m = new MovieModelImpl();
    m.setMovieLength(-1);
    m.getRuntime();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetMovieLength0() {
    MovieModelImpl m = new MovieModelImpl();
    m.setMovieLength(0);
    m.getRuntime();
  }

  //----------- tests -----------

  @Test
  public void testSetMovieLength() {
    MovieModelImpl m = new MovieModelImpl();
    assertEquals(100, m.getRuntime());
    m.setMovieLength(52);
    assertEquals(52, m.getRuntime());
  }

  //-------------------------- FITMOVIETOMAX() tests --------------------------

  //----------- tests -----------

  @Test
  public void testFitMovieToMaxSeveralTran() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    movie1.addTranslateToShape("e", 10, 30, 10, 10, 30, 30);
    movie1.addResizeToShape("e", 10, 30, 20, 40, 40, 45);
    movie1.addColorChangeToShape("e", 10, 30, 20, 40, 60,
            40, 50, 70);
    assertEquals(100, movie1.getRuntime());
    movie1.fitMovieToMax();
    assertEquals(30, movie1.getRuntime());
  }

  @Test
  public void testFitMovieToMaxNoTran() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    assertEquals(100, movie1.getRuntime());
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
  }

  //----------------------- ADDTRANSLATETOSHAPE() tests ------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testAddTranSeveralBad() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addTranslateToShape("e", -2, -2, -2, 2, 2, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTranBadStartTick() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addTranslateToShape("e", -2, 2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTranBadEndTick() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addTranslateToShape("e", 2, -2, 2, 2, 2, 2);
  }

  //----------------------- ADDRESIZETOSHAPE() tests ------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testAddResSeveralBad() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", -2, -2, -2, 2, 2, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddResBadStartTick() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", -2, 2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddResBadEndTick() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", 2, -2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddResBadStartWidth() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", 2, 2, -2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddResBadStartHeight() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", 2, 2, 2, -2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddResBadEndWidth() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", 2, 2, 2, 2, -2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddResBadEndHeight() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addResizeToShape("e", 2, 2, 2, 2, 2, -2);
  }

  //----------------------- ADDTRANSLATETOSHAPE() tests ------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeSeveralBad() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", -2, -2, -2, 2, 2, -2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadStartTick() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", -2, 2, 2, 2, 2, 2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadEndTick() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, -2, 2, 2, 2, 2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadStartRed() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, 2, -2, 2, 2, 2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadStartGreen() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, 2, 2, -2, 2, 2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadStartBlue() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, 2, 2, 2, -2, 2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadEndRed() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, 2, 2, 2, 2, -2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadEndGreen() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, 2, 2, 2, 2, 2, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColChangeBadEndBlue() {
    MovieModelImpl m = new MovieModelImpl();
    m.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    m.addColorChangeToShape("e", 2, 2, 2, 2, 2, 2, 0, -2);
  }

  //-------------------------- DELETESHAPE() tests --------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShapeNoShapeExists() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.deleteShape("ee");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShapeNoShapeWithNameExists() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    movie1.deleteShape("ee");
  }

  //----------- tests -----------

  @Test
  public void testDeleteShape() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addEllipseShape("e", 10, 10, 20, 40, 20, 40, 60, 0);
    movie1.addTranslateToShape("e", 2, 3, 10, 10, 6, 6);
    movie1.fitMovieToMax();
    assertEquals(3, movie1.getRuntime());
    movie1.deleteShape("e");
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
  }

  //-------------------- DELETETRANSLATEFROMSHAPE() tests ---------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteTransformationFromEmptyShape() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addRectangleShape("rect2", 0, 20, 40, 30, 40, 30, 20, 0);
    movie1.addTranslateToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addTranslateToShape("rect1", 55, 57, 40, 50, 40, 50);
    movie1.deleteTranslateFromShape("rect2", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNotExistingTransformationFromShapeSameType() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addTranslateToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addTranslateToShape("rect1", 55, 57, 40, 50, 40, 50);
    movie1.deleteTranslateFromShape("rect1", 4);
  }

  //----------- tests -----------

  @Test
  public void testDeleteFromShapeValid() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addTranslateToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addTranslateToShape("rect1", 50, 57, 40, 50, 40, 50);
    movie1.fitMovieToMax();
    assertEquals(57, movie1.getRuntime());
    movie1.deleteTranslateFromShape("rect1", 50);
    movie1.fitMovieToMax();
    assertEquals(50, movie1.getRuntime());
  }

  //-------------------- DELETERESIZEFROMSHAPE() tests ---------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteTransformationFromEmptyShapeResize() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 10, 20, 40, 30, 20, 0);
    movie1.addRectangleShape("rect2", 0, 20, 40, 30, 40, 30, 20, 0);
    movie1.addResizeToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addResizeToShape("rect1", 55, 57, 40, 50, 40, 50);
    movie1.deleteResizeFromShape("rect2", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNotExistingTransformationFromShapeSameTypeResize() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 10, 20, 40, 30, 20, 0);
    movie1.addResizeToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addResizeToShape("rect1", 55, 57, 40, 50, 40, 50);
    movie1.deleteResizeFromShape("rect1", 4);
  }

  //----------- tests -----------

  @Test
  public void testDeleteFromShapeValidResize() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1");
    movie1.addResizeToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addResizeToShape("rect1", 50, 57, 40, 50, 40, 50);
    movie1.fitMovieToMax();
    assertEquals(57, movie1.getRuntime());
    movie1.deleteResizeFromShape("rect1", 50);
    movie1.fitMovieToMax();
    assertEquals(50, movie1.getRuntime());
  }

  //------------------- DELETECOLORCHANGEFROMSHAPE() tests --------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteTransformationFromEmptyShapeColChange() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 10, 20, 40, 30, 20, 0);
    movie1.addRectangleShape("rect2", 0, 20, 40, 30, 40, 30, 20, 0);
    movie1.addColorChangeToShape("rect1", 0, 50, 40, 30, 20, 40, 50, 40);
    movie1.addColorChangeToShape("rect1", 55, 57, 40, 50, 40, 50, 0, 0);
    movie1.deleteColorChangeFromShape("rect2", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNotExistingTransformationFromShapeSameTypeColChange() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 10, 20, 40, 30, 20, 0);
    movie1.addColorChangeToShape("rect1", 0, 50, 0, 30, 20, 40, 50, 40);
    movie1.addColorChangeToShape("rect1", 55, 57, 40, 50, 40, 50, 0, 0);
    movie1.deleteColorChangeFromShape("rect1", 4);
  }

  //----------- tests -----------

  @Test
  public void testDeleteFromShapeValidColChange() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 10, 20, 40, 30, 20, 0);
    movie1.addColorChangeToShape("rect1", 0, 50, 40, 30, 20, 40, 50, 40);
    movie1.addColorChangeToShape("rect1", 50, 57, 40, 50, 40, 50, 0, 0);
    movie1.fitMovieToMax();
    assertEquals(57, movie1.getRuntime());
    movie1.deleteColorChangeFromShape("rect1", 50);
    movie1.fitMovieToMax();
    assertEquals(50, movie1.getRuntime());
  }

  //------------------------- ADDELLIPSESHAPE() tests -------------------------

  //--------- errors ---------

  @Test
  public void IllegalEllipseDimensionsWidthTest() {
    MovieModel movie1 = new MovieModelImpl();
    try {
      movie1.addEllipseShape("e1", 0, 20, -10, 40, 40, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, 10, -40, 40, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, -10, -40, 40, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }
  }

  @Test
  public void IllegalEllipseColorsWidthTest() {
    MovieModel movie1 = new MovieModelImpl();
    try {
      movie1.addEllipseShape("e1", 0, 20, 10, 40, 265, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, 10, 40, 40, 265, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, 10, 40, 40, 30, 265, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, 10, 40, 40, 30, -2, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, 10, 40, 40, -3, 2, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addEllipseShape("e1", 0, 20, 10, 40, -40, 3, 2, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void DuplicateShapeNameTestEllipse() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addEllipseShape("rect", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addEllipseShape("rect", 0, 0, 10, 10, 10, 10, 10, 0);
  }

  //------------------------- ADDELLIPSESHAPE() tests -------------------------

  //--------- errors ---------

  @Test
  public void IllegalRectangleDimensionsWidthTest() {
    MovieModel movie1 = new MovieModelImpl();
    try {
      movie1.addRectangleShape("rect1", 0, 20, -10, 40, 40, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("rect1", 0, 20, 10, -40, 40, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("rect1", 0, 20, -10, -40, 40, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }
  }

  @Test
  public void IllegalRectangleColorsWidthTest() {
    MovieModel movie1 = new MovieModelImpl();
    try {
      movie1.addRectangleShape("e1", 0, 20, 10, 40, 265, 30, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("e1", 0, 20, 10, 40, 40, 265, 20, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("e1", 0, 20, 10, 40, 40, 30, 265, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("e1", 0, 20, 10, 40, 40, 30, -2, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("e1", 0, 20, 10, 40, 40, -3, 2, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }

    try {
      movie1.addRectangleShape("e1", 0, 20, 10, 40, -40, 3, 2, 0);
    } catch (IllegalArgumentException e) {
      assertTrue(movie1.getShapeAnimations().size() == 0);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void DuplicateShapeNameTest() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addRectangleShape("rect", 0, 0, 10, 10, 10, 10, 10, 0);
  }

  //------------------- PRINTFRAME() tests --------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testPrintFrameNegTick() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.printFrame(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPrintFrameBigTick() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.printFrame(1000);
  }

  //---------- tests ----------

  @Test
  public void testPrintFrame() {
    MovieModel movie1 = new MovieModelImpl();
    assertEquals(100, movie1.getRuntime());

    assertEquals("Frame 33:\n",
            movie1.printFrame(32));
  }

  @Test
  public void testGetFrame() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("r");
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
    movie1.addTranslateToShape("r", 0, 30,
            20, 50, 40, 100);
    movie1.fitMovieToMax();
    assertEquals(30, movie1.getRuntime());
    movie1.addTranslateToShape("r", 30, 35,
            40, 100, 40, 10);
    movie1.addResizeToShape("r", 0, 35,
            40, 40, 20, 20);
    movie1.addColorChangeToShape("r", 0, 35,
            0, 0, 0, 255, 255, 0);
    movie1.fitMovieToMax();
    assertEquals(35, movie1.getRuntime());

    assertEquals("Frame 1:\nr 20 50 40 40 0 0 0 0",
            movie1.printFrame(0));
  }

  //------------------- PRINTMOVIEFRAMES() tests --------------------

  //---------- tests ----------

  @Test
  public void testPrintEmptyMovieFrames() {
    MovieModel movie1 = new MovieModelImpl();
    assertEquals(100, movie1.getRuntime());
    movie1.setMovieLength(1);
    assertEquals("Frame 1:\n\nFrame 2:\n",
            movie1.printMovieFrames());
  }

  @Test
  public void testPrintMovieFrames() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("r", 20, 50, 40, 40, 0, 0, 0, 0);
    movie1.addEllipseShape("e", 20, 50, 40, 40, 0, 0, 0, 0);
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
    movie1.addTranslateToShape("r", 1, 2, 20, 50, 40, 100);
    movie1.addColorChangeToShape("e", 1, 2, 0, 0, 0, 255, 255, 255);
    movie1.fitMovieToMax();
    assertEquals(2, movie1.getRuntime());
    movie1.addTranslateToShape("r", 2, 3, 40, 100, 40, 10);
    movie1.addResizeToShape("r", 1, 2, 40, 40, 20, 20);
    movie1.fitMovieToMax();
    assertEquals(3, movie1.getRuntime());

    assertEquals("Frame 1:\n"
                    + "r 20 50 40 40 0 0 0 0\n"
                    + "e 20 50 40 40 0 0 0 0\n"
                    + "Frame 2:\n"
                    + "r 20 50 40 40 0 0 0 0\n"
                    + "e 20 50 40 40 0 0 0 0\n"
                    + "Frame 3:\n"
                    + "r 40 100 20 20 0 0 0 0\n"
                    + "e 20 50 40 40 255 255 255 0\n"
                    + "Frame 4:\n"
                    + "r 40 10 20 20 0 0 0 0\n"
                    + "e 20 50 40 40 255 255 255 0",
            movie1.printMovieFrames());
  }

  //------------------------------- OTHER TESTS -------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNotExistingTransformationFromShapeDiffType() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addEllipseShape("ellipse1", 20, 20, 60, 40, 40, 30, 20, 0);
    movie1.addRectangleShape("rect2", 0, 20, 40, 30, 40, 30, 20, 0);
    movie1.addTranslateToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addTranslateToShape("rect1", 55, 57, 40, 50, 40, 50);
    movie1.deleteColorChangeFromShape("rect1", 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNotExistingTransformationFromShapeDiffType2() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addEllipseShape("ellipse1", 20, 20, 60, 40, 40, 30, 20, 0);
    movie1.addRectangleShape("rect2", 0, 20, 40, 30, 40, 30, 20, 0);
    movie1.addTranslateToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addTranslateToShape("rect1", 55, 57, 40, 50, 40, 50);
    movie1.deleteResizeFromShape("rect1", 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteNotExistingTransformationFromShapeSameType2() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 10, 20, 40, 40, 40, 30, 20, 0);
    movie1.addEllipseShape("ellipse1", 20, 20, 60, 40, 40, 30, 20, 0);
    movie1.addRectangleShape("rect2", 0, 20, 40, 30, 40, 30, 20, 0);
    movie1.addTranslateToShape("rect1", 0, 50, 10, 20, 40, 50);
    movie1.addResizeToShape("rect1", 55, 57, 40, 40, 40, 50);
    movie1.deleteResizeFromShape("rect1", 56);
  }

  //---------------- illegal moves ----------------

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTransformationTeleportTest() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 0, 0, 10, 10, 10, 10, 10, 0);
    movie1.addTranslateToShape("rect1", 0, 10, 0, 0, 40, 80);
    movie1.addTranslateToShape("rect1", 10, 20, 40, 50, 30, 80);
  }

  // think the check for overlap might reversed, it throws an exception when it shouldn't
  // and doesn't throw exception when it should
  @Test(expected = IllegalArgumentException.class)
  public void InvalidTransformationOverlapTranslateTest1() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 0, 0, 10, 10, 10, 10, 10, 0);
    movie1.addTranslateToShape("rect1", 0, 10, 0, 0, 40, 80);
    movie1.addTranslateToShape("rect1", 0, 80, 40, 50, 30, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTransformationOverlapTranslateTest2() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 0, 0, 10, 10, 10, 10, 10, 0);
    movie1.addTranslateToShape("rect1", 0, 10, 0, 0, 40, 80);
    movie1.addTranslateToShape("rect1", 5, 80, 40, 50, 30, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTransformationOverlapResizeTest1() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 0, 0, 10, 10, 10, 10, 10, 0);
    movie1.addResizeToShape("rect1", 0, 10, 10, 10, 40, 80);
    movie1.addResizeToShape("rect1", 5, 80, 40, 80, 30, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTransformationOverlapResizeTest2() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 0, 0, 10, 10, 10, 10, 10, 0);
    movie1.addResizeToShape("rect1", 0, 10, 10, 10, 40, 80);
    movie1.addResizeToShape("rect1", 9, 80, 40, 80, 30, 80);
  }

  @Test
  public void addShapeAndTransformationsTest() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 20, 40, 10, 10, 10, 10, 10, 0);
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
    movie1.addTranslateToShape("rect1", 0, 10, 20, 40, 40, 80);
    movie1.fitMovieToMax();
    assertEquals(10, movie1.getRuntime());
    movie1.addColorChangeToShape("rect1", 0, 20, 200, 0, 0,
            100, 100, 100);
    movie1.fitMovieToMax();
    assertEquals(20, movie1.getRuntime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addTransformationWithGapTest() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1", 20, 40, 10, 10, 10, 10, 10, 0);
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
    movie1.addTranslateToShape("rect1", 0, 10, 20, 40, 40, 80);
    movie1.fitMovieToMax();
    assertEquals(10, movie1.getRuntime());
    movie1.addColorChangeToShape("rect1", 0, 20, 200, 0, 0,
            100, 100, 100);
    movie1.fitMovieToMax();
    assertEquals(20, movie1.getRuntime());
    movie1.addTranslateToShape("rect1", 15, 30, 40, 80, 20, 60);

  }

  @Test
  public void testTwoAddTranslatesBackToBack() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("r", 20, 50, 40, 40, 0, 0, 0,0);
    movie1.fitMovieToMax();
    assertEquals(0, movie1.getRuntime());
    movie1.addTranslateToShape("r", 0, 30,
            20, 50, 40, 100);
    movie1.fitMovieToMax();
    assertEquals(30, movie1.getRuntime());
    movie1.addTranslateToShape("r", 30, 35,
            40, 100, 40, 10);
    movie1.fitMovieToMax();
    assertEquals(35, movie1.getRuntime());
  }

  //------------------------- tests for SETTERS AND GETTERS -------------------------

  @Test
  public void setAndGetRuntimeTest() {
    MovieModel m = new MovieModelImpl();
    assertFalse(m.getRuntime() == 10000);
    m.setMovieLength(10000);
    assertTrue(m.getRuntime() == 10000);
  }

  @Test
  public void setAndGetDimensionsTest() {
    MovieModel m = new MovieModelImpl();
    assertFalse(m.getMaxWidth() == 50);
    assertFalse(m.getMaxHeight() == 100);
    assertFalse(m.getScrollWidth() == 40);
    assertFalse(m.getScrollHeight() == 60);
    m.setBounds(40, 60, 50, 100);
    assertTrue(m.getMaxWidth() == 50);
    assertTrue(m.getMaxHeight() == 100);
    assertTrue(m.getScrollWidth() == 40);
    assertTrue(m.getScrollHeight() == 60);
  }

  @Test
  public void getShapeAnimationsTest() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().size() == 0);
    m.addEllipseShape("e");
    m.addEllipseShape("E");
    m.addRectangleShape("r");
    m.addRectangleShape("R");
    assertTrue(m.getShapeAnimations().size() == 4);
    m.getShapeAnimations().remove(2);
    assertTrue(m.getShapeAnimations().size() == 4);
  }

  @Test
  public void setAndGetSpeed() {
    MovieModel m = new MovieModelImpl();
    assertFalse(m.getSpeed() == 5);
    m.setSpeed(5);
    assertTrue(m.getSpeed() == 5);
  }

  //------------------------------ ADDKEYFRAME() ------------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testValidNameBadAddKeyFrame() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 3, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.addKeyFrame("el", -1, 3, 3, 30, 30, 255, 0, 255, 0);
  }

  //---------- tests ----------

  @Test
  public void addKeyFrameToEmpty() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addKeyFrame("el", 1, 5, 5, 2, 2, 255, 255, 255, 0);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 2 2 255 255 255 0     1 5 5 2 2 255 255 255 0", sa.toTable());
  }

  @Test
  public void addKeyFrameBeforeExistingStart() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 3, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 3 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.addKeyFrame("el", 1, 3, 3, 30, 30, 255, 0, 255, 0);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 3 3 30 30 255 0 255 0     3 5 5 20 10 0 0 255 0\n"
            + "motion el 3 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa.toTable());
  }

  @Test
  public void addKeyFrameToStart() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.addKeyFrame("el", 1, 3, 3, 30, 30, 255, 0, 255, 0);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 3 3 30 30 255 0 255 0     5 2 1 1 1 255 0 0 0", sa.toTable());
  }

  @Test
  public void addKeyFrameToMiddle() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.addKeyFrame("el", 3, 3, 3, 30, 30, 255, 0, 255, 0);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     3 3 3 30 30 255 0 255 0\n"
            + "motion el 3 3 3 30 30 255 0 255 0     5 2 1 1 1 255 0 0 0", sa.toTable());
  }

  @Test
  public void addKeyFrameToEnd() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.addKeyFrame("el", 5, 3, 3, 30, 30, 255, 0, 255, 0);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 3 3 30 30 255 0 255 0", sa.toTable());
  }

  @Test
  public void addKeyFrameAfterExistingEnd() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.addKeyFrame("el", 100, 3, 3, 30, 30, 255, 0, 255, 0);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0\n"
            + "motion el 5 2 1 1 1 255 0 0 0     100 3 3 30 30 255 0 255 0" , sa.toTable());
  }

  //----------------------------- DELETEKEYFRAME() ----------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testValidNameNegativeDeleteKeyFrame() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 3, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.deleteKeyFrame("el", -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteFromEmpty() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.deleteKeyFrame("el", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteOutOfRange() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 3, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.deleteKeyFrame("el", 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyFrameMiddleInvalidKeyFrame() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 3, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.deleteKeyFrame("el", 2);
  }

  //---------- tests ----------

  @Test
  public void deleteKeyFrameStartOneMotion() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.deleteKeyFrame("el", 1);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 5 2 1 1 1 255 0 0 0     5 2 1 1 1 255 0 0 0", sa.toTable());
  }

  @Test
  public void deleteKeyFrameEndOneMotion() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa1.toTable());
    m.deleteKeyFrame("el", 5);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     1 5 5 20 10 0 0 255 0", sa.toTable());
  }

  @Test
  public void deleteKeyFrameStartTwoMotions() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.addMotion("el", 5, 2, 1, 1, 1, 255, 0, 0, 0,
            10, 1, 1, 1, 1, 1, 1, 1, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0\n"
            + "motion el 5 2 1 1 1 255 0 0 0     10 1 1 1 1 1 1 1 0", sa1.toTable());
    m.deleteKeyFrame("el", 1);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 5 2 1 1 1 255 0 0 0     10 1 1 1 1 1 1 1 0", sa.toTable());
  }

  @Test
  public void deleteKeyFrameMiddleTwoMotions() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.addMotion("el", 5, 2, 1, 1, 1, 255, 0, 0, 0,
            10, 1, 1, 1, 1, 1, 1, 1, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0\n"
            + "motion el 5 2 1 1 1 255 0 0 0     10 1 1 1 1 1 1 1 0", sa1.toTable());
    m.deleteKeyFrame("el", 5);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     10 1 1 1 1 1 1 1 0", sa.toTable());
  }

  @Test
  public void deleteKeyFrameMiddleThreeMotions() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.addMotion("el", 5, 2, 1, 1, 1, 255, 0, 0, 0,
            10, 1, 1, 1, 1, 1, 1, 1, 0);
    m.addMotion("el", 10, 1, 1, 1, 1, 1, 1, 1, 0,
            12, 2, 2, 2, 2, 2, 2, 2, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0\n"
            + "motion el 5 2 1 1 1 255 0 0 0     10 1 1 1 1 1 1 1 0\n"
            + "motion el 10 1 1 1 1 1 1 1 0     12 2 2 2 2 2 2 2 0", sa1.toTable());
    m.deleteKeyFrame("el", 10);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0\n"
            + "motion el 5 2 1 1 1 255 0 0 0     12 2 2 2 2 2 2 2 0", sa.toTable());
  }

  @Test
  public void deleteKeyFrameEndTwoMotions() {
    MovieModel m = new MovieModelImpl();
    assertTrue(m.getShapeAnimations().isEmpty());
    m.addEllipseShape("el");
    m.addMotion("el", 1, 5, 5, 20, 10, 0, 0, 255, 0,
            5, 2, 1, 1, 1, 255, 0, 0, 0);
    m.addMotion("el", 5, 2, 1, 1, 1, 255, 0, 0, 0,
            10, 1, 1, 1, 1, 1, 1, 1, 0);
    ShapeAnimation sa1 = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0\n"
            + "motion el 5 2 1 1 1 255 0 0 0     10 1 1 1 1 1 1 1 0", sa1.toTable());
    m.deleteKeyFrame("el", 10);
    ShapeAnimation sa = m.getShapeAnimations().get(0);
    assertEquals("shape el Ellipse\n"
            + "motion el 1 5 5 20 10 0 0 255 0     5 2 1 1 1 255 0 0 0", sa.toTable());
  }
}