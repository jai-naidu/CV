package cs3500.animator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A helper to read animation data and construct an animation from it.
 */
public class AnimationReader {
  /**
   * A factory for producing new animations, given a source of shapes and a
   * builder for constructing animations.
   *
   * <p>
   * The input file format consists of two types of lines:
   * <ul>
   * <li>Shape lines: the keyword "shape" followed by two identifiers (i.e.
   * alphabetic strings with no spaces), giving the unique name of the shape,
   * and the type of shape it is.</li>
   * <li>Motion lines: the keyword "motion" followed by an identifier giving the name
   * of the shape to move, and 16 integers giving the initial and final conditions of the motion:
   * eight numbers giving the time, the x and y coordinates, the width and height,
   * and the red, green and blue color values at the start of the motion; followed by 
   * eight numbers for the end of the motion.  See {@link AnimationBuilder#addMotion}</li>
   * </ul>
   * </p>
   *
   * @param readable The source of data for the animation
   * @param builder  A builder for helping to construct a new animation
   * @param <Doc>    The main model interface type describing animations
   * @return
   */
  public static <Doc> Doc parseFile(Readable readable, AnimationBuilder<Doc> builder) {
    Objects.requireNonNull(readable, "Must have non-null readable source");
    Objects.requireNonNull(builder, "Must provide a non-null AnimationBuilder");
    Scanner s = new Scanner(readable);
    // Split at whitespace, and ignore # comment lines
    s.useDelimiter(Pattern.compile("(\\p{Space}+|#.*)+")); 
    while (s.hasNext()) {
      String word = s.next();
      switch (word) {
        case "canvas":
          readCanvas(s, builder);
          break;
        case "shape":
          readShape(s, builder);
          break;
        case "motion":
          readMotion(s, builder);
          break;
        default:
          throw new IllegalStateException("Unexpected keyword: " + word + s.nextLine());
      }
    }
    return builder.build();
  }

  private static <Doc> void readCanvas(Scanner s, AnimationBuilder<Doc> builder) {
    int[] vals = new int[4];
    String[] fieldNames = {"left", "top", "width", "height"};
    for (int i = 0; i < 4; i++) {
      vals[i] = getInt(s, "Canvas", fieldNames[i]);
    }
    builder.setBounds(vals[0], vals[1], vals[2], vals[3]);
  }

  private static <Doc> void readShape(Scanner s, AnimationBuilder<Doc> builder) {
    String name;
    String type;
    String layer;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Shape: Expected a name, but no more input available");
    }
    if (s.hasNext()) {
      type = s.next();
    } else {
      throw new IllegalStateException("Shape: Expected a type, but no more input available");
    }
    if (s.hasNext("shape") || s.hasNext("motion")) {
      layer = "Layer 1";
    } else if (s.hasNext()) {
      layer = s.next();
    } else {
      layer = "Layer 1";
    }
    builder.declareShape(name, type, layer);
  }

  private static <Doc> void readMotion(Scanner s, AnimationBuilder<Doc> builder) {
    String[] fieldNames = new String[]{
      "initial time",
      "initial x-coordinate", "initial y-coordinate",
      "initial width", "initial height",
      "initial red value", "initial green value", "initial blue value", "initial angle value",
      "final time",
      "final x-coordinate", "final y-coordinate",
      "final width", "final height",
      "final red value", "final green value", "final blue value", "final angle value",
    };
    List<Integer> vals = new ArrayList<Integer>(18);
    String name;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Motion: Expected a shape name, but no more input available");
    }
    for (int i = 0; i < 18; i++) {
      vals.add(getInt(s, "Motion", fieldNames[i]));
    }
    if (vals.get(8) == Integer.MIN_VALUE || vals.get(17) == Integer.MIN_VALUE) {
      vals.add(8,0);
      vals.add(17,0);
      vals.remove(vals.size() - 1);
      vals.remove(vals.size() - 1);
    }
    builder.addMotion(name,
            vals.get(0), vals.get(1), vals.get(2), vals.get(3), vals.get(4), vals.get(5),
            vals.get(6), vals.get(7), vals.get(8), vals.get(9), vals.get(10), vals.get(11),
            vals.get(12), vals.get(13), vals.get(14), vals.get(15), vals.get(16), vals.get(17));
  }
  
  private static int getInt(Scanner s, String label, String fieldName) {
    if (s.hasNextInt()) {
      return s.nextInt();
    } else if (s.hasNext()) {
      return Integer.MIN_VALUE;
    } else {
      return Integer.MIN_VALUE;
    }
  }

}
