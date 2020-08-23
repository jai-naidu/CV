package cs3500.animator.model.transformation;

import cs3500.animator.model.shape.Shape;

/**
 * An abstract class that represents common functionality between all function objects. All
 * transformations must start and end at a tick. They must all be able to be applied to a shape.
 * Attributes of a transformation should be retrievable.
 */
public abstract class AbstractTransformation implements Transformation {
  protected int startTick;
  protected int endTick;

  /**
   * An abstract constructor that initializes the start and end ticks of a Transformation.
   *
   * @param startTick the start tick of the transformation
   * @param endTick   the end tick of the transformation
   */
  protected AbstractTransformation(int startTick, int endTick) {
    if (startTick < 0 || endTick < 0 || endTick < startTick) {
      throw new IllegalArgumentException("Ticks must be non-negative and end tick"
              + " must be greater than start tick.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
  }

  /**
   * Creates a copy of the given transformation.
   *
   * @param t the transformation
   */
  protected AbstractTransformation(Transformation t) {
    this.startTick = t.getStartTick();
    this.endTick = t.getEndTick();
  }

  @Override
  public abstract void apply(Shape s, int tick);

  @Override
  public abstract String toTag(Shape s, int speed);

  @Override
  public int getStartTick() {
    return startTick;
  }

  @Override
  public int getEndTick() {
    return endTick;
  }

  @Override
  public boolean checkTick(int tick) {
    return tick >= startTick && tick <= endTick;
  }

  @Override
  public abstract boolean sameTransformation(Transformation that);

  @Override
  public boolean sameResize(Resize r) {
    return false;
  }

  @Override
  public boolean sameColorChange(ColorChange c) {
    return false;
  }

  @Override
  public boolean sameTranslate(Translate t) {
    return false;
  }

  @Override
  public boolean sameRotate(Rotate r) {
    return false;
  }

  @Override
  public abstract boolean isConflicting(Transformation t);

  @Override
  public boolean isTranslateConflicting(Translate translate) {
    return false;
  }

  @Override
  public boolean isColorChangeConflicting(ColorChange colorChange) {
    return false;
  }

  @Override
  public boolean isResizeConflicting(Resize resize) {
    return false;
  }

  @Override
  public boolean isRotateConflicting(Rotate rotate) {
    return false;
  }

  @Override
  public boolean conflictingTick(Transformation t) {
    return ((this.startTick < t.getEndTick() && this.startTick >= t.getStartTick())
            || (this.endTick <= t.getEndTick() && this.endTick > t.getStartTick())
            || (this.startTick <= t.getStartTick() && this.endTick >= t.getEndTick()))
            && !((this.startTick == t.getStartTick() && this.endTick == t.getStartTick())
            || (this.startTick == t.getEndTick() && this.endTick == t.getEndTick()));
  }

  @Override
  public abstract void editKeyFrame(int t, double x, double y, double w,
      double h, float r, float g, float b, double a);

  @Override
  public abstract Transformation splitTransformation(int t, double x, double y, double w,
      double h, float r, float g, float b, double a);

  @Override
  public abstract void extendToRight(Transformation t);

  @Override
  public void extendTranslate(Translate t) {
    throw new IllegalArgumentException("Passed in transformation has to be a translate.");
  }

  @Override
  public void extendColorChange(ColorChange c) {
    throw new IllegalArgumentException("Passed in transformation has to be a color change.");
  }

  @Override
  public void extendResize(Resize r) {
    throw new IllegalArgumentException("Passed in transformation has to be a resize.");
  }

  @Override
  public void extendRotate(Rotate r) {
    throw new IllegalArgumentException("Passed in transformation has to be a Rotate.");
  }
}