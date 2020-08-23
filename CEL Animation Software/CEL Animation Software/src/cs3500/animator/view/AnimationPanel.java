package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import cs3500.animator.model.shape.Shape;

/**
 * A class which holds the shapes on the screen of an animation. It draws the shapes stored in a
 * pop-up window.
 */
public class AnimationPanel extends JPanel {

  private List<Shape> shapes;

  /**
   * Initializes this AnimationPanel with an empty list of Shapes.
   */
  AnimationPanel() {
    this.shapes = new ArrayList<>();
  }

  /**
   * Sets the shapes to be drawn as the given list of shapes.
   *
   * @param shapes the shapes to be drawn on this panel
   */
  public void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    VisualShapeVisitor visitor = new VisualShapeVisitorImpl(g2D);
    for (Shape s : shapes) {
      s.accept(visitor);
    }
  }
}
