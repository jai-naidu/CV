package cs3500.animator.model.transformation;

import cs3500.animator.model.supplementary.Color;

import cs3500.animator.model.shape.Shape;

/**
 * A transformation function object that represents an object that is changing color from a point in
 * time to another. Can be used to calculate what color a shape should be on a canvas at a given
 * tick.
 */
public class ColorChange extends AbstractTransformation {
  private Color startColor;
  private Color endColor;

  /**
   * Initializes a color change for a shape from a given point in time to another.
   *
   * @param startTick when the color change starts
   * @param endTick   when the color change ends
   * @param sc        the starting color
   * @param ec        the ending color
   * @throws IllegalArgumentException if any of the given Colors are null or if ticks are invalid
   */
  public ColorChange(int startTick, int endTick, Color sc, Color ec) {
    super(startTick, endTick);
    if (sc == null || ec == null) {
      throw new IllegalArgumentException("Given colors cannot be null.");
    }
    this.startColor = sc;
    this.endColor = ec;
  }

  /**
   * Makes a copy of this color change.
   *
   * @param original the color change
   */
  private ColorChange(ColorChange original) {
    super(original);
    this.startColor = original.startColor;
    this.endColor = original.endColor;
  }

  @Override
  public void apply(Shape s, int tick) {
    if (startTick == endTick) {
      s.setColor(this.startColor);
    } else if (checkTick(tick)) {
      float diffTemp = ((float) (endTick - startTick));
      if (diffTemp == 0) {
        diffTemp = 1;
      }
      float red =
              ((endColor.getRed() - startColor.getRed()) / diffTemp)
                      * ((float) (tick - startTick));
      float green =
              ((endColor.getGreen() - startColor.getGreen()) / diffTemp)
                      * ((float) (tick - startTick));
      float blue = ((endColor.getBlue() - startColor.getBlue()) / diffTemp)
              * ((float) (tick - startTick));
      s.setColor(new Color(red + startColor.getRed(), green + startColor.getGreen(),
              blue + startColor.getBlue()));
    }
  }

  @Override
  public String toTag(Shape s, int speed) {
    double duration = ((this.endTick - this.startTick) * 1000) / (float) speed;
    String tag =
            String.format("<animate attributeType=\"xml\" begin=\"%sms\" "
                            + "dur=\"%sms\" attributeName=\"fill\" from=\"%s\" "
                            + "to=\"%s\" />",
                    ((double) this.startTick * 1000) / speed, duration,
                    this.startColor.toString(),
                    this.endColor.toString());
    return tag;
  }

  @Override
  public boolean sameTransformation(Transformation that) {
    return that.sameColorChange(this);
  }

  @Override
  public boolean sameColorChange(ColorChange c) {
    return true;
  }

  @Override
  public boolean isConflicting(Transformation t) {
    return t.isColorChangeConflicting(this);
  }

  @Override
  public boolean isColorChangeConflicting(ColorChange c) {
    if (this.startTick == c.endTick) {
      return !(this.startColor.equals(c.endColor));
    } else if (this.endTick == c.startTick) {
      return !(this.endColor.equals(c.startColor));
    } else if (conflictingTick(c)) {
      return true;
    }
    return false;
  }

  @Override
  public void editKeyFrame(int t, double x, double y, double w,
      double h, float r, float g, float b, double a) {
    if (this.startTick == t) {
      this.startColor = new Color(r, g, b);
    } else if (this.endTick == t) {
      this.endColor = new Color(r, g, b);
    }
  }

  @Override
  public Transformation splitTransformation(int t, double x, double y, double w,
      double h, float r, float g, float b, double a) {
    ColorChange copy = new ColorChange(this);
    this.endTick = t;
    this.endColor = new Color(r, g, b);
    copy.startTick = t;
    copy.startColor = new Color(r, g, b);
    return copy;
  }

  @Override
  public void extendToRight(Transformation t) {
    t.extendColorChange(this);
  }

  @Override
  public void extendColorChange(ColorChange c) {
    c.endTick = this.endTick;
    c.endColor = this.endColor;
  }
}
