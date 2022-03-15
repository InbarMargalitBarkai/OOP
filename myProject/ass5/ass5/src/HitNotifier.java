/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-01
 */

/**
 * The HitNotifier interface indicate that objects that implement it
 * send notifications when they are being hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl - the listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the listener.
     */
    void removeHitListener(HitListener hl);
}
