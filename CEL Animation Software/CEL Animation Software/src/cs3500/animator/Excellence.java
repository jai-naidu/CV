package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.MovieModelLayerImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.controller.MovieController;
import cs3500.animator.controller.MVCAnimationController;
import cs3500.animator.view.MovieView;

/**
 * A class for modifying the type of animation according to the given specs (in text file, view
 * type, out text or svg file, and speed of animation). Then passing in the model (populated by the
 * in file) and the appropriate view (depending on the specified view type - passing in the correct
 * out PrintWriter if necessary). Then running the the controller's run method.
 */
public final class Excellence {

  /**
   * Modify the type of animation according to the given specs (in text file, view type, out text or
   * svg file, and speed of animation). Then pass in the model (populated by the in file) and the
   * appropriate view (depending on the specified view type - passing in the correct out PrintWriter
   * if necessary). Then run the the controller's run method.
   *
   * @param args the passed in arguments from the command line
   * @throws IllegalArgumentException if there was no input file given
   * @throws IllegalArgumentException if the view isn's specified
   * @throws IllegalArgumentException if the speed is not a positive integer
   * @throws IllegalArgumentException if the passed in view is null
   */
  public static void main(String[] args) {
    FileReader in = null;
    String view = null;
    PrintWriter out = new PrintWriter(System.out);
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          nextArgCheck(i, args.length);
          try {
            in = new FileReader(args[i + 1]);
            break;
          } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Input file was not found");
          }
        case "-view":
          nextArgCheck(i, args.length);
          if (args[i + 1].equals("visual") || args[i + 1].equals("text")
                  || args[i + 1].equals("svg") || args[i + 1].equals("edit")) {
            view = args[i + 1];
            break;
          } else {
            throw new IllegalArgumentException("Unsupported input for -view");
          }
        case "-out":
          nextArgCheck(i, args.length);
          try {
            out = new PrintWriter(args[i + 1]);
            break;
          } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Output file was not found");
          }
        case "-speed":
          nextArgCheck(i, args.length);
          if (isNumeric(args[i + 1])) {
            speed = Integer.parseInt(args[i + 1]);
            break;
          } else {
            throw new IllegalArgumentException("Unsupported input for -speed");
          }
        default:
          break;
      }
    }
    start(in, view, out, speed);
  }

  /**
   * Renders and outputs the appropriate animation depending on the command line input.
   *
   * @param in    the in file
   * @param view  the type of view
   * @param out   the PrintWriter to append to
   * @param speed the speed of the animation
   * @throws IllegalArgumentException if there was no input file given
   * @throws IllegalArgumentException if the view isn's specified
   * @throws IllegalArgumentException if the speed is not a positive integer
   * @throws IllegalArgumentException if the passed in view is null
   */
  private static void start(FileReader in, String view, PrintWriter out, int speed) {
    if (in == null) {
      throw new IllegalArgumentException("No input file was given");
    } else if (view == null) {
      throw new IllegalArgumentException("No view specified");
    } else if (speed == 0) {
      throw new IllegalArgumentException("Speed must be positive");
    } else {
      MovieModel model = AnimationReader.parseFile(in, new MovieModelLayerImpl.Builder());
      model.setSpeed(speed);
      MovieView mView = new ViewFactory().getView(view);
      if (mView == null) {
        throw new IllegalArgumentException("View cannot be null.");
      }
      mView.setOut(out);
      MovieController controller = new MVCAnimationController(model, mView);
      controller.run();
    }
  }

  /**
   * Checks if the given string is a number.
   *
   * @param str the given string
   * @return is the string a number?
   */
  private static boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Checks of the next value of args exists.
   *
   * @param i      the current arg
   * @param length the length of args
   */
  private static void nextArgCheck(int i, int length) {
    if (i + 1 == length) {
      throw new IllegalArgumentException("Incomplete input");
    }
  }
}