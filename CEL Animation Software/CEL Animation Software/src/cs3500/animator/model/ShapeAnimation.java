package cs3500.animator.model;

import cs3500.animator.model.transformation.Rotate;
import cs3500.animator.view.TextShapeVisitor;
import cs3500.animator.view.TextShapeVisitorImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import cs3500.animator.view.SVGShapeVisitorImpl;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.supplementary.Color;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.model.transformation.ColorChange;
import cs3500.animator.model.transformation.Resize;
import cs3500.animator.model.transformation.Transformation;
import cs3500.animator.model.transformation.Translate;

/**
 * A class that represents a shape and all of the transformations that occur on it. Each
 * transformation starts and ends at a tick and the are used to retrieve the state of a shape at any
 * given tick.
 */
public class ShapeAnimation {
  private Shape shape;
  private List<Transformation> transformations;

  /**
   * A constructor to initialize a shape animation given a shape.
   *
   * @param s a shape
   */
  public ShapeAnimation(Shape s) {
    if (s == null) {
      throw new IllegalArgumentException("Passed in shape can't be null");
    }
    this.shape = s;
    this.transformations = new ArrayList<Transformation>();
  }

  /**
   * A constructor to initialize a shape animation given a shape and a list of transformations. Used
   * for copying.
   *
   * @param s a shape
   * @param t the list of transformations
   */
  private ShapeAnimation(Shape s, List t) {
    this.shape = s;
    this.transformations = t;
  }

  /**
   * Constructor to copy this shape animation.
   *
   * @param sa the shape animation to copy
   */
  private ShapeAnimation(ShapeAnimation sa) {
    this.shape = sa.shape;
    this.transformations = new ArrayList<Transformation>(sa.transformations);
  }

  /**
   * Gets a copy of the given shape animation.
   *
   * @return a copy of the given shape animation
   */
  public ShapeAnimation copy() {
    return new ShapeAnimation(this);
  }

  /**
   * Gets the instantaneous shape at the given tick.
   *
   * @param tick the time at which the state of the shape is retrieved
   * @return a copy of this shape at the given time
   */
  public Shape getShapeAtTick(int tick) {
    Shape sAtTick = shape.copy();
    for (Transformation t : transformations) {
      if (tick >= t.getStartTick() && tick <= t.getEndTick()) {
        t.apply(sAtTick, tick);
      } else if (tick > t.getEndTick()) {
        t.apply(sAtTick, t.getEndTick());
      }
    }
    return sAtTick;
  }

  /**
   * Formats this shapeAnimation's transformations into before/after statements in a table.
   *
   * @return this shape's animation as a series of before/after statements
   */
  public String toTable() {
    String s = "";
    TextShapeVisitor visitor = new TextShapeVisitorImpl();
    List<Transformation> copyList = new ArrayList<Transformation>(transformations);
    ShapeAnimation copy = new ShapeAnimation(shape.copy(), copyList);
    List<Integer> ticks = getTickList();
    StringBuilder temp = new StringBuilder();
    temp.append(this.shape.accept(visitor)).append("\nmotion ").append(shape.getName()).append(" ");
    for (int i = 0; i < ticks.size(); i++) {
      int time = ticks.get(i);
      s = (copy.getShapeAtTick(time).shapeInfo());
      if (i == 0) {
        temp.append(String.format("%s %s", time, s));
      } else if (i == ticks.size() - 1) {
        temp.append(String.format("%-5s%s %s", "", time, s));
      } else {
        temp.append(String.format("%-5s%s %s\n%s %s %s %s", "",
                time, s, "motion", shape.getName(), time, s));
      }
    }
    if (ticks.size() == 1) {
      return temp.append("     ").append(ticks.get(0)).append(" ").append(s).toString();
    }
    return temp.toString();
  }

  /**
   * Get a list of SVG formatted animations for this shape.
   *
   * @return a list of SVG formatted animations for this shape
   */
  public String toAnimationTag(int speed) {
    ShapeAnimation sa = copy();
    Shape s = sa.getShapeAtTick(getMinTick());
    String shapeTag = s.accept(new SVGShapeVisitorImpl());
    StringBuilder animeTags = new StringBuilder();
    animeTags.append("\n");
    animeTags.append(makeOpacityTag(speed)).append("\n");
    for (Transformation t : transformations) {

      animeTags.append(t.toTag(getShapeAtTick(t.getStartTick()), speed)).append("\n");
    }
    return String.format(shapeTag, animeTags.toString());
  }

  /**
   * Add the opacity tag for the shape animations to make shapes visible at the right time for SVG
   * formatted files. Sets the speed to 1 if the speed is 0.
   *
   * @param speed the speed of the animation
   * @return the opacity tag for a given shape
   */
  private String makeOpacityTag(int speed) {

    int min = getMinTick() * 1000 / speed;
    int dur = (getMaxTick() * 1000 / speed) - min;
    return String.format("<animate attributeType=\"xml\" begin=\"%sms\" "
            + "dur=\"%sms\" attributeName=\"opacity\" "
            + "from=\"1\" to=\"1\"/>", min, dur);
  }

  /**
   * Adds the given transformation to the list of transformations for this shape if it exists in the
   * shape's existence.
   *
   * @param t the transformation to be applied to this shape
   * @throws IllegalArgumentException if the transformation conflicts with any existing
   *                                  transformations
   */
  public void addTransformation(Transformation t) {
    checkNoConflicts(t);
    rightAfter(t);
    transformations.add(t);
  }

  /**
   * Adds an individual keyframe to this shape animation.
   *
   * @param t The time for this keyframe
   * @param x The x-position of the shape
   * @param y The y-position of the shape
   * @param w The width of the shape
   * @param h The height of the shape
   * @param r The red color-value of the shape
   * @param g The green color-value of the shape
   * @param b The blue color-value of the shape
   * @param a The angle of the shape
   */
  public void insertKeyFrame(int t, double x, double y, double w,
                             double h, float r, float g, float b, double a) {
    if (transformations.isEmpty()) {
      addTransformation(new Translate(t, t, new Posn(x, y), new Posn(x, y)));
      addTransformation(new ColorChange(t, t, new Color(r, g, b), new Color(r, g, b)));
      addTransformation(new Resize(t, t, w, h, w, h));
      addTransformation(new Rotate(t, t, a, a));
    } else {
      if (t >= getMinTick() && t <= getMaxTick()) {
        for (int i = 0; i < transformations.size(); i++) {
          if (t == transformations.get(i).getStartTick()
                  || t == transformations.get(i).getEndTick()) {
            transformations.get(i).editKeyFrame(t, x, y, w, h, r, g, b, a);
          } else if (t > transformations.get(i).getStartTick()
                  && t < transformations.get(i).getEndTick()) {
            Transformation copy = transformations.get(i)
                    .splitTransformation(t, x, y, w, h, r, g, b, a);
            transformations.add(i + 1, copy);
            i++;
          }
        }
      } else if (t < getMinTick()) {
        addToStart(t, getMinTick(), x, y, w, h, r, g, b, a, getShapeAtTick(getMinTick()));
      } else if (t > getMaxTick()) {
        addToEnd(getMaxTick(), t, x, y, w, h, r, g, b, a, getShapeAtTick(getMaxTick()));
      }
    }
  }

  /**
   * Deletes an individual keyframe from this shape animation.
   *
   * @param t The time for this keyframe
   * @throws IllegalArgumentException if there are no transformations in this shape
   * @throws IllegalArgumentException if the specified key frame doesn't exist
   */
  public void deleteKeyFrameSA(int t) {
    Shape s = null;
    int end = -1;
    if (transformations.isEmpty()) {
      throw new IllegalArgumentException("This shape animation has no keyframes to delete.");
    } else {
      checkValidKeyFrame(t);
      for (int i = 0; i < transformations.size(); i++) {
        if (t == transformations.get(i).getStartTick()) {
          Transformation right = nextTransformSameType(transformations.get(i));
          if (right == null) {
            end = transformations.get(i).getEndTick();
            s = (s == null) ? getShapeAtTick(end) : s;
          }
          transformations.remove(i);
          i--;
        } else if (t == transformations.get(i).getEndTick()) {
          Transformation right = nextTransformSameType(transformations.get(i));
          Transformation left = prevTransformSameType(transformations.get(i));
          if (right == null && left == null) {
            end = transformations.get(i).getStartTick();
            s = (s == null) ? getShapeAtTick(end) : s;
            transformations.remove(i);
          } else if (left != null && right == null) {
            transformations.remove(i);
          } else {
            transformations.get(i).extendToRight(right);
            transformations.remove(right);
          }
          i--;
        }
      }
    }
    if (s != null && end > 0) {
      addToStart(end, end, s.getX(), s.getY(), s.getWidth(), s.getHeight(), s.getR(), s.getG(),
              s.getB(), s.getAngle(), s);
    }
  }

  /**
   * Gets a list of all the start ticks of this shape's animations.
   *
   * @return list of all the start ticks of this shape's animations.
   */
  public List<Integer> getStartTicks() {
    List<Integer> res = new ArrayList<>();
    for (Transformation t : transformations) {
      if (!res.contains(t.getStartTick())) {
        res.add(t.getStartTick());
      }
    }
    return res;
  }

  /**
   * Check to see if the specified key frame exists.
   *
   * @param t The time for this keyframe
   * @throws IllegalArgumentException if the specified key frame doesn't exist
   */
  private void checkValidKeyFrame(int t) {
    if (!getTickList().contains(t)) {
      throw new IllegalArgumentException("The specified key frame doesn't exists.");
    }
  }

  /**
   * Gets the next transformation of the same type in this transformation.
   *
   * @param t the reference transformation
   * @return the next transformation, or null if it doesn't exist
   */
  private Transformation prevTransformSameType(Transformation t) {
    for (Transformation curr : transformations) {
      if (t.sameTransformation(curr) && curr.getEndTick() == t.getStartTick()) {
        return curr;
      }
    }
    return null;
  }

  /**
   * Gets the next transformation of the same type in this transformation.
   *
   * @param t the reference transformation
   * @return the next transformation, or null if it doesn't exist
   */
  private Transformation nextTransformSameType(Transformation t) {
    for (Transformation curr : transformations) {
      if (t.sameTransformation(curr) && t.getEndTick() == curr.getStartTick()) {
        return curr;
      }
    }
    return null;
  }

  /**
   * A helper to add a key frame before the original first tick of this shape.
   *
   * @param st the new start tick
   * @param et the end tick
   * @param x  the x position
   * @param y  the y position
   * @param w  the width
   * @param h  the height
   * @param r  the red value
   * @param g  the green value
   * @param b  the blue value
   * @param a  the angle
   * @param s  the shape that represents the original starting state
   */
  private void addToStart(int st, int et, double x, double y, double w, double h, float r, float g,
                          float b, double a, Shape s) {
    transformations.add(0, (new Translate(st, et, new Posn(x, y), new Posn(s.getX(), s.getY()))));
    transformations.add(0, (new ColorChange(st, et, new Color(r, g, b),
            new Color(s.getR(), s.getG(), s.getB()))));
    transformations.add(0, (new Resize(st, et, w, h, (int) s.getWidth(),
            (int) s.getHeight())));
    transformations.add(0, (new Rotate(st, et, a, (int) s.getAngle())));
  }

  /**
   * A helper to add a key frame after the original last tick of this shape.
   *
   * @param st the start tick
   * @param et the new end tick
   * @param x  the x position
   * @param y  the y position
   * @param w  the width
   * @param h  the height
   * @param r  the red value
   * @param g  the green value
   * @param b  the blue value
   * @param a  the angle
   * @param s  the shape that represents the original ending state
   */
  private void addToEnd(int st, int et, double x, double y, double w, double h, float r, float g,
                        float b, double a, Shape s) {
    transformations.add((new Translate(st, et, new Posn(s.getX(), s.getY()), new Posn(x, y))));
    transformations.add((new ColorChange(st, et, new Color(s.getR(), s.getG(), s.getB()),
            new Color(r, g, b))));
    transformations.add((new Resize(st, et, (int) s.getWidth(), (int) s.getHeight(), w, h)));
    transformations.add((new Rotate(st, et, (int) s.getAngle(), a)));
  }

  /**
   * Ensures that the transformation being added is right after the last transformation of the same
   * type.
   *
   * @param t the transformation to add
   */
  private void rightAfter(Transformation t) {
    for (int i = transformations.size() - 1; i >= 0; i--) {
      if (transformations.get(i).sameTransformation(t)) {
        if (t.getStartTick() == transformations.get(i).getEndTick()) {
          break;
        }
        throw new IllegalArgumentException("Passed in transformation's "
                + "start tick does not align with the end tick of the last "
                + "transformation of the same type.");
      }
    }
  }

  /**
   * When adding a transformation to a shape, check to see if it conflicts with any of the other
   * existing transformations.
   *
   * @param t the transformation that is attempting to be added
   * @throws IllegalArgumentException if the given transformation is invalid for this shape's
   *                                  transformations
   */
  public void checkNoConflicts(Transformation t) {
    checkOverlap(t);
    if (getTransformationType(t) == -1) {
      return;
    }
    int index = getTransformationType(t);
    Transformation left = transformations.get(index);
    for (Transformation curr : transformations) {
      if (curr.sameTransformation(t)) {
        if (curr.getEndTick() <= t.getStartTick()) {
          left = decideClosestLeft(curr, left, t.getStartTick());
        }
      }
    }
    if (left.isConflicting(t)) {
      throw new IllegalArgumentException("The passed in transformation "
              + "conflicts with an existing transformation.");
    }
  }


  /**
   * Deletes the translate transformation at the given start time.
   *
   * @param startTick the time of the translation to be deleted.
   * @throws IllegalArgumentException if there is no translation to delete at the given tick
   */
  public void deleteTranslate(int startTick) {
    deleteTransformation(startTick,
            new Translate(startTick, startTick + 1, new Posn(0, 0), new Posn(0,
                    0)));
  }

  /**
   * Deletes the resize transformation at the given start time.
   *
   * @param startTick the time of the resize transformation
   * @throws IllegalArgumentException if there is no resize to delete at the given tick
   */
  public void deleteResize(int startTick) {
    deleteTransformation(startTick,
            new Resize(startTick, startTick + 1, 20, 20, 20, 20));
  }

  /**
   * Deletes the color change transformation at the given start time.
   *
   * @param startTick the time of the resize transformation
   * @throws IllegalArgumentException if there is no translation to delete at the given tick
   */
  public void deleteColorChange(int startTick) {
    deleteTransformation(startTick,
            new ColorChange(startTick, startTick + 1, new Color(20, 20, 20),
                    new Color(20, 20, 20)));
  }

  /**
   * Deletes the color change transformation at the given start time.
   *
   * @param startTick the time of the resize transformation
   * @throws IllegalArgumentException if there is no translation to delete at the given tick
   */
  public void deleteRotate(int startTick) {
    deleteTransformation(startTick,
            new Rotate(startTick, startTick + 1, 10, 10));
  }

  /**
   * Get the minimum tick of all the transformations of this shape.
   *
   * @return the minimum tick of all the transformations of this shape
   */
  private int getMinTick() {
    return this.transformations.get(0).getStartTick();
  }

  /**
   * Gets the transformation closest to the left of the given tick.
   *
   * @param newLeft  the potential new closest left transformation
   * @param currLeft the current transformation closest to the left
   * @param leftOf   the tick to be to the left of
   * @return the transformation closest to the left of the given tick
   */
  private static Transformation decideClosestLeft(Transformation newLeft,
                                                  Transformation currLeft,
                                                  int leftOf) {
    if (currLeft.getEndTick() > leftOf) {
      if (newLeft.getEndTick() <= currLeft.getEndTick()) {
        return newLeft;
      } else {
        return currLeft;
      }
    }
    if (newLeft.getEndTick() <= leftOf
            && newLeft.getEndTick() > currLeft.getEndTick()) {
      return newLeft;
    }
    return currLeft;
  }

  /**
   * Checks if this transformation overlaps with the given transformation.
   *
   * @param t the given transformation
   * @throws IllegalArgumentException if the transformations overlap
   */
  private void checkOverlap(Transformation t) {
    for (Transformation curr : transformations) {
      if (curr.sameTransformation(t) && curr.conflictingTick(t)) {
        throw new IllegalArgumentException("Passed in transformation "
                + "overlaps with existing transformation.");
      }
    }
  }

  /**
   * The index of the first occurrence of a transformation of the same type.
   *
   * @param t the given transformations
   * @return the index of the transformation or -1 if it doesn't exist
   */
  private int getTransformationType(Transformation t) {
    for (int i = 0; i < transformations.size(); i++) {
      if (transformations.get(i).sameTransformation(t)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns a sorted list of all the tick values at which a transformation on a shape starts or
   * ends.
   *
   * @return list of all the tick values at which a transformation occurs
   */
  private List<Integer> getTickList() {
    List<Integer> res = new ArrayList<Integer>();
    HashSet<Integer> added = new HashSet<Integer>();
    for (Transformation t : transformations) {
      if (!added.contains(t.getStartTick())) {
        res.add(t.getStartTick());
        added.add(t.getStartTick());
      }
      if (!added.contains(t.getEndTick())) {
        res.add(t.getEndTick());
        added.add(t.getEndTick());
      }
    }
    Collections.sort(res);
    return res;
  }

  /**
   * Gets the greatest tick value in the given shape animation; the last tick of this animation.
   *
   * @return the greatest tick value in the given shape animation
   */
  public int getMaxTick() {
    int max = 0;
    for (Transformation t : transformations) {
      max = Math.max(max, t.getEndTick());
    }
    return max;
  }

  /**
   * Checks if this Animation's shape has the same name as the given name.
   *
   * @param name the name of the shape to be compared to
   * @return whether this Animation has the given shape name
   */
  public boolean hasName(String name) {
    return this.shape.getName().equals(name);
  }

  /**
   * Get the shape in this shape animation.
   *
   * @return the shape in this shape animation
   */
  public Shape getShape() {
    return this.shape;
  }

  /**
   * Deletes a transformation that takes place at the given time of the type of the given
   * transformation object.
   *
   * @param startTick the start time on the animation
   * @param tr        a dummy transformation object
   * @throws IllegalArgumentException if there is not a transformation at the given time
   */
  private void deleteTransformation(int startTick, Transformation tr) {
    for (int i = 0; i < transformations.size(); i++) {
      if (transformations.get(i).sameTransformation(tr)
              && transformations.get(i).getStartTick() == startTick) {
        transformations.remove(i);
        return;
      }
    }
    throw new IllegalArgumentException("No transformation starting at this tick exists.");
  }
}