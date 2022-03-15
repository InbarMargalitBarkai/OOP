import biuoop.DrawSurface;
import java.awt.Color;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-02
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
    // Fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int height;
    private int width;
    private Point startPoint;
    private GameEnvironment gameEnvironment;
    private Point leftSide;
    private Point rightSide;

    /**
     * Constructor.
     * @param center the center location of the ball
     * @param r      the size of the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.height = HEIGHT_SCREEN;
        this.width = WIDTH_SCREEN;
        this.startPoint = new Point(0, 0);
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor.
     * @param x     the x of the center location of the ball
     * @param y     the y of the center location of the ball
     * @param r     the size of the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        Point c = new Point(x, y);
        this.center = c;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.height = HEIGHT_SCREEN;
        this.width = WIDTH_SCREEN;
        this.startPoint = new Point(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor.
     * @param x     the x of the center of the ball
     * @param y     the y of the center of the ball
     * @param r     the size of the radius of the ball
     * @param color the color of the ball
     * @param high  the high of the frame of the ball
     * @param width the high of the frame of the ball
     * @param edge  the upper, lefter point of the frame
     */
    public Ball(int x, int y, int r, java.awt.Color color, int high, int width, Point edge) {
        Point c = new Point(x, y);
        this.center = c;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.height = high;
        this.width = width;
        this.startPoint = edge;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Gets the x coordinate.
     *
     * @return the x coordinate
     */
    public int getX() {
        // we need to do casting, because the getX method we did in point class, returns a double
        int x = (int) this.center.getX();
        return x;
    }

    /**
     * Gets y coordinate.
     *
     * @return the y coordinate
     */
    public int getY() {
        // we need to do casting, because the getY method we did in point class, returns a double
        int y = ((int) this.center.getY());
        return y;
    }

    /**
     * Gets center ppoint.
     *
     * @return the the center of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the radius.
     *
     * @return the radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets the height.
     *
     * @return the height of the screen.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets the width.
     * @return the width of the screen.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets width.
     * @param w - the width of the screen
     */
    public void setWidth(int w) {
        this.width = w;
    }

    /**
     * Sets height.
     * @param h - the height of the screen.
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * Gets the color.
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets the velocity.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Gets game environment.
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Set the center of the ball to a new point.
     * @param p the new x of the point
     */
    void setCenter(Point p) {
        this.center.setPoint(p);
    }

    /**
     * Set the center of the ball to a new x and y.
     * @param x the new x of the center
     * @param y the new y of the center
     */
    void setCenter(double x, double y) {
        Point p = new Point(x, y);
        this.center.setPoint(p);
    }

    /**
     * Set the velocity of the ball to a new velocity.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity.setDx(v.getDx());
        this.velocity.setDy(v.getDy());
    }

    /**
     * Set the velocity of the ball to a new dx and dy.
     *
     * @param dx the new dx of the velocity
     * @param dy the new dy of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Adding to the game environment of the ball to a new collidable.
     *
     * @param c the new collidable to add
     */
    public void addToGameEnvironment(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * Set a new game environment to the ball.
     *
     * @param newGameEnvironment the new game environment
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.radius);
        d.setColor(Color.BLACK);
        d.drawCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
        // for the ball, it should move one step
        this.moveOneStep();
    }

    /**
     * Checks if a ball will pass the edges of the frame if it will keep moving in the same direction.
     * if it will, changes the direction
     * @return (flag) 1 if there is a need to do a change in the velocity, 0 if not
     */
    private int checkEdges() {
        int flag = 0;
        double tmpX = this.center.getX();
        double tmpY = this.center.getY();
        // if the ball touches the right or left edges, change the direction of dx
        if (this.center.getX() + this.radius == this.width
                || this.center.getX() - this.radius == this.startPoint.getX()) {
            this.velocity.setDx(-this.velocity.getDx());
        } else if (this.getSize() + this.velocity.getDx() + this.center.getX() > this.width) {
            // if the ball will pass the right edge on the next move, move it so it will touch the edge precisely
            double x = this.width - this.radius;
            this.center = new Point(x, this.getY());
            flag = 1;
        } else if (this.center.getX() + this.velocity.getDx() - this.getSize() < this.startPoint.getX()) {
            // if the ball will pass the left edge on the next move, move it so it will touch the edge precisely
            double x = this.radius + this.startPoint.getX();
            this.center = new Point(x, this.getY());
            flag = 1;
        }
        // if the ball touches the up or down edges, change the direction of dy
        if (this.center.getY() + this.radius == this.height
                || this.center.getY() - this.radius == this.startPoint.getY()) {
            this.velocity.setDy(-this.velocity.getDy());
        } else if (this.getSize() + this.velocity.getDy() + this.center.getY() > this.height) {
            // if the ball will pass the down edge on the next move, move it so it will touch the edge precisely
            double y = this.height - this.radius;
            this.center = new Point(this.getX(), y);
            flag = 1;
        } else if (this.center.getY() + this.velocity.getDy() - this.getSize() < this.startPoint.getY()) {
            // if the ball will pass the up edge on the next move, move it so it will touch the edge precisely
            double y = this.radius + this.startPoint.getY();
            this.center = new Point(this.getX(), y);
            flag = 1;
        }
        //change the parameter of velocity that wasn't change above
        if (flag == 1) {
            if (tmpX == this.center.getX()) {
                this.center = new Point(tmpX + this.velocity.getDx(), this.getY());
            }
            if (tmpY == this.center.getY()) {
                this.center = new Point(this.getX(), tmpY + this.velocity.getDy());
            }
        }
        return flag;
    }

    /**
     * Changes the center coordinate of the ball by the velocity of the ball.
     * The velocity can change by hits.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            if (collisionInfo.getCollisionSide() == CollisionInfo.TOP) {
                this.setCenter(this.getCenter().getX(),
                        collisionInfo.collisionPoint().getY() - this.getSize());
            } else if (collisionInfo.getCollisionSide() == CollisionInfo.BOTTOM) {
                this.setCenter(this.getCenter().getX(),
                        collisionInfo.collisionPoint().getY() + this.getSize());
            } else if (collisionInfo.getCollisionSide() == CollisionInfo.LEFT) {
                this.setCenter(collisionInfo.collisionPoint().getX() - this.getSize(),
                        this.getCenter().getY());
            } else { // right
                this.setCenter(collisionInfo.collisionPoint().getX() + this.getSize(),
                        this.getCenter().getY());
            }
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                    this.getVelocity()));

        } else { // if there is no collision point
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Adding the objects to the game.
     *
     * @param g - the game to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * BallRemover will be notified whenever a ball hits the death-region.
     * Whenever this happens, the BallRemover will remove the ball from the game.
     *
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Sets the point of the board from the left side.
     *
     * @param x - the x coordinate point.
     * @param y - the y coordinate point.
     */
    public void setBoardLeft(int x, int y) {
        Point left = new Point(x, y);
        this.leftSide = left;
    }

    /**
     * Sets the point of the board from the right side.
     *
     * @param x - the x coordinate point.
     * @param y - the y coordinate point.
     */
    public void setBoardRight(int x, int y) {
        Point right = new Point(x, y);
        this.rightSide = right;
    }

    /**
     * Gets the point of the left side.
     *
     * @return Point - the left side point.
     */
    public Point getLeftSide() {
        return this.leftSide;
    }

    /**
     * Gets the point of the right side.
     *
     * @return Point - the right side point.
     */
    public Point getRightSide() {
        return this.rightSide;
    }
}
