import cs3500.animator.view.TextView;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.MovieModelImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.controller.MVCAnimationController;
import cs3500.animator.view.MovieView;
import cs3500.animator.view.VisualView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the MVCAnimationController class.
 */
public class MVCAnimationControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullControllerInput1() {
    MovieModel model = new MovieModelImpl();
    MVCAnimationController controller = new MVCAnimationController(model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullControllerInput2() {
    MovieView view = new VisualView();
    MVCAnimationController controller = new MVCAnimationController(null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullControllerInput3() {
    MVCAnimationController controller = new MVCAnimationController(null, null);
  }

  @Test
  public void MockTextViewTest1() throws FileNotFoundException {
    FileReader in = new FileReader("smallDemoTest.txt");
    MovieModel model = AnimationReader.parseFile(in, new MovieModelImpl.Builder());
    StringBuilder out = new StringBuilder();
    MovieView view = new MockTextView(out);
    MVCAnimationController controller = new MVCAnimationController(model, view);
    controller.run();
    String log = out.toString();
    assertTrue(log.contains("canvas 200 70 360 360"));
    assertTrue(log.contains("motion"));
    assertTrue(log.contains("Ellipse"));
    assertTrue(log.contains("Rectangle"));
    assertEquals("canvas 200 70 360 360\n"
        + "shape R Rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0 0     10 200 200 50 100 255 0 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0 0     50 300 300 50 100 255 0 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0 0     51 300 300 50 100 255 0 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0 0     70 300 300 25 100 255 0 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0 0     100 200 200 25 100 255 0 0 0\n"
        + "\n"
        + "shape C Ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255 0     20 440 70 120 60 0 0 255 0\n"
        + "motion C 20 440 70 120 60 0 0 255 0     50 440 250 120 60 0 0 255 0\n"
        + "motion C 50 440 250 120 60 0 0 255 0     70 440 370 120 60 0 170 85 0\n"
        + "motion C 70 440 370 120 60 0 170 85 0     80 440 370 120 60 0 255 0 0\n"
        + "motion C 80 440 370 120 60 0 255 0 0     100 440 370 120 60 0 255 0 0", log);
  }

  @Test
  public void MockTextViewTest2() {
    MovieModel movie1 = new MovieModelImpl();
    movie1.addRectangleShape("rect1");
    movie1.addTranslateToShape("rect1", 0, 10, 20, 40, 40, 80);
    movie1.addTranslateToShape("rect1", 10, 15, 40, 80, 40, 80);
    movie1.addTranslateToShape("rect1", 15, 30, 40, 80, 50, 70);
    movie1.addColorChangeToShape("rect1", 0, 30, 0, 0, 0, 0, 0, 0);
    movie1.addResizeToShape("rect1", 0, 30, 10, 30, 10, 30);
    movie1.addRectangleShape("rect2");
    movie1.addTranslateToShape("rect2", 0, 10, 40, 100, 20, 50);
    movie1.addTranslateToShape("rect2", 10, 14, 20, 50, 20, 50);
    movie1.addTranslateToShape("rect2", 14, 18, 20, 50, 20, 100);
    movie1.addColorChangeToShape("rect2", 0, 18, 50, 50, 50, 50, 50, 50);
    movie1.addResizeToShape("rect2", 0, 18, 20, 60, 20, 60);
    movie1.addEllipseShape("ellipse1");
    movie1.addTranslateToShape("ellipse1", 0, 10, 40, 100, 20, 50);
    movie1.addTranslateToShape("ellipse1", 10, 20, 20, 50, 20, 100);
    movie1.addColorChangeToShape("ellipse1", 0, 5, 50, 50, 50, 50, 50, 50);
    movie1.addColorChangeToShape("ellipse1", 5, 10, 50, 50, 50, 100,
        100, 100);
    movie1.addResizeToShape("ellipse1", 0, 10, 20, 60, 20, 60);
    movie1.addResizeToShape("ellipse1", 10, 20, 20, 60, 100, 200);
    movie1.addEllipseShape("ellipse2");
    movie1.addTranslateToShape("ellipse2", 0, 10, 20, 40, 40, 80);
    movie1.addTranslateToShape("ellipse2", 10, 15, 40, 80, 40, 80);
    movie1.addTranslateToShape("ellipse2", 15, 30, 40, 80, 50, 70);
    movie1.addColorChangeToShape("ellipse2", 0, 30, 0, 0, 0, 0, 0, 0);
    movie1.addResizeToShape("ellipse2", 0, 30, 10, 30, 10, 30);

    StringBuilder out = new StringBuilder();
    MovieView view = new MockTextView(out);
    MVCAnimationController controller = new MVCAnimationController(movie1, view);
    controller.run();
    String log = out.toString();

    assertEquals("canvas 500 500 700 700\n"
            + "shape rect1 Rectangle\n"
            + "motion rect1 0 20 40 10 30 0 0 0 0     10 40 80 10 30 0 0 0 0\n"
            + "motion rect1 10 40 80 10 30 0 0 0 0     15 40 80 10 30 0 0 0 0\n"
            + "motion rect1 15 40 80 10 30 0 0 0 0     30 50 70 10 30 0 0 0 0\n\n"
            + "shape rect2 Rectangle\n"
            + "motion rect2 0 40 100 20 60 50 50 50 0     10 20 50 20 60 50 50 50 0\n"
            + "motion rect2 10 20 50 20 60 50 50 50 0     14 20 50 20 60 50 50 50 0\n"
            + "motion rect2 14 20 50 20 60 50 50 50 0     18 20 100 20 60 50 50 50 0\n\n"
            + "shape ellipse1 Ellipse\n"
            + "motion ellipse1 0 40 100 20 60 50 50 50 0     5 30 75 20 60 50 50 50 0\n"
            + "motion ellipse1 5 30 75 20 60 50 50 50 0     10 20 50 20 60 100 100 100 0\n"
            + "motion ellipse1 10 20 50 20 60 100 100 100 0     20 20 "
                    + "100 100 200 100 100 100 0\n\n"
            + "shape ellipse2 Ellipse\n"
            + "motion ellipse2 0 20 40 10 30 0 0 0 0     10 40 80 10 30 0 0 0 0\n"
            + "motion ellipse2 10 40 80 10 30 0 0 0 0     15 40 80 10 30 0 0 0 0\n"
            + "motion ellipse2 15 40 80 10 30 0 0 0 0     30 50 70 10 30 0 0 0 0",
        log);
  }

  @Test
  public void MockSVGViewTest1() throws FileNotFoundException {
    FileReader in = new FileReader("smallDemoTest.txt");
    MovieModel model = AnimationReader.parseFile(in, new MovieModelImpl.Builder());
    StringBuilder out = new StringBuilder();
    MovieView view = new MockSVGView(out);
    MVCAnimationController controller = new MVCAnimationController(model, view);
    controller.run();
    String log = out.toString();
    String[] logArray = log.split("\n");
    assertTrue(log.contains("</svg>"));
    assertTrue(log.contains("</rect>"));
    assertTrue(log.contains("</ellipse>"));
    assertTrue(log.contains("animate"));
    assertEquals("<ellipse cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,0,255)\""
        + " visibility=\"visible\" opacity=\"0\">", logArray[36]);
    assertEquals("<rect x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255,0,"
        + "0)\" visibility=\"visible\" opacity=\"0\">", logArray[1]);
    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">", logArray[0]);
    assertEquals("</svg>", logArray[logArray.length - 1]);
  }


  // INVALID CONTROLLERS===========================================================

  @Test(expected = IllegalArgumentException.class)
  public void IllegalControllerConstructorTest_Text() {
    try {
      MovieView view =  new TextView();
      view.setOut(new PrintWriter("text.txt"));
      new MVCAnimationController(null, view);
    } catch (FileNotFoundException f) {
      throw new IllegalStateException("file not found, null not checked");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void IllegalControllerConstructorTest_SVG() {
    try {
      MovieView view =  new TextView();
      view.setOut(new PrintWriter("text.txt"));
      new MVCAnimationController(null, view);
    } catch (FileNotFoundException f) {
      throw new IllegalStateException("file not found, null not checked");
    }
  }

}
