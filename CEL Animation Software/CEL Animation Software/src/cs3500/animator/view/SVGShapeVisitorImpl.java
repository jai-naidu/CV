package cs3500.animator.view;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;

/**
 * A class which acts as a function object to format an svg tag. It visits a Shape.
 */
public class SVGShapeVisitorImpl implements TextShapeVisitor {

  @Override
  public String visit(Ellipse e) {
    return String.format("<ellipse cx=\"%s\" cy=\"%s\" rx=\"%s\" "
                    + "ry=\"%s\" fill=\"%s\" "
                    + "visibility=\"visible\" opacity=\"0\">\n%%s</ellipse>",
            e.getX(), e.getY(), e.getWidth() / 2, e.getHeight() / 2,
            e.getRGB().toString());
  }

  @Override
  public String visit(Rectangle r) {
    double xCorner = r.getX();
    double yCorner = r.getY();
    return String.format("<rect x=\"%s\" y=\"%s\" width=\"%s\" "
                    + "height=\"%s\" fill=\"%s\" visibility=\"visible\" "
                    + "opacity=\"0\">\n%%s</rect>",
            xCorner, yCorner, r.getWidth(), r.getHeight(),
            r.getRGB().toString());
  }
}
