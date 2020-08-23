package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * A class to represent a visitor which draws a shape on a Graphics2D object. It uses the shape's
 * information to draw.
 */
public class VisualShapeVisitorImpl implements VisualShapeVisitor {

  private final Graphics2D g2D;

  /**
   * Initializes this visitor with the graphics object to be drawn to.
   *
   * @param g2D the graphics object to be drawn to
   */
  public VisualShapeVisitorImpl(Graphics2D g2D) {
    this.g2D = g2D;
  }

  @Override
  public void visit(Ellipse e) {
    AffineTransform tx = new AffineTransform();
    tx.rotate(Math.toRadians(e.getAngle()), (int) e.getX() + (e.getWidth() / 2),
        (int) e.getY() + (e.getHeight() / 2));
    Ellipse2D oval = new Ellipse2D.Double((int) e.getX(),
        (int) e.getY(), (int) e.getWidth(),
        (int) e.getHeight());
    g2D.setPaint(new Color(e.getR() / 255, e.getG() / 255, e.getB() / 255));
    Shape shape = tx.createTransformedShape(oval);
    g2D.fill(shape);
    g2D.draw(shape);

  }

  @Override
  public void visit(Rectangle r) {
    AffineTransform tx = new AffineTransform();
    tx.rotate(Math.toRadians(r.getAngle()), (int) r.getX() + (r.getWidth() / 2),
        (int) r.getY() + (r.getHeight() / 2));
    Rectangle2D rect = new Rectangle2D.Double(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    g2D.setPaint(new Color(r.getR() / 255, r.getG() / 255, r.getB() / 255));
    Shape shape = tx.createTransformedShape(rect);
    g2D.fill(shape);
    g2D.draw(shape);
  }
}
