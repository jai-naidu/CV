package cs3500.animator.model.shape;

import cs3500.animator.model.supplementary.Color;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.VisualShapeVisitor;

/**
 * An interface that represents all supported Shapes. Any Shape that implements this interface can
 * be added to an instance of a Movie.
 */
public interface Shape {

  /**
   * Get the info of the shape in String form.
   *
   * @return the info of the shape in String form
   */
  String shapeInfo();

  /**
   * Copies a shape.
   *
   * @return a copy of this shape
   */
  Shape copy();

  /**
   * Gets the red value from this shapes color.
   *
   * @return the red value (0 - 255)
   */
  float getR();

  /**
   * Gets the green value from this shapes color.
   *
   * @return the green value (0 - 255)
   */
  float getG();

  /**
   * Gets the blue value from this shapes color.
   *
   * @return the blue value (0 - 255)
   */
  float getB();

  /**
   * Gets the height of the shape.
   *
   * @return the height of the shape
   */
  double getWidth();

  /**
   * Gets the height of the shape.
   *
   * @return the height of the shape
   */
  double getHeight();

  /**
   * Gets the x value of the shape's position.
   *
   * @return the x value of the shape's position
   */
  double getX();

  /**
   * Gets the y value of the shape's position.
   *
   * @return the y value of the shape's position
   */
  double getY();

  /**
   * Sets the position of the shape.
   *
   * @param x the x position
   * @param y the y position
   */
  void setPosn(double x, double y);

  /**
   * Sets the angle of the shape.
   *
   * @param a the color
   */
  void setAngle(double a);

  /**
   * Sets the color of the shape.
   *
   * @param c the color
   */
  void setColor(Color c);

  /**
   * Sets the width of the shape.
   *
   * @param width the width of the shape
   */
  void setWidth(double width);

  /**
   * Sets the height of the shape.
   *
   * @param height the width of the shape
   */
  void setHeight(double height);

  /**
   * Gets the name of this shape.
   *
   * @return the name of this shape
   */
  String getName();

  /**
   * Get the rgb field of the shape as a Color.
   *
   * @return the rgb field of the shape as a Color
   */
  Color getRGB();

  /**
   * Get the position of the shape as a Posn.
   *
   * @return the position of the shape as a Posn
   */
  Posn getPosn();

  /**
   * Get the angle of the shape.
   *
   * @return the angle of the shape
   */
  double getAngle();

  /**
   * Calls the visitors method on this shape for java swing related operations on Shapes.
   *
   * @param visitor the visitor
   */
  void accept(VisualShapeVisitor visitor);

  /**
   * Calls the visitors method on this shape for text output related operations on Shapes.
   *
   * @param visitor the visitor
   */
  String accept(TextShapeVisitor visitor);

  /**
   * Gets the attribute name for the width attribute of the specific shape.
   *
   * @return the attribute name for the width attribute of the specific shape
   */
  String getAttributeResizeW();

  /**
   * Gets the attribute name for the height attribute of the specific shape.
   *
   * @return the attribute name for the height attribute of the specific shape
   */
  String getAttributeResizeH();

  /**
   * Gets the attribute name for the x-position attribute of the specific shape.
   *
   * @return the attribute name for the x-position attribute of the specific shape
   */
  String getAttributeTranslateX();

  /**
   * Gets the attribute name for the y-position attribute of the specific shape.
   *
   * @return the attribute name for the y-position attribute of the specific shape
   */
  String getAttributeTranslateY();

  /**
   * Gets the width of the object modified for the specific shape for the SVG format.
   *
   * @return the width of the object modified for the specific shape for the SVG format
   */
  String svgW();

  /**
   * Gets the height of the object modified for the specific shape for the SVG format.
   *
   * @return the height of the object modified for the specific shape for the SVG format
   */
  String svgH();

  /**
   * Gets the x value of the shape's position formatted for a rotate SVG animation.
   *
   * @return the x value of the shape's position formatted for a rotate SVG animation
   */
  double getRotateSVGX();

  /**
   * Gets the y value of the shape's position formatted for a rotate SVG animation.
   *
   * @return the y value of the shape's position formatted for a rotate SVG animation
   */
  double getRotateSVGY();

}