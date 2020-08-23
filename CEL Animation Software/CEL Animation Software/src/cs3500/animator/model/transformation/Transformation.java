package cs3500.animator.model.transformation;

import cs3500.animator.model.shape.Shape;

/**
 * An interface that represents all transformation function objects. A transformation is a function
 * object that can apply a change to a given shape if the transformation is occurring at a tick
 * value where the shape exists on the canvas.
 */
public interface Transformation {
  /**
   * Applies this transformation on the given shape at the the given time.
   *
   * @param s    the shape to to be transformed
   * @param tick the time at which to get the given shape
   */
  void apply(Shape s, int tick);

  /**
   * Returns the tick that indicates when the transformation begins.
   *
   * @return the tick that indicates when the transformation begins
   */
  int getStartTick();

  /**
   * Returns the tick that indicates when the transformation ends.
   *
   * @return the tick that indicates when the transformation ends
   */
  int getEndTick();

  /**
   * Checks to see if the transformation occurs at the given tick.
   *
   * @param tick the tick
   * @return is this transformation occurring at this tick?
   */
  boolean checkTick(int tick);

  /**
   * Checks if the given transformation is the same type of transformation as this transformation.
   *
   * @param that the other transformation that is being compared
   * @return whether this and that transformation are the same type
   */
  boolean sameTransformation(Transformation that);

  /**
   * Checks if the given Resize the same as this Resize.
   *
   * @param r the Resize you are checking with
   * @return are the Resize's equal?
   */
  boolean sameResize(Resize r);

  /**
   * Checks if the given ColorChange the same as this resize.
   *
   * @param c the ColorChange you are checking against
   * @return are the ColorChange's equal?
   */
  boolean sameColorChange(ColorChange c);


  /**
   * Checks if the given Translate the same as this resize.
   *
   * @param t the Translate you are checking against
   * @return are the Translate's equal?
   */
  boolean sameTranslate(Translate t);

  /**
   * Checks if the given Rotate the same as this rotate.
   *
   * @param r the Rotate you are checking against
   * @return are the Rotate's equal?
   */
  boolean sameRotate(Rotate r);

  /**
   * Checks to see if the given transformation conflicts with this transformation.
   *
   * @param t the given transformation
   * @return do the transformations conflict?
   */
  boolean isConflicting(Transformation t);

  /**
   * Checks to see if the given Translate conflicts with this Translate.
   *
   * @param translate the given Translate
   * @return do the Translates conflict?
   */
  boolean isTranslateConflicting(Translate translate);

  /**
   * Checks to see if the given ColorChange conflicts with this ColorChange.
   *
   * @param colorChange the given ColorChange
   * @return do the ColorChanges conflict?
   */
  boolean isColorChangeConflicting(ColorChange colorChange);

  /**
   * Checks to see if the given Resize conflicts with this Resize.
   *
   * @param resize the given Resize
   * @return do the Resizes conflict?
   */
  boolean isResizeConflicting(Resize resize);

  /**
   * Checks to see if the given Rotate conflicts with this Rotate.
   *
   * @param rotate the given Rotate
   * @return do the Rotates conflict?
   */
  public boolean isRotateConflicting(Rotate rotate);

  /**
   * Checks if the ticks of this transformation interfere with t's transformation.
   *
   * @param t the other transformation
   * @return ticks of this transformation interfere with t's transformation?
   */
  boolean conflictingTick(Transformation t);

  /**
   * Formats this transformation as a tag.
   *
   * @return this transformation as a tag
   */
  String toTag(Shape s, int speed);

  /**
   * Edits an existing key frame in this transformation.
   *
   * @param t The time for this keyframe
   * @param x The x-position of the shape
   * @param y The y-position of the shape
   * @param w The width of the shape
   * @param h The height of the shape
   * @param r The red color-value of the shape
   * @param g The green color-value of the shape
   * @param b The blue color-value of the shape
   * @param a The angle of the shape
   */
  void editKeyFrame(int t, double x, double y, double w,
                    double h, float r, float g, float b, double a);

  /**
   * Split the transformation on the given keyframe.
   *
   * @param t The time for this keyframe
   * @param x The x-position of the shape
   * @param y The y-position of the shape
   * @param w The width of the shape
   * @param h The height of the shape
   * @param r The red color-value of the shape
   * @param g The green color-value of the shape
   * @param b The blue color-value of the shape
   * @param a The angle of the shape
   * @return the second half of the split transformation
   */
  Transformation splitTransformation(int t, double x, double y, double w,
                                     double h, float r, float g, float b, double a);

  /**
   * Extend this transformation to include the attributes of the given transformation.
   *
   * @param t the transformation to include
   */
  void extendToRight(Transformation t);

  /**
   * Extends the given translate to include the attributes of this translate.
   *
   * @param t the translate to extend
   */
  void extendTranslate(Translate t);

  /**
   * Extends the given color change to include the attributes of this color change.
   *
   * @param c the color change to extend
   */
  void extendColorChange(ColorChange c);

  /**
   * Extends the given resize to include the attributes of this resize.
   *
   * @param r the resize to extend
   */
  void extendResize(Resize r);

  /**
   * Extends the given rotate to include the attributes of this rotate.
   *
   * @param r the rotate to extend
   */
  void extendRotate(Rotate r);
}