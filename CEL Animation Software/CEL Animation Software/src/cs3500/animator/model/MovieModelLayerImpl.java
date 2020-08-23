package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;

/**
 * An implementation of a model of an animated movie. Instead of having one list of all animations,
 * this model has layers which contains shapes of the same level of priority.
 */
public class MovieModelLayerImpl extends AbstractMovieModel {

  private List<Layer> layers;

  /**
   * Initializes a layered movie model.
   */
  public MovieModelLayerImpl() {
    super();
    this.layers = new ArrayList<Layer>();
    this.layers.add(new Layer("Layer 1"));
  }

  @Override
  public List<ShapeAnimation> getShapeAnimations() {
    List<ShapeAnimation> res = new ArrayList<ShapeAnimation>();
    for (Layer l : layers) {
      for (ShapeAnimation sa : l.getShapeAnimations()) {
        res.add(sa);
      }
    }
    return res;
  }


  @Override
  public void deleteShape(String name) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.deleteShape(name);
      }
    }
  }

  @Override
  public void deleteTranslateFromShape(String name, int startTick) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.deleteTranslateFromShape(name, startTick);
      }
    }
  }

  @Override
  public void deleteColorChangeFromShape(String name, int startTick) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.deleteColorChangeFromShape(name, startTick);
      }
    }
  }

  @Override
  public void deleteResizeFromShape(String name, int startTick) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.deleteResizeFromShape(name, startTick);
      }
    }
  }

  @Override
  public Frame getFrame(int tick) {
    return null;
  }

  @Override
  public List<Frame> getMovieFrames() {
    return null;
  }

  @Override
  public String printFrame(int tick) {
    return null;
  }

  @Override
  public String printMovieFrames() {
    return null;
  }

  @Override
  protected int getMovieLength() {
    int len = 0;
    for (Layer l : layers) {
      l.fitMovieToMax();
      len = Math.max(len, l.getRuntime());
    }
    return len;
  }

  @Override
  public void moveShapeToLayer(String shapeName, String layerName) {
    nullStringCheck(shapeName);
    nullStringCheck(layerName);
    int layerInd = addLayerIfNew(layerName);
    if (layerInd == 0) {
      return;
    }
    for (int i = 0; i < layers.get(0).getShapeAnimations().size(); i++) {
      if (layers.get(0).getShapeAnimations().get(i).hasName(shapeName)) {
        ShapeAnimation sa = layers.get(0).getShapes().get(i);
        layers.get(layerInd).getShapes().add(sa);
        layers.get(0).getShapes().remove(i);
      }
    }
  }

  @Override
  public void addTranslateToShape(String name, int start, int end,
                                  double x1, double y1, double x2, double y2) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.addTranslateToShape(name, start, end, x1, y1, x2, y2);
        break;
      }
    }
  }

  @Override
  public void addResizeToShape(String name, int start, int end, int w1, int h1, int w2, int h2) {
    for (Layer l : layers) {
      nullStringCheck(name);
      if (l.hasShape(name)) {
        l.addResizeToShape(name, start, end, w1, h1, w2, h2);
        break;
      }
    }
  }

  @Override
  public void addColorChangeToShape(String name, int start, int end,
                                    float r1, float g1, float b1, float r2, float g2, float b2) {
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.addColorChangeToShape(name, start, end, r1, g1, b1, r2, g2, b2);
        break;
      }
    }
  }

  @Override
  public void addRotateToShape(String name, int start, int end, double a1, double a2) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.addRotateToShape(name, start, end, a1, a2);
        break;
      }
    }
  }

  @Override
  public void addEllipseShape(String name, double x, double y, double w, double h,
                              float r, float g, float b, double a) {
    nullStringCheck(name);
    existingShapeCheck(name);
    this.layers.get(0).addEllipseShape(name, x, y, w, h, r, g, b, a);
  }

  @Override
  public void addEllipseShape(String name) {
    nullStringCheck(name);
    existingShapeCheck(name);
    this.layers.get(0).addEllipseShape(name);

  }

  @Override
  public void addRectangleShape(String name, double x, double y, double w, double h,
                                float r, float g, float b, double a) {
    nullStringCheck(name);
    existingShapeCheck(name);
    this.layers.get(0).addRectangleShape(name, x, y, w, h, r, g, b, a);
  }

  @Override
  public void addRectangleShape(String name) {
    nullStringCheck(name);
    existingShapeCheck(name);
    for (ShapeAnimation sa : getShapeAnimations()) {
      if (sa.hasName(name)) {
        throw new IllegalArgumentException("A shape with this name already exists.");
      }
    }
    this.layers.get(0).addRectangleShape(name);
  }

  @Override
  public void addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                        int r1, int g1, int b1, int a1,
                        int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2, int a2) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.addMotion(name, t1, x1, y1, w1, h1, r1, g1, b1, a1, t2, x2, y2, w2, h2, r2, g2, b2, a2);
        break;
      }
    }
  }

  @Override
  public void addKeyFrame(String name, int t, double x, double y, double w, double h,
                          float r, float g, float b, double a) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.addKeyFrame(name, t, x, y, w, h, r, g, b, a);
        break;
      }
    }
  }

  @Override
  public void deleteKeyFrame(String name, int t) {
    nullStringCheck(name);
    for (Layer l : layers) {
      if (l.hasShape(name)) {
        l.deleteKeyFrame(name, t);
        break;
      }
    }
  }

  @Override
  public void addLayer(String layerName) {
    nullStringCheck(layerName);
    if (getLayers().contains(layerName)) {
      throw new IllegalArgumentException("A layer already exists with this name.");
    }
    this.layers.add(new Layer(layerName));
  }

  @Override
  public void deleteLayer(String layerName) {
    nullStringCheck(layerName);
    this.layers.removeIf(layer -> layer.hasLayerName(layerName));
  }

  @Override
  public void moveLayerUp(String layerName) {
    moveLayer(layerName, true);
  }

  @Override
  public void moveLayerDown(String layerName) {
    moveLayer(layerName, false);
  }

  /**
   * Checks if a shape with this name already exists.
   *
   * @param name the name to be checked
   */
  private void existingShapeCheck(String name) {
    for (ShapeAnimation sa : getShapeAnimations()) {
      if (sa.hasName(name)) {
        throw new IllegalArgumentException("A shape with this name already exists.");
      }
    }
  }

  /**
   * Checks if the given string is null.
   *
   * @param str the string to be checked
   * @throws IllegalArgumentException if the given string is null
   */
  private void nullStringCheck(String str) {
    if (str == null) {
      throw new IllegalArgumentException("Cannot input null.");
    }
  }

  /**
   * Adds a layer with the given name if it does not yet exist. Returns the index of the layer with
   * the given name.
   *
   * @param layerName the layer to be found or added
   * @return the index of the layer with that name
   */
  private int addLayerIfNew(String layerName) {
    for (Layer l : layers) {
      if (l.getLayerName().equals(layerName)) {
        return layers.indexOf(l);
      }
    }
    layers.add(new Layer(layerName));
    return layers.size() - 1;
  }

  /**
   * Moves a the layer with a given name in a given direction.
   *
   * @param layerName the layer to be moved
   * @param isUp      whether the layer should be moved up
   * @throws IllegalArgumentException if the given layer is null
   * @throws IllegalArgumentException if the layer to be moved is not in this model
   */
  private void moveLayer(String layerName, boolean isUp) {
    nullStringCheck(layerName);
    if (!getLayers().contains(layerName)) {
      throw new IllegalArgumentException("Cannot move a layer that does not exist");
    }
    int edge;
    int direction;
    if (isUp) {
      edge = layers.size() - 1;
      direction = 1;
    } else {
      edge = 0;
      direction = -1;
    }

    Layer toBeMoved = null;
    int index = -1;
    if (layers.size() <= 1) {
      return;
    }
    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).hasLayerName(layerName)) {
        if (i == edge) {
          return;
        }
        toBeMoved = layers.get(i);
        index = i;
      }
    }

    Layer prev = layers.get(index + direction);
    layers.set(index + direction, toBeMoved);
    layers.set(index, prev);
  }

  @Override
  public List<String> getLayers() {
    List<String> res = new ArrayList<>();
    for (Layer l : layers) {
      res.add(l.getLayerName());
    }
    return res;
  }

  @Override
  public List<String> getLayerShapeNames(String layer) {
    for (Layer l : layers) {
      if (l.getLayerName().equals(layer)) {
        return l.getShapeNames();
      }
    }
    return new ArrayList<String>();
  }

  /**
   * A class to represent a layer in an animated movie. A layer contains shape animations with the
   * same level of priority.
   */
  private class Layer extends MovieModelImpl {

    private String name;

    /**
     * Initialized an empty layer with a name.
     *
     * @param name a name for this layer
     */
    public Layer(String name) {
      this.name = name;
    }

    public List<ShapeAnimation> getShapes() {
      return this.animations;
    }

    public String getLayerName() {
      return this.name;
    }

    public boolean hasLayerName(String name) {
      nullStringCheck(name);
      return this.name.equals(name);
    }

    public boolean hasShape(String shapeName) {
      nullStringCheck(shapeName);
      for (ShapeAnimation sa : animations) {
        if (sa.hasName(shapeName)) {
          return true;
        }
      }
      return false;
    }

    public List<String> getShapeNames() {
      List<String> res = new ArrayList<String>();
      for (ShapeAnimation sa : animations) {
        res.add(sa.getShape().getName());
      }
      return res;
    }
  }

  /**
   * A builder method that parses the string input from a text file and uses existing methods in the
   * MovieModelImpl class to populate the model with the metadata for the specified animation.
   */
  public static final class Builder implements AnimationBuilder<MovieModel> {

    private MovieModelLayerImpl model;

    /**
     * Return the model at its current state.
     */
    public Builder() {
      this.model = new MovieModelLayerImpl();
    }

    @Override
    public MovieModelLayerImpl build() {
      return model;
    }

    @Override
    public AnimationBuilder<MovieModel> setBounds(int x, int y, int width, int height) {
      model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<MovieModel> declareShape(String name, String type, String layer) {
      if (type.equals("rectangle")) {
        model.addRectangleShape(name);
      } else if (type.equals("ellipse")) {
        model.addEllipseShape(name);
      }
      model.moveShapeToLayer(name, layer);
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
