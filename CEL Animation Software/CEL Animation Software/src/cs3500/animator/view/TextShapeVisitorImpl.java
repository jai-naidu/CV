package cs3500.animator.view;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;

/**
 * A class to represent a Visitor which visits Shapes and formats a string for a textual view of a
 * movie model. It uses the given shape's information to return an appropriate string.
 */
public class TextShapeVisitorImpl implements TextShapeVisitor {

  @Override
  public String visit(Ellipse e) {
    return "shape " + e.getName() + " Ellipse";
  }

  @Override
  public String visit(Rectangle r) {
    return "shape " + r.getName() + " Rectangle";
  }

}
