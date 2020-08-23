package cs3500.animator.model.shape;

import cs3500.animator.model.supplementary.Color;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.VisualShapeVisitor;

/**
 * Represents an abstract shape. All shapes can be animated in a movie. Holds all of the abstracted
 * methods, fields, and constructors of all shapes.
 */
public abstract class AbstractShape implements Shape {

  protected final String name;
  protected Color rgb;
  protected Posn position;
  protected double width;
  protected double height;
  protected double angle; // initial right pointing horizontal vector from the center of the shape

  /**
   * Default abstract constructor, creates a Shape given just a name.
   *
   * @param name a string name that identifies a shape
   * @throws IllegalArgumentException if the name is null
   */
  protected AbstractShape(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.name = name;
    this.rgb = new Color(0, 0, 0);
    this.position = new Posn(0, 0);
    this.width = 0;
    this.height = 0;
    this.angle = 0;
  }

  /**
   * Creates a shape given specific values.
   *
   * @param name     name of the shape
   * @param position position of the shape
   * @param c        color of the shape
   * @param width    width of the shape
   * @param height   height of the shape
   * @throws IllegalArgumentException if dimensions are negative or any arguments are null
   */
  protected AbstractShape(String name, Posn position, Color c, double width,
      double height, double angle) {
    if (width <= 0 || height <= 0 || name == null || position == null
        || c == null) {
      throw new IllegalArgumentException("Dimensions must be positive and "
          + "arguments must be non null.");
    }
    this.rgb = c;
    this.position = position;
    this.width = width;
    this.height = height;
    this.name = name;
    this.angle = angle;
  }

  /**
   * A constructor to copy a shape.
   *
   * @param s the shape to copy
   */
  protected AbstractShape(Shape s) {
    this.rgb = s.getRGB();
    this.position = s.getPosn();
    this.width = s.getWidth();
    this.height = s.getHeight();
    this.name = s.getName();
    this.angle = s.getAngle();
  }

  @Override
  public Color getRGB() {
    return this.rgb;
  }

  @Override
  public Posn getPosn() {
    return this.position;
  }

  @Override
  public String shapeInfo() {
    return (int) this.getX() + " " + (int) this.getY() + " " + (int) this.width
        + " " + (int) this.height + " " + (int) this.rgb.getRed()
        + " " + (int) this.rgb.getGreen() + " " + (int) this.rgb.getBlue() + " "
        + (int) this.angle;
  }

  @Override
  public abstract Shape copy();

  @Override
  public float getR() {
    return this.rgb.getRed();
  }

  @Override
  public float getG() {
    return this.rgb.getGreen();
  }

  @Override
  public float getB() {
    return this.rgb.getBlue();
  }

  @Override
  public void setColor(Color c) {
    this.rgb = c;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(double w) {
    this.width = w;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(double h) {
    this.height = h;
  }

  @Override
  public double getX() {
    return this.position.getX();
  }

  @Override
  public double getY() {
    return this.position.getY();
  }

  @Override
  public void setPosn(double x, double y) {
    this.position = new Posn(x, y);
  }

  @Override
  public void setAngle(double a) {
    this.angle = a;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public double getAngle() {
    return this.angle;
  }

  @Override
  public abstract void accept(VisualShapeVisitor visitor);

  @Override
  public abstract String accept(TextShapeVisitor visitor);

  @Override
  public abstract String getAttributeResizeW();

  @Override
  public abstract String getAttributeResizeH();

  @Override
  public abstract String getAttributeTranslateX();

  @Override
  public abstract String getAttributeTranslateY();

  @Override
  public abstract String svgW();

  @Override
  public abstract String svgH();

  @Override
  public abstract double getRotateSVGX();

  @Override
  public abstract double getRotateSVGY();
}