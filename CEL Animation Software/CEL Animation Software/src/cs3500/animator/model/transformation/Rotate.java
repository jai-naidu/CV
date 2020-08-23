package cs3500.animator.model.transformation;

import cs3500.animator.model.shape.Shape;

/**
 * A transformation function object that represents an object that is moving from a start point to
 * an end point. Can be used to calculate where a shape should be on a canvas at a given tick.
 */
public class Rotate extends AbstractTransformation {
  private double startAngle;
  private double endAngle;

  /**
   * Initializes a translation for a shape to be moved from one point to another in a given time
   * frame.
   *
   * @param startTick  the start tick of the transformation
   * @param endTick    the end tick of the transformation
   * @param startAngle the start position of the transformation
   * @param endAngle   the end position of the transformation
   * @throws IllegalArgumentException if any of the given positions are null
   */
  public Rotate(int startTick, int endTick, double startAngle, double endAngle) {
    super(startTick, endTick);
    this.startAngle = startAngle;
    this.endAngle = endAngle;
  }

  /**
   * Makes a copy of this Rotate.
   *
   * @param original the rotate
   */
  private Rotate(Rotate original) {
    super(original);
    this.startAngle = original.startAngle;
    this.endAngle = original.endAngle;
  }

  @Override
  public void apply(Shape s, int tick) {
    if (startAngle == endAngle) {
      s.setAngle(this.startAngle);
    } else if (checkTick(tick)) {
      double change =
              (this.endAngle - this.startAngle) / ((double) (endTick - startTick))
                      * ((double) (tick - startTick));
      s.setAngle(change + startAngle);
    }

  }

  @Override
  public String toTag(Shape s, int speed) {
    double duration = ((this.endTick - this.startTick) * 1000) / (float) speed;
    String rotate =
        String.format("<animateTransform attributeName=\"transform\" attributeType=\"xml\" "
                + "begin=\"%sms\" type=\"rotate\" from=\"%s %s %s\" to=\"%s %s %s\" "
                + "dur=\"%sms\" />",
            ((double) this.startTick * 1000) / speed, this.startAngle,
            s.getRotateSVGX(), s.getRotateSVGY(), this.endAngle, s.getRotateSVGX(),
            s.getRotateSVGY(), duration);
    if (this.startAngle == 0 && this.endAngle == 0) {
      return "";
    }
    return rotate;
  }


  @Override
  public boolean sameTransformation(Transformation that) {
    return that.sameRotate(this);
  }

  @Override
  public boolean sameRotate(Rotate t) {
    return true;
  }

  @Override
  public boolean isConflicting(Transformation t) {
    return t.isRotateConflicting(this);
  }

  @Override
  public boolean isRotateConflicting(Rotate t) {
    if (this.startTick == t.endTick) {
      return (this.startAngle != t.endAngle);
    } else if (this.endTick <= t.startTick) {
      return (this.endAngle != startAngle);
    } else {
      return conflictingTick(t);
    }
  }

  @Override
  public void editKeyFrame(int t, double x, double y, double w,
                           double h, float r, float g, float b, double a) {
    if (this.startTick == t) {
      this.startAngle = a;
    } else if (this.endTick == t) {
      this.endAngle = a;
    }
  }

  @Override
  public Transformation splitTransformation(int t, double x, double y, double w,
                                            double h, float r, float g, float b, double a) {
    Rotate copy = new Rotate(this);
    this.endTick = t;
    this.endAngle = a;
    copy.startTick = t;
    copy.startAngle = a;
    return copy;
  }

  @Override
  public void extendToRight(Transformation t) {
    t.extendRotate(this);
  }

  @Override
  public void extendRotate(Rotate t) {
    t.endTick = this.endTick;
    t.endAngle = this.endAngle;
  }
}