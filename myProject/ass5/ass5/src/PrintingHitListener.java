/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-02
 */

/**
 * In order to see if our listener is working,
 * lets implement a simple HitListener that prints a message to the screen whenever a block is hit.
 */
public class PrintingHitListener implements HitListener {

    @Override
    /**
     * Hit Event.
     * @see HitListener#hitEvent(Block, Ball).
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}