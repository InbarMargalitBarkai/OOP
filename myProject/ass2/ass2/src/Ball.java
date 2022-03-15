import biuoop.DrawSurface;

/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * Ball is an object, like a point just that he has a volume.
 * it also can move in the space it has.
 */
public class Ball {
    // constant fot the width of the screen
    static final int WIDTH_SCREEN = 800;
    // constant fot the height of the screen
    static final int HEIGHT_SCREEN = 600;

    // Fields
    private Point center;
    private int size;
    private java.awt.Color color;
    private Velocity vel;
    private int height;
    private int width;
    private Point leftSide;
    private Point rightSide;

    /**
     * Constructor.
     * @param center is a point consisting of x and y, both are integers.
     * @param r      is the radius of the ball, can be only a natural number, except for zero.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.width = WIDTH_SCREEN;
        this.height = HEIGHT_SCREEN;
    }

    /**
     * Gets the x coordinate of the center of the ball.
     * @return the x coordinate
     */
    public int getX() {
        // we need to do casting, because the getX method we did in point class, returns a double
        int x = (int) this.center.getX();
        return x;
    }

    /**
     * Gets the y coordinate of the center of the ball.
     * @return the y coordinate
     */
    public int getY() {
        // we need to do casting, because the getY method we did in point class, returns a double
        int y = ((int) this.center.getY());
        return y;
    }

    /**
     * Sets the center of the point.
     * @param newCenter - our new center of the point
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Gets the size of the radius.
     * @return the radius
     */
    public int getSize() {
        return (this.size);
    }

    /**
     * Gets the color of the ball.
     * @return the color
     */
    public java.awt.Color getColor() {
        return (this.color);
    }

    /**
     * Drawing the ball on the given DrawSurface.
     * @param surface - the surface to draw on him.
     */
    public void drawOn(DrawSurface surface) {
        // sending the color
        surface.setColor(this.color);
        // sending the circle: the point coordinates and radius
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.size);
    }

    /**
     * Saving the parameter v in our variable that parts of creating the ball.
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Sets the velocity of the ball.
     * @param dx is the change in the place of x coordinate.
     * @param dy is the change in the place of y coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return (this.vel);
    }

    /**
     * Gets the height of the board.
     * @return the height of the screen.
     */
    public int getHeight() {
        return (this.height);
    }

    /**
     * Gets the width of the board.
     * @return the width of the screen.
     */
    public int getWidth() {
        return (this.width);
    }

    /**
     * Sets the width of the board.
     * @param w - the width of the screen
     */
    public void setWidth(int w) {
        this.width = w;
    }

    /**
     * Sets the height of the board.
     * @param h - the height of the screen.
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * Sets the point of the board from the left side.
     * @param x - the x coordinate point.
     * @param y - the y coordinate point.
     */
    public void setBoardLeft(int x, int y) {
        Point left = new Point(x, y);
        this.leftSide = left;
    }

    /**
     * Sets the point of the board from the right side.
     * @param x - the x coordinate point.
     * @param y - the y coordinate point.
     */
    public void setBoardRight(int x, int y) {
        Point right = new Point(x, y);
        this.rightSide = right;
    }

    /**
     * Gets the point of the left side.
     * @return Point - the left side point.
     */
    public Point getLeftSide() {
        return (this.leftSide);
    }

    /**
     * Gets the point of the right side.
     * @return Point - the right side point.
     */
    public Point getRightSide() {
        return (this.rightSide);
    }

    /**
     * The ball is moving as the velocity defined,
     * and we also needs to check the boundaries of the screen.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        // checking the left and right boundaries
        if (this.center.getX() + this.size >= this.getRightSide().getX()
                || this.center.getX() - this.size <= this.getLeftSide().getX()) {
            // changing the dx to -dx, to turn ball's direction
            this.setVelocity(-this.vel.getDx(), this.vel.getDy());
        }
        // checking the up and down boundaries
        if (this.center.getY() + this.size >= this.getRightSide().getY()
                || this.center.getY() - this.size <= this.getLeftSide().getY()) {
            // changing the dx to -dy, to turn ball's direction
            this.setVelocity(this.vel.getDx(), -this.vel.getDy());
        }
    }
}


