package cs3500.animator.model;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.supplementary.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.supplementary.Posn;
import cs3500.animator.model.transformation.ColorChange;
import cs3500.animator.model.transformation.Resize;
import cs3500.animator.model.transformation.Rotate;
import cs3500.animator.model.transformation.Transformation;
import cs3500.animator.model.transformation.Translate;
import cs3500.animator.util.AnimationBuilder;

/**
 * A class that represents a MovieModel. A MovieModel represents a singular animation with several
 * shapes in it. Each shape can have several different motions. All the shapes in this MovieModel
 * are contained to one animation that the MovieModel represents.
 */
public class MovieModelImpl extends AbstractMovieModel {
  protected List<ShapeAnimation> animations;

  /**
   * A constructor that initializes the movie with a blank canvas with default values.
   */
  public MovieModelImpl() {
    super();
    this.animations = new ArrayList<ShapeAnimation>();

  }

  @Override
  public void addTranslateToShape(String name, int start, int end, double x1, double y1, double x2,
                                  double y2) {
    addTransformationToShape(name, new Translate(start, end, new Posn(x1, y1), new Posn(x2, y2)));
  }

  @Override
  public void addResizeToShape(String name, int start, int end, int w1, int h1, int w2,
                               int h2) {
    addTransformationToShape(name, new Resize(start, end, w1, h1, w2, h2));
  }

  @Override
  public void addColorChangeToShape(String name, int start, int end, float r1, float g1, float b1,
                                    float r2, float g2, float b2) {
    addTransformationToShape(name, new ColorChange(start, end, new Color(r1, g1, b1),
            new Color(r2, g2, b2)));
  }

  @Override
  public void addRotateToShape(String name, int start, int end, double a1, double a2) {
    addTransformationToShape(name, new Rotate(start, end, a1, a2));
  }

  @Override
  public void addRectangleShape(String name) {
    alreadyExists(name);
    this.animations.add(new ShapeAnimation(new Rectangle(name)));
  }

  @Override
  public void addRectangleShape(String name, double x, double y, double w,
                                double h, float r, float g, float b, double a) {
    alreadyExists(name);
    this.animations.add(new ShapeAnimation(new Rectangle(name, x, y, w, h, r, g, b, a)));
  }

  @Override
  public void addEllipseShape(String name) {
    alreadyExists(name);
    this.animations.add(new ShapeAnimation(new Ellipse(name)));
  }

  @Override
  public void addEllipseShape(String name, double x, double y, double w,
                              double h, float r, float g, float b, double a) {
    alreadyExists(name);
    this.animations.add(new ShapeAnimation(new Ellipse(name, x, y, w, h, r, g, b, a)));
  }

  @Override
  public void addMotion(String name, int t1, int x1, int y1,
                        int w1, int h1, int r1, int g1, int b1, int a1,
                        int t2, int x2, int y2, int w2, int h2,
                        int r2, int g2, int b2, int a2) {
    addTranslateToShape(name, t1, t2, x1, y1, x2, y2);
    addResizeToShape(name, t1, t2, w1, h1, w2, h2);
    addColorChangeToShape(name, t1, t2, r1, g1, b1, r2, g2, b2);
    addRotateToShape(name, t1, t2, a1, a2);
  }

  @Override
  public void addKeyFrame(String name, int t, double x, double y, double w,
                          double h, float r, float g, float b, double a) {
    if (animations.isEmpty()) {
      throw new IllegalArgumentException("There are no shape animations in this Movie.");
    } else if (t >= 1) {
      for (ShapeAnimation sa : this.animations) {
        if (sa.hasName(name)) {
          sa.insertKeyFrame(t, x, y, w, h, r, g, b, a);
        }
      }
    } else {
      throw new IllegalArgumentException("Given tick is illegal for this animation.");
    }
  }

  @Override
  public void deleteKeyFrame(String name, int t) {
    if (animations.isEmpty()) {
      throw new IllegalArgumentException("There are no keyframes to delete.");
    } else if (t >= 1) {
      for (ShapeAnimation sa : this.animations) {
        if (sa.hasName(name)) {
          sa.deleteKeyFrameSA(t);
        }
      }
    } else {
      throw new IllegalArgumentException("Given tick " + t + " is illegal.");
    }
  }



  @Override
  public List<ShapeAnimation> getShapeAnimations() {
    return new ArrayList<ShapeAnimation>(animations);
  }



  @Override
  public Frame getFrame(int tick) {
    tickCheck(tick);
    Frame res = new Frame(new ArrayList<Shape>());
    for (ShapeAnimation sa : animations) {
      res.addToFrame(sa.getShapeAtTick(tick));
    }
    return res;
  }



  @Override
  public void deleteShape(String name) {
    doesNotExist(name);
    animations.removeIf(sa -> sa.hasName(name));
  }

  @Override
  public void deleteTranslateFromShape(String name, int startTick) {
    doesNotExist(name);
    for (ShapeAnimation s : animations) {
      if (s.hasName(name)) {
        s.deleteTranslate(startTick);
        break;
      }
    }
  }

  @Override
  public void deleteColorChangeFromShape(String name, int startTick) {
    doesNotExist(name);
    for (ShapeAnimation s : animations) {
      if (s.hasName(name)) {
        s.deleteColorChange(startTick);
        break;
      }
    }
  }

  @Override
  public void deleteResizeFromShape(String name, int startTick) {
    doesNotExist(name);
    for (ShapeAnimation s : animations) {
      if (s.hasName(name)) {
        s.deleteResize(startTick);
        break;
      }
    }
  }

  @Override
  public String printFrame(int tick) {
    tickCheck(tick);
    return getFrame(tick).printFrame(tick);
  }

  @Override
  public void addLayer(String layerName) {
    if (layerName == null ) {
      throw new IllegalArgumentException("null string");
    }
    return ;
  }

  @Override
  public void deleteLayer(String layerName) {
    if (layerName == null ) {
      throw new IllegalArgumentException("null string");
    }
    this.animations = new ArrayList<>();
  }

  @Override
  public void moveLayerUp(String layerName) {
    if (layerName == null ) {
      throw new IllegalArgumentException("null string");
    }
    return ;
  }

  @Override
  public void moveLayerDown(String layerName) {
    if (layerName == null ) {
      throw new IllegalArgumentException("null string");
    }
    return ;
  }

  @Override
  public List<String> getLayers() {
    ArrayList<String> res = new ArrayList<>();
    res.add("Layer 1");
    return res;
  }

  @Override
  public List<String> getLayerShapeNames(String layer) {
    if (layer == null ) {
      throw new IllegalArgumentException("null string");
    }
    List<String> res = new ArrayList<String>();
    for (ShapeAnimation sa : animations) {
      res.add(sa.getShape().getName());
    }
    return res;
  }

  @Override
  public void moveShapeToLayer(String shapeName, String layerName) {
    if (layerName == null || shapeName == null) {
      throw new IllegalArgumentException("null string");
    }
    return ;
  }

  /**
   * Adds the given transformations to the shape with the given name in this Movie.
   *
   * @param name           name of the shape the transformation will be added to
   * @param transformation The transformation to be applied to the shape with the given name
   * @throws IllegalArgumentException if there is not a shape with that name
   * @throws IllegalArgumentException if the given transformations cannot be performed on the shape
   * @throws IllegalArgumentException if either of the passed in arguments are null
   */
  private void addTransformationToShape(String name, Transformation transformation) {
    Objects.requireNonNull(name, "Input for name can't be null.");
    Objects.requireNonNull(transformation, "Transformation can't be null.");
    for (ShapeAnimation sa : animations) {
      if (sa.hasName(name)) {
        sa.addTransformation(transformation);
      }
    }
  }

  /**
   * Gets the current length of the movie.
   *
   * @return the current length of the movie
   */
  protected int getMovieLength() {
    int res = 0;
    for (ShapeAnimation sa : animations) {
      res = Math.max(res, sa.getMaxTick());
    }
    return res;
  }

  /**
   * Checks if this movie contains an animation with a shape of the given name.
   *
   * @param name the name of the shape in the search
   * @throws IllegalArgumentException if this name is not a shape in this animation
   */
  private void alreadyExists(String name) {
    if (hasName(name)) {
      throw new IllegalArgumentException("There is already a shape with this"
              + " name.");
    }
  }

  /**
   * Throws an error if there is no shape with the given name.
   *
   * @param name the name of the shape
   */
  private void doesNotExist(String name) {
    if (!hasName(name)) {
      throw new IllegalArgumentException("There is no shape with this name.");
    }
  }

  /**
   * Checks if this movie contains an animation with a shape of the given name.
   *
   * @param name the name of the shape in the search
   * @throws IllegalArgumentException if this name is not a shape in this movie
   */
  private boolean hasName(String name) {
    Objects.requireNonNull(name, "Input for name can't be null.");
    for (ShapeAnimation sa : this.getShapeAnimations()) {
      if (sa.hasName(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the tick is valid.
   *
   * @param t the tick
   * @throws IllegalArgumentException if the tick is less than or equal to 0 or if the tick value is
   *                                  greater than the length of the movie
   */
  private void tickCheck(int t) {
    if (t < 0 || t > this.getRuntime()) {
      throw new IllegalArgumentException("Tick value must be more than 0 and "
              + "less than the length of the movie.");
    }
  }

  /**
   * A builder method that parses the string input from a text file and uses existing methods in the
   * MovieModelImpl class to populate the model with the metadata for the specified animation.
   */
  public static final class Builder implements AnimationBuilder<MovieModel> {
    private MovieModelImpl model;

    /**
     * Return the model at its current state.
     */
    public Builder() {
      this.model = new MovieModelImpl();
    }

    @Override
    public MovieModelImpl build() {
      return model;
    }

    @Override
    public AnimationBuilder<MovieModel> setBounds(int x, int y, int width, int height) {
      model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<MovieModel> declareShape(String name, String type,
                                                     String layer) {
      if (type.equals("rectangle")) {
        model.addRectangleShape(name);
      } else if (type.equals("ellipse")) {
        model.addEllipseShape(name);
      }
      return this;
    }

    @Override
    public AnimationBuilder<MovieModel> addMotion(String name, int t1, int x1, int y1,
                                                  int w1, int h1, int r1, int g1, int b1, int a1,
                                                  int t2, int x2, int y2, int w2, int h2,
                                                  int r2, int g2, int b2, int a2) {
      model.addMotion(name, t1, x1, y1, w1, h1, r1, g1, b1, a1, t2, x2, y2, w2, h2, r2, g2, b2, a2);
      return this;
    }

    @Override
    public AnimationBuilder<MovieModel> addKeyframe(String name, int t, int x, int y, int w,
                                                    int h, int r, int g, int b, int a) {
      model.addKeyFrame(name, t, x, y, w, h, (float) r, (float) g, (float) b, a);
      return this;
    }
  }
}