package cs3500.animator;

import cs3500.animator.view.EditorView;
import cs3500.animator.view.MovieView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * A class that represents a factory for views. It has one method and given a string it creates and
 * returns the appropriate MovieView.
 */
public class ViewFactory {

  /**
   * Returns the appropriate view given a string specifier.
   *
   * @param viewType the type of view as a string
   * @return the appropriate view
   */
  public static MovieView getView(String viewType) {
    if (viewType.equals("visual")) {
      return new VisualView();

    } else if (viewType.equals("svg")) {
      return new SVGView();

    } else if (viewType.equals("text")) {
      return new TextView();

    } else if (viewType.equals("edit")) {
      return new EditorView();

    }
    return null;
  }
}
