package cs3500.animator.model;

import java.util.List;

/**
 * A model of an animated movie. Contains all the metadata for creating a given animation. Made for
 * additional methods that we considered not core functionality i.e. for the purposes of adding
 * getter methods and methods for testing.
 */
public interface MovieModel extends MovieModelCore {

  /**
   * Gets a list of all shape animations in this movie.
   *
   * @return a list of all shape animations in this movie
   */
  List<ShapeAnimation> getShapeAnimations();

  /**
   * Get the speed (in ticks per second) for the movie.
   */
  int getSpeed();

  /**
   * Get the width of the canvas (not the window).
   *
   * @return the width of the canvas
   */
  int getMaxWidth();


  /**
   * Get the height of the canvas (not the window).
   *
   * @return the height of the canvas
   */
  int getMaxHeight();

  /**
   * Gets the width of the scroll pane.
   *
   * @return the width of the scroll pane
   */
  int getScrollWidth();

  /**
   * Gets the width of the scroll pane.
   *
   * @return the width of the scroll pane
   */
  int getScrollHeight();

  /**
   * Gets the movie length with the current set of ShapeAnimations. For testing.
   *
   * @return the movie length
   */
  int getRuntime();

  /**
   * Deletes the shape with the given name and its animations. For testing.
   *
   * @param name the name of the shape to be deleted
   * @throws IllegalArgumentException if the passed in string is null or if the shape with the given
   *                                  name doesn't exists
   */
  void deleteShape(String name);

  /**
   * Deletes the translation from the shape with the given name at the given starting time. For
   * testing.
   *
   * @param name      the name of the shape to be deleted
   * @param startTick the time of the translation be deleted
   * @throws IllegalArgumentException if the passed in string is null
   * @throws IllegalArgumentException if the passed in shape does not exist
   * @throws IllegalArgumentException if the passed in shape does not have a translation at the
   *                                  given time
   */
  void deleteTranslateFromShape(String name, int startTick);

  /**
   * Deletes the color change from the shape with the given name at the given starting time. For
   * testing.
   *
   * @param name      the name of the shape to be deleted
   * @param startTick the time of the translation be deleted
   * @throws IllegalArgumentException if the passed in string is null
   * @throws IllegalArgumentException if the passed in shape does not exist
   * @throws IllegalArgumentException if the passed in shape does not have a color change at the
   *                                  given time
   */
  void deleteColorChangeFromShape(String name, int startTick);

  /**
   * Deletes the color transformation from the shape with the given name at the given starting time.
   * For testing.
   *
   * @param name      the name of the shape to be deleted
   * @param startTick the time of the translation be deleted
   * @throws IllegalArgumentException if the passed in string is null
   * @throws IllegalArgumentException if the passed in shape does not exist
   * @throws IllegalArgumentException if the passed in shape does not have a resize at the given
   *                                  time
   */
  void deleteResizeFromShape(String name, int startTick);

  /**
   * Gets the frame at the given tick containing all pictures in the movie at that frame. For
   * testing.
   *
   * @param tick the tick of the movie
   * @return the frame at the given tick containing all pictures in the movie
   * @throws IllegalArgumentException if the passed in tick is less tha 0
   */
  Frame getFrame(int tick);

  /**
   * Gets all the frames in a movie containing all ShapeAnimations in the movie at every frame. For
   * testing.
   *
   * @return a list of all frames in the movie
   */
  List<Frame> getMovieFrames();

  /**
   * Print the attributes of all the shapes in the frame. For testing.
   *
   * @return the attributes of all the shapes in the frame in string form
   * @throws IllegalArgumentException if the tick is less than 0
   */
  String printFrame(int tick);

  /**
   * Prints each frame in a movie in string form. For testing.
   *
   * @return each frame in a movie in string form
   */
  String printMovieFrames();

  /**
   * Fits the movie length to the max tick of all current transformations. For testing.
   */
  void fitMovieToMax();

  /**
   * Adds an empty layer with the given name to this movie.
   *
   * @param layerName the name of the layer to be added
   */
  void addLayer(String layerName);

  /**
   * Deletes the layer with the given name from this movie.
   *
   * @param layerName the name of the layer to be deleted
   */
  void deleteLayer(String layerName);

  /**
   * Moves the a layer with the given name on top of the layer it is currently below.
   * The topmost layer has the largest index.
   *
   * @param layerName the name of the layer to be moved
   * @throws IllegalArgumentException if the given layer name is null
   */
  void moveLayerUp(String layerName);

  /**
   * Moves the layer with the given name on below of the layer it is currently on top of.
   * The topmost layer has the largest index.
   *
   * @param layerName the name of the layer to be moved
   * @throws IllegalArgumentException if the given layer name is null
   */
  void moveLayerDown(String layerName);

  /**
   * Gets a copy of the layers in this movie model.
   *
   * @return a copy of the layers in this movie model
   */
  List<String> getLayers();

  /**
   * Get the names of all the shapes in this layer.
   *
   * @param layer the layer
   * @return the names of all the shapes in this layer
   * @throws IllegalArgumentException if the given layer name is null
   */
  List<String> getLayerShapeNames(String layer);

  /**
   * Move the specified shape to the specified layer.
   *
   * @param shapeName the shape
   * @param layerName the layer
   * @throws IllegalArgumentException if the given shape or layer name is null
   */
  void moveShapeToLayer(String shapeName, String layerName);
}