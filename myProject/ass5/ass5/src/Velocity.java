/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // Fields
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx - is the change in position on the 'x' axe.
     * @param dy - is the change in position on the 'y' axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * It is convenient to specify the velocity in terms of: angle and a speed.
     * @param angle - is degrees direction.
     * @param speed - is the units.
     * @return Velocity which will be the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets the dx of the ball's velocity.
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the dy of the ball's velocity.
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the dx of the ball's velocity.
     * @param newDx - enter the change in position on the 'x' axe.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * Sets the dy of the ball's velocity.
     * @param newDy - enter the change in position on the 'y' axe.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * The change position of the point.
     * @param p - take a point with position (x,y) the point to change his place.
     * @return new point that will be the new place of the point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        // creating the new x coordinate of the point
        double xP = p.getX();
        double xNew = (xP + (this.dx));
        // creating the new y coordinate of the point
        double yP = p.getY();
        double yNew = (yP + (this.dy));
        // creating the new point
        Point newP = new Point(xNew, yNew);
        return (newP);
    }

    /**
     * Calculate a speed from a velocity (of: dx, dy).
     * @return the speed
     */
    public double speed() {
        double speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        return speed;
    }
}