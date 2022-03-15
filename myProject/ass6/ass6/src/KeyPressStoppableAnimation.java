import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-14
 */

/**
 * Decorator-class that will wrap an existing animation
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    // Fields
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;
    private boolean stop = true;

    /**
     * Constructor.
     * @param sensor - the keyboard sensor.
     * @param key - the pressed key.
     * @param animation - the animation using this class.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    /**
     * Do one frame.
     * @see Animation#doOneFrame(DrawSurface).
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.ks.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                stop = false;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    /**
     * Should stop.
     * @see Animation#shouldStop().
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
