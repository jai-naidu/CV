package cs3500.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.model.shape.Shape;

import javax.swing.Timer;

/**
 * A class which visualizes an animation by playing as a movie. The movie is displayed in a pop-up
 * window.
 */
public class VisualView extends JFrame implements MovieView, ActionListener {
  protected final AnimationPanel panel;
  protected int tick;
  protected MovieModel model;
  protected Timer timer;

  /**
   * Initializes a VisualView with the default parameters for a movie.
   */
  public VisualView() {
    this.tick = 0;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Excellence!");
    panel = new AnimationPanel();
    panel.setBackground(Color.WHITE);
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(scrollPane);
  }

  @Override
  public void runAnimation(MovieModel model) {
    makeVisible();
    this.model = model;
    setConstraints();
    model.fitMovieToMax();
    this.timer = new Timer(1000 / model.getSpeed(), this);
    timer.start();
  }

  @Override
  public void setOut(PrintWriter out) {
    return;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    List<Shape> res = new ArrayList<Shape>();
    for (ShapeAnimation sa : model.getShapeAnimations()) {
      res.add(sa.getShapeAtTick(tick));
    }
    panel.setShapes(res);
    this.tick++;
    this.refresh();
  }


  /**
   * Makes this movie visible.
   */
  private void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Sets the size of the panel of this movie.
   *
   * @param w the width of the panel
   * @param h the height of the panel
   */
  private void setCanvas(int w, int h) {
    panel.setPreferredSize(new Dimension(w, h));
  }

  /**
   * Refreshes this canvas by repainting the model on the canvas.
   */
  protected void refresh() {
    this.repaint();
  }

  /**
   * Sets the bounds for the size of the window when first opened and the bounds of the canvas on
   * which the shapes will be drawn.
   */
  private void setConstraints() {
    this.setSize(model.getScrollWidth(), model.getScrollHeight());
    setCanvas(model.getMaxWidth(), model.getMaxHeight());
  }
}

