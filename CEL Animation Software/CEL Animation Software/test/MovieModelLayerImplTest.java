import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.MovieModelLayerImpl;
import cs3500.animator.util.AnimationReader;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class to test all of MovieModelLayerImpl's methods that aren't .
 */
public class MovieModelLayerImplTest {

  //-------------------------- ADDLAYER() tests --------------------------

  //--------- errors ---------

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerNullArg() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMultipleLayerSameNameToEmpty() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    m.addLayer("test");
  }

  //---------- tests ----------

  @Test
  public void testAddToEmpty() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.deleteLayer("Layer 1");
    assertEquals(new ArrayList<String>(), m.getLayers());
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Collections.singletonList("test")), m.getLayers());
  }

  @Test
  public void testEmptyExistingLayer() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    assertEquals(new ArrayList<String>(Collections.singletonList("Layer 1")), m.getLayers());
  }

  @Test
  public void testAddMultipleLayerToEmpty() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    m.addLayer("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test", "test2")), m.getLayers());
  }

  //------------------------- DELETELAYER() tests ------------------------

  //---------- tests ----------

  @Test
  public void testDeleteMultipleLayers() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    m.deleteLayer("Layer 1");
    assertEquals(new ArrayList<String>(Collections.singletonList("test")), m.getLayers());
    m.deleteLayer("test");
    assertEquals(new ArrayList<String>(), m.getLayers());
  }

  //------------------------- MOVELAYERUP() tests ------------------------

  //------------------------ MOVELAYERDOWN() tests -----------------------

  //--------- errors ---------

  @Test
  public void testNullNameForLayerRearrange() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("1");
    m.addLayer("2");

    try {
      m.moveLayerUp(null);
    } catch (IllegalArgumentException e) {
      assertEquals("2", m.getLayers().get(2));
    }

    try {
      m.moveLayerDown(null);
    } catch (IllegalArgumentException e) {
      assertEquals("2", m.getLayers().get(2));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingLayersUpIncorrectName() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    m.addLayer("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test", "test2")), m.getLayers());
    m.moveLayerUp("not real");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingLayersDownIncorrectName() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    m.addLayer("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test", "test2")), m.getLayers());
    m.moveLayerDown("not real");
  }

  //---------- tests ----------

  @Test
  public void testMovingLayers() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    m.addLayer("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test", "test2")), m.getLayers());
    m.moveLayerUp("Layer 1");
    assertEquals(new ArrayList<String>(Arrays.asList("test", "Layer 1", "test2")), m.getLayers());
    m.moveLayerUp("Layer 1");
    assertEquals(new ArrayList<String>(Arrays.asList("test", "test2", "Layer 1")), m.getLayers());
    m.moveLayerUp("Layer 1");
    assertEquals(new ArrayList<String>(Arrays.asList("test", "test2", "Layer 1")), m.getLayers());
    m.moveLayerDown("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("test2", "test", "Layer 1")), m.getLayers());
    m.moveLayerDown("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("test2", "test", "Layer 1")), m.getLayers());
  }

  //------------------------ GETLAYERS() tests -----------------------

  //--------- already tested above ---------

  @Test
  public void testGetLayers() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    assertEquals(new ArrayList<String>(Collections.singletonList("Layer 1")), m.getLayers());
  }

  //------------------------ GETLAYERSHAPENAMES() tests -----------------------

  //---------- tests ----------

  @Test
  public void testGetLayerShapeNamesLayerDoesntExists() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    assertEquals(new ArrayList<String>(), m.getLayerShapeNames("test2"));
  }

  @Test
  public void testGetLayerShapeNamesNoShapes() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    assertEquals(new ArrayList<String>(), m.getLayerShapeNames("Layer 1"));
  }

  @Test
  public void testGetLayerShapeNames() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    assertEquals(new ArrayList<String>(), m.getLayerShapeNames("Layer 1"));
    m.addEllipseShape("test1");
    m.addRectangleShape("test2");
    assertEquals(new ArrayList<String>(Arrays.asList("test1", "test2")),
            m.getLayerShapeNames("Layer 1"));
  }

  //------------------------ MOVESHAPETOLAYER() tests -----------------------

  //---------- tests ----------

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeNameMoveTest() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.moveShapeToLayer(null, "l");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullLayerNameMoveTest() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.moveShapeToLayer("s", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullNamesMoveTest() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    m.moveShapeToLayer(null, null);
  }

  @Test
  public void testMoveShapeToLayer() {
    MovieModelLayerImpl m = new MovieModelLayerImpl();
    assertEquals(new ArrayList<String>(), m.getLayerShapeNames("Layer 1"));
    m.addEllipseShape("test1");
    m.addRectangleShape("test2");
    m.addLayer("test");
    assertEquals(new ArrayList<String>(Arrays.asList("Layer 1", "test")), m.getLayers());
    assertEquals(new ArrayList<String>(Arrays.asList("test1", "test2")),
            m.getLayerShapeNames("Layer 1"));
    assertEquals(new ArrayList<String>(), m.getLayerShapeNames("test"));
    m.moveShapeToLayer("test1", "test");
    assertEquals(new ArrayList<String>(Collections.singletonList("test2")),
            m.getLayerShapeNames("Layer 1"));
    assertEquals(new ArrayList<String>(Collections.singletonList("test1")),
            m.getLayerShapeNames("test"));

    // nothing should happen
    m.moveShapeToLayer("not real shape", "not real layer");
    assertEquals(new ArrayList<String>(Collections.singletonList("test2")),
            m.getLayerShapeNames("Layer 1"));
    assertEquals(new ArrayList<String>(Collections.singletonList("test1")),
            m.getLayerShapeNames("test"));

    // nothing should happen
    m.moveShapeToLayer("test1", "not real layer");
    assertEquals(new ArrayList<String>(Collections.singletonList("test2")),
            m.getLayerShapeNames("Layer 1"));
    assertEquals(new ArrayList<String>(Collections.singletonList("test1")),
            m.getLayerShapeNames("test"));

    // nothing should happen
    m.moveShapeToLayer("not real shape", "Layer 1");
    assertEquals(new ArrayList<String>(Collections.singletonList("test2")),
            m.getLayerShapeNames("Layer 1"));
    assertEquals(new ArrayList<String>(Collections.singletonList("test1")),
            m.getLayerShapeNames("test"));
  }

  //------------------------ GETSHAPEANIMATIONS() tests -----------------------

  //---------- tests ----------

  @Test
  public void testGetShapeAnimations() throws FileNotFoundException {
    FileReader in = new FileReader("smallDemoTest.txt");
    MovieModel m = AnimationReader.parseFile(in, new MovieModelLayerImpl.Builder());
    assertEquals("shape R Rectangle\n"
                    + "motion R 1 200 200 50 100 255 0 0 0     10 200 200 50 100 255 0 0 0\n"
                    + "motion R 10 200 200 50 100 255 0 0 0     50 300 300 50 100 255 0 0 0\n"
                    + "motion R 50 300 300 50 100 255 0 0 0     51 300 300 50 100 255 0 0 0\n"
                    + "motion R 51 300 300 50 100 255 0 0 0     70 300 300 25 100 255 0 0 0\n"
                    + "motion R 70 300 300 25 100 255 0 0 0     100 200 200 25 100 255 0 0 0",
            m.getShapeAnimations().get(0).toTable());
  }

  //------------------------ DELETESHAPE() tests -----------------------

  //---------- tests ----------

  @Test
  public void testDeleteShape() throws FileNotFoundException {
    FileReader in = new FileReader("smallDemoTest.txt");
    MovieModel m = AnimationReader.parseFile(in, new MovieModelLayerImpl.Builder());
    String test = "shape R Rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 0     10 200 200 50 100 255 0 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 0     50 300 300 50 100 255 0 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 0     51 300 300 50 100 255 0 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 0     70 300 300 25 100 255 0 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 0     100 200 200 25 100 255 0 0 0";
    assertEquals(test, m.getShapeAnimations().get(0).toTable());
    m.deleteShape("R");
    assertNotSame(test, m.getShapeAnimations().get(0).toTable());
  }

  //------------------------ DELETING TRANSFORMATIONS tests -----------------------

  //---------- tests ----------

  @Test
  public void testDeleteTransformations() throws FileNotFoundException {
    FileReader in = new FileReader("smallDemoTest.txt");
    MovieModel m = AnimationReader.parseFile(in, new MovieModelLayerImpl.Builder());
    String test = "shape R Rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 0     10 200 200 50 100 255 0 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 0     50 300 300 50 100 255 0 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 0     51 300 300 50 100 255 0 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 0     70 300 300 25 100 255 0 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 0     100 200 200 25 100 255 0 0 0";
    assertEquals(test, m.getShapeAnimations().get(0).toTable());
    m.deleteTranslateFromShape("R", 70);

    assertEquals("shape R Rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 0     10 200 200 50 100 255 0 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 0     50 300 300 50 100 255 0 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 0     51 300 300 50 100 255 0 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 0     70 300 300 25 100 255 0 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 0     100 300 300 25 100 255 0 0 0",
            m.getShapeAnimations().get(0).toTable());

    assertEquals(38.157, m.getShapeAnimations().get(0).getShapeAtTick(60).getWidth(),
            0.01);
    m.deleteResizeFromShape("R", 51);
    assertEquals(50.0, m.getShapeAnimations().get(0).getShapeAtTick(60).getWidth(),
            0.01);
  }

  //------------------------ FITMOVIETOMAX tests ---------------------------

  //---------- tests ----------

  @Test
  public void fitLayeredMovieToMaxTest_SameLayer() {
    MovieModel m = new MovieModelLayerImpl();
    m.addRectangleShape("r");
    m.moveShapeToLayer("r", "2");
    m.addKeyFrame("r", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("r", 200, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addEllipseShape("e");
    m.moveShapeToLayer("e", "2");
    m.addKeyFrame("e", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("e", 300, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.deleteKeyFrame("e", 300);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addKeyFrame("e", 250, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(250, m.getRuntime());

    m.deleteLayer("2");
    m.fitMovieToMax();
    assertEquals(0, m.getRuntime());
  }

  @Test
  public void fitLayeredMovieToMaxTest_DiffLayer() {
    MovieModel m = new MovieModelLayerImpl();
    m.addRectangleShape("r");
    m.moveShapeToLayer("r", "1");
    m.addKeyFrame("r", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("r", 200, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addEllipseShape("e");
    m.moveShapeToLayer("e", "2");
    m.addKeyFrame("e", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("e", 300, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.deleteKeyFrame("e", 300);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addKeyFrame("e", 250, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(250, m.getRuntime());

    m.deleteLayer("2");
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
  }

  @Test
  public void fitLayeredMovieToMaxTest_DeleteShape() {
    MovieModel m = new MovieModelLayerImpl();
    m.addRectangleShape("r");
    m.moveShapeToLayer("r", "1");
    m.addKeyFrame("r", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("r", 200, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addEllipseShape("e");
    m.moveShapeToLayer("e", "2");
    m.addKeyFrame("e", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("e", 300, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.deleteShape("e");
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
  }

  @Test
  public void fitLayeredMovieToMaxTest_DeleteShape_SameLayer() {
    MovieModel m = new MovieModelLayerImpl();
    m.addRectangleShape("r");
    m.moveShapeToLayer("r", "1");
    m.addKeyFrame("r", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("r", 200, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addEllipseShape("e");
    m.moveShapeToLayer("e", "1");
    m.addKeyFrame("e", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("e", 300, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.deleteShape("e");
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
  }

  @Test
  public void fitLayeredMovieToMaxTest_NoChange() {
    MovieModel m = new MovieModelLayerImpl();
    m.addRectangleShape("r");
    m.moveShapeToLayer("r", "1");
    m.addKeyFrame("r", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("r", 200, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(200, m.getRuntime());
    m.addEllipseShape("e");
    m.moveShapeToLayer("e", "2");
    m.addKeyFrame("e", 20, 20, 20, 20, 20, 20, 20, 20, 20);
    m.addKeyFrame("e", 300, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.deleteKeyFrame("r", 200);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());
    m.addKeyFrame("r", 250, 20, 20, 20, 20, 20, 20, 20, 20);
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.addEllipseShape("e2");
    m.moveShapeToLayer("e2", "1");
    m.deleteShape("r");
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());

    m.deleteLayer("1");
    m.fitMovieToMax();
    assertEquals(300, m.getRuntime());
  }

  //------------------------ ADD[shapes] tests ---------------------------

  //---------- tests ----------

  @Test
  public void testAddingExistingShape() {
    MovieModel m = new MovieModelLayerImpl();
    m.addEllipseShape("c");
    m.moveShapeToLayer("c", "2");
    m.addKeyFrame("c", 2, 4, 6, 8, 10, 12, 14, 16, 18);
    try {
      m.addEllipseShape("c");
    } catch (IllegalArgumentException e) {
      assertEquals(1, m.getShapeAnimations().size());
    }

    try {
      m.addRectangleShape("c");
    } catch (IllegalArgumentException e) {
      assertEquals(1, m.getShapeAnimations().size());
    }
  }

}