package cs3500.animator.model.transformation;

import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.supplementary.Posn;

/**
 * A transformation function object that represents an object that is moving from a start point to
 * an end point. Can be used to calculate where a shape should be on a canvas at a given tick.
 */
public class Translate extends AbstractTransformation {
  private Posn startPoint;
  private Posn endpoint;

  /**
   * Initializes a translation for a shape to be moved from one point to another in a given time
   * frame.
   *
   * @param startTick  the start tick of the transformation
   * @param endTick    the end tick of the transformation
   * @param startPoint the start position of the transformation
   * @param endPoint   the end position of the transformation
   * @throws IllegalArgumentException if any of the given positions are null
   */
  public Translate(int startTick, int endTick, Posn startPoint, Posn endPoint) {
    super(startTick, endTick);
    if (startPoint == null || endPoint == null) {
      throw new IllegalArgumentException("Input start and end points must be "
              + "non-null");
    }
    this.startPoint = startPoint;
    this.endpoint = endPoint;
  }

  /**
   * Makes a copy of this translate.
   *
   * @param original the translate
   */
  private Translate(Translate original) {
    super(original);
    this.startPoint = original.startPoint;
    this.endpoint = original.endpoint;
  }

  @Override
  public void apply(Shape s, int tick) {
    if (startTick == endTick) {
      s.setPosn(this.startPoint.getX(), this.startPoint.getY());
    } else if (checkTick(tick)) {
      double xChange =
              (this.endpoint.getX() - this.startPoint.getX()) / ((double) (endTick - startTick))
                      * ((double) (tick - startTick));
      double yChange =
              ((this.endpoint.getY() - this.startPoint.getY()) / ((double) (endTick - startTick)))
                      * ((double) (tick - startTick));
      s.setPosn(xChange + startPoint.getX(), yChange + startPoint.getY());
    }

  }

  @Override
  public String toTag(Shape s, int speed) {
    double duration = ((this.endTick - this.startTick) * 1000) / (float) speed;
    String xTag =
            String.format("<animate attributeType=\"xml\" begin=\"%sms\" "
                            + "dur=\"%sms\" attributeName=\"%s\" from=\"%s\" "
                            + "to=\"%s\" />",
                    ((double) this.startTick * 1000) / speed, duration,
                    s.getAttributeTranslateX(),
                    startPoint.getX(), endpoint.getX());
    String yTag =
            String.format("<animate attributeType=\"xml\" begin=\"%sms\" "
                            + "dur=\"%sms\" attributeName=\"%s\" from=\"%s\" "
                            + "to=\"%s\" />",
                    ((double) this.startTick * 1000) / speed, duration,
                    s.getAttributeTranslateY(),
                    startPoint.getY(), endpoint.getY());
    return xTag + "\n" + yTag;
  }

  @Override
  public boolean sameTransformation(Transformation that) {
    return that.sameTranslate(this);
  }

  @Override
  public boolean sameTranslate(Translate t) {
    return true;
  }

  @Override
  public boolean isConflicting(Transformation t) {
    return t.isTranslateConflicting(this);
  }

  @Override
  public boolean isTranslateConflicting(Translate t) {
    if (this.startTick == t.endTick) {
      return !(this.startPoint.equals(t.endpoint));
    } else if (this.endTick <= t.startTick) {
      return !(this.endpoint.equals(t.startPoint));
    } else if (conflictingTick(t)) {
      return true;
    }
    return false;
  }

  @Override
  public void editKeyFrame(int t, double x, double y, double w,
                           double h, float r, float g, float b, double a) {
    if (this.startTick == t) {
      this.startPoint = new Posn(x, y);
    } else if (this.endTick == t) {
      this.endpoint = new Posn(x, y);
    }
  }

  @Override
  public Transformation splitTransformation(int t, double x, double y, double w,
                                            double h, float r, float g, float b, double a) {
    Translate copy = new Translate(this);
    this.endTick = t;
    this.endpoint = new Posn(x, y);
    copy.startTick = t;
    copy.startPoint = new Posn(x, y);
    return copy;
  }

  @Override
  public void extendToRight(Transformation t) {
    t.extendTranslate(this);
  }

  @Override
  public void extendTranslate(Translate t) {
    t.endTick = this.endTick;
    t.endpoint = this.endpoint;
  }
}