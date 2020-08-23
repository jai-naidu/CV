import cs3500.animator.view.SVGShapeVisitorImpl;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.TextShapeVisitorImpl;
import org.junit.Test;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class for Shape tests.
 */
public class ShapeTest {

  //---------------- errors ----------------

  @Test(expected = IllegalArgumentException.class)
  public void testNullString() {
    new Rectangle(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullStringEllipse() {
    new Ellipse(null);
  }

  //----------------- tests -----------------

  @Test
  public void RectangleTests() {
    TextShapeVisitor visitor = new TextShapeVisitorImpl();
    Shape s = new Rectangle("name", 1, 2, 3, 4, 5, 6, 7, 0);
    assertEquals("name", s.getName());
    assertEquals("shape name Rectangle", s.accept(visitor));
    assertEquals("1 2 3 4 5 6 7 0", s.shapeInfo());
    assertTrue(s.getX() == 1);
    assertTrue(s.getY() == 2);
    assertTrue(s.getWidth() == 3);
    assertTrue(s.getHeight() == 4);
    assertTrue(s.getR() == 5);
    assertTrue(s.getG() == 6);
    assertTrue(s.getB() == 7);
    Shape c = s.copy();
    assertEquals("name", c.getName());
    assertEquals("shape name Rectangle", c.accept(visitor));
    assertEquals("1 2 3 4 5 6 7 0", c.shapeInfo());
    assertTrue(c.getX() == 1);
    assertTrue(c.getY() == 2);
    assertTrue(c.getWidth() == 3);
    assertTrue(c.getHeight() == 4);
    assertTrue(c.getR() == 5);
    assertTrue(c.getG() == 6);
    assertTrue(c.getB() == 7);
  }

  @Test
  public void EllipseTests() {
    TextShapeVisitor visitor = new TextShapeVisitorImpl();
    Shape s = new Ellipse("name", 1, 2, 3, 4, 5, 6, 7, 0);
    assertEquals("name", s.getName());
    assertEquals("shape name Ellipse", s.accept(visitor));
    assertEquals("1 2 3 4 5 6 7 0", s.shapeInfo());
    assertTrue(s.getX() == 1);
    assertTrue(s.getY() == 2);
    assertTrue(s.getWidth() == 3);
    assertTrue(s.getHeight() == 4);
    assertTrue(s.getR() == 5);
    assertTrue(s.getG() == 6);
    assertTrue(s.getB() == 7);
    Shape c = s.copy();
    assertEquals("name", c.getName());
    assertEquals("shape name Ellipse", c.accept(visitor));
    assertEquals("1 2 3 4 5 6 7 0", c.shapeInfo());
    assertTrue(c.getX() == 1);
    assertTrue(c.getY() == 2);
    assertTrue(c.getWidth() == 3);
    assertTrue(c.getHeight() == 4);
    assertTrue(c.getR() == 5);
    assertTrue(c.getG() == 6);
    assertTrue(c.getB() == 7);

  }

  @Test
  public void NewEllipseTest() {
    Shape e = new Ellipse("e");
    TextShapeVisitor text = new TextShapeVisitorImpl();
    TextShapeVisitor svg = new SVGShapeVisitorImpl();
    assertEquals("shape e Ellipse", e.accept(text));
    assertEquals("<ellipse cx=\"0.0\" cy=\"0.0\" rx=\"0.0\" ry=\"0.0\" "
        + "fill=\"rgb(0,0,0)\" visibility=\"visible\" opacity=\"0\">\n"
        + "%s</ellipse>", e.accept(svg));
    assertEquals("rx", e.getAttributeResizeW());
    assertEquals("ry", e.getAttributeResizeH());
    assertEquals("cx", e.getAttributeTranslateX());
    assertEquals("cy", e.getAttributeTranslateY());
    assertFalse(e.getHeight() == 400);
    assertFalse(e.getWidth() == 100);
    e.setHeight(400);
    assertTrue(e.getHeight() == 400);
    e.setWidth(100);
    assertTrue(e.getWidth() == 100);
    assertEquals("200.0", e.svgH());
    assertEquals("50.0", e.svgW());

  }
}