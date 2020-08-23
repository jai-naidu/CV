package cs3500.animator.model.transformation;

import cs3500.animator.model.shape.Shape;

/**
 * A transformation function object that represents an object that is changing shape from a point in
 * time to another. Can be used to calculate what size a shape should be on a canvas at a given
 * tick.
 */
public class Resize extends AbstractTransformation {
  private double startWidth;
  private double startHeight;
  private double endHeight;
  private double endWidth;

  /**
   * A constructor that initializes a resizing of a shape from one tick in time to another.
   *
   * @param startTick when the resizing begins
   * @param endTick   when the resizing ends
   * @param sw        the starting width of the shape
   * @param sh        the starting height of the shape
   * @param eh        the ending height of the shape
   * @param ew        the ending width of the shape
   * @throws IllegalArgumentException if dimensions are non-positive
   */
  public Resize(int startTick, int endTick, double sw, double sh, double ew, double eh) {
    super(startTick, endTick);
    if (sw <= 0 || sh <= 0 || eh <= 0 || ew <= 0) {
      throw new IllegalArgumentException("Dimensions for a resize must be positive.");
    }
    this.startWidth = sw;
    this.startHeight = sh;
    this.endHeight = eh;
    this.endWidth = ew;
  }

  /**
   * A constructor to copy this resize.
   *
   * @param original the resize to copy
   */
  private Resize(Resize original) {
    super(original);
    this.startHeight = original.startHeight;
    this.startWidth = original.startWidth;
    this.endHeight = original.endHeight;
    this.endWidth = original.endWidth;
  }

  @Override
  public void apply(Shape s, int tick) {
    if (startTick == endTick) {
      s.setWidth(this.startWidth);
      s.setHeight(this.startHeight);
    } else if (checkTick(tick)) {
      double widthChange =
              ((this.endWidth - this.startWidth) / ((double) (endTick - startTick)))
                      * ((double) (tick - startTick));
      double heightChange =
              ((this.endHeight - this.startHeight) / (double) (endTick - startTick))
                      * (double) (tick - startTick);
      s.setWidth(widthChange + this.startWidth);
      s.setHeight(heightChange + this.startHeight);
    }
  }

  @Override
  public String toTag(Shape s, int speed) {
    double duration = ((this.endTick - this.startTick) * 1000) / (float) speed;
    Shape start = s.copy();
    apply(start, startTick);
    Shape end = s.copy();
    apply(end, endTick);
    String widthTag =
            String.format("<animate attributeType=\"xml\" begin=\"%sms\" "
                            + "dur=\"%sms\" attributeName=\"%s\" from=\"%s\" "
                            + "to=\"%s\" />",
                    ((double) this.startTick * 1000) / speed, duration,
                    s.getAttributeResizeW(),
                    start.svgW(), end.svgW());
    String heightTag =
            String.format("<animate attributeType=\"xml\" begin=\"%sms\" "
                            + "dur=\"%sms\" attributeName=\"%s\" from=\"%s\" "
                            + "to=\"%s\" />",
                    ((double) this.startTick * 1000) / speed, duration,
                    s.getAttributeResizeH(),
                    start.svgH(), end.svgH());
    return widthTag + "\n" + heightTag;
  }

  @Override
  public boolean sameTransformation(Transformation that) {
    return that.sameResize(this);
  }

  @Override
  public boolean sameResize(Resize r) {
    return true;
  }

  @Override
  public boolean isConflicting(Transformation t) {
    return t.isResizeConflicting(this);
  }

  @Override
  public boolean isResizeConflicting(Resize r) {
    if (this.startTick == r.endTick) {
      return !(this.startHeight == r.endHeight && this.startWidth == r.endWidth);
    } else if (this.endTick == r.startTick) {
      return !(r.startHeight == this.endHeight && r.startWidth == this.endWidth);
    } else if (conflictingTick(r)) {
      return true;
    }
    return false;
  }

  @Override
  public void editKeyFrame(int t, double x, double y, double w,
      double h, float r, float g, float b, double a) {
    if (this.startTick == t) {
      this.startWidth = w;
      this.startHeight = h;
    } else if (this.endTick == t) {
      this.endWidth = w;
      this.endHeight = h;
    }
  }

  @Override
  public Transformation splitTransformation(int t, double x, double y, double w,
      double h, float r, float g, float b, double a) {
    Resize copy = new Resize(this);
    this.endTick = t;
    this.endWidth = w;
    this.endHeight = h;
    copy.startTick = t;
    copy.startWidth = w;
    copy.startHeight = h;
    return copy;
  }

  @Override
  public void extendToRight(Transformation t) {
    t.extendResize(this);
  }

  @Override
  public void extendResize(Resize r) {
    r.endTick = this.endTick;
    r.endWidth = this.endWidth;
    r.endHeight = this.endHeight;
  }
}
