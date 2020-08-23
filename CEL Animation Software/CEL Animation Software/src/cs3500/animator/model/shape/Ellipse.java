package cs3500.animator.model.shape;

import cs3500.animator.model.supplementary.Color;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.VisualShapeVisitor;

/**
 * Represents an ellipse. It is a type of Shape with no corners.
 */
public class Ellipse extends AbstractShape {

  /**
   * Default constructor for a Ellipse.
   *
   * @param name String that represents the name of the shape
   */
  public Ellipse(String name) {
    super(name);
  }

  /**
   * Constructor to create an Ellipse with specified starting attributes.
   *
   * @param name name of the shape
   * @param x    the starting x position
   * @param y    the starting y position
   * @param w    the starting width
   * @param h    the starting height
   * @param r    the starting red color value
   * @param g    the starting green color value
   * @param b    the starting blue color value
   */
  public Ellipse(String name, double x, double y, double w, double h, float r, float g, float b,
      double a) {
    super(name, new Posn(x, y), new Color(r, g, b), w, h, a);
  }

  /**
   * A constructor for creating a copy of this ellipse.
   *
   * @param e the ellipse to copy
   */
  private Ellipse(Ellipse e) {
    super(e);
  }

  @Override
  public Shape copy() {
    return new Ellipse(this);
  }

  @Override
  public void accept(VisualShapeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public String accept(TextShapeVisitor visitor) {
    return visitor.visit(this);
  }

  @Override
  public String getAttributeResizeW() {
    return "rx";
  }

  @Override
  public String getAttributeResizeH() {
    return "ry";
  }

  @Override
  public String getAttributeTranslateX() {
    return "cx";
  }

  @Override
  public String getAttributeTranslateY() {
    return "cy";
  }

  @Override
  public String svgW() {
    return (this.width / 2.0) + "";
  }

  @Override
  public String svgH() {
    return (this.height / 2.0) + "";
  }

  @Override
  public double getRotateSVGX() {
    return this.position.getX();
  }

  @Override
  public double getRotateSVGY() {
    return this.position.getY();
  }

}
