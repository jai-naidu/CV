package cs3500.animator.model.shape;

import cs3500.animator.model.supplementary.Color;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.VisualShapeVisitor;

/**
 * Represents a rectangle. It is a type of Shape. Its a quadrilateral with 4 right angles.
 */
public class Rectangle extends AbstractShape {

  /**
   * Default constructor for a Rectangle.
   *
   * @param name String that represents the name of the shape
   */
  public Rectangle(String name) {
    super(name);
  }

  /**
   * Constructor to create a Rectangle with specified starting attributes.
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
  public Rectangle(String name, double x, double y, double w, double h, float r, float g, float b,
      double a) {
    super(name, new Posn(x, y), new Color(r, g, b), w, h, a);
  }

  /**
   * A constructor for creating a copy of this rectangle.
   *
   * @param r the rectangle to copy
   */
  private Rectangle(Rectangle r) {
    super(r);
  }

  @Override
  public Shape copy() {
    return new Rectangle(this);
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
    return "width";
  }

  @Override
  public String getAttributeResizeH() {
    return "height";
  }

  @Override
  public String getAttributeTranslateX() {
    return "x";
  }

  @Override
  public String getAttributeTranslateY() {
    return "y";
  }

  @Override
  public String svgW() {
    return this.width + "";
  }

  @Override
  public String svgH() {
    return this.height + "";
  }

  @Override
  public double getRotateSVGX() {
    return this.position.getX() + (this.getWidth() / 2);
  }

  @Override
  public double getRotateSVGY() {
    return this.position.getY() + (this.getHeight() / 2);
  }

}
