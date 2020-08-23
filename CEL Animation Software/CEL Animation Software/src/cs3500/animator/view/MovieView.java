package cs3500.animator.view;

import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;

/**
 * An interface to represent a view of an animated Movie. It displays a MovieModel according to
 * its guidelines.
 */
public interface MovieView {

  /**
   * Runs the given MovieModel according to this view's specifications on displaying an animated
   * movie.
   * @param model the movie to be viewed
   */
  void runAnimation(MovieModel model);

  /**
   * Set the PrintWriter in a text or SVG view.
   *
   * @param out the PrintWriter
   */
  void setOut(PrintWriter out);

}
