package cs3500.animator.view;

import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.ShapeAnimation;

/**
 * A class to represent a view of a MovieModel which prints to the specified out file.
 */
public class SVGView implements MovieView {
  private PrintWriter out;
  private MovieModel model;

  @Override
  public void runAnimation(MovieModel model) {
    this.model = model;
    out.append(listMotionsSVG());
    out.flush();
    out.close();
  }

  @Override
  public void setOut(PrintWriter out) {
    this.out = out;
  }


  /**
   * Returns a formatted SVG tag for this Movie.
   *
   * @return a formatted SVG tag for this Movie
   */
  private String listMotionsSVG() {
    StringBuilder res = new StringBuilder();
    res.append("<svg width=\"" + model.getMaxWidth() + "\" height=\"" + model.getMaxHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    for (ShapeAnimation sa : model.getShapeAnimations()) {

      res.append(sa.toAnimationTag(model.getSpeed()) + "\n\n");
    }
    res.append("</svg>");
    return res.toString();
  }
}
