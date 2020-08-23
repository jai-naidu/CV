package cs3500.animator.model.supplementary;

/**
 * A class to represent a color value. A color consists of a mixture of red, green and blue
 * and each value must be between 0 and 255 inclusive.
 */
public class Color {
  private float r;
  private float g;
  private float b;

  /**
   * Initializes a color with RGB values from 0 to 255.
   * @param red numeric representation of much red is present in the color
   * @param green numeric representation of how green is present in the color
   * @param blue numeric representation of how blue is present in the color
   * @throws IllegalArgumentException if arguments are not in the range [0, 255]
   */
  public Color(float red, float green, float blue) {
    if (!((red >= 0 && red <= 255) && (green >= 0 && green <= 255)
        && (blue >= 0 && blue <= 255))) {
      throw new IllegalArgumentException("Color values are not in the range [0, 255]");
    }
    this.r = red;
    this.g = green;
    this.b = blue;
  }

  /**
   * Returns the red value of this Color.
   * @return number on a scale from 0-255 of how much red is present in this color
   */
  public float getRed() {
    return this.r;
  }

  /**
   * Returns the green value of this Color.
   * @return number on a scale from 0-255 of how much green is present in this color
   */
  public float getGreen() {
    return this.g;
  }

  /**
   * Returns the blue value of this Color.
   * @return number on a scale from 0-255 of how much blue is present in this color
   */
  public float getBlue() {
    return this.b;
  }

  @Override
  public String toString() {
    return "rgb(" + (int)getRed() + "," + (int)getGreen() + "," + (int)getBlue() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Color) {
      Color that = (Color) o;
      return (that.r == this.r)
          && (that.g == this.g)
          && (that.b == this.b);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Float.hashCode(r * 10000 + g * 10000 + b * 10000);
  }
}
