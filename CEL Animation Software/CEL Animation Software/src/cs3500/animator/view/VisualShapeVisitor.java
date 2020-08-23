package cs3500.animator.view;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;

/**
 * An interface for a Visitor which draws graphics for a visual view of a Movie Model
 * implementation. It draws an appropriate graphic based on the given shape.
 */
public interface VisualShapeVisitor {

  /**
   * Draws an oval shape on the given graphics2d using the given ellipse's information.
   *
   * @param e   the ellipse to be drawn
   */
  void visit(Ellipse e);

  /**
   * Draws a rectangle shape on the given graphics2d using the given rectangle's information.
   *
   * @param e   the rectangle to be drawn
   */
  void visit(Rectangle e);

}
