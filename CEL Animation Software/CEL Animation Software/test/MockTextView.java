import java.io.IOException;
import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;
import cs3500.animator.view.MovieView;

/**
 * A mock textual view for a movie model for testing.
 */
public class MockTextView implements MovieView {
  private Appendable out;
  private MovieModel model;

  /**
   * Constructs a textual view.
   */
  public MockTextView(StringBuilder out) {
    this.out = out;

  }

  @Override
  public void runAnimation(MovieModel model) {
    this.model = model;
    listMotions();
  }

  @Override
  public void setOut(PrintWriter out) {
    return;
  }

  /**
   * Appends a formatted list of text tables to this class' Appendable.
   */
  private void listMotions() {
    StringBuilder res = new StringBuilder();
    res.append("canvas " + model.getScrollWidth() + " " + model.getScrollHeight() + " "
            + model.getMaxWidth() + " " + model.getMaxHeight() + "\n");
    for (int i = 0; i < model.getShapeAnimations().size(); i++) {
      res.append(model.getShapeAnimations().get(i).toTable());
      if (i != model.getShapeAnimations().size() - 1) {
        res.append("\n\n");
      }
    }
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
