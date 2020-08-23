package cs3500.animator.view;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;

/**
 * A visitor interface to create text strings for formatting the visited shape. It creates specific
 * strings according to the given shape.
 */
public interface TextShapeVisitor {

  /**
   * Visits an Ellipse shape and constructs a string with the Ellipse's information.
   *
   * @param e the ellipse to be visited
   * @return a formatted string
   */
  String visit(Ellipse e);

  /**
   * Visits an Rectangle shape and constructs a string with the Rectangle's information.
   *
   * @param r the ellipse to be visited
   * @return a formatted string
   */
  String visit(Rectangle r);
}
