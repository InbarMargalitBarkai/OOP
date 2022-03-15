import biuoop.DrawSurface;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-11
 */

/**
 * Control on the animation.
 */
public interface Animation {

    /**
     * Do one frame on the given surface.
     * @param d - the DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Say if the animation needs to stop.
     * @return - true if the animation needs to stop,
     * and false other.
     */
    boolean shouldStop();
}
