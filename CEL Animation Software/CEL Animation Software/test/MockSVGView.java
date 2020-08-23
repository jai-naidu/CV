import java.io.IOException;
import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.MovieView;

/**
 * A class to represent a view of a MovieModel which print to an Appendable for testing.
 */
public class MockSVGView implements MovieView {
  private Appendable out;
  private MovieModel model;

  /**
   * Constructs a mock SVG view.
   */
  public MockSVGView(StringBuilder out) {
    this.out = out;
  }

  @Override
  public void runAnimation(MovieModel model) {
    this.model = model;
    listMotionsSVG();
  }

  @Override
  public void setOut(PrintWriter out) {
    return;
  }

  /**
   * Appends a formatted SVG tag to this class' Appendable.
   */
  private void listMotionsSVG() {
    StringBuilder res = new StringBuilder();
    res.append("<svg width=\"" + model.getMaxWidth() + "\" height=\"" + model.getMaxHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    for (ShapeAnimation sa : model.getShapeAnimations()) {

      res.append(sa.toAnimationTag(model.getSpeed()) + "\n\n");
    }
    res.append("</svg>");
    tryAppend(res.toString());
  }

  /**
   * Given a String, try to append it to the Appendable.
   *
   * @param s the String to append to the Appendable
   * @throws IllegalStateException if the append fails
   */
  private void tryAppend(String s) {
    try {
      out.append(s);
    } catch (IOException e) {
      throw new IllegalStateException("Append failed.", e);
    }
  }
}
