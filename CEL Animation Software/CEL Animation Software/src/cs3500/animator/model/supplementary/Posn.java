package cs3500.animator.model.supplementary;

/**
 * Represents an x,y position on a 2D coordinate plane. Negative x coordinates are to the left of
 * the origin, and negative y coordinates are to the right of the origin at (0,0).
 */
public class Posn {
  private final double x;
  private final double y;

  /**
   * Initializes the a position coordinate with the given x and y values.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets this position's x value.
   *
   * @return the x coordinate
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets this position's y value.
   *
   * @return the y coordinate
   */
  public double getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Posn) {
      Posn that = (Posn) o;
      return (this.x == that.x) && (this.y == that.y);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.x * 1000 + this.y * 1000);
  }
}