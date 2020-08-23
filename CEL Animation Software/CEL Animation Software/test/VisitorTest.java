import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.SVGShapeVisitorImpl;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.TextShapeVisitorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the TextShapeVisitorImpl and SVGShapeVisitorImpl.
 */
public class VisitorTest {

  @Test
  public void TextShapeVisitorTest() {
    TextShapeVisitor visitor = new TextShapeVisitorImpl();
    Ellipse ellipse = new Ellipse("e");
    String str = visitor.visit(ellipse);
    assertEquals("shape e Ellipse", str);

    Rectangle rect = new Rectangle("r");
    String str2 = visitor.visit(rect);
    assertEquals("shape r Rectangle", str2);
  }

  @Test
  public void SVGShapeVisitorTest() {
    TextShapeVisitor visitor = new SVGShapeVisitorImpl();
    Ellipse ellipse = new Ellipse("e");
    String str = visitor.visit(ellipse);
    assertEquals("<ellipse cx=\"0.0\" cy=\"0.0\" rx=\"0.0\" ry=\"0.0\" fill=\"rgb(0,0,0)\" "
        + "visibility=\"visible\" opacity=\"0\">\n%s</ellipse>", str);

    Rectangle rect = new Rectangle("r");
    String str2 = visitor.visit(rect);
    assertEquals("<rect x=\"0.0\" y=\"0.0\" width=\"0.0\" height=\"0.0\" "
        + "fill=\"rgb(0,0,0)\" visibility=\"visible\" opacity=\"0\">\n%s</rect>", str2);
  }
}
