package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.animator.model.shape.Shape;

/**
 * A class that represents a frame in a movie. A frame holds all the info for what must be displayed
 * at a given point in time in a movie. This class exists for testing purposes.
 */
public class Frame {
  private List<Shape> frame;

  /**
   * Initializes a frame given a list of shapes to insert into the frame.
   *
   * @param s the shapes to insert into the frame
   * @throws IllegalArgumentException if the passed in list is null
   */
  public Frame(List<Shape> s) {
    Objects.requireNonNull(s, "Passed in list cannot be null");
    this.frame = s;
  }

  /**
   * Adds the given shape to this frame.
   *
   * @param s the shape to be added to this frame
   */
  public void addToFrame(Shape s) {
    frame.add(s);
  }

  /**
   * Print the attributes of all the shapes in the frame.
   *
   * @return the attributes of all the shapes in the frame in string form
   * @throws IllegalArgumentException if the given tick is less than 0
   */
  public String printFrame(int tick) {
    StringBuilder res = new StringBuilder();
    res.append("Frame " + (tick + 1) + ":\n");
    for (int i = 0; i < frame.size(); i++) {
      if (i == frame.size() - 1) {
        res.append(frame.get(i).getName() + " " + frame.get(i).shapeInfo());
      } else {
        res.append(frame.get(i).getName() + " " + frame.get(i).shapeInfo() + "\n");
      }
    }
    return res.toString();
  }

  /**
   * Returns the shapes in this frame.
   *
   * @return the shapes in this frame
   */
  public List<Shape> getShapes() {
    List<Shape> res = new ArrayList<Shape>();
    for (Shape s : frame) {
      res.add(s);
    }
    return res;
  }
}
