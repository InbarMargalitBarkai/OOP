import biuoop.DrawSurface;

import java.util.ArrayList;

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
public class Ball implements Sprite {
    // constant fot the width of the screen
    static final int WIDTH_SCREEN = 800;
    // constant fot the height of the screen
    static final int HEIGHT_SCREEN = 600;
    // the first value of the minDistance variable, so we will be able to change it's value
    static final int MAX_DIS = 1000;
    // the border of the screen after putting the right block
    static final int RIGHT_BORDER = 760;
    // the border of the screen after putting the left block
    static final int LEFT_BORDER = 40;
    // the border of the screen after putting the up block
    static final int UP_BORDER = 40;
    // the border of the screen after putting the bottom block
    static final int BOTTOM_BORDER = 560;

    // Fields
    private Point center;
    private int size;
    private java.awt.Color color;
    private Velocity vel;
    private int height;
    private int width;
    private Point leftSide;
    private Point rightSide;
    private GameEnvironment gameEnvironment;

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
     * Sets the game environment.
     * @param ge - the gameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
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
     * Adding the objects to the game.
     * @param g - the game to add the ball to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Changes the center coordinate of the ball by the velocity of the ball.
     * The velocity can be change by hits.
     */
    public void moveOneStep() {
        // check the limits of the board
        if ((this.center.getX() + this.size >= RIGHT_BORDER
                && this.vel.getDx() > 0)
                || (this.center.getX() - this.size <= LEFT_BORDER
                && this.vel.getDx() < 0)) {
            this.setVelocity(-this.vel.getDx(), this.vel.getDy());
        }
        if ((this.center.getY() - this.size <= UP_BORDER
                && this.vel.getDy() < 0)
                || (this.center.getY() + this.size >= BOTTOM_BORDER
                && this.vel.getDy() > 0)) {
            this.setVelocity(this.vel.getDx(), -this.vel.getDy());
        }
        ArrayList<CollisionInfo> collInfo = new ArrayList<>();
        Line trajectory,
                minLine = null;
        double minDistace = MAX_DIS, tempDistance;
        Point tempPoint;
        CollisionInfo minCollision = null,
                collisionXpoint = null,
                collisionYpoint = null,
                collisionXpointOther = null,
                collisionYpointOther = null;
        Velocity v = new Velocity(this.vel.getDx(), this.vel.getDy());
        if (this.vel.getDx() > 0) {
            trajectory = new Line(this.getX() + this.size, this.getY(),
                    this.getX() + this.vel.getDx() + this.size, this.getY() + this.vel.getDy());
            collisionXpoint = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionXpoint != null) {
                collInfo.add(collisionXpoint);
            }
        }
        if (this.vel.getDx() < 0) {
            trajectory = new Line(this.getX() - this.size, this.getY(),
                    this.getX() + this.vel.getDx() - this.size, this.getY() + this.vel.getDy());
            collisionXpointOther = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionXpointOther != null) {
                collInfo.add(collisionXpointOther);
            }
        }
        if (this.vel.getDy() > 0) {
            trajectory = new Line(this.getX(), this.getY() + this.size,
                    this.getX() + this.vel.getDx(), this.getY() + this.vel.getDy() + this.size);
            collisionYpoint = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionYpoint != null) {
                collInfo.add(collisionYpoint);
            }
        }
        if (this.vel.getDy() < 0) {
            trajectory = new Line(this.getX(), this.getY() - this.size,
                    this.getX() + this.vel.getDx(), this.getY() + this.vel.getDy() - this.size);
            collisionYpointOther = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionYpointOther != null) {
                collInfo.add(collisionYpointOther);
            }
        }
        for (int i = 0; i < collInfo.size(); i++) {
            if (collInfo.get(i) == collisionXpoint) {
                tempPoint = new Point(this.getX() + this.size, this.getY());
                tempDistance = collisionXpoint.collisionPoint().distance(tempPoint);
                if (tempDistance < minDistace) {
                    minDistace = tempDistance;
                    minLine = new Line(tempPoint.getX(), tempPoint.getY(),
                            collisionXpoint.collisionPoint().getX(), collisionXpoint.collisionPoint().getY());
                    minCollision = collisionXpoint;
                }
            }
            if (collInfo.get(i) == collisionXpointOther) {
                tempPoint = new Point(this.getX() - this.size, this.getY());
                tempDistance = collisionXpointOther.collisionPoint().distance(tempPoint);
                if (tempDistance < minDistace) {
                    minDistace = tempDistance;
                    minLine = new Line(tempPoint.getX(), tempPoint.getY(),
                            collisionXpointOther.collisionPoint().getX(), collisionXpointOther.collisionPoint().getY());
                    minCollision = collisionXpointOther;
                }
            }
            if (collInfo.get(i) == collisionYpoint) {
                tempPoint = new Point(this.getX(), this.getY() + this.size);
                tempDistance = collisionYpoint.collisionPoint().distance(tempPoint);
                if (tempDistance < minDistace) {
                    minDistace = tempDistance;
                    minLine = new Line(tempPoint.getX(), tempPoint.getY(),
                            collisionYpoint.collisionPoint().getX(), collisionYpoint.collisionPoint().getY());
                    minCollision = collisionYpoint;
                }
            }
            if (collInfo.get(i) == collisionYpointOther) {
                tempPoint = new Point(this.getX(), this.getY() - this.size);
                tempDistance = collisionYpointOther.collisionPoint().distance(tempPoint);
                if (tempDistance < minDistace) {
                    minDistace = tempDistance;
                    minLine = new Line(tempPoint.getX(), tempPoint.getY(),
                            collisionYpointOther.collisionPoint().getX(), collisionYpointOther.collisionPoint().getY());
                    minCollision = collisionYpointOther;
                }
            }
            // move the ball to "almost" the hit point, but just slightly before it
            this.center.setX(this.getX() + (minLine.end().getX() - minLine.start().getX()));
            this.center.setY(this.getY() + (minLine.end().getY() - minLine.start().getY()));
            // update the velocity to the new velocity returned by the hit() method
            v = minCollision.collisionObject().hit(this, minLine.end(), this.vel);
            this.setVelocity(v);
        }
        // checking the edges
        if (this.gameEnvironment.getClosestCollisionCorner(new Point(this.getX() + this.vel.getDx(), this.getY()
                + this.vel.getDy()), this.size) != null) {
            this.setVelocity(-this.vel.getDx(), -this.vel.getDy());
        } else { // if no, then move the ball to the end of the trajectory (there is no hit)
            this.setVelocity(v);
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }


    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface) .
     */
    public void drawOn(DrawSurface d) {
        // sending the color
        d.setColor(this.color);
        // sending the circle: the point coordinates and radius
        d.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.size);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed() .
     */
    public void timePassed() {
        // for the ball, it should move one step
        moveOneStep();
    }
}