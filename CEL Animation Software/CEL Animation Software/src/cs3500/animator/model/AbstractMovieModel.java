package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class which contains information relevant to all movie models. This includes data on
 * the dimensions, length of the movie, and the speed of the movie.
 *
 */
public abstract class AbstractMovieModel implements MovieModel {

  private int canvasWidth;
  private int canvasHeight;
  private int maxX;
  private int maxY;
  private int movieLength;
  private int speed;

  /**
   * Initializes an abstract movie model.
   */
  protected AbstractMovieModel() {
    this.canvasWidth = 500;
    this.canvasHeight = 500;
    this.maxX = 700;
    this.maxY = 700;
    this.movieLength = 100;
    this.speed = 1;
  }

  @Override
  public void setMovieLength(int runtime) {
    if (runtime <= 0) {
      throw new IllegalArgumentException("Movie length must be positive.");
    }
    this.movieLength = runtime;
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void setBounds(int x, int y, int w, int h) {
    this.canvasWidth = x;
    this.canvasHeight = y;
    this.maxX = w;
    this.maxY = h;
  }

  @Override
  public int getSpeed() {
    int res = this.speed;
    return res;
  }

  @Override
  public int getScrollWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getScrollHeight() {
    return this.canvasHeight;
  }

  @Override
  public int getMaxWidth() {
    return this.maxX;
  }

  @Override
  public int getMaxHeight() {
    return this.maxY;
  }

  @Override
  public int getRuntime() {
    return this.movieLength;
  }

  @Override
  public List<Frame> getMovieFrames() {
    List<Frame> res = new ArrayList<Frame>();
    for (int i = 0; i < this.movieLength; i++) {
      res.add(getFrame(i));
    }
    return res;
  }

  @Override
  public String printMovieFrames() {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i <= this.movieLength; i++) {
      if (i == this.movieLength) {
        res.append(printFrame(i));
      } else {
        res.append(printFrame(i) + "\n");
      }
    }
    return res.toString();
  }

  @Override
  public void fitMovieToMax() {
    this.movieLength = getMovieLength();
  }

  /**
   * Gets the current length of the movie.
   *
   * @return the current length of the movie
   */
  protected abstract int getMovieLength();

}
