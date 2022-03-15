/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-02
 */

/**
 * This class includes details about the collision.
 */
public class CollisionInfo {
    // Fields
    static final int TOP = 1;
    static final int RIGHT = 2;
    static final int BOTTOM = 3;
    static final int LEFT = 4;
    private Point collisionPoint;
    private Collidable collisionObject;
    private int collisionSide;

    /**
     * Constructor.
     * @param collisionPoint - the point of the collision.
     * @param collisionObject - the object that hit the ball.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point.
     * @return - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object.
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Gets collision side.
     * @return the side of the collision.
     */
    public int getCollisionSide() {
        return this.collisionSide;
    }

    /**
     * Set the side of the object, that the collision occurred at.
     * @param newCollisionSide the new side of the collision
     */
    public void setCollisionSide(int newCollisionSide) {
        this.collisionSide = newCollisionSide;
    }

    /**
     * Checks on which rib of the rectangle the ball collided to, and set it.
     */
    public void setCollisionSide() {
        if (new Line(this.collisionObject().getCollisionRectangle().getBottomLeft(), // bottom
                this.collisionObject().getCollisionRectangle().getBottomRight()).pointInLine(collisionPoint)) {
            this.collisionSide = BOTTOM;
        } else if (new Line(this.collisionObject().getCollisionRectangle().getUpperLeft(), // up
                this.collisionObject().getCollisionRectangle().getUpperRight()).pointInLine(collisionPoint)) {
            this.collisionSide = TOP;
        } else if (new Line(this.collisionObject().getCollisionRectangle().getUpperLeft(), // left
                this.collisionObject().getCollisionRectangle().getBottomLeft()).pointInLine(collisionPoint)) {
            this.collisionSide = LEFT;
        } else { // right
            this.collisionSide = RIGHT;
        }
    }
}