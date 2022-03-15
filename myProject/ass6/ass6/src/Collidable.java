/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-01
 */

/**
 * Details about the collide,
 * like: object and the velocity that will be after the hit.
 */
public interface Collidable {

    /**
     * Get collision rectangle.
     * @return - return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param hitter - the ball who hits the collidable object.
     * @param collisionPoint - the point of the collision.
     * @param currentVelocity - the start velocity.
     * @return - return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
