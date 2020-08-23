package cs3500.animator.view;

import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;

/**
 * A textual view for a movie model. Each line describes the before and after of the shape after a
 * duration of time.
 */
public class TextView implements MovieView {
  private PrintWriter out;
  private MovieModel model;


  @Override
  public void runAnimation(MovieModel model) {
    this.model = model;
    out.append(listMotions());
    out.flush();
    out.close();
  }

  @Override
  public void setOut(PrintWriter out) {
    this.out = out;
  }

  /**
   * Returns a formatted table of all motions for each shape.
   *
   * @return a formatted table of all the motions of each shape
   */
  private String listMotions() {
    StringBuilder res = new StringBuilder();
    res.append("canvas ").append(model.getScrollWidth()).append(" ");
    res.append(model.getScrollHeight()).append(" ").append(model.getMaxWidth()).append(" ");
    res.append(model.getMaxHeight()).append("\n");
    for (int i = 0; i < model.getShapeAnimations().size(); i++) {
      res.append(model.getShapeAnimations().get(i).toTable());
      if (i != model.getShapeAnimations().size() - 1) {
        res.append("\n\n");
      }
    }
    return res.toString();
  }
}
