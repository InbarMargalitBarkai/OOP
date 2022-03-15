/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * A Point details.
 * A point is a primitive notion that models an exact location in the space, and has no length, width, or thickness.
 */
public class Point {
    // Fields
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x - can be any real number.
     * @param y - can be any real number.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x coordinate of the point.
     * @return the x coordinate.
     */
    public double getX() {
        return (this.x);
    }

    /**
     * Gets y coordinate of the point.
     * @return the y coordinate.
     */
    public double getY() {
        return (this.y);
    }

    /**
     * Sets x coordinate of the point.
     * @param newX - enter the x coordinate.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Sets y coordinate of the point.
     * @param newY - enter the y coordinate.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * Calculate the distance between two points.
     * @param other - the point to calculate with.
     * @return zero if it's the same point,
     * positive real number if there are two different points
     * and minus one if there is no other point.
     */
    public double distance(Point other) {
        if (other == null) {
            // it can be any negative number
            return -1;
        } else {
            double thisX = this.getX();
            double thisY = this.getY();
            double otherX = other.getX();
            double otherY = other.getY();
            // returns number between zero to positive infinity
            double distanceBetweenPoints =
                    Math.sqrt(((thisX - otherX) * (thisX - otherX)) + ((thisY - otherY) * (thisY - otherY)));
            return distanceBetweenPoints;
        }
    }

    /**
     * Compare between points.
     * @param other - the point to compare to.
     * @return true if points are equals, false others.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double thisX = this.getX();
        double thisY = this.getY();
        double otherX = other.getX();
        double otherY = other.getY();
        if ((thisX == otherX) && (thisY == otherY)) {
            return true;
        }
        return false;
    }
}
