package cs3500.animator.controller;

import cs3500.animator.model.MovieModel;
import cs3500.animator.view.MovieView;

/**
 * A class to represent a controller for a MovieModel. It controls interaction between a MovieModel
 * to a MovieView when run.
 */
public class MVCAnimationController implements MovieController {

  private MovieModel model;
  private MovieView view;

  /**
   * Initializes a MVCAnimationController with a model and a view.
   *
   * @param model the model to be played
   * @param view  the view of how the model should be displayed
   */
  public MVCAnimationController(MovieModel model, MovieView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Arguments can't be null");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {
    view.runAnimation(model);
  }

}
