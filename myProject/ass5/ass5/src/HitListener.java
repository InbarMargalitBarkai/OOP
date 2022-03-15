/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-01
 */

/**
 * Objects that want to be notified of hit events,
 * should implement the HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that the ball hit.
     * @param hitter - the hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
