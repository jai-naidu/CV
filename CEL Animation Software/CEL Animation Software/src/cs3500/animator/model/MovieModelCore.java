package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;

/**
 * A class that represents a movie animation. Contains all of the core functionality/metadata for an
 * animation. Stores shapes to animate as ShapeAnimations that contains a Shape and a list of all of
 * its Transformations.
 */
public interface MovieModelCore {

  /**
   * Adds a translation transformation to the given shape at the given time from and to the given x
   * and y values.
   *
   * @param name  the name of the shape from which the transformation should be added
   * @param start the time the translation starts
   * @param end   the time the translation ends
   * @param x1    the starting x coordinate
   * @param y1    the starting y coordinate
   * @param x2    the x coordinate after being translated
   * @param y2    the y coordinate after being translated
   * @throws IllegalArgumentException if there is no shape with the name, or if any of the
   *                                  parameters are not valid for transformations
   */
  void addTranslateToShape(String name, int start, int end, double x1, double y1,
                           double x2, double y2);

  /**
   * Adds a resizing transformation to the given shape at the given time from and to the which
   * changes the shape to and from the given dimensions.
   *
   * @param name  the name of the shape from which the transformation should be added
   * @param start the time the translation starts
   * @param end   the time the translation ends
   * @param w1    the starting width
   * @param h1    the starting length
   * @param w2    the width after being resized
   * @param h2    the length coordinate after being resized
   * @throws IllegalArgumentException if there is no shape with the name, or if any of the
   *                                  parameters are not valid for transformations
   */
  void addResizeToShape(String name, int start, int end, int w1, int h1,
                        int w2, int h2);

  /**
   * Adds a color changing transformation to the given shape at the given time from and to the which
   * changes the shape to and from the given colors.
   *
   * @param name  the name of the shape from which the transformation should be added
   * @param start the time the translation starts
   * @param end   the time the translation ends
   * @param r1    the starting red value
   * @param g1    the starting green value
   * @param b1    the starting blue value
   * @param r2    the red value to be changed to
   * @param g2    the length coordinate after being resized
   * @param b2    the length coordinate after being resized
   * @throws IllegalArgumentException if there is no shape with the name, or if any of the
   *                                  parameters are not valid for transformations
   */
  void addColorChangeToShape(String name, int start, int end, float r1, float g1, float b1,
                             float r2, float g2, float b2);

  /**
   * Adds a rotating transformation to the given shape at the given time from and to the which
   * changes the shape to and from the given angles.
   *
   * @param name  the name of the shape from which the transformation should be added
   * @param start the time the translation starts
   * @param end   the time the translation ends
   * @param a1    the starting angle
   * @param a2    the ending angle
   * @throws IllegalArgumentException if there is no shape with the name, or if any of the
   *                                  parameters are not valid for transformations
   */
  void addRotateToShape(String name, int start, int end, double a1, double a2);

  /**
   * Adds an ellipse to this movie with no transformations yet.
   *
   * @param name the name to be added
   * @param x    x position
   * @param y    y position
   * @param w    width
   * @param h    height
   * @param r    red value
   * @param g    green value
   * @param b    blue value
   * @param a    angle
   * @throws IllegalArgumentException if the inputs are illegal for a ellipse shape or if the name
   *                                  is null
   */
  void addEllipseShape(String name, double x, double y, double w,
                       double h, float r, float g, float b, double a);

  /**
   * Adds an Ellipse shape to the animation given just a name.
   *
   * @param name name of the shape
   */
  void addEllipseShape(String name);

  /**
   * Adds a rectangle to this movie with no transformations yet.
   *
   * @param name the name to be added
   * @param x    x position
   * @param y    y position
   * @param w    width
   * @param h    height
   * @param r    red value
   * @param g    green value
   * @param b    blue value
   * @param a    angle
   * @throws IllegalArgumentException if the inputs are illegal for a rectangle shape or if the name
   *                                  is null
   */
  void addRectangleShape(String name, double x, double y, double w,
                         double h, float r, float g, float b, double a);

  /**
   * Adds a Rectangle shape to the animation given just a name.
   *
   * @param name name of the shape
   */
  void addRectangleShape(String name);

  /**
   * Adds a motion to this movie.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param a1   The initial angle of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @param a2   The final angle of the shape
   */
  void addMotion(String name, int t1, int x1, int y1,
                 int w1, int h1, int r1, int g1, int b1, int a1,
                 int t2, int x2, int y2, int w2, int h2,
                 int r2, int g2, int b2, int a2);

  /**
   * Adds an individual keyframe to the growing document.
   *
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   * @param a    The angle of the shape
   * @throws IllegalArgumentException if the shape with the given name doesn't exist
   */
  void addKeyFrame(String name, int t, double x, double y, double w,
                   double h, float r, float g, float b, double a);

  /**
   * Delete the key frame that is specified by the passed in conditions if it exists.
   *
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @throws IllegalArgumentException if the shape with the given name doesn't exists
   * @throws IllegalArgumentException if the specified keyframe doesn't already exists
   */
  void deleteKeyFrame(String name, int t);

  /**
   * Sets the bounds of the frame and the window.
   *
   * @param x the width of the frame
   * @param y the height of the frame
   * @param w the width of the window
   * @param h the height of the window
   */
  void setBounds(int x, int y, int w, int h);

  /**
   * Set the speed (in ticks per second) for the movie.
   *
   * @param speed the speed
   */
  void setSpeed(int speed);

  /**
   * Sets the movie length.
   *
   * @param runtime how many ticks long the movie will be
   * @throws IllegalArgumentException if runtime to set is negative
   */
  void setMovieLength(int runtime);
}
