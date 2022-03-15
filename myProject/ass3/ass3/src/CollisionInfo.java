/**
 * assignment 3.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-15
 */

/**
 * This class includes details about the collision.
 */
public class CollisionInfo {
    // Fields
    private Point collisionPoint;
    private Collidable collisionObject;

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
        return (this.collisionPoint);
    }

    /**
     * Collision object.
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return (this.collisionObject);
    }
}
